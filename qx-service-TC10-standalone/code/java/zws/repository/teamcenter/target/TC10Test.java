package zws.repository.teamcenter.target; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 22, 2007
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.origin.TC10Origin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.QxContext;


import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.target.RepositoryStructureTarget;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.TC10RepositoryBase;
import zws.repository.teamcenter.util.TC10Util;
import zws.security.Authentication;
import zws.util.PrintUtil;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataFamilyInstance;
import zws.data.MetadataFamilyInstanceBase;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.data.tc10.TC10MetadataBinary;
import zws.exception.CanNotMaterialize;

import java.util.Iterator;
import java.util.Map;



// TODO: Auto-generated Javadoc
/**
 * The Class TC10Test.
 */
public class TC10Test
{

  /**
   * The main method.
   *
   * @param args the args
   */
  public static void main(String[] args) {TC10Test x = new TC10Test(); x.runTest(args);}

  /**
   * Run test.
   *
   * @param args the args
   */
  public void runTest(String[] args)
  {

	  //String url=args[0];
	  String username=args[0];
	  String password=args[1];
	  String hostname=args[2];
	  String port=args[3];
	  String option=args[4];

{} //System.out.println("User: "+username);
{} //System.out.println("Pwd: "+password);
{} //System.out.println("hostname: "+hostname);
{} //System.out.println("Port: "+port);
{} //System.out.println("Option: "+option);

    String itemId;
    String revId;
    String type;
    String name;
    String desc;
    String uom;
    
	switch(Integer.parseInt(option))
	{
		case 1:
			{} //System.out.println("Create Test");
      if (15!=args.length) {
        {} //System.out.println("usage: user pwd hostname port 1 itemID rev type desc uom");
        System.exit(1);
      }
      itemId=args[5];
      revId=args[6];
      type=args[7];
      name=args[8];
      desc=args[9];
      uom=args[10];
      
			//createTest(hostname, port, username, password, itemId, revId, type, name, desc, uom);
      
      String datasetType=args[11];
      String datasetName=args[12];
      String namedRef=args[13];
      String fileName=args[14];
      
      createTest( hostname, port, username, password, itemId, revId, 
                  type, name, desc, uom, datasetType, datasetName, namedRef, fileName);
      
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
      {} //System.out.println("Create BOM Test");
      if (15!=args.length) {
        {} //System.out.println("usage: user pwd hostname port 4 itemID rev type desc uom itemID2 rev2 itemID3 rev3");
        System.exit(1);
      }
      itemId=args[5];
      revId=args[6];
      type=args[7];
      name=args[8];
      desc=args[9];
      uom=args[10];
      
      String itemID2=args[11];
      String rev2=args[12];
      String itemID3=args[13];
      String rev3=args[14];
      
      createBOMTest( hostname, port, username, password, itemId, revId, 
                     type, name, desc, uom, itemID2, rev2, itemID3, rev3);
			break;
		default:
			{} //System.out.println("Invalid Option.");
			printUsage();
			break;
	}
  System.exit(0);
  }

  public void printUsage()
  {

  }

  public static Origin materializeTC10Origin(String itemId, String revision, String datasetName, String datasetType, String fileName) throws CanNotMaterialize {
	    String domainName = "zws"; //this should come from actual routing information
	    String serverName = "node-0"; //this should come from actual routing information
	    String uniqueID = "";//TC10RepositoryBase.materializeUniqueID(itemId, revision, datasetName, datasetType, fileName);
	    TC10Origin o = (TC10Origin)OriginMaker.materialize(domainName, serverName, Origin.FROM_TEAMCENTER_10, "TC10-Test-Server", 0, uniqueID, null, null);
	    return o;
  }
  
  public static Origin materializeTC10ItemRevOrigin(String itemId, String revision) throws CanNotMaterialize {
    String domainName = "zws"; //this should come from actual routing information
    String serverName = "node-0"; //this should come from actual routing information
    String uniqueID = TC10Util.materializeItemRevUniqueID("AAAAAAAAAAAAAA", itemId, revision);
    TC10ItemRevOrigin o = (TC10ItemRevOrigin)OriginMaker.materialize(domainName, serverName, Origin.FROM_TEAMCENTER_10_REV, "TC10-Test-Server", 0, uniqueID, null, null);
    return o;
  }
  
  public static Origin materializeTC10ImanFileOrigin(String itemId, String revision, String datasetType, String datasetName, String fileName) throws CanNotMaterialize {
    String domainName = "zws"; //this should come from actual routing information
    String serverName = "node-0"; //this should come from actual routing information
    String uniqueID = TC10Util.materializeIMANFileUniqueID("AAAAAAAAAAAAAA", itemId, revision, datasetType, datasetName, fileName);
    TC10IMANFileOrigin o = (TC10IMANFileOrigin)OriginMaker.materialize(domainName, serverName, Origin.FROM_TEAMCENTER_10_IMANFILE, "TC10-Test-Server", 0, uniqueID, null, null);
    return o;
 }  
  
