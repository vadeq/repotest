package zws.service.recorder.qx;

/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 15, 2006, 2:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.database.*;
import zws.application.Names;
import zws.application.Properties;
import zws.exception.*;
import zws.recorder.ExecutionRecordBase;
//impoer zws.util.Logwriter;

import java.util.SortedSet;

public class RecorderTest {
  RecorderTest() { }

  private static String HOST = "10.10.10.185";
  
  public static void main(String[] args) {    
    (new RecorderTest()).run("zws.database.Oracle", "DesignState" , "zero0");
  }
  
  void run(String driver, String username, String password) {
    
    Oracle oracle = null;
    System.out.println("\tStarting Test ...");
    
    try {
      
      oracle = new Oracle();
       
      Properties.add(Names.RECORDER_DATABASE, "recorder");  
      oracle.setName(Names.RECORDER_DATABASE);
      oracle.setUsername(username);
      oracle.setPassword(password);
      oracle.setUrl("jdbc:oracle:thin:@10.10.10.185:1521:ZWSDEV");
      DB.add(oracle); 

      new RecorderTest().test();
      
    } catch(Exception e) { 
      e.printStackTrace(); 
    } finally { 
      try { if (oracle != null) DB.remove(oracle); }
        catch (Exception e) { e.printStackTrace(); };
    }
    System.out.println("\tTest Complete ...");
  }
    
  void test() throws Exception {
    long id = RecorderSvc.recordStartTime("ns1", "oracledbtest", "just begginning");
    System.out.println("\tID Saved: " + id);
    RecorderSvc.recordActivity(id, "zws", "node-1", "activity", "heellloo workld");
    SortedSet set = RecorderSvc.getChildRecordings(2912);
    ExecutionRecordBase b = (ExecutionRecordBase)set.first();
    System.out.println("\tb : " + b);
    // test the limitResults modification
    System.out.println("\tgetLastRecording(test): " + RecorderSvc.getLastRecording("test"));
    System.out.println("\tgetRecording: " + RecorderSvc.getRecording(id));
  }
}
