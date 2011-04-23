package zws.op.queue;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2005, 2:26 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.ThreadedOpBase;

public class Test {
  public Test() { }
  public static void main(String[] args) { 
    Test t= new Test();
    t.run();
  }
  
  public void run() {
    try {
      //Set up the Quueue and the op to process queue items
      NextPrinter op = new NextPrinter(); // simply prints the next object to system out
      Queue q = new Queue(op);
    
      //Create a bunch of threads that add to the queue independently
      QPrinter p1 = new QPrinter(q, "one");
      QPrinter p2 = new QPrinter(q, "two");
      QPrinter p3 = new QPrinter(q, "three");
      QPrinter p4 = new QPrinter(q, "four");
    
      //launch all threads
      p1.execute();
      p2.execute();
      p3.execute();
      p4.execute();
      {} //System.out.println("DONE***********************************");
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  private class QPrinter extends ThreadedOpBase {
    QPrinter (Queue queue, String k) { q = queue; key = k; }
    public void executeRun() throws Exception{
      int count = 100;
      for (int i = 0; i< count; i++){ q.add(key);  }
    }
    private String key;
    private Queue q;
  }
  
  private class NextPrinter extends QueueOp {
   public void processNext(Object o) { {} //System.out.println(o.toString()); }
     
   }
  }
}