  /* Shows how to generically model an item and its metadata
   *
   * @param origin object origin
   * @param atts object attribute values
   */
  /**
   * Materialize metadata.
   *
   * @param atts the atts
   * @param o the o
   *
   * @return the metadata
   */
  public static Metadata materializeMetadata(Origin o, Map atts) {
    MetadataBase m = new MetadataBase();
    m.setOrigin(o);
    Iterator i = atts.keySet().iterator();
    String key, value;
    while (i.hasNext()) {
      key = (String)i.next();
      value = (String)atts.get(key);
      m.set(key, value);
    }
    return m;
  }

  /* Shows how to generically model an item being recorded as a subcomponent to another item
   *
   * @param origin object origin
   * @param atts object attribute values
   */
  /**
   * Materialize metadata subcomponent.
   *
   * @param quan the quan
   * @param atts the atts
   * @param o the o
   *
   * @return the metadata sub component
   */
  public static MetadataSubComponent materializeMetadataSubcomponent(Origin o, int quan, Map atts) {
    //A subcomponent is modeled the same as metadata, except the implementing class is different
    Metadata m = materializeMetadata(o, atts);
    MetadataSubComponentBase child = new MetadataSubComponentBase(m);
    return child;
  }


  /* Shows how to generically model an item being recorded as an instance of a generic
   *
   * @param origin object origin
   * @param atts object attribute values
   */
  /**
   * Materialize family table instance.
   *
   * @param atts the atts
   * @param o the o
   *
   * @return the metadata family instance
   */
  public static MetadataFamilyInstance materializeFamilyTableInstance(Origin o, Map atts) {
    //An instance is modeled the same as metadata, except the implementing class is different
    Metadata m = materializeMetadata(o, atts);
    MetadataFamilyInstanceBase instance = new MetadataFamilyInstanceBase(m);
    return instance;
  }

  //Option 1
  public void createTest( String hostname, 
                          String port, 
                          String username, 
                          String password, 
                          String itemId, 
                          String revision,
                          String type,
                          String name,
                          String desc,
                          String uom,
                          String datasetType, 
                          String datasetName, 
                          String namedRef, 
                          String fileName)
  {

	  {} //System.out.println("");
	  {} //System.out.println("---------------Create Test----------------");
	  
    TC10RepositoryBase tc10 = new TC10RepositoryBase();
	  tc10.setDomainName("zws");
	  tc10.setServerName("node-0");
	  tc10.setRepositoryName("TC-QA-server");
	  tc10.setHostName(hostname);
	  tc10.setPort(port);
	  tc10.setServicesPath("tc/services/PLMGatewayService");
    
	  try
	  {

		  RepositoryMetadataTarget target = tc10.materializeMetadataTarget();
		  QxContext ctx = new QxContext();
		  Authentication id = new Authentication(username, password);

		  tc10.login(id);      
/*
      {} //System.out.println("+itemId: "+itemId);
      {} //System.out.println("+revId: "+revision);
      {} //System.out.println("+type: "+type);
      {} //System.out.println("+name: "+name);
      {} //System.out.println("+desc: "+desc);      
      {} //System.out.println("+uom: "+uom);          
*/
      
      TC10IMANFileOrigin oFile = (TC10IMANFileOrigin)materializeTC10ImanFileOrigin(itemId, revision, datasetType, datasetName, fileName);
{} //System.out.println("Origin: "+oFile);      
      TC10MetadataBinary bm = new TC10MetadataBinary(new MetadataBase());
      bm.set(TC10Constants.ORIGINAL_FILE_NAME, oFile.getFileName());
      bm.set(TC10Constants.NAMED_REF, namedRef);
      bm.setOrigin(oFile);

      // Create a new item/item revision
      TC10ItemRevOrigin o = (TC10ItemRevOrigin)materializeTC10ItemRevOrigin(itemId, revision);
      MetadataBase data = new MetadataBase();
      data.setOrigin(o);
      data.set(TC10Constants.ITEM_ID, itemId);
      data.set(TC10Constants.REVISION, revision);
      data.set(TC10Constants.TYPE, type);
      data.set(TC10Constants.NAME, name);
      data.set(TC10Constants.DESCRIPTION, desc);
      data.set(TC10Constants.UNIT_OF_MEASURE, uom);
      data.addBinary(bm);
      
      Origin tcOrig = target.create(ctx, data, null, id);
      PrintUtil.print(tcOrig);
      
		  tc10.logout(id);
	  }
	  catch (Exception e) {
		  {} //System.out.println(e.getMessage());
		  e.printStackTrace();
	  }
	  {} //System.out.println("");
	  {} //System.out.println("---------------Create Test End--------------");
  }

