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
import zws.queue.QxQueueRecordBase;
import zws.qx.QxContext;
//impoer zws.util.Logwriter;

import java.util.Collection;

public class QueueTest {
  QueueTest() { }

  private static String HOST = "10.10.10.185";
  
  public static void main(String[] args) {    
    (new QueueTest()).run("zws.database.Oracle", "DesignState" , "zero0");
  }
  
  void run(String driver, String username, String password) {
    
    Oracle oracle = null;
    System.out.println("\tStarting Test ...");
    
    try {
      
      oracle = new Oracle();
       
      Properties.add(Names.QX_QUEUE_DATABASE, "recorder");  
      oracle.setName(Names.QX_QUEUE_DATABASE);
      oracle.setUsername(username);
      oracle.setPassword(password);
      oracle.setUrl("jdbc:oracle:thin:@10.10.10.185:1521:ZWSDEV");
      DB.add(oracle); 

      new QueueTest().test();
      
    } catch(Exception e) { 
      e.printStackTrace(); 
    } finally { 
      try { if (oracle != null) DB.remove(oracle); }
        catch (Exception e) { e.printStackTrace(); };
    }
    System.out.println("\tTest Complete ...");
  }
    
  void test() throws Exception {
    QxQueueFactory.createQueue("TEST1", "TEST1");
    QxQueueFactory.createQueue("TEST1-2", "TEST1-2");
    QxQueueFactory.createQueue("TEST2", "TEST2");
    QxQueueFactory.createQueue("TEST3", "TEST3");
    QxQueueFactory.createQueue("TEST3-1", "TEST3-1");
    QxQueueFactory.createQueue("TEST3-2", "TEST3-2");
    boolean active = QxQueueFactory.isActive("test3");
    System.out.println("active..." + active);
    Collection qList =QxQueueFactory.getQueues();
    System.out.println("qList..." + qList);
    QxQueueRecordBase qRecord = QxQueueFactory.getQueue("TEST");
    System.out.println("qRecord..." + qRecord);
  }
}
