package zws.repository.agile.sdk.test;

import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.application.Names;
import zws.application.Properties;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.data.eco.ECO;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.agile.sdk.AgileSDKConstants;
import zws.repository.agile.sdk.AgileSDKRepositoryBinaryTarget;
import zws.repository.agile.sdk.AgileSDKRepositoryECOSource;
import zws.repository.agile.sdk.AgileSDKRepositoryECOTarget;
import zws.repository.agile.sdk.AgileSDKRepositoryMetadataSource;
import zws.repository.agile.sdk.AgileSDKRepositoryMetadataTarget;
import zws.repository.agile.sdk.AgileSDKRepositoryStructureSource;
import zws.repository.agile.sdk.AgileSDKRepositoryStructureTarget;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;

/**
 *
 */
/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Jul 9, 2007 4:28:54 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author ptoleti
 *
 */
public class TestAgileSDK {
  TestAgileSDK() {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "designstate-0");
    repSvc = RepositoryClient.getClient();
    base = repSvc.findRepository("agile-sdk");
    System.out.println(base.toXML());
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Authentication id;
    try {
      TestAgileSDK t = new TestAgileSDK();
      System.out.println("Start......");
      id = new Authentication("admin", "agile");
      //t.metadataTarget(id);
      //t.structureTarget(id);
      //t.structureSource(id);
      //t.setECOAtts(id);
      //t.setBOMAtts(id);
      //t.addAttachment(id);
      t.deleteECO(id);
      System.out.println("END......");
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   }


    
  private void addAttachment(Authentication id) {
    try {
      /*
      QxContext ctx = new QxContext();
      AgileSDKRepositoryMetadataSource mSource= (AgileSDKRepositoryMetadataSource ) base.materializeMetadataSource();
      AgileSDKRepositoryBinaryTarget binTarget= (AgileSDKRepositoryBinaryTarget) base.materializeBinaryTarget();
      //File binary = new File("C:\\temp\\agile_error2.txt"); 
      {} //System.out.println("===>>>********Verify that the url (below) to download from exist ***********");

      URL url = new URL("http://vm-file-depot/FileDepot/Repository/vm-chrysalis-sv-1195374803629-0/drw_base_line111.zip");
      RemoteFileImpl rf = new RemoteFileImpl(url);
      rf.setBinaryFileName("drw_base_line.stp");
      ctx.set(QxContext.FILE_DESCRIPTION, "file description");
      ctx.set(QxContext.FOLDER_DESCRIPTION, "file description");
      Metadata m = mSource.findLatest(ctx, "DDD", id);
      Origin o = m.getOrigin();
      binTarget.storeAttachment(ctx, o.getName(), rf, id);
      //binTarget.storeAttachmentForECOItem(ctx, o, "C01441", rf, id);
*/
      QxContext ctx = new QxContext();
      AgileSDKRepositoryBinaryTarget binTarget= (AgileSDKRepositoryBinaryTarget) base.materializeBinaryTarget();      
      ctx.set(QxContext.ATTACHMENT_FILE_DESCRIPTION, "Testing, testing attachment FILE description programmatically - EKA 12192007");
      ctx.set(QxContext.ATTACHMENT_FOLDER_DESCRIPTION, "UPDATED FOLDER description programmatically - EKA 12192007");      
      File attachment = new File("c:\\temp\\AttachmentTest4.txt");
      //binTarget.TestStoreAttachment(ctx, "3721-0300DOC", attachment, id); 
      binTarget.TestStoreAttachment(ctx, "TEST_VAULTDOC", attachment, id);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void setBOMAtts(Authentication id) {
    try {
      QxContext runningCtx = new QxContext();
      AgileSDKRepositoryECOSource ecoSource= (AgileSDKRepositoryECOSource ) base.materializeECOSource();
      AgileSDKRepositoryECOTarget ecoTarget= (AgileSDKRepositoryECOTarget) base.materializeECOTarget();
      ECO eco = ecoSource.findECO(runningCtx, "C01441", id);
      ecoTarget.setBOMAttribute(runningCtx, "C01441", "P00061", "P00062", "Find Num", "77", id);
      ecoTarget.setBOMAttribute(runningCtx, "C01441", "P00061", "P00062", "BOM Notes", "this is a not a note", id);
      ecoTarget.setBOMAttribute(runningCtx, "C01441", "P00061", "P00062", "CAD Source", "Intralink", id);
      ecoTarget.setBOMAttribute(runningCtx, "C01441", "P00061", "P00062", "Usage Type", "your wish is my command", id);
      ecoTarget.setBOMAttribute(runningCtx, "C01441", "P00061", "P00062", "Trace Code", "breadcrumbs", id);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  
  private void setECOAtts(Authentication id) {
    try {
      QxContext runningCtx = new QxContext();
      AgileSDKRepositoryECOSource ecoSource=
        (AgileSDKRepositoryECOSource ) base.materializeECOSource();
      AgileSDKRepositoryECOTarget ecoTarget=
        (AgileSDKRepositoryECOTarget) base.materializeECOTarget();
      
     ECO eco = ecoSource.findECO(runningCtx, "C00026556", id);
     ecoSource.findECO(runningCtx, "C00026556", id);
     ecoTarget.setECOAttribute(runningCtx, "C00026556", "Project Authorization", "1235", id);
     ecoTarget.setECOAttribute(runningCtx, "C00026556", "uom", "Box", id);
     ecoTarget.setECOOriginator(runningCtx, "C00026556", "admin", id);
     ecoTarget.setECOChangeAnalyst(runningCtx, "C00026556", "intralink", id);
     /*
     {} //System.out.println("-----------BEFORE---------");
     {} //System.out.println(eco);
     Metadata m = new MetadataBase();
     
     m.set("Project Authorization", "projzws");
     m.set("UOM", "each");
     m.set("Description of Change", "TESTING ATT SETTING");
     m.set("Owning Organization", "harris");
     m.set("Originator", "admin");
     
     ecoTarget.createECO(runningCtx, ecoType, id);
     //update(runningCtx, "C01441", m, id);
     eco = ecoSource.findECO(runningCtx, "C01441", id);
     {} //System.out.println("-----------AFTER---------");
     */
     {} //System.out.println(eco);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private void metadataTarget(Authentication id) {
    try {
    AgileSDKRepositoryMetadataSource b = (AgileSDKRepositoryMetadataSource) base.materializeMetadataSource();
    AgileSDKRepositoryMetadataTarget t = (AgileSDKRepositoryMetadataTarget) base.materializeMetadataTarget();
    File file = new File("c:/test.txt");
    Metadata root = createData("ROOT1", "NEW description1");

    {} //System.out.println("update......");
    t.update(new QxContext(), root, file, id);
    Metadata m = b.findLatest(new QxContext(), "ROOT1", id);
    {} //System.out.println("result...");
    {} //System.out.println(m);
  } catch (NoSuchAlgorithmException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }

  }


  private void structureTarget(Authentication id) {
    try {
      AgileSDKRepositoryStructureTarget sTarget =
                            (AgileSDKRepositoryStructureTarget) base.materializeStructureTarget();


      Metadata root = createData("ROOT1", "ROOT1");
      MetadataSubComponent child =  new MetadataSubComponentBase(createData("ROOT2-CHILD1", "ROOT2-CHILD1"));
      child.set(MetadataSubComponent.QUANTITY, "5");
      //root.addSubComponent(child);

      child =  new MetadataSubComponentBase(createData("ROOT2-CHILD2", "ROOT2-CHILD2"));
      child.set(MetadataSubComponent.QUANTITY, "11");
      root.addSubComponent(child);

      {} //System.out.println("structureTarget");
      BillOfMaterials bom = new BillOfMaterials(root);
      {} //System.out.println(bom.toString());
      sTarget.structureBill(new QxContext(), bom, id);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  
  private void structureSource(Authentication id) {
    try {
      AgileSDKRepositoryMetadataSource source = (AgileSDKRepositoryMetadataSource) base.materializeMetadataSource();
      Metadata data = source.findLatest(new QxContext(), "MEP_77-8001_P", id);
      {} //System.out.println("data " + data);
      AgileSDKRepositoryStructureSource structureSource =
                            (AgileSDKRepositoryStructureSource) base.materializeStructureSource();

      Metadata bom = structureSource.reportBOM(new QxContext(), data.getOrigin(), id);
      {} //System.out.println("bom " + bom);
      
      Collection items = structureSource.reportFirstLevelDependencies(new QxContext(), data.getOrigin(), id);
      
      zws.util.PrintUtil.print(items);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

 
  private void deleteECO(Authentication id) throws Exception {
	  AgileSDKRepositoryECOTarget ecoTarget = (AgileSDKRepositoryECOTarget) base.materializeECOTarget();
	  ecoTarget.deleteEmptyECO(new QxContext(),  "C00026565", false, id);
  }
  
  private Metadata createData(String name, String desc) {
    Metadata root = new MetadataBase();
    root.set("name", name);
    root.set(AgileSDKConstants.ATT_PART_NUMBER, name);
    root.set(AgileSDKConstants.ATT_AGILE_CLASS_TYPE, "CAD Part");
    root.set("description", desc);
    root.set("rev", "Introductory");
    return root;
  }
  private  RepositoryService repSvc = null;
  private  Repository base = null;
}
