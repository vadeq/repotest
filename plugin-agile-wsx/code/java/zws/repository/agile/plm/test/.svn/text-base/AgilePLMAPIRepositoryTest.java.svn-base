package zws.repository.agile.plm.test;
  /*
   * DesignState - Design Compression Technology
   * @author: arbind @version: 1.0 Created on Mar 18,
   * 2007 6:52:38 PM Copywrite (c) 2007 Zero
   * Wait-State Inc. All rights reserved
   */


import zws.application.Names;
import zws.application.Properties;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.MetadataSubComponentBase;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.repository.target.RepositoryStructureTarget;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
//import zws.util.{}//Logwriter;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;



/**
 * The Class AgileSDKRepositoryTest.
 */
public class AgilePLMAPIRepositoryTest {

  /**
   * The Constructor.
   */
  private AgilePLMAPIRepositoryTest() {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "plm-rtp-002-d");
    c = RepositoryClient.getClient();
    r = c.findRepository("agile-wsx");
  }

  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(String[] args) {
    AgilePLMAPIRepositoryTest t = new AgilePLMAPIRepositoryTest();
    t.runAsPublishingEngine();
  }

  /**
   * Run as publishing engine.
   */
  public void runAsPublishingEngine() {
    try {
      Properties.set(Names.SERVICE_FINDER_HOSTNAME, "plm-rtp-002-d");
      Properties.set(Names.DOMAIN_NAME, "cisco");
      String name = "MEP_700-00419-01_P";
      File f = new File("C:/test.txt");

      Authentication id = new Authentication("user81", "user81");
      Metadata mData = null;
      QxContext ctx = materializeQxContext();
      RepositoryMetadataSource source = r.materializeMetadataSource();
      mData = source.findLatest(materializeQxContext(), name, id);
      System.out.println("mData -----" + mData);
      mData.set("attachment", f.getAbsolutePath());
      createBill(ctx, mData, id);

      System.out.println("Java Returned-----");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void createBill(QxContext ctx, Metadata mData, Authentication id) throws Exception{
    RepositoryStructureTarget structureTarget = r.materializeStructureTarget();
    ctx = RecorderUtil.startNewProcess(ctx, "test", "test", "test class");
    structureTarget.createAndStructureBill(ctx, new BillOfMaterials(mData), id);
  }

  private void populateDependencies(QxContext ctx, RepositoryStructureSource bomSource, Metadata bill, Authentication id) throws Exception {
    Collection deps = bomSource.reportFirstLevelLatestDependencies(ctx, bill.getOrigin(), id);
    if (null !=deps && !deps.isEmpty()) {
      Iterator i = deps.iterator();
      Metadata dep = null;
      MetadataSubComponentBase subDep = null;
      while (i.hasNext()) {
        dep = (Metadata)i.next();
        subDep = new MetadataSubComponentBase(dep);
        if(!bill.hasSubComponent(subDep.getName())) bill.addSubComponent(subDep);
      }
    }
    if(bill.hasSubComponents()) {
      Collection subs = bill.getSubComponents();
      if(null != subs && !subs.isEmpty()) {
        Iterator itr = deps.iterator();
        Metadata subData = null;
        while (itr.hasNext()) {
          subData = (Metadata)itr.next();
          populateDependencies(ctx, bomSource, subData, id);
        }
      }
    }
  }
  /*
   * IntralinkOrigin o; o = new IntralinkOrigin(); o.setBranch("br");
   * o.setReleaseLevel("Released"); o.setDatasourceName("ilink-00");
   * o.setDomainName("zws"); o.setServerName("DesignState-node-0");
   * o.setName("abc.prt"); o.setRevision("G"); o.setVersion(8);
   */

 private QxContext materializeQxContext() {
   QxContext ctx = new QxContext();
   String CREATED_BY_TOOL = "createdByTool";
   String CREATED_BY_TOOL_VERSION = "createdByToolVersion";
   String CREATED_FOR_PROJECT = "createdForProject";
   String createdByTool="MCAD Libraby";
   String createdByToolVersion="PLS 1.3";
   String createdForProject="project PLS 1.3";
   ctx.set(CREATED_BY_TOOL,createdByTool);
   ctx.set(CREATED_FOR_PROJECT,createdForProject);
   ctx.set(CREATED_BY_TOOL_VERSION,createdByToolVersion);
   ctx.set("asyncFlag","true");
   return ctx;
 }
 private  RepositoryService c = null;
 private  Repository r = null;
}
