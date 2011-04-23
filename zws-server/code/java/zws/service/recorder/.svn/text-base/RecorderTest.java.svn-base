package zws.service.recorder;

import zws.database.*;
import zws.application.Names;
import zws.application.Properties;
import zws.exception.*;
//impoer zws.util.Logwriter;

public class RecorderTest {
  RecorderTest() { }

  private static String HOST = "vm-oracle-10g.dev.zerowait-state.com";
  
  public static void main(String[] args) {    
    (new RecorderTest()).run("zws.database.MySQL", "DesignState" , "zero0");
    (new RecorderTest()).run("zws.database.Oracle", "zws" , "zws123");
  }
  
  void run(String driver, String username, String password) {
    
    DatabaseBase db = null;
    
    try {
      {}//Logwriter.printOnConsole(driver + " Test ...");
      db = (DatabaseBase) Class.forName(driver).newInstance() ;
      
      RecorderTest test = new RecorderTest();
      test.setConfig(db, username, password);
      test.test(); 
      
    } catch(Exception e) { 
      e.printStackTrace(); 
    } finally { 
      try { if (db != null) DB.remove(db); }
        catch (Exception e) { e.printStackTrace(); };
    }
    {}//Logwriter.printOnConsole("\tTest Complete ...");
  }
    
  void test() throws Exception {
    long id = RecorderSvc.recordStartTime("ns1", "oracledbtest", "just begginning");
    {} //System.out.println("\tID Saved: " + id);
    RecorderSvc.recordActivity(id, "zws", "node-1", "activity", "heellloo workld");
    
    // test the limitResults modification
    {} //System.out.println("\tgetLastRecording(test): " + RecorderSvc.getLastRecording("test"));
    {} //System.out.println("\tgetRecording: " + RecorderSvc.getRecording(id));
  }

  void setConfig(DatabaseBase db, String username, String password) throws DuplicateName {
    Properties.add(Names.RECORDER_DATABASE, "recorder");  
    db.setHost(HOST);
    db.setName(Names.RECORDER_DATABASE);
    db.setDatabaseName("zwstest");
    db.setUsername(username);
    db.setPassword(password);
    DB.add(db);      
  }
}