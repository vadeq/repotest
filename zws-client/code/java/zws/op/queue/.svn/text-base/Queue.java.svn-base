package zws.op.queue;
/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2005, 1:19 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.ThreadedOpBase;

import java.util.*;

public class Queue {
  private LinkedList queue=null;
  private Object currentItem=null;
  private Collection results=Collections.synchronizedCollection(new Vector());
  
  public Queue(QueueOp op) {
    queue = new LinkedList();
    QueueThread qThread = new QueueThread(op);
    try { qThread.execute(); }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  public void add(Object o) { 
    synchronized(queue) { 
      queue.addLast(o); 
      queue.notify();
    }
  }

  public Collection getQueue() { 
    Collection c = new Vector();
    if (null!=currentItem) c.add(currentItem);
    if (null!=queue) c.addAll(queue);
    return c; 
  }
  
  private class QueueThread extends ThreadedOpBase { 
    public QueueThread(QueueOp op){ qOp = op; }
  
    public void executeRun() {
      Object next;
      while (true) {
        synchronized(queue) {
          while (queue.isEmpty()) {
            try { queue.wait(); }
            catch(InterruptedException ignore) {}
          }
          next = queue.removeFirst();
          currentItem=next;
        }
        try { 
          qOp.processNext(next); 
          results.add(qOp.getResult());
        }
        catch (RuntimeException e) { //+++log this somewhere too
          {} //System.out.println("Error processing item in Queue: " + next.toString());
          e.printStackTrace(); 
        } 
        catch (Exception e) { //+++log this somewhere
          {} //System.out.println("Error processing item in Queue: " + next.toString());
          e.printStackTrace(); 
        } 
        currentItem=null;
      }
    }
    QueueOp qOp = null;
  }
}

//here's a good reference: http://www-106.ibm.com/developerworks/java/library/j-jtp0730.html
