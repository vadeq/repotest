package zws.repository.agile.sdk;
  /*
   * DesignState - Design Compression Technology
   * @author: arbind @version: 1.0 Created on Mar 18,
   * 2007 6:52:38 PM Copywrite (c) 2007 Zero
   * Wait-State Inc. All rights reserved
   */



import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;

import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryECOSource;
import zws.repository.target.RepositoryECOTarget;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;
import java.util.Collection;
import java.util.Iterator;

import zws.data.eco.ECO;


/**
 * The Class AgileSDKTest.
 */
public class AgileSDKTest {

  /**
   * The Constructor.
   */
  private AgileSDKTest() {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "10.10.10.175");
    RepositoryService c = RepositoryClient.getClient();
    r = c.findRepository("agile-sdk");
  }

  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    AgileSDKTest t = new AgileSDKTest();
    try {
        //t.runAsPublishingEngine();
        //t.setECOAttribute();
         //t.findPendingECOs();    	
		t.deleteAttachments();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  private void deleteAttachments() throws Exception{
	  RepositoryECOTarget ecoTarget = r.materializeECOTarget();
      id = new Authentication("admin", "agile");
	  //ecoTarget.removeAttachment(materializeQxContext(), "NR00036047", "test.txt", "1701-0101", id);
	  ecoTarget.removeAttachments(materializeQxContext(), "C00026568", "3801-0300DOC", id);
  }
  private void findPendingECOs() {
    try {
      id = new Authentication("admin", "agile");
      RepositoryECOSource ecoSrc = r.materializeECOSource();
      Collection c = ecoSrc.findPendingECOs(materializeQxContext(), "200-1300", id);
      System.out.println(c);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //ecoSrc
  }

  private void setECOAttribute(){
    String ecoNumber;
    try {

      
      Authentication id = new Authentication("admin", "agile");
      //RepositoryECOTarget ecoTarget =    r.materializeECOTarget();
      //ecoNumber =  ecoTarget.createECO(materializeQxContext(), "Engineering Change Order", id);

      ecoNumber = "C01264";
      RepositoryECOSource ecoSource =    r.materializeECOSource();
      ECO eco = ecoSource.findECO(materializeQxContext(), ecoNumber, id);
      
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  
  /**
   * Run as publishing engine.
   */
  public void runAsPublishingEngine() {
    try {

      //RepositoryMetadataSource mSource = r.materializeMetadataSource();
      //Metadata data = mSource.findLatest(materializeQxContext(), "MEP_29-5001-01_P", id);
      //LogWriter.printOnConsole("data -----" + data.toString());
      /*Metadata data = new MetadataBase();
      data.set("name", "MEP_29-5001-101_P");
      data.set("number", "MEP_29-5001-101_P");
      data.set("description", "test_data description");
      data.set("lib_x", "33");
      data.set("agile-class", "CAD Part");
      data.set("CAGE_CODE", "cgx-");*/

      //LogWriter.printOnConsole("data -----" + data.toString());
      //boolean status = getSyncStatus(data);
      //LogWriter.printOnConsole("status  -----" + status );
      //RepositoryWorkflowTarget target = r.materializeRepositoryWorkflowTarget();
      //target.update(materializeQxContext(), data, new File("c:/test.txt"), id);
      String itemNumber = "51-0078-01";
      RepositoryECOTarget ecoTarget =    r.materializeECOTarget();
      String ecoNumber =  ecoTarget.createECO(materializeQxContext(), "ECO", id);

      RepositoryECOSource ecoSource =    r.materializeECOSource();
      ECO eco = ecoSource.findECO(materializeQxContext(), ecoNumber, id);

       eco = ecoSource.findECO(materializeQxContext(), "C01202", id);

      Collection c = ecoSource.findPendingECOs(materializeQxContext(), itemNumber, id);
      zws.util.PrintUtil.print(c);

      // LogWriter.printOnConsole(m);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /*
   * IntralinkOrigin o; o = new IntralinkOrigin(); o.setBranch("br");
   * o.setReleaseLevel("Released"); o.setDatasourceName("ilink-00");
   * o.setDomainName("zws"); o.setServerName("DesignState-node-0");
   * o.setName("abc.prt"); o.setRevision("G"); o.setVersion(8);
   */

 private QxContext materializeQxContext() {
   //QxWebClient webClient = QxWebClient.materializeClient();
   QxContext ctx = new QxContext();
   //ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
   //ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
   //ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
   //ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
   //ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
   //ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
   return ctx;
 }

 private boolean getSyncStatus(Metadata txData) throws Exception{
   boolean isSync = false;
   SynchronizationService r = SynchronizationClient.getClient();
   Collection origins = r.findAllSynchronizationOrigins(txData.getOrigin());
   if(null != origins) {
     Origin syncRecord = null;
     Iterator itr = origins.iterator();
     while(itr.hasNext()) {
       syncRecord = (Origin) itr.next();
       if(null != syncRecord) {
         if(syncRecord.toString().equals(txData.getOrigin().toString())) {
           isSync = true;
           break;
         }
       }
     }
   }
   return isSync;
 }

 private static Repository r = null;
 private static Authentication id= null;
}
