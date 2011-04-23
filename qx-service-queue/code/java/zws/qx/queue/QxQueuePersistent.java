package zws.qx.queue;

import zws.application.Names;
import zws.op.ThreadedOpBase;
import zws.queue.QxElementRecordBase;
import zws.qx.QxContext;
import zws.qx.QxOp;
import zws.qx.xml.QxXML;
import zws.recorder.util.RecorderUtil;

import java.util.Collection;
import java.util.Iterator;

public class QxQueuePersistent {

  public QxQueuePersistent(String name, QxOp op) throws Exception {
    init(name,op, true);
  }
  
  public QxQueuePersistent(String name, QxOp op, boolean start) throws Exception {
    init(name,op, start);
  }

  private void init(String name, QxOp op, boolean start) throws Exception{
    queue = new PersistentQueue(name);
    if(queue.getQueueState().equalsIgnoreCase(Names.STATUS_STARTED)) {
      start();
    } else {
      stop();
    }
    qxOp = op;
    qxThread = new QxQueueThread(op);
    if(start) {
      qxThread.execute();
    }
  }
  
  public  void start() throws Exception {
    inShutdown = false;
    
    synchronized (queue){
      isRunning = true;
      queue.setQueueState(Names.STATUS_STARTED);
      queue.notify();
      queue.startQueue();
    }
  }
  
  public boolean startExecute() throws Exception {
    if(null == qxThread) return false;
    boolean result = true;
    inShutdown = false;
    try {
      qxThread.execute();
    } catch (Exception e) {
      result = false;
      e.printStackTrace();
      throw e;
    }
    return result;
  }

  public void stop() throws Exception {
    synchronized (queue) {
      isRunning = false;
      queue.setQueueState(Names.STATUS_STOPPED);
      queue.stopQueue();
    }
   }

  public void shutdown() throws Exception {
    synchronized (queue) {
      inShutdown = true;
      isRunning = false;
      queue.notify();
    }
   }
  
  public void cancel() throws Exception {
    synchronized (queue) {
      try {
        stop();
        qxThread.clearThread();
        qxThread = new QxQueueThread(qxOp);
        qxThread.execute();
      } catch(Exception ex){
        ex.printStackTrace();
      }
    }
  }

  public void add(int priority, QxContext qxContext, QxXML dataInstruction) throws Exception {
    synchronized (queue) {
      QxContext ctx = pushQxContext(qxContext, priority);
      queue.enqueue(priority, new QxQueueElement(priority, ctx, dataInstruction));
      queue.notify();
    }
  }
  
  public void add(int priority, QxContext qxContext, QxXML dataInstruction,
      String summary, String annotations)  throws Exception {
    synchronized (queue) {
      QxContext ctx = pushQxContext(qxContext, priority);
      queue.enqueue(priority, new QxQueueElement(priority, ctx, dataInstruction), summary, annotations);
      queue.notify();
    }
  }
  
  public boolean isRunning() {
    return isRunning;  
  }
  
  public boolean isStopped() {
    return !isRunning;  
  }

  public void loadPersistantState() throws Exception {
    synchronized (queue) {
      queue.loadPersistantState();
    }
  }
  
  /*public void loadInitialState() throws Exception {
    if( queue.getQueueState().equalsIgnoreCase(Names.STATUS_STARTED)) {
      start();
    } else {
      stop();
    }
  }*/
  
  public QxQueueElement[] getQueueElements() throws Exception{
    return queue.getAllQueueElements();
  }
  
  public QxQueueElement[] getQueueElements(int count) throws Exception{
    return queue.getQueueElements(count);
  }


  private void popQxContext(QxContext qxContext) {
    try {
      RecorderUtil.endRecProcess(qxContext, Names.STATUS_COMPLETE);
      qxContext.configureParent(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public  void requeueActiveItems() throws Exception {
    synchronized (queue) {
      if(queue.hasActiveInstructions() ) {
        QxElementRecordBase qRecord = null;
        Collection activeItems = queue.getActiveInstructions();
        Iterator itr = activeItems.iterator();
        while(itr.hasNext()) {
          qRecord = (QxElementRecordBase) itr.next();
          queue.requeue(qRecord.getId());
        }
      }
    }
  }
  
  public  void notifyActiveItems() throws Exception {
    synchronized (queue) {
      if( queue.hasActiveInstructions() ) queue.notifyActiveItems();
    }
  }

  public void purgeActiveItems() throws Exception {
    synchronized (queue) {
      if( queue.hasActiveInstructions() ) queue.purgeAllActiveItems();
    }
  }

  public void purgeAllItems() throws Exception {
    synchronized (queue) {
      queue.purgeAllItems();
    }
  }
  
  private QxContext pushQxContext(QxContext qxContext, int priority) {
    QxContext ctx = null;
    try {
      ctx = RecorderUtil.startNewProcess(qxContext, Names.PEN_NAMESPACE, Names.QUEUE_PROCESS_NAME, "Added to queue with priority " + priority);
      RecorderUtil.setStatus(ctx, Names.STATUS_STARTED);
      ctx.configureParent(qxContext);
    }
    catch(Exception e) {
      e.printStackTrace();
    }

    return ctx;
  }
  
  private class QxQueueThread extends ThreadedOpBase {

    private static final long serialVersionUID = 6075880223996142988L;

    public QxQueueThread(QxOp op) {
      qOp = op;
    }

    public QxOp getQOp() {
      return qOp;
    }

    public void setQOp(QxOp op) {
      qOp = op;
    }

   public void executeRun() throws Exception {
     QxContext ctx=null;
     QxQueueElement next=null;
     String status = "";
      while (!inShutdown) {
        synchronized (queue) {
         while ( !inShutdown && (queue.isEmpty() || isStopped()) ) {
            try {
              queue.wait();
            } catch (InterruptedException ignore) {}
          }
         if (inShutdown) break;
         
         next = (QxQueueElement) queue.dequeueToActive();
        }
        try {
          ctx = next.getQxContext();
          qOp.setQxContext(ctx);

          qOp.setDataInstruction(next.getXmlDataInstruction());
          RecorderUtil.setStatus(ctx, Names.STATUS_PROCESSING);
          qOp.execute(); 
          queue.deleteActive(next); 
          popQxContext(ctx);
          status = Names.STATUS_COMPLETE;

        } catch (RuntimeException e) { 
          status = Names.STATUS_ERROR;
          RecorderUtil.logActivity(ctx, "error", e.getMessage());
          e.printStackTrace();
        } catch (Exception e) { 
          status = Names.STATUS_ERROR;
          RecorderUtil.logActivity(ctx, "error", e.getMessage());
          e.printStackTrace();
          throw e;
        } finally {
          RecorderUtil.setStatus(ctx, status);
        }
      }
    }

    QxOp qOp = null;
  }
  
  private boolean inShutdown = false;
  private PersistentQueue queue = null;
  private boolean isRunning = false;
  private QxQueueThread qxThread = null;
  private QxOp qxOp = null;
}

// here's a good reference:
// http://www-106.ibm.com/developerworks/java/library/j-jtp0730.html
