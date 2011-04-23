package zws.repository.teamcenter; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 22, 2007
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.origin.TC10Origin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.origin.tc10.TC10ItemOrigin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.source.RepositoryConfigurationSource;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.util.PrintUtil;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataFamilyInstance;
import zws.data.MetadataFamilyInstanceBase;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.data.tc10.TC10MetadataBinary;
import zws.exception.CanNotMaterialize;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

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
	  String option_param=args[5];

{} //System.out.println("User: "+username);
{} //System.out.println("Pwd: "+password);
{} //System.out.println("hostname: "+hostname);
{} //System.out.println("Port: "+port);
{} //System.out.println("Option: "+option);
{} //System.out.println("Option Param: "+option_param);

    //testOrigin();
    //testMetadata();
    //testMetadataBOM();
    //testFamilyTable();
    //testSearching(hostname, port, username, password, searchCriteria);
    //testFindMetadata(url, username, password, name);
    //testDownloadBinary(url, username, password, name);
	//testMultiuser(username, password);
	//testReportBOM(username, password);
	//testConfigurationReader(username, password);

	switch(Integer.parseInt(option))
	{
		case 1:
			{} //System.out.println("Search Test");
			searchTest(hostname, port, username, password, option_param);
			break;
		case 2:
			{} //System.out.println("Find By Origin Test");
			findByOriginTest(hostname, port, username, password, option_param);
			break;
		case 3:
			{} //System.out.println("Report BOM");
			reportBOMTest(hostname, port, username, password, option_param);
			break;
		case 4:
			{} //System.out.println("Download Binary");
			downloadBinaryTest(hostname, port, username, password, option_param);
			break;
		default:
			{} //System.out.println("Invalid Option.");
			printUsage();
			break;
	}

  }

  public void printUsage()
  {

  }
  /*
   * Creates some tc10Origins using random hard coded routing and identification information
   */
  /**
   * Test origin.
   */
  public void testOrigin()
  {
    Origin o1=null, o2=null, o3=null, o4=null;
    {} //System.out.println("");
    {} //System.out.println("-------Origin Test------------------------");
    try {
      //o1 = materializeOrigin("zws8", "8880", 8, 55550);
      {} //System.out.println(o1);

      //o2 = materializeOrigin("zws9", "8881", 7, 55551);
      {} //System.out.println(o2);

      //o3 = materializeOrigin("zwsA", "8882", 6, 55552);
      {} //System.out.println(o3);

      //o4 = materializeOrigin("zwsB", "8883", 5, 55553);
      {} //System.out.println(o4);

    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("-------Origin Test End--------------------");
  }

  /**
   * Test metadata.
   */
  public void testMetadata()
  {
    Origin o1=null, o2=null, o3=null, o4=null;
    Metadata m1=null, m2=null, m3=null, m4=null;
    {} //System.out.println("");
    {} //System.out.println("-------Metadata Test----------------------");
    Map x1= new HashMap();
    Map x2= new HashMap();
    Map x3= new HashMap();
    Map x4= new HashMap();
    x1.put("project", "proj-x1"); x1.put("length","15");x1.put("width","5");
    x2.put("project", "proj-x2"); x2.put("length","16");x2.put("width","6");
    x3.put("project", "proj-x3"); x3.put("length","17");x3.put("width","7");
    x4.put("project", "proj-x4"); x4.put("length","18");x4.put("width","8");
    try {
      //o1 = materializeOrigin("zws8", "8880",  8, 55550);
      m1 = materializeMetadata(o1, x1);
      System.out.print(m1);

      //o2 = materializeOrigin("zws9", "8881", 7, 55551);
      m2 = materializeMetadata(o2, x2);
      System.out.print(m2);

      //o3 = materializeOrigin("zwsA", "8882", 6, 55552);
      m3 = materializeMetadata(o3, x3);
      System.out.print(m3);

      //o4 = materializeOrigin("zwsB", "8883", 5, 55553);
      m4 = materializeMetadata(o4, x4);
      System.out.print(m4);
    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("-------Metadata Test End------------------");
  }

  /**
   * Test metadata BOM.
   */
  public void testMetadataBOM()
  {
    Origin o1=null, o21=null, o22=null, o31=null;
    Metadata root=null;
    MetadataSubComponent child21=null, child22=null, child31=null;
    {} //System.out.println("");
    {} //System.out.println("-------Metadata BOM Test-----------------------");
    {} //System.out.println("Multi nesting is not indented properly, but it is proper XML :)");
    {} //System.out.println("-----------------------------------------------");
    Map x1= new HashMap();
    Map x21= new HashMap();
    Map x22= new HashMap();
    Map x31= new HashMap();
    x1.put("project", "proj-x"); x1.put("length","15");x1.put("width","5");
    x21.put("project", "proj-x"); x21.put("length","16");x21.put("width","6");
    x22.put("project", "proj-x"); x22.put("length","17");x22.put("width","7");
    x31.put("project", "proj-x"); x31.put("length","18");x31.put("width","8");
    try {
      //o1 = materializeOrigin("zws8", "8880", 8, 55550);
      root = materializeMetadata(o1, x1);

      //o21 = materializeOrigin("zws9", "8881", 7, 55551);
      child21 = materializeMetadataSubcomponent(o21, 21, x21);

      //o31 = materializeOrigin("zwsA", "8882", 6, 55552);
      child31 = materializeMetadataSubcomponent(o31, 31, x31);

      //o22 = materializeOrigin("zwsB", "8883", 5, 55553);
      child22 = materializeMetadataSubcomponent(o22, 22, x22);

      root.addSubComponent(child21); //add child21 as child to root
      root.addSubComponent(child22); //also add child22 as child to root
      child21.addSubComponent(child31); //nest child31 as child to child21
      {} //System.out.println(root);
    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("-------Metadata BOM Test End-------------------");
  }


  /**
   * Test family table.
   */
  public void testFamilyTable() {
    Origin o0=null, o1=null, o2=null, o3=null;
    Metadata generic=null;
    MetadataFamilyInstance instance1=null, instance2=null, instance3=null;
    {} //System.out.println("");
    {} //System.out.println("-------Metadata FamilyTable Test-----------------------");
    {} //System.out.println("FT nesting is not indented properly, but it is proper XML :)");
    {} //System.out.println("-----------------------------------------------");
    Map x0= new HashMap();
    Map x1= new HashMap();
    Map x2= new HashMap();
    Map x3= new HashMap();
    x0.put("project", "proj-x"); x0.put("length","15");x0.put("width","5");
    x1.put("project", "proj-x"); x1.put("length","16");x1.put("width","6");
    x2.put("project", "proj-x"); x2.put("length","17");x2.put("width","7");
    x3.put("project", "proj-x"); x3.put("length","18");x3.put("width","8");
    try {
      //o0 = materializeOrigin("zws8", "8880", 8, 55550);
      generic = materializeMetadata(o0, x1);

      //o1 = materializeOrigin("zws9", "8881", 7, 55551);
      instance1 = materializeFamilyTableInstance(o1, x1);

      //o2 = materializeOrigin("zwsA", "8882", 6, 55552);
      instance2 = materializeFamilyTableInstance(o2, x2);

      //o3 = materializeOrigin("zwsB", "8883", 5, 55553);
      instance3 = materializeFamilyTableInstance(o3, x3);

      generic.addFamilyInstance(instance1); //add instance1 as instance to generic
      generic.addFamilyInstance(instance2); //add instance2 as instance to generic
      instance2.addFamilyInstance(instance3); //add instance3 as nested instance to instance2
      {} //System.out.println(generic);
    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("-------Metadata FamilyTable Test End-------------------");
  }



  /**
   * Test find metadata.
   *
   * @param username the username
   * @param name the name
   * @param password the password
   * @param url the url
   */
  public void testFindMetadata(String url, String username, String password, String name) {
    {} //System.out.println("");
    {} //System.out.println("-------Search Test------------------------");
    TC10RepositoryBase tc10 = new TC10RepositoryBase();
    tc10.setDomainName("zws");
    tc10.setServerName("node-0");
    tc10.setRepositoryName("TC-QA-server");
	tc10.setHostName("localhost");
	//tc10.setServerMarker("TcServer1");
	//tc10.setTCServerName("TcData");
	//tc10.setPort("1572");
	tc10.setHostName("localhost");
	tc10.setPort("8090");
	tc10.setServicesPath("tc/services/PLMGatewayService");

    try {

      RepositoryMetadataSource source = tc10.materializeMetadataSource();
      QxContext ctx = new QxContext();
      Authentication id = new Authentication(username, password);

  	  // Login
      tc10.login(id);

      //Origin tcOrig = materializeTC10Origin("000001", "A", "000001/A", "Text", "000001_A.dat");
      TC10ItemOrigin tcOrig = new TC10ItemOrigin( tc10.getDomainName(),
    		  									  tc10.getServerName(),
    		  									  tc10.getRepositoryName(),
    		  									  "AAAAAAAAAAAAAA|000001",
    		  									  0,
    		  									  null);

      //Metadata m = source.findLatest(ctx, name, id);
      //PrintUtil.print(m);

      Metadata m = source.find(ctx, tcOrig, id);
      PrintUtil.print(m);

      tc10.logout(id);

    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("");
    {} //System.out.println("-------Search Test End--------------------");
  }

  /**
   * Test download binary.
   *
   * @param username the username
   * @param name the name
   * @param password the password
   * @param url the url
   */
  public void testDownloadBinary(String url, String username, String password, String name)
  {

    {} //System.out.println("");
    {} //System.out.println("-------Download Binary Test------------------------");
    TC10RepositoryBase tc10 = new TC10RepositoryBase();
    tc10.setDomainName("zws");
    tc10.setServerName("node-0");
    tc10.setRepositoryName("TC-QA-server");
    tc10.setHostName("localhost");
    //tc10.setServerMarker("TcServer1");
    //tc10.setTCServerName("TcData");
    //tc10.setPort("1572");
    tc10.setPort("1572");

    try
    {

      QxContext ctx = new QxContext();
      Authentication id = new Authentication(username, password);

      // Login
      tc10.login(id);

      //Origin tcOrig = materializeTC10Origin("000001", "A", "000001/A", "Text", "000001_A.dat");
      TC10IMANFileOrigin tcOrig = new TC10IMANFileOrigin( tc10.getDomainName(),
				  										  tc10.getServerName(),
				  										  tc10.getRepositoryName(),
				  										  "AAAAAAAAAAAAAA|000001|A|Text|000001/A|000001_A.dat",
				  										  0,
				  										  null);

      // Get File
      RepositoryBinarySource source = tc10.materializeBinarySource();

      //File toDir = new File("C:\\temp\\toDir");
      //File f = source.download(ctx, name, id);
      //File f = source.download(ctx, name, toDir, id);
      //+++ Need to update to use RemoteFile
      //File f = source.download(ctx, tcOrig, id);
      //File f = source.download(ctx, tcOrig, "C:\\Temp", id);
      //File f = source.download(ctx, tcOrig, toDir, id);
      //PrintUtil.print(f);
      {} //System.out.println("\n");

      //{} //System.out.println("File Size: "+source.getBinaryLength(ctx, tcOrig, id));
      /*
      InputStream in = source.openStream(ctx, tcOrig, id);
      try  {
        InputStreamReader inR = new InputStreamReader (  in  ) ;
        BufferedReader buf = new BufferedReader (  inR  ) ;
        String line;
        while  (   (  line = buf.readLine (  )   )  != null  )   {
          {} //System.out.println (  line  ) ;
         }
       }  finally  {
        in.close (  ) ;
       }
       */

      // Logout
      tc10.logout(id);

    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }

    {} //System.out.println("");
    {} //System.out.println("-------Download Binary End--------------------");
  }

  /**
   * Test searching.
   */
  public void testSearching(String hostname, String port, String username, String password, String searchCriteria)
  {
	  {} //System.out.println("");
	  {} //System.out.println("-------Search Test------------------------");
	  TC10RepositoryBase tc10 = new TC10RepositoryBase();
	  tc10.setDomainName("zws");
	  tc10.setServerName("node-0");
	  tc10.setRepositoryName("TC-QA-server");
	  //tc10.setServerMarker("TcServer1");
	  //tc10.setTCServerName("TcData");
	  //tc10.setPort("1572");

	  //tc10.setHostName("localhost");
	  //tc10.setPort("8090");
	  tc10.setHostName(hostname);
	  tc10.setPort(port);
	  tc10.setServicesPath("tc/services/PLMGatewayService");

	  try
	  {

		  QxContext ctx = new QxContext();
		  Authentication id = new Authentication(username, password);

		  SearchAgent agent = tc10.materializeSearchAgent();
		  TC10RepositorySearchAgent tc_agent = (TC10RepositorySearchAgent)agent;
		  tc_agent.setAuthenticationToken(id);

		  String c=null;
		  //c = "(000013)"; // simple querry
		  c = "( ItemID="+searchCriteria+" )"; // compound query: +=and
		  //c = "(name=r-* + rev=pilot) | (name=abc* + rev=released)"; // complex query:  +=and, |=or
		  //c = "(name=r-*)";
		  //c = "(original_file_name=user1.txt)";
		  //tc_agent.setQueryTarget(TC10Constants.IMANFILE_CLASS);
		  tc_agent.initializeStorage(new Vector());
		  tc_agent.setCriteria(c);
		  tc_agent.search();
		  Collection r = agent.getResults();
		  //PrintUtil.print(r);

		  //tc10.login(id);

		  {} //System.out.println("Number of Items Found: "+r.size());
		  int i = 0;
		  Iterator it = r.iterator();
		  while(it.hasNext())
		  {
			  i++;
			  MetadataBase m = (MetadataBase)it.next();
			  Origin o = m.getOrigin();
			  {} //System.out.println(i+": " + o.toString());

/*
{} //System.out.println("\nName: " + m.get("object_name"));
			  Collection binaryCol = m.getBinaries();
			  if(binaryCol!=null)
			  {
{} //System.out.println("Number of Binary File: " + binaryCol.size());
				  Iterator it2 = binaryCol.iterator();
				  if(it2.hasNext())
				  {
					  TC10MetadataBinary bm = (TC10MetadataBinary)it2.next();
					  RepositoryBinarySource source = tc10.materializeBinarySource();
{} //System.out.println("Downloading...: " + bm.getOrigin());
					  File f = source.download(ctx, bm.getOrigin(), id);
				  }
			  }
*/
		  }

		  //tc10.logout(id);
	  }
	  catch (Exception e) {
		  {} //System.out.println(e.getMessage());
		  e.printStackTrace();
	  }
	  {} //System.out.println("");
	  {} //System.out.println("-------Search Test End--------------------");
  }

  public void testMultiuser(String username1, String password1)
  {
  /*
   * login usera
   * login userb
   * usera - find(abc);
   * usera - find(def);
   * userb - find (abc);
   * userb - find (def);
   *
   * usera has access to abc but not to def
   * userb has access to def but not to abc
   *
   */
	  //String username1 = "user1";
	  //String password1 = "user1";

	  String username2 = "user2";
	  String password2 = "user2";

	  {} //System.out.println("");
	  {} //System.out.println("-------MultiUser Test------------------------");
	  TC10RepositoryBase tc10 = new TC10RepositoryBase();
	  tc10.setDomainName("zws");
	  tc10.setServerName("node-0");
	  tc10.setRepositoryName("TC-QA-server");
	  tc10.setHostName("localhost");
	  //tc10.setServerMarker("TcServer1");
	  //tc10.setTCServerName("TcData");
	  tc10.setPort("1572");

	  try
	  {

	    QxContext ctx = new QxContext();
	    Authentication id1 = new Authentication(username1, password1);
	    Authentication id2 = new Authentication(username2, password2);

	    // Login
	    tc10.login(id1);
	    tc10.reportServerInformation(id1);

	    tc10.login(id2);
	    tc10.reportServerInformation(id2);


	    {} //System.out.println("Downloading Files...");
	    // Get File
	    RepositoryBinarySource source = tc10.materializeBinarySource();

	    Origin tcOrig1 = materializeTC10Origin("000013", "A", "000013/A", "Text", "user1.txt");
	    Origin tcOrig2 = materializeTC10Origin("000012", "A", "000012/A", "Text", "user2.txt");

      //+++ Need to update to use RemoteFile
	    /*File f1 = source.download(ctx, tcOrig1, id1);
	    PrintUtil.print(f1);

	    File f2 = source.download(ctx, tcOrig2, id2);
	    PrintUtil.print(f2);*/

	    // Logout
	    tc10.logout(id2);
	    tc10.logout(id1);


	  }
	  catch (Exception e)
	  {
		  {} //System.out.println(e.getMessage());
	      e.printStackTrace();
	  }

	  {} //System.out.println("");
	  {} //System.out.println("-------MultiUser End--------------------");
  }

  public void testReportBOM(String username, String password)
  {

	  {} //System.out.println("");
	  {} //System.out.println("-------Report BOM Test------------------------");

	  TC10RepositoryBase tc10 = new TC10RepositoryBase();
	  tc10.setDomainName("zws");
	  tc10.setServerName("node-0");
	  tc10.setRepositoryName("TC-QA-server");
	  tc10.setHostName("localhost");
	  //tc10.setServerMarker("TcServer1");
	  //tc10.setTCServerName("TcData");
	  tc10.setPort("1572");

	  try
	  {

		RepositoryStructureSource source = tc10.materializeStructureSource();

	    QxContext ctx = new QxContext();
	    Authentication id = new Authentication(username, password);

	    // Login
	    tc10.login(id);

	    Origin tcOrig = materializeTC10Origin("000001", "A", "000001/A", "Text", "000001_A.dat");

	    //Metadata m = source.reportAllDependencies(arg0, arg1, arg2, arg3);
	    //Metadata m = source.reportBill(arg0, arg1, arg2);
	    //Metadata m = source.reportLatestBOM(ctx,tcOrig,id);
	    //Metadata m = source.reportDesignDependencies(arg0, arg1, arg2, arg3);
	    //Metadata m = source.reportWhereUsed(arg0, arg1, arg2, arg3);

	    //PrintUtil.print(m);
	    //{} //System.out.println(m);

	    // Logout
	    tc10.logout(id);
	  }
	  catch (Exception e)
	  {
		  {} //System.out.println(e.getMessage());
	      e.printStackTrace();
	  }

	  {} //System.out.println("");
	  {} //System.out.println("-------Report BOM End--------------------");
  }

  public void testConfigurationReader(String username, String password)
  {
	  {} //System.out.println("");
	  {} //System.out.println("-------Configuration Reader Test------------------------");

	  TC10RepositoryBase tc10 = new TC10RepositoryBase();
	  tc10.setDomainName("zws");
	  tc10.setServerName("node-0");
	  tc10.setRepositoryName("TC-QA-server");
	  tc10.setHostName("localhost");
	  //tc10.setServerMarker("TcServer1");
	  //tc10.setTCServerName("TcData");
	  tc10.setPort("1572");

	  try
	  {

		RepositoryConfigurationSource source = tc10.materializeConfigurationSource();

	    QxContext ctx = new QxContext();
	    Authentication id = new Authentication(username, password);

	    // Login
	    tc10.login(id);

	    Origin tcOrig = materializeTC10Origin("000001", "A", "000001/A", "Text", "000001_A.dat");

	    //Metadata m = source.reportAllDependencies(arg0, arg1, arg2, arg3);
	    //Metadata m = source.reportBill(arg0, arg1, arg2);
	    //Metadata m = source.reportBOM(ctx,tcOrig, "Latest Working", id);
	    //Metadata m = source.reportDesignDependencies(arg0, arg1, arg2, arg3);
	    //Metadata m = source.reportWhereUsed(arg0, arg1, arg2, arg3);

	    //PrintUtil.print(m);
	    //{} //System.out.println(m);

	    // Logout
	    tc10.logout(id);
	  }
	  catch (Exception e)
	  {
		  {} //System.out.println(e.getMessage());
	      e.printStackTrace();
	  }

	  {} //System.out.println("");
	  {} //System.out.println("-------Configuration Reader End--------------------");
  }

  /* Shows how to Create an tc10Origin.
   * An origin provides the routing and identification information for an object.
   * Format of an tc10 Origin is:
   * DomainName | ServerName | DatasourceType |  DatasourceName | TimeOfCreationInMillis | OID | Branch | Revision | Iteration | Name
   *
   * An Example origin looks like:
   * zws| node-0| ilink-8 | test-ilink-server| 00011122234 | 888877788877| main | C | 4 | abc.asm
   *
   * @param name object name
   * @param uniqueID object oid
   * @param branch
   * @param revision
   * @param iteration
   */
  /**
   * Materialize origin.
   *
   * @param oid the oid
   * @param timeOfCreation the time of creation
   * @param name the name
   * @param version the version
   *
   * @return the origin
   *
   * @throws CanNotMaterialize the can not materialize
   */
  /*
  public static Origin materializeOrigin(String name, String oid, int version, long timeOfCreation) throws CanNotMaterialize {
    String domainName = "zws"; //this should come from actual routing information
    String serverName = "node-0"; //this should come from actual routing information
    String uniqueID = TC10RepositoryBase.materializeUniqueID(name, oid, version);
    Origin o = OriginMaker.materialize(domainName, serverName, Origin.FROM_TEAMCENTER_10, "TC10-Test-Server", timeOfCreation, uniqueID, null, null);
    return o;
  }
*/
  public static Origin materializeTC10Origin(String itemId, String revision, String datasetName, String datasetType, String fileName) throws CanNotMaterialize {
	    String domainName = "zws"; //this should come from actual routing information
	    String serverName = "node-0"; //this should come from actual routing information
	    String uniqueID = "";//TC10RepositoryBase.materializeUniqueID(itemId, revision, datasetName, datasetType, fileName);
	    TC10Origin o = (TC10Origin)OriginMaker.materialize(domainName, serverName, Origin.FROM_TEAMCENTER_10, "TC10-Test-Server", 0, uniqueID, null, null);
	    /*
	    o.setItemId(itemId);
	    o.setRevision(revision);
	    o.setDatasetName(datasetName);
	    o.setDatasetType(datasetType);
	    o.setFileName(fileName);
	    */
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
  public void searchTest(String hostname, String port, String username, String password, String searchCriteria)
  {
	  testSearching(hostname, port, username, password, searchCriteria);
  }

  //Option 2
  public void findByOriginTest(String hostname, String port, String username, String password, String uniqueSequence)
  {

	  {} //System.out.println("");
	  {} //System.out.println("-------Find By Origin Test------------------------");
	  TC10RepositoryBase tc10 = new TC10RepositoryBase();
	  tc10.setDomainName("zws");
	  tc10.setServerName("node-0");
	  tc10.setRepositoryName("TC-QA-server");
	  tc10.setHostName(hostname);
	  tc10.setPort(port);
	  tc10.setServicesPath("tc/services/PLMGatewayService");

	  try
	  {

		  RepositoryMetadataSource source = tc10.materializeMetadataSource();
		  QxContext ctx = new QxContext();
		  Authentication id = new Authentication(username, password);

		  tc10.login(id);

		  TC10ItemRevOrigin tcOrig = new TC10ItemRevOrigin( tc10.getDomainName(),
	    		  									        tc10.getServerName(),
	    		  									        tc10.getRepositoryName(),
	    		  									        uniqueSequence,
	    		  									        0,
	    		  									        null);

	      Metadata m = source.find(ctx, tcOrig, id);
	      PrintUtil.print(m);

		  tc10.logout(id);
	  }
	  catch (Exception e) {
		  {} //System.out.println(e.getMessage());
		  e.printStackTrace();
	  }
	  {} //System.out.println("");
	  {} //System.out.println("-------Find By Origin Test End--------------------");
  }

  //Option 3
  public void reportBOMTest(String hostname, String port, String username, String password, String uniqueSequence)
  {
	  {} //System.out.println("");
	  {} //System.out.println("-------Report BOM Test------------------------");
	  TC10RepositoryBase tc10 = new TC10RepositoryBase();
	  tc10.setDomainName("zws");
	  tc10.setServerName("node-0");
	  tc10.setRepositoryName("TC-QA-server");
	  tc10.setHostName(hostname);
	  tc10.setPort(port);
	  tc10.setServicesPath("tc/services/PLMGatewayService");

	  try
	  {

		  RepositoryStructureSource source = tc10.materializeStructureSource();
		  QxContext ctx = new QxContext();
		  Authentication id = new Authentication(username, password);

		  tc10.login(id);

		  TC10ItemRevOrigin tcOrig = new TC10ItemRevOrigin( tc10.getDomainName(),
			        										tc10.getServerName(),
			        										tc10.getRepositoryName(),
			        										uniqueSequence,
			        										0,
			        										null);

		  Metadata m = null;
      //m = source.reportBOM(ctx, tcOrig, "Latest Working", id);

		  {} //System.out.println("Root: " + m.getOrigin().toString() );

		  Collection children = m.getSubComponents();

		  Object childrenA[] = children.toArray();
		  for(int i=0; i<childrenA.length; i++)
		  {
			  MetadataSubComponent subm = (MetadataSubComponent)childrenA[i];
			  {} //System.out.println(i+": "+ subm.getOrigin().toString());
			  printChildren(subm, new Integer(i).toString());
		  }

		  tc10.logout(id);
	  }
	  catch (Exception e) {
		  {} //System.out.println(e.getMessage());
		  e.printStackTrace();
	  }
	  {} //System.out.println("");
	  {} //System.out.println("-------Report BOM Test End--------------------");
  }

  public void printChildren(MetadataSubComponent parent, String indent)
  {
	  Collection children = parent.getSubComponents();
	  if(children!=null)
	  {
		  Object childrenA[] = children.toArray();
		  for(int i=0; i<childrenA.length; i++)
		  {
			  MetadataSubComponent subm = (MetadataSubComponent)childrenA[i];
			  {} //System.out.println(indent+"."+i+": "+ subm.getOrigin().toString());
			  printChildren(subm, indent);
		  }
	  }
  }


  //Option 4
  public void downloadBinaryTest(String hostname, String port, String username, String password, String uniqueSequence)
  {
	  {} //System.out.println("");
	  {} //System.out.println("-------Download Binary Test------------------------");
	  TC10RepositoryBase tc10 = new TC10RepositoryBase();
	  tc10.setDomainName("zws");
	  tc10.setServerName("node-0");
	  tc10.setRepositoryName("TC-QA-server");
	  tc10.setHostName(hostname);
	  tc10.setPort(port);
	  tc10.setServicesPath("tc/services/PLMGatewayService");

	  try
	  {
		  RepositoryMetadataSource metaSrc = tc10.materializeMetadataSource();
		  RepositoryBinarySource binarySrc = tc10.materializeBinarySource();

		  QxContext ctx = new QxContext();
		  Authentication id = new Authentication(username, password);

		  tc10.login(id);

		  TC10ItemRevOrigin tcOrig = new TC10ItemRevOrigin( tc10.getDomainName(),
			        										tc10.getServerName(),
			        										tc10.getRepositoryName(),
			        										uniqueSequence,
			        										0,
			        										null);

		  Metadata m = metaSrc.find(ctx, tcOrig, id);

	      // Get File
		  Collection binaryCol = m.getBinaries();
		  if(binaryCol!=null)
		  {
{} //System.out.println("Number of Binary File: " + binaryCol.size());
			  Iterator it2 = binaryCol.iterator();
			  if(it2.hasNext())
			  {
				  TC10MetadataBinary bm = (TC10MetadataBinary)it2.next();
{} //System.out.println("Downloading...: " + bm.getOrigin());
          //+++ Need to update to use RemoteFile
				  /*File f = binarySrc.download(ctx, bm.getOrigin(), id);
				  PrintUtil.print(f);*/
				  {} //System.out.println("\n");
			  }
		  }

		  tc10.logout(id);
	  }
	  catch (Exception e) {
		  {} //System.out.println(e.getMessage());
		  e.printStackTrace();
	  }
	  {} //System.out.println("");
	  {} //System.out.println("-------Download Binary Test End--------------------");
  }



  /** The oid. */
  public static long oid = 88888888;

  /** The delim. */
  private static String delim = Origin.delim;
}
