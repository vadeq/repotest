package zws.qx.queue;

import zws.application.Names;
import zws.application.Properties;
import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.QxOp;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import java.util.HashMap;
import java.util.Iterator;


public class QxQueueService implements Qx{

  public QxQueueService() { }

  public static QxQueuePersistent lookupQueue(QxContext ctx) throws Exception {
    return lookupQueue(ctx.get(QxContext.QUEUE_NAME));
  }
  
  public static QxQueuePersistent lookupQueue(String queueName) throws Exception {
    
    if (queueName == null || queueName.trim().length() < 1) return null;
    QxQueuePersistent q = (QxQueuePersistent) queueHM.get(queueName);
    
    if( q == null ) {
      QxOp op = materializeHandler(queueName);
      q = new QxQueuePersistent(queueName ,op);
      queueHM.put(queueName, q);
      q.start();
    }
    return q;
  }

  private QxXML listQueues() throws Exception {
    
    QxQueuePersistent queue;
    QxXML qxXML = new QxXML("<response message='listing queues'>");
    
    Iterator iterator = queueHM.keySet().iterator();
    while (iterator.hasNext()) {
      String queueName = (String) iterator.next();
      queue = (QxQueuePersistent) queueHM.get(queueName);        
      qxXML.write("<queue-status name='").write(queueName).write("' ");
      qxXML.write("active='").write( Boolean.toString(queue.isRunning()) ).write("' >");
      
      QxQueueElement[] elements = queue.getQueueElements(50);
      for (int i=0; i<elements.length; i++) {
        qxXML.write("<element id='").write(Long.toString(elements[i].getRecordID())).write("' ");
        qxXML.write("priority='").write( Integer.toString(elements[i].getPriority()) ).write("'>");
        qxXML.write("<summary>").write(elements[i].getSummary()).write("</summary>");
        qxXML.write("<instruction>").write(elements[i].getXmlDataInstruction().toString()).write("</instruction>");
        qxXML.write("</element>");
      }
      qxXML.write("</queue-status>");
    }
    qxXML.write("</response>");  
    return qxXML;
  }
  
  public QxXML executeQx(QxContext context, QxXML xmlDataInstr){
    if(null == xmlDataInstr) { 
      return null; 
    }
    QxXML qxXML = null;
    int priority = -1;
    QxQueuePersistent q = null;
    QxInstruction qxProgramTree = null;
    String program = "";
    String qName = context.get(QxContext.QUEUE_NAME);
    try {
      qxProgramTree = xmlDataInstr.toQxProgram();
      if(null == qxProgramTree) { 
        return null; 
      }
      program = qxProgramTree.getName().toLowerCase();
      // "list queues" will not have qName in the the instruction
      if (program.equalsIgnoreCase("list") ){
        qxXML = listQueues();
        return qxXML;
      } else if (qName == null || qName.trim().length() < 1) {
        return getMessage("Error found in the request: invalid queue-name", "");
      }
      
      q = lookupQueue(context);      
      if("stop".equalsIgnoreCase(program )) {
        q.stop();
        qxXML = getMessage("stopped the queue", qName);
      } else if("start".equalsIgnoreCase(program )) {
        q.start();
        qxXML = getMessage("started the queue", qName);
      } else if("cancel".equalsIgnoreCase(program )) {
        q.purgeActiveItems();
        qxXML = getMessage("cancelled/purged all active items in the queue", qName);
      } else if("purge".equalsIgnoreCase(program )) {
        q.purgeAllItems();
        qxXML = getMessage("cancelled/purged all items in the queue", qName);
      } else {
        String prValue = (String) context.get("PRIORITY");
        if(prValue != null && prValue.length() > 0) {
          priority = Integer.parseInt(prValue);
        } else {
          priority = 1;
        }
        String summary = context.get(Names.SUMMARY);
        String annotations = context.get(Names.ANNOTATIONS);
        q.add(priority, context, xmlDataInstr, summary, annotations);
        qxXML = getMessage("queued the instruction", qName);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return qxXML;
  }


  public synchronized static void reInstantiate(String queueName) throws Exception {
    boolean reprocessInterruptedItems = true; //+++make configurable
    
    if( queueHM.containsKey(queueName) ) {
      QxQueuePersistent q = (QxQueuePersistent) queueHM.get(queueName);
      q.cancel();
      queueHM.remove(queueName);
    }
    
    QxOp op = materializeHandler(queueName);
    QxQueuePersistent q = new QxQueuePersistent(queueName ,op, false);
    q.notifyActiveItems();
    
    if (reprocessInterruptedItems) {
      q.requeueActiveItems();
      q.startExecute();
    } else {
      q.purgeActiveItems();
    }
    queueHM.put(queueName, q);
  }
  
  public synchronized static void shutdown() throws Exception {
    QxQueuePersistent queue;
     
    Iterator iterator = queueHM.keySet().iterator();
    while (iterator.hasNext()) {
      String queueName = (String) iterator.next();
      queue = (QxQueuePersistent) queueHM.get(queueName);        
      queue.shutdown();
   }
  }
    
  private QxXML getMessage(String msg, String qName) {
    return new QxXML("<response message='" + msg + "' queue-name='" + qName +"' />");
  }
  
  private static QxOp materializeHandler(String queueName){
    QxOp op = null;
    String qOpKey = "qx-queue-" + queueName;
    String qOpFQCN= Properties.get(qOpKey);
    if (null==qOpFQCN || qOpFQCN.length() < 1) op = new XMLPrinter();
    else {
      try {
        Class qOpClass = Class.forName(qOpFQCN);
        op = (QxOp) qOpClass.newInstance();
      }
      catch(Exception e) {
        e.printStackTrace();
        op = new XMLPrinter();
      }
    }
    return op;
  }
  
  private static HashMap queueHM = new HashMap();

}

class XMLPrinter extends QxOp {
  private static final long serialVersionUID = 2509841727888323250L;
  public QxXML executeQx(QxContext qxContext, QxXML dataInstruction) {
  return dataInstruction;
  }
 }
