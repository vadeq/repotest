package zws.qx.queue;

/*
 * DesignState - Design Compression Technology @author: athakur @version: 1.0
 * Created on February 26, 2005, 1:19 PM Copywrite (c) 2003 Zero Wait-State Inc.
 * All rights reserved
 */

import zws.op.ThreadedOpBase;
import zws.qx.QxContext;
import zws.qx.QxOp;
import zws.qx.xml.QxXML;
//impoer zws.util.Logwriter;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

public class QxQueue {

  private SortedSet queue = null;

  private Object currentItem = null;

  private Collection results = Collections.synchronizedCollection(new Vector());

  private boolean isRunning = true;

  public QxQueue(QxOp op) {
    queue = new TreeSet();
    QxQueueThread qxThread = new QxQueueThread(op);
    try {
      qxThread.execute();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void add(int priority, QxContext qxContext, QxXML dataInstruction) {
    synchronized (queue) {
      queue.add(new QxQueueElement(priority, qxContext, dataInstruction));
      queue.notify();
    }
  }

  public  void start() {   
    synchronized (queue){
      isRunning = true;
      queue.notify();
    }
  }

  public void stop() {   
    synchronized (queue) {
      isRunning = false;  
    }    
  }

  public boolean isRunning() {    return isRunning;  }

  public boolean isStopped() {    return !isRunning;  }

  public Collection getQueue() {
    Collection c = new Vector();
    if (null != currentItem) c.add(currentItem);
    if (null != queue) c.addAll(queue);
    return c;
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
    
    public Object dequeue(){

      if (queue.isEmpty()) return null;

      Iterator i = queue.iterator();

      QxQueueElement e = (QxQueueElement) i.next();

      i.remove();
      {}//Logwriter.printOnConsole(" "+e.getPriority());
      return e;

    }

    public void executeRun() {
      QxQueueElement next;
      while (true) {
        synchronized (queue) {

         while (queue.isEmpty() || isStopped()) {
            try {
              queue.wait();
            } catch (InterruptedException ignore) {}
          }

          next = (QxQueueElement) dequeue();
          currentItem = next;

        }
        try {
          qOp.setQxContext(next.getQxContext());
          qOp.setDataInstruction(next.getXmlDataInstruction());
          qOp.execute();
          results.add(qOp.getResult());
        } catch (RuntimeException e) { // +++log this somewhere too
          {}//Logwriter.printOnConsole("Error processing item in Queue: "
          //    + next.toString());
          e.printStackTrace();
        } catch (Exception e) { // +++log this somewhere
          {}//Logwriter.printOnConsole("Error processing item in Queue: "
           //   + next.toString());
          e.printStackTrace();
        }
        currentItem = null;
      }
    }

    QxOp qOp = null;
  }

}

// here's a good reference:
// http://www-106.ibm.com/developerworks/java/library/j-jtp0730.html
