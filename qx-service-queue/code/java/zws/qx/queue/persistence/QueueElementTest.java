package zws.qx.queue.persistence;

/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 15, 2006, 2:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.database.*;
import zws.application.Names;
import zws.application.Properties;
import zws.queue.QxElementRecordBase;
import zws.qx.QxContext;

import java.util.Collection;
import java.util.SortedSet;

public class QueueElementTest {
  QueueElementTest() { }
  
  public static void main(String[] args) {    
    (new QueueElementTest()).run("zws.database.Oracle", "DesignState" , "zero0");
  }
  
  void run(String driver, String username, String password) {
    
    Oracle oracle = null;
    System.out.println("\tStarting Test ...");
    
    try {
      
      oracle = new Oracle();
       
      Properties.add(Names.QX_ELEMENT_DATABASE, "zws-qx-element");  
      oracle.setName(Names.QX_ELEMENT_DATABASE);
      oracle.setUsername(username);
      oracle.setPassword(password);
      oracle.setUrl("jdbc:oracle:thin:@10.10.10.185:1521:ZWSDEV");
      DB.add(oracle); 

      new QueueElementTest().test();
      
    } catch(Exception e) { 
      e.printStackTrace(); 
    } finally { 
      try { if (oracle != null) DB.remove(oracle); }
        catch (Exception e) { e.printStackTrace(); };
    }
    System.out.println("\tTest Complete ...");
  }
  

  void test() {
    String ctx =null;
    String instr = null;
    SortedSet  set = null;
    try {
      //set = QxQueueSvc.getPendingElements("QUEUE", "test-queue");
      //System.out.println("total " + set.size());
      set = QxQueueSvc.getPendingElements("QUEUE", "test-queue", 1);
      System.out.println("restricted "+set.size());
      //QxElementRecordBase e = QxQueueSvc.deQueue("QUEUE", "test-queue");
      //System.out.println("deQueued " + e.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    /*for(int i =0;i<100000;i++) {
      ctx = "<string_context queue-name='test-queue' soap-hostname='designstate-0' process-name='queue' process-name-space='PEN' " +
      		  "soap-service-operation='runQx' soap-port='80' process-id='24869' java-services-package='zws.qx.queue' soap-service-name='QxWebService' soap-services-path='ZeroWait-State/services' java-service-classname='QxQueueService' />";
      instr ="<test testParam='test " +
              i +
              "' wait-time='5' file-name='C:\\queue-test\\reliability-test\\result.txt'/>";
      try {
        zws.util.Sleep.sleep(100);
        QxQueueSvc.enQueue("QUEUE", "test-queue", 1, ctx, instr);
      } catch (Exception e) {
        zws.util.Sleep.sleep(5000);
        try {
          QxQueueSvc.enQueue("QUEUE", "test-queue", 1, ctx, instr);
        } catch (Exception e1) { 
          System.out.println(e1.getMessage());
        }
      }
    }*/
    //QxQueueSvc.enQueue("NAMESPACE1", "pen-queue", 1, new QxContext().toString(), "test inster");
    /*QxQueueSvc.enQueue("NAMESPACE1", "TEST1", 1, new QxContext().toString(), "test inster", "test summary...", "test notes....");
    QxQueueSvc.enQueue("NAMESPACE1", "TEST1-2", 2, new QxContext().toString(), "test inster", "test summary...", "test notes....");
    QxQueueSvc.enQueue("NAMESPACE2", "TEST2", 1, new QxContext().toString(), "test inster", "test summary...", "test notes....");
    QxQueueSvc.enQueue("NAMESPACE3", "TEST3", 2, new QxContext().toString(), "test inster", "test summary...", "test notes....");
    QxQueueSvc.enQueue("NAMESPACE3", "TEST3-1", 3, new QxContext().toString(), "test inster", "test summary...", "test notes....");
    QxQueueSvc.enQueue("NAMESPACE3", "TEST3-2", 4, new QxContext().toString(), "test inster", "test summary...", "test notes....");
    
    QxElementRecordBase element = QxQueueSvc.deQueue("NAMESPACE3", "TEST3-2");
    System.out.println("e " + element);
    QxQueueSvc.archive(element.getId());*/
    //Collection c = QxQueueSvc.getPendingElements("QUEUE", "pen-queue");
    //QxQueueSvc.cancelAllElements("QUEUE", "test-queue");
    //QxQueueSvc.cancelElementByID(338);
  }
}