  //Option 1
  public void createTest( String hostname, 
                          String port, 
                          String username, 
                          String password, 
                          String itemId, 
                          String revision,
                          String type,
                          String name,
                          String desc,
                          String uom)
  {

    {} //System.out.println("");
    {} //System.out.println("---------------Create Test----------------");
    
    TC10RepositoryBase tc10 = new TC10RepositoryBase();
    tc10.setDomainName("zws");
    tc10.setServerName("node-0");
    tc10.setRepositoryName("TC-QA-server");
    tc10.setHostName(hostname);
    tc10.setPort(port);
    tc10.setServicesPath("tc/services/PLMGatewayService");
    
    try
    {

      RepositoryMetadataTarget target = tc10.materializeMetadataTarget();
      QxContext ctx = new QxContext();
      Authentication id = new Authentication(username, password);

      tc10.login(id);

      // Create a new item/item revision
      TC10ItemRevOrigin o = (TC10ItemRevOrigin)materializeTC10ItemRevOrigin(itemId, revision);
      MetadataBase data = new MetadataBase();
/*
      {} //System.out.println("+itemId: "+itemId);
      {} //System.out.println("+revId: "+revision);
      {} //System.out.println("+type: "+type);
      {} //System.out.println("+name: "+name);
      {} //System.out.println("+desc: "+desc);      
      {} //System.out.println("+uom: "+uom);          
*/      
      data.setOrigin(o);
      data.set(TC10Constants.ITEM_ID, itemId);
      data.set(TC10Constants.REVISION, revision);
      data.set(TC10Constants.TYPE, type);
      data.set(TC10Constants.NAME, name);
      data.set(TC10Constants.DESCRIPTION, desc);
      data.set(TC10Constants.UNIT_OF_MEASURE, uom);
      
      Origin tcOrig = target.create(ctx, data, null, id);
      PrintUtil.print(tcOrig);
      
      tc10.logout(id);
    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("");
    {} //System.out.println("---------------Create Test End--------------");
  }

  //Option 1
  public void createBOMTest(  String hostname, 
                          String port, 
                          String username, 
                          String password, 
                          String itemId, 
                          String revision,
                          String type,
                          String name,
                          String desc,
                          String uom,
                          String itemId2, 
                          String rev2, 
                          String itemId3, 
                          String rev3)
  {

    {} //System.out.println("");
    {} //System.out.println("---------------Create BOM Test----------------");
    
    TC10RepositoryBase tc10 = new TC10RepositoryBase();
    tc10.setDomainName("zws");
    tc10.setServerName("node-0");
    tc10.setRepositoryName("TC-QA-server");
    tc10.setHostName(hostname);
    tc10.setPort(port);
    tc10.setServicesPath("tc/services/PLMGatewayService");
    
    try
    {
      
      // Create a new item/item revision
      TC10ItemRevOrigin o = (TC10ItemRevOrigin)materializeTC10ItemRevOrigin(itemId, revision);
      MetadataBase data = new MetadataBase();
      data.set(TC10Constants.ITEM_ID, itemId);
      data.set(TC10Constants.REVISION, revision);
      data.set(TC10Constants.TYPE, type);
      data.set(TC10Constants.NAME, name);
      data.set(TC10Constants.DESCRIPTION, desc);
      data.set(TC10Constants.UNIT_OF_MEASURE, uom);
      data.setOrigin(o);
      
      TC10ItemRevOrigin o2 = (TC10ItemRevOrigin)materializeTC10ItemRevOrigin(itemId2, rev2);
      MetadataBase data2 = new MetadataBase();
      data2.set(TC10Constants.ITEM_ID, itemId2);
      data2.set(TC10Constants.REVISION, rev2);
      data2.set(TC10Constants.TYPE, type);
      data2.set(TC10Constants.NAME, name);
      data2.set(TC10Constants.DESCRIPTION, desc);
      data2.set(TC10Constants.UNIT_OF_MEASURE, uom);
      data2.setOrigin(o2);
      
      
     
      TC10ItemRevOrigin o3 = (TC10ItemRevOrigin)materializeTC10ItemRevOrigin(itemId3, rev3);
      MetadataBase data3 = new MetadataBase();
      data3.setOrigin(o3);
      data3.set(TC10Constants.ITEM_ID, itemId3);
      data3.set(TC10Constants.REVISION, rev3);
      data3.set(TC10Constants.TYPE, type);
      data3.set(TC10Constants.NAME, name);
      data3.set(TC10Constants.DESCRIPTION, desc);
      data3.set(TC10Constants.UNIT_OF_MEASURE, uom);
      
      MetadataSubComponentBase sub1 = new MetadataSubComponentBase(data2);
      MetadataSubComponentBase sub2 = new MetadataSubComponentBase(data3);
      
      sub2.setQuantity(3);
      data2.addSubComponent(sub2);
      
      sub1.setQuantity(2);
      data.addSubComponent(sub1);
      sub2.setQuantity(1);
      data.addSubComponent(sub2);      
      
      RepositoryStructureTarget target = tc10.materializeStructureTarget();
      QxContext ctx = new QxContext();
      Authentication id = new Authentication(username, password);
      
      tc10.login(id);      
      
      BillOfMaterials bill = new BillOfMaterials(data);      
      target.structureBill(ctx, bill, id);
      
      tc10.logout(id);
      
    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("");
    {} //System.out.println("---------------Create BOM Test End--------------");
  }  
  
  /** The oid. */
  public static long oid = 88888888;

  /** The delim. */
  private static String delim = Origin.delim;
}
