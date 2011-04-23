package zws.service.synchronization;

import zws.database.*;
import zws.application.Names;
import zws.exception.*;
//impoer zws.util.{}//Logwriter;
import zws.origin.Origin;
import zws.origin.OriginMaker;


public class SynchronizationTest {

  private static String HOST = "vm-oracle-10g.dev.zerowait-state.com";
  
  public static void main(String[] args) {    
    (new SynchronizationTest()).run("zws.database.MySQL", "DesignState" , "zero0");
    (new SynchronizationTest()).run("zws.database.Oracle", "zws" , "zws123");
  }
  
  void run(String driver, String username, String password) {
    
    DatabaseBase db = null;
    
    try {
      {}//Logwriter.printOnConsole(driver + " Test ...");
      db = (DatabaseBase) Class.forName(driver).newInstance() ;
      
      SynchronizationTest test = new SynchronizationTest();
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
    
    Origin originB = OriginMaker.materialize("zws|node-1|ilink|ilink|1186694499000|main|1|0|77-8002.prt|N/A|N/A|N/A|N/A");
    Origin originA = OriginMaker.materialize("cisco|node-1|agile.CAD.Part|agile-sdk|1192038904766|6069339|MEP_77-8002_P");
    SynchronizationSvc.record(originA, originB);     

    {} //System.out.println("\trecord()");
    {} //System.out.println("\tfindAllSynchronizationRecords: "+SynchronizationSvc.findAllSynchronizationRecords("77-8002.prt").size()+ " records");
  }

  void setConfig(DatabaseBase db, String username, String password) throws DuplicateName {
    db.setHost(HOST);
    db.setName(Names.SYNCHRONIZATION_DATABASE);
    db.setDatabaseName("zwstest");
    db.setUsername(username);
    db.setPassword(password);
    DB.add(db);      
  }
}