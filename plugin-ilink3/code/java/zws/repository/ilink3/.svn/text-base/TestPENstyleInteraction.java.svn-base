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
import zws.service.chrysalis.ChrysalisClient;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.repository.target.RepositoryBinaryTarget;
//import zws.util.{}//Logwriter;
import zws.util.KeyValue;
import zws.util.RemotePackage;
import zws.application.Properties;
import java.io.FileNotFoundException;

/**
 * The Class TestPlugin.
 */
public class TestPENstyleInteraction {

//  private static final String ILINK_SERVER_NODE = "designstate-0";
  private static final String REPOSITORY_SERVER_NODE = "designstate-poet";  
  private java.util.Properties clientParams = null;
  private static String itemName = "TEST_ATTACHMENTTDOC";
  
  /**
   * The Constructor.
   */


  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    Notice();
    try {
      setEnvVars();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }
    TestPENstyleInteraction t = new TestPENstyleInteraction();
    t.runAsPublishingEngine();
  }

 
  private static void Notice() {
    {} //System.out.println("------------ NOTICE !!! ------------------");
    {} //System.out.println("THIS TEST ASSUMES THAT THE DOCUMENT NAMED " + itemName + " exists in Agile to make attachment to.");
    {} //System.out.println("VERIFY AND MAKE APPROPRIATE CHANGES. E.G, BY CREATING THE DOCUMENT OR PUBLISHING A DRAWING.");
    {} //System.out.println("------------ END NOTICE ------------------");    
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
    Properties.addDirectoryPath(new KeyValue("zws-data" , "[zws-home]\\data"));      
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
  
  private RemotePackage translate(RemotePackage rf) throws Exception {
    
    {} //System.out.println("******** ASSUMING DRAWING  ******");
    {} //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    RemotePackage xlation = null;    
    String srcDesignFileName = rf.getName();
    String srcDesignFormat = "ProE";
    String targetDesignFormat = "PDF";
    String tgtDesignFileName = srcDesignFileName;
    tgtDesignFileName = tgtDesignFileName.replace(".drw", ".pdf");
    
    ChrysalisClient client = ChrysalisClient.materializeClient();
    
    
    QxContext emptyCtx = new QxContext();    
   xlation = client.translate(emptyCtx, srcDesignFileName, null, rf, srcDesignFormat, targetDesignFormat, tgtDesignFileName);
    return xlation;
  }
  

  
  private void attach(QxContext ctx, RemotePackage rf, RepositoryBinaryTarget agile, Authentication id) throws Exception {
    if (null == agile) throw new Exception("Target Repository for Attaching is null!!!");    
    agile.storeAttachment(ctx, itemName, rf, id);
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
      
      RepositoryService c = RepositoryClient.getClient();
      Repository r = c.findRepository("ilink");
      RepositoryBinarySource downloader = r.materializeBinarySource();

      {}//Logwriter.printOnConsole("************* PENStyleInteration ***************");
      

//988-6000.drw|main|A|0
//  - 988-600.asm|main|A|0
//    - [4] 988-602_02.prt|main|A|0 
//    - 988-601.prt|main|A|0
//  - harris.frm|main|A|1       

      {}//Logwriter.printOnConsole("fetch...");      
      name = "988-6000.drw";
      uniqueid = "|main|A|0|";   
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("fetchDesignFiles Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getLocation());      
      {}//Logwriter.printOnConsole("Done fetching");
     
      {}//Logwriter.printOnConsole("translate..."); 
      RemotePackage xlatedDesign = translate(rdesign);
      {}//Logwriter.printOnConsole("translate Results:");      
      {}//Logwriter.printOnConsole(" url: " + xlatedDesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + xlatedDesign.getLocation()); 
      {}//Logwriter.printOnConsole(" binaryFileName : " + xlatedDesign.getName());       
      {}//Logwriter.printOnConsole("Done translating");
      
      {}//Logwriter.printOnConsole("attach...");
      RepositoryBinaryTarget agile = null;
      attach(ctx, xlatedDesign, agile, authentication);
      {}//Logwriter.printOnConsole("translate Results:");      
      {}//Logwriter.printOnConsole(" url: " + xlatedDesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + xlatedDesign.getLocation());      
      {}//Logwriter.printOnConsole("Done attaching");     
      
      {}//Logwriter.printOnConsole("************* DONE ***************");

    } catch (Exception e) { e.printStackTrace(); }
  }
}
