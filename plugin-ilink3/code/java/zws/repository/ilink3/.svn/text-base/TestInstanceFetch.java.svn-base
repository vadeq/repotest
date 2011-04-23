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
public class TestInstanceFetch {
  
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
    TestInstanceFetch t = new TestInstanceFetch();
    t.run();
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
    Properties.add("email-recipients" , "eankutse@zerowait-state.com");
    Properties.add("central-server" , "DesignState-node-0");
    Properties.add("max-ilink-tk-licenses" , "5");  
  }
  
  
  
  


  /**
   * Run as publishing engine.
   */
  public void run() {
    String name = null;
    String uniqueid = null;
    Origin origin = null; 
    RemotePackage rdesign = null;
    QxContext ctx = new QxContext();

    try {
      Authentication authentication = new Authentication("admin", "agile");
      Configurator.reinitialize();
      Configurator.load();
 
      RepositoryService c = RepositoryClient.getClient();
      Repository r = c.findRepository("ilink");
      RepositoryBinarySource downloader = r.materializeBinarySource();

      {}//Logwriter.printOnConsole("************* TestInstanceFetch ***************");
      /*      
      //Has one level of parent Generics
      name = "csdd02.prt";
      uniqueid = "|main|E|0|"; 
      {}//Logwriter.printOnConsole("0) fetch instance ..." + "[" + name + "]");  
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("fetchDesignFiles Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getName());      
      {}//Logwriter.printOnConsole("Done fetching"); 
      */      

/*
      //Has two levels of parent Generics
      name = "zcsdd87.prt";
      uniqueid = "|main|1|0|"; 
      {}//Logwriter.printOnConsole("0) fetch instance g..." + "[" + name + "]");  
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("fetchDesignFiles Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getName());      
      {}//Logwriter.printOnConsole("Done fetching");
*/      
      //Has two levels of parent Generics t
      name = "zcsdd87.prt";
      uniqueid = "|main|1|0|"; 
      {}//Logwriter.printOnConsole("0) fetch instance g..." + "[" + name + "]");  
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("fetchDesignFiles Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getName());      
      {}//Logwriter.printOnConsole("Done fetching");
      
      /*    

      name = "inst-drawing.drw";
      uniqueid = "|main|A|0|"; 
      {}//Logwriter.printOnConsole("1) fetch drawing that refers to generic instance..." + "[" + name + "]");        
      origin = OriginMaker.materialize("harris", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("fetchDesignFiles Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getName());      
      {}//Logwriter.printOnConsole("Done fetching");

 
      name = "inst-01.prt";
      uniqueid = "|main|1|0|"; 
      {}//Logwriter.printOnConsole("2) fetch instance part referred to by drawing..." + "[" + name + "]");  
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("fetchDesignFiles Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getName());      
      {}//Logwriter.printOnConsole("Done fetching");
      
  
      name = "inst-02.prt";
      uniqueid = "|main|1|0|"; 
      {}//Logwriter.printOnConsole("3) fetch instance part NOT referred to by drawing..." + "[" + name + "]");  
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("fetchDesignFiles Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getName());      
      {}//Logwriter.printOnConsole("Done fetching");   

    
      name = "inst-generic.prt";
      uniqueid = "|main|1|0|";
      {}//Logwriter.printOnConsole("4) fetch generic part..." + "[" + name + "]");   
      origin = OriginMaker.materialize("cicso", "node-zero", Origin.FROM_ILINK, "ilink", 1234567, uniqueid+name, "", "");            
      rdesign = downloader.fetchDesignFiles(ctx, origin, authentication);
      {}//Logwriter.printOnConsole("fetchDesignFiles Results:");
      {}//Logwriter.printOnConsole(" url: " + rdesign.getUrl().toString());
      {}//Logwriter.printOnConsole(" id : " + rdesign.getName());      
      {}//Logwriter.printOnConsole("Done fetching");
*/
    } catch (Exception e) { e.printStackTrace();}
 
  }
}
