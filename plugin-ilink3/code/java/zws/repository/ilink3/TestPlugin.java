package zws.repository.ilink3;
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 18, 2007 6:52:38 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;

import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;

/**
 * The Class TestPlugin.
 */
public class TestPlugin {

  /**
   * The Constructor.
   */
  private TestPlugin() { }

  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    TestPlugin t = new TestPlugin();
    t.runAsPublishingEngine();
  }

  /**
   * Run as publishing engine.
   */
  public void runAsPublishingEngine() {
    String name = null;
    QxContext ctx = new QxContext();
    try {
      Properties.set(Names.SERVICE_FINDER_HOSTNAME, "10.10.10.151");
      RepositoryService c = RepositoryClient.getClient();
      Repository r = c.findRepository("ilink");
      Authentication id = new Authentication("admin", "agile");
      Metadata m = null;
      //name = "3524-0100.asm";
      name = "00-9901-100.asm";

      //RepositoryMetadataSource metadataSrc = r.materializeMetadataSource();
      //metadataSrc.findLatest(ctx, "29-5001-01.prt", id);
/*
      SearchAgent agent = r.materializeSearchAgent();
      agent.resetStorage();
      agent.initializeStorage();
      agent.setCriteria("(name='29-*.prt')");
      agent.setAuthenticationToken(id);
      agent.execute(); // launches asynchrouns thread

      WaitForThreads wait = new WaitForThreads();
      wait.addThread(agent.getThread());
      wait.execute();

      Collection results = agent.getResults();
      {}//Logwriter.printOnConsole("Found " + results.size() + " results.");
      PrintUtil.print(results);
*/      
      RepositoryMetadataSource mSource = r.materializeMetadataSource();
      RepositoryStructureSource sSource = r.materializeStructureSource();
      //m = mSource.findLatest(ctx, name, id);
      /*m = sSource.reportLatestBOM(ctx, name, id);
      {}//Logwriter.printOnConsole("reportLatestBOM-----");
      {}//Logwriter.printOnConsole(m);
      {}//Logwriter.printOnConsole("Done.........!");
      m = mSource.findLatest(ctx, name, id);
      m = sSource.reportBOM(ctx, m.getOrigin(), id);
      {}//Logwriter.printOnConsole("reportBOM-----");
      {}//Logwriter.printOnConsole(m);
      {}//Logwriter.printOnConsole("Done.........!");*/
      
      
      String recipients= Properties.get(Names.EMAIL_RECIPIENTS);
      if(null!=recipients && recipients.length() >0) recipients += "|"; 
      recipients +=  "admin@harris.com|test@harris.com";
      String subject ="Intralink revison and Agile revision are not matched for";
      String message ="Synchronized item: "; 
      message += Names.NEW_LINE;
      message += "Intralink latest item: ";
      {} //System.out.println("recipients " + recipients);
     
      
      //zws.Alert.notify(subject, message);
      zws.Alert.notify(subject, message,"admin@harris.com");
      
      
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
