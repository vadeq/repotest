package zws.repository.ilink3;
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 18, 2007 6:52:38 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */


import zws.application.Configurator;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryBinarySource;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
//import zws.util.{}//Logwriter;
import zws.util.KeyValue;
import zws.util.RemotePackage;
import zws.application.Properties;
import java.io.FileNotFoundException;

/**
 * The Class TestPlugin.
 */
public class TestBinaryRetrievalAndStoreIntoFileDepot {

  /**
   * The Constructor.
   */
  private TestBinaryRetrievalAndStoreIntoFileDepot() { }

  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    try {
      setEnvVars();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
    TestBinaryRetrievalAndStoreIntoFileDepot t = new TestBinaryRetrievalAndStoreIntoFileDepot();
    t.runAsPublishingEngine();
  }

  //in lieu of reading it from server-system-property.xml had we been in the DesignState server
  private static void setEnvVars()throws FileNotFoundException {  
    setEnvDirectoryPaths();
    setEnvProperties();   
  }

  private static void setEnvDirectoryPaths() throws FileNotFoundException {

    Properties.addDirectoryPath(new KeyValue("zws-home", "\\zws")); 
    Properties.addDirectoryPath(new KeyValue("zws-env", "[zws-home]\\env"));    
    Properties.addDirectoryPath(new KeyValue("zws-bin", "[zws-home]\\bin")); 
    Properties.addDirectoryPath(new KeyValue("zws-temp", "[zws-home]\\temp"));    
//    Properties.addDirectoryPath(new KeyValue("zws-app", "[zws-home]\\app"));    
    Properties.addDirectoryPath(new KeyValue("zws-data" , "[zws-home]\\data"));    
//    Properties.addDirectoryPath(new KeyValue("zws-local" , "[zws-data]\\local"));    
//    Properties.addDirectoryPath(new KeyValue("zws-log", "[zws-home]\\log"));    
//    Properties.addDirectoryPath(new KeyValue("zws-work", "[zws-log]\\work"));    
//    Properties.addDirectoryPath(new KeyValue("DesignState-home", "\\zws\\DesignState"));    
  }

  private static void setEnvProperties() {
    Properties.add("os-platform" , "windows");
    Properties.add("debug-mode" , "true");
    Properties.add("pen-namespace" , "publishing.engine");
    Properties.add("pen-name" , "transfer");
    Properties.add("domain-name" , "cisco");
    Properties.add("server-name" , "DesignState-node-0");
    Properties.add("hostname" , "DesignState-0");
    Properties.add("organization" , "Cisco");
    Properties.add("project" , "Release-1.1");
    Properties.add("email-from-address" , "pen@zerowait-state.com");
    Properties.add("email-smtp-server" , "mail.zerowait-state.com");
    Properties.add("email-recipients" , "ptoleti@zerowait-state.com|dstewart@zerowait-state.com|athakur@zerowait-state.com|eankutse@zerowait-state.com");
    Properties.add("central-server" , "DesignState-node-0");
    Properties.add("max-ilink-tk-licenses" , "5");  
  }
  
  /**
   * Run as publishing engine.
   */
  public void runAsPublishingEngine() {
    String name = null;
    String uniqueid = null;
    Origin origin = null; 
    RemotePackage rdesign = null;
    QxContext ctx = new QxContext();
    try {
      Authentication authentication = new Authentication("admin", "agile");
      Configurator.reinitialize();
      Configurator.load();
      
      //name = "200-1501.drw";
      name = "000-100.asm";
      //name = "line_roller.prt";
      
      RepositoryService c  = RepositoryClient.getClient();
      Repository r = c.findRepository("ilink");
      RepositoryBinarySource downloader = r.materializeBinarySource();

      {}//Logwriter.printOnConsole("************* BINARY RETRIEVAL ***************");
      
      {}//Logwriter.printOnConsole("Checkout and Export main|a|1"+name + ":");
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, "main|a|1|" + name, "", "");      
      downloader.fetchNativeFile(ctx, origin,  authentication);  
      {}//Logwriter.printOnConsole("    ----- " +  "main|a|1"+name + " retrieved! --- "); 
       
//      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1, "main|A|0|frame.asm", "", "");      
//      {}//Logwriter.printOnConsole("Checkout and Export main|A|0|frame.asm" + ":");
//      downloader.fetchNativeFile(ctx, origin,  authentication);      ;
//      {}//Logwriter.printOnConsole("    ----- " +  "main|A|0|frame.asm" + " retrieved! --- "); 
//      
///*
////988-6000.drw|main|A|0
////  - 988-600.asm|main|A|0
////    - [4] 988-602_02.prt|main|A|0 
////    - 988-601.prt|main|A|0
////  - harris.frm|main|A|1       
//*/
//      name = "988-6000.drw";
//      uniqueid = "|main|A|0|";    
//      {}//Logwriter.printOnConsole("Checkout and Export "+ uniqueid+name + ":");
//      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");      
//      downloader.fetchNativeFile(ctx, origin,  authentication);  
//      {}//Logwriter.printOnConsole("    ----- " +  uniqueid+name + " retrieved! --- ");       
//      
//      name = "988-601.prt";
//      uniqueid = "|main|A|0|";     
//      {}//Logwriter.printOnConsole("Checkout and Export "+ uniqueid+name + ":");
//      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");      
//      downloader.fetchNativeFile(ctx, origin, authentication);  
//      {}//Logwriter.printOnConsole("    ----- " +  uniqueid+name + " retrieved! --- ");      
// 
//      name = "988-600.asm";
//      uniqueid = "|main|A|0|";     
//      {}//Logwriter.printOnConsole("Checkout, Export and Store "+ uniqueid+name + ":");
//      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");      
//      RemoteFile rf = downloader.fetchNativeFile(ctx, origin, authentication);
//      {}//Logwriter.printOnConsole("uploadToFileDepot Results:");
//      {}//Logwriter.printOnConsole(" url: " + rf.getURL().toString());
//      {}//Logwriter.printOnConsole(" id : " + rf.getId());      
//      {}//Logwriter.printOnConsole("    ----- " +  uniqueid+name + " retrieved! --- ");      
//      {}//Logwriter.printOnConsole("************* DONE ***************");

      /*
      name = "988-600.asm";
      uniqueid = "|main|A|0|";   
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("uploadToFileDepot Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getURL().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getId());      
      {}//Logwriter.printOnConsole("    ----- " +  uniqueid+name + " retrieved! --- ");      
     
      name = "988-6000.drw";
      uniqueid = "|main|A|0|";   
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("uploadToFileDepot Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getURL().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getId());      
      {}//Logwriter.printOnConsole("    ----- " +  uniqueid+name + " retrieved! --- ");      
         
      {}//Logwriter.printOnConsole("************* DONE ***************");
*/
    } catch (Exception e) { e.printStackTrace(); }
  }

  /*
  IntralinkOrigin o;
  o = new IntralinkOrigin();
  o.setBranch("br");
  o.setReleaseLevel("Released");
  o.setDatasourceName("ilink-00");
  o.setDomainName("zws");
  o.setServerName("DesignState-node-0");
  o.setName("abc.prt");
  o.setRevision("G");
  o.setVersion(8);
*/
}
