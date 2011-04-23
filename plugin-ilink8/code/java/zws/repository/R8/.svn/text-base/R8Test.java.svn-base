/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 22, 2007
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
package zws.repository.R8;


import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.origin.R8Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.RepositoryBase;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.util.PrintUtil;
import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataFamilyInstance;
import zws.data.MetadataFamilyInstanceBase;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.exception.CanNotMaterialize;

import java.util.*;

public class R8Test {
  public R8Test() {
      repositoryClient = RepositoryClient.getClient();
  }

//windchill -classpath=C:\zws-dojo\Workspace\plugin-ilink8\out;C:\zws-dojo\Workspace\zws-client\out;C:\zws-dojo\Workspace\plugin-zws-interface zws.repository.R8.R8Test

  public static void main(String[] args) throws Exception {
    R8Test x = new R8Test();
    //x.testSearching();
    //x.testBinaryDownload();
    x.generateBom();
    //x.testFind();
    //x.reportReferences();
    //x.generateBom();
    // *search using criteria...
    // *get search results..
    // *pick one item from search results
    // *get origin of item...
    // *find by origin (obid)..
    // *download by origin (obid)..
    // +getBOM by Origin (obid)..
    // ++get dependency by Origin (obid)..
   }
/*
  PDMLink         Agile
  1.asm(b)        1.asm(b)
    2.prt(b)        2.prt(b)
    3.prt(b)        3.prt(b)
    i.prt(g)        i.prt(?)
*/
    // code - unit test - merge/integrate - system testing - deploy to Cisco - validate
    // code - unit test - merge/integrate -->
    // update our code to use origin
    //-- test against part, instance, generic
    //-- test against assy, FT asssy, and assy that refer to instances

  private Authentication auth() throws Exception {
    //Authentication auth = new Authentication("ilinkadmin", "ilinkadmin");
    //Authentication auth = new Authentication("admin", "agile");
    Authentication auth = new Authentication("user31", "user31");
    return auth;
  }
  private Repository getRepository() throws Exception {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "plm-rtp-002-d");
    //Properties.set(Names.SERVICE_FINDER_HOSTNAME, "designstate-0");
    RepositoryService c = RepositoryClient.getClient();
    Repository r = c.findRepository("ilink-8");

    return r;
  }

  public void testFind() throws Exception{
    Repository r = getRepository();
    QxContext runningCtx = new QxContext();

    String select;
    //select = "*";
    //select = "name,number,version,iteration,createdOn,CADName,state,container,quantity,unit,IBA|LIB_BB_MAX_Y,IBA|LIB_BB_MAX_X";
    select = "DESC, LIB_MKP1_X,LIB_MKP1_Y,LIB_MKP2_X,LIB_MKP2_Y,LIB_MKP3_X,LIB_MKP3_Y,LIB_MKP4_X,LIB_MKP4_Y,LIB_BB_MIN_X,LIB_BB_MIN_Y,LIB_BB_MAX_X,LIB_BB_MAX_Y,LIB_BB_HEIGHT,LIB_UNITS,LIB_PIN_TECH";
    //select = "*";
    runningCtx.set("select-attributes", select);
	  RepositoryMetadataSource source = r.materializeMetadataSource();
    String part = null;
    //part = "08-0367-02.PRT";
    part = "25-0598.prt";
    part = "29-3228-01.prt";
	  Metadata md = source.findLatest(runningCtx, part, auth());
	  {} //System.out.println(md);
  }

  public void testBinaryDownload() throws Exception {
    //Properties.set(Names.SERVICE_FINDER_HOSTNAME,"plm-rtp-002-d");
    Properties.set(Names.SERVICE_FINDER_HOSTNAME,"Designstate-0");
    RepositoryClient rc = RepositoryClient.getClient();
    RepositoryBase r = rc.findRepository("ilink-8");
	  //R8RepositoryBase r = localRepository();

    RepositoryMetadataSource source = r.materializeMetadataSource();
    RepositoryBinarySource bin = r.materializeBinarySource();
      QxContext ctx = new QxContext();
      ctx.set(QxContext.HOST_NAME, "mustafar.cisco.com");
      ctx.set(QxContext.PORT, "80");
      ctx.set("return_attributes", "name,number,versionInfo.identifier.versionId,state.state,iterationInfo.identifier.iterationId");

      /*r.setServicesPath("servlet/RPC?CLASS=com.infoengine.soap");
      r.setDownloadServicesPath("netmarkets/jsp/zws/downloadpdmcontent.jsp");
      r.setInstanceName("Windchill");
      r.setRemoteMethod("zws-ilink-interface");*/

      //+++ need to convert to RemoteFile
      //File f = bin.simulatedownload(runningCtx, "", auth());

      Metadata m = source.findLatest(ctx, "frame.asm", auth());
      {} //System.out.println("origin " + m.getOrigin());
      //bin.fetchDesignFiles(ctx, m.getOrigin(), auth());
  }

  public void testFindByOrigin() throws Exception{
	   	Repository r = getRepository();
	    QxContext runningCtx = new QxContext();
	    runningCtx.set("returnAttributes", "name,number,version,iteration,createdOn,CADName,state,container,quantity,unit");

	    R8Origin o = (R8Origin) OriginMaker.materialize("zws|node-0|ilink-8|PDMLink|-1|VR:wt.epm.EPMDocument:6379:906062161-1181842747437-835474-9-1-168-192@plm.najanaja.com|0000000001.ASM|null|A|4|0000000001.asm|N/A|N/A|N/A|N/A");

		  RepositoryMetadataSource source = r.materializeMetadataSource();
		  Metadata md = source.find(runningCtx, o, auth());
		  {} //System.out.println(md);
	  }

  public void testSearching() throws Exception{
    Repository r = getRepository();


		SearchAgent agent = r.materializeSearchAgent();

		agent.setAuthenticationToken(auth());
    agent.setSelect("name,number,version,iteration,creator,createdOn,CADName,state,containerName");


		String criteria = "( name=27-0699-01.PRT* )";

    //simple criteria: ( name='abc.prt' )
    //simple and:      ( name='abc.prt' + revision='B' )
    //simple or:       ( name='abc.prt' ) | (name='def.prt' )
    //compounded:      ( name='abc.prt' + revision='A' ) | (name='def.prt' + release='Pilot' )
    //criteria = "( iterationInfo.identifier.iterationId='2' + versionInfo.identifier.versionId='A' + state.state='INWORK' )";
    //String criteria = "( iteration='2' + versionInfo.identifier.versionId='A' + state.state='INWORK' )";

    //criteria = "( obid=VR:wt.epm.EPMDocument:6382 )";

		agent.initializeStorage(new Vector());
		agent.setCriteria(criteria);
		agent.search();

		System.out.print(agent.getResult());
  }


  public void reportDesignDependencies() throws Exception{
	    Repository r = getRepository();
			QxContext runningCtx = new QxContext();

			runningCtx.set("depth", "999999");
			runningCtx.set("returnAttributes", "name,number,version,iteration,createdOn,CADName,state,container,quantity,unit,suppressed,depType");
			runningCtx.set("ilinkDomain", "com.najanaja.plm");

			RepositoryStructureSource structure = r.materializeStructureSource();

			R8Origin o = (R8Origin) OriginMaker.materialize("zws|node-0|ilink-8|PDMLink|-1|VR:wt.epm.EPMDocument:6379:906062161-1181842747437-835474-9-1-168-192@plm.najanaja.com|0000000001.ASM|null|A|4|0000000001.asm|N/A|N/A|N/A|N/A");

			structure.reportDependencies(runningCtx, o, auth());
	  }


  public void generateBom() throws Exception{
	  Repository r = getRepository();
		QxContext runningCtx = new QxContext();
		RepositoryStructureSource structure = r.materializeStructureSource();
    RepositoryMetadataSource source = r.materializeMetadataSource();
    String name = null;
    Origin origin=null;
    Metadata md = null;
    //name = "DEMUX24_SLIM.ASM";
    //name = "73-6746-03.asm";
    name = "700-19127-02.asm";
    //name = "800-25518-01.asm";
    md = source.findLatest(runningCtx, name, auth());
    origin = md.getOrigin();
    Collection c= source.searchLatest(runningCtx, name, auth());
    System.out.println("");
    System.out.println(" c " + c);
//    String originString="zws|node-0|ilink-8|ilink-8|1111523876000|VR:wt.epm.EPMDocument:218214:782464935-1161317588600-27148073-55-207-18-172@mustafar.cisco.com|700-19127-02.ASM|700-19127-02.asm|XX|1|700-19127-02.asm|N/A|N/A|N/A|N/A";
    //origin = OriginMaker.materialize(originString);
    //md = source.find(runningCtx, origin, auth());
    //origin = md.getOrigin();
    runningCtx.set("depth", "999999");
    //runningCtx.set(Names.SELECT_ATTRIBUTES,"LIB_MKP1_X,LIB_MKP1_Y,LIB_MKP2_X,LIB_MKP2_Y,LIB_MKP3_X,LIB_MKP3_Y,LIB_MKP4_X,LIB_MKP4_Y,LIB_BB_MIN_X,LIB_BB_MIN_Y,LIB_BB_MAX_X,LIB_BB_MAX_Y,LIB_BB_HEIGHT,LIB_UNITS,LIB_PIN_TECH");
    Metadata latestBom = structure.reportLatestBOM(runningCtx, origin.getName(), auth());
    Metadata asStoredBom = structure.reportBOM(runningCtx, origin, auth());

    System.out.println("reportLatestBOM " + latestBom);
    System.out.println("reportBOM " + asStoredBom);
  }


  /*
   * Creates some R8Origins using random hard coded routing and identification information
   */
  public void testOrigin() {
    Origin o1=null, o2=null, o3=null, o4=null;
    {} //System.out.println("");
    {} //System.out.println("-------Origin Test------------------------");
    try {
      o1 = materializeOrigin("zws8", "8880", "br", "A", 8, 55550);
      {} //System.out.println(o1);

      o2 = materializeOrigin("zws9", "8881", "br", "B", 7, 55551);
      {} //System.out.println(o2);

      o3 = materializeOrigin("zwsA", "8882", "br", "C", 6, 55552);
      {} //System.out.println(o3);

      o4 = materializeOrigin("zwsB", "8883", "br", "D", 5, 55553);
      {} //System.out.println(o4);

    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("-------Origin Test End--------------------");
  }

  public void testMetadata() {
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
      o1 = materializeOrigin("zws8", "8880", "br", "A", 8, 55550);
      m1 = materializeMetadata(o1, x1);
      System.out.print(m1);

      o2 = materializeOrigin("zws9", "8881", "br", "B", 7, 55551);
      m2 = materializeMetadata(o2, x2);
      System.out.print(m2);

      o3 = materializeOrigin("zwsA", "8882", "br", "C", 6, 55552);
      m3 = materializeMetadata(o3, x3);
      System.out.print(m3);

      o4 = materializeOrigin("zwsB", "8883", "br", "D", 5, 55553);
      m4 = materializeMetadata(o4, x4);
      System.out.print(m4);
    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("-------Metadata Test End------------------");
  }

  public void testMetadataBOM() {
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
      o1 = materializeOrigin("zws8", "8880", "br", "A", 8, 55550);
      root = materializeMetadata(o1, x1);

      o21 = materializeOrigin("zws9", "8881", "br", "B", 7, 55551);
      child21 = materializeMetadataSubcomponent(o21, 21, x21);

      o31 = materializeOrigin("zwsA", "8882", "br", "C", 6, 55552);
      child31 = materializeMetadataSubcomponent(o31, 31, x31);

      o22 = materializeOrigin("zwsB", "8883", "br", "D", 5, 55553);
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
      o0 = materializeOrigin("zws8", "8880", "br", "A", 8, 55550);
      generic = materializeMetadata(o0, x1);

      o1 = materializeOrigin("zws9", "8881", "br", "B", 7, 55551);
      instance1 = materializeFamilyTableInstance(o1, x1);

      o2 = materializeOrigin("zwsA", "8882", "br", "C", 6, 55552);
      instance2 = materializeFamilyTableInstance(o2, x2);

      o3 = materializeOrigin("zwsB", "8883", "br", "D", 5, 55553);
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


  public void testSearching(String url, String username, String password, String name) {
	    {} //System.out.println("");
	    {} //System.out.println("-------Find Test------------------------");
	    /*R8RepositoryBase r8 = new R8RepositoryBase();
	    r8.setDomainName("zws");
	    r8.setServerName("node-0");
	    r8.setRepositoryName("TC-QA-server");*/

	    try {
        r8 = repositoryClient.findRepository("ilink-8");
	      RepositoryMetadataSource source = r8.materializeMetadataSource();
	      QxContext ctx = new QxContext();
	      ctx.set(ctx.HOST_NAME, "10.10.10.208");
	      ctx.set(ctx.PORT, "80");
	      //ctx.set("instance-name", "Windchill");
	      //ctx.set(ctx.SERVICES_PATH, "servlet/RPC?CLASS=com.infoengine.soap");
	      //ctx.set("remote-method", "zws-ilink-interface");
      //return "http://" + getHostName() +":" + getPort() +"/" + getInstanceName() + "/" + this.getServicesPath();

	      Authentication id = new Authentication(username, password);
	      Collection collection = source.searchLatest(ctx, name, id);
	      Iterator iterator = collection.iterator();
	      while(iterator.hasNext()){
	    	  Metadata m = (Metadata) iterator.next();
	          PrintUtil.print(m);
	      }
	    }
	    catch (Exception e) {
	      {} //System.out.println(e.getMessage());
	      e.printStackTrace();
	    }
	    {} //System.out.println("");
	    {} //System.out.println("-------Search Test End--------------------");
	  }

/*
  public void testSearching() {
    {} //System.out.println("");
    {} //System.out.println("-------Search Test------------------------");

    R8RepositoryBase r8 = new R8RepositoryBase();
    r8.setDomainName("zws");
    r8.setServerName("node-0");
    r8.setRepositoryName("ilink-QA-server");
    r8.setPort("80");
    r8.setServicesPath("servlet/RPC?CLASS=com.infoengine.soap");
    r8.setDownloadServicesPath("netmarkets/jsp/ssi/intralink/downloadpdmcontent.jsp");
    r8.setInstanceName("ilinkuser");



    //public final Metadata findLatest(final QxContext runtimeCtx, String name, Authentication id) throws Exception {

    try {
      RepositoryMetadataSource ms = r8.materializeMetadataSource();

      QxContext ctx = new QxContext();
      Authentication id = new Authentication("admin", "adming");
      ctx.set("SELECTED_ATTRIBUTES", "name,number,versionInfo.identifier.versionId,state.state");
      Metadata md = ms.findLatest(ctx, "HELLO.PRT", id);
      {} //System.out.println(md.toString());

      /*
      RepositoryBinarySource bin = r8.materializeBinarySource();
      File f = bin.download(ctx, "ac.prt", id);



      SearchAgent agent = r8.materializeSearchAgent();
      String c=null;
      //c = "(name=r-1.prt)"; // simple querry
      //c = "(name=r-* + rev=pilot)"; // compound query: +=and
      //c = "(name=r-* + rev=pilot) | (name=abc* + rev=released)"; // complex query:  +=and, |=or
      c = "(name=r-*)";
      agent.initializeStorage(new Vector());
      agent.setCriteria(c);
      agent.search();
      Collection r = agent.getResults();
      PrintUtil.print(r);
      *//*
    }
    catch (Exception e) {
      {} //System.out.println(e.getMessage());
      e.printStackTrace();
    }
    {} //System.out.println("");
    {} //System.out.println("-------Search Test End--------------------");
  }

  */

  /* Shows how to Create an R8Origin.
   * An origin provides the routing and identification information for an object.
   * Format of an R8 Origin is:
   * DomainName | ServerName | DatasourceType |  DatasourceName | TimeOfCreationInMillis | OID | Branch | Revision | Iteration | Name
   *
   * An Example origin looks like:
   * zws| node-0| ilink-8 | test-ilink-server| 00011122234 | 888877788877| main | C | 4 | abc.asm
   *
   * @param	name object name
   * @param uniqueID object oid
   * @param branch
   * @param revision
   * @param iteration
   */
  public static Origin materializeOrigin(String name, String oid, String branch, String revision, int iteration, long timeOfCreation) throws CanNotMaterialize {
    String domainName = "zws"; //this should come from actual routing information
    String serverName = "node-0"; //this should come from actual routing information
    //String uniqueID = R8RepositoryBase.materializeUiqueID(name, oid, branch, revision, iteration);
    //Origin o = OriginMaker.materialize(domainName, serverName, Origin.FROM_ILINK_8, Origin.FROM_ILINK_8, timeOfCreation, uniqueID, null, null);
    return null;
  }

  /* Shows how to generically model an item and its metadata
   *
   * @param origin object origin
   * @param atts object attribute values
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
  public static MetadataSubComponent materializeMetadataSubcomponent(Origin o, int quan, Map atts) {
    //A subcomponent is modeled the same as metadata, except the implementing class is different
    Metadata m = materializeMetadata(o, atts);
    MetadataSubComponentBase child = new MetadataSubComponentBase(m);
    child.setQuantity(quan);
    return child;
  }


  /* Shows how to generically model an item being recorded as an instance of a generic
   *
   * @param origin object origin
   * @param atts object attribute values
   */
  public static MetadataFamilyInstance materializeFamilyTableInstance(Origin o, Map atts) {
    //An instance is modeled the same as metadata, except the implementing class is different
    Metadata m = materializeMetadata(o, atts);
    MetadataFamilyInstanceBase instance = new MetadataFamilyInstanceBase(m);
    return instance;
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
    {} //System.out.println("-------Find Test------------------------");
    //R8RepositoryBase r8 = new R8RepositoryBase();
    /*r8.setDomainName("zws");
    r8.setServerName("node-0");
    r8.setRepositoryName("TC-QA-server");*/

    try {
      r8 = repositoryClient.findRepository("ilink-8");
      {} //System.out.println("r8 --> " + r8);
      {} //System.out.println("r8 --> " + r8.toString());
      RepositoryMetadataSource source = r8.materializeMetadataSource();
      QxContext ctx = new QxContext();
      ctx.set(ctx.HOST_NAME, "10.10.10.208");
      ctx.set(ctx.PORT, "80");
      //ctx.set("instance-name", "Windchill");
      //ctx.set(ctx.SERVICES_PATH, "servlet/RPC?CLASS=com.infoengine.soap");
      //ctx.set("remote-method", "zws-ilink-interface");
      //return "http://" + getHostName() +":" + getPort() +"/" + getInstanceName() + "/" + this.getServicesPath();

      Authentication id = new Authentication(username, password);
      Metadata m = source.findLatest(ctx, name, id);
      PrintUtil.print(m);
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

  public void testDownloadBinary(String url, String username, String password, String name) {
	    {} //System.out.println("");
	    {} //System.out.println("-------Find Test------------------------");
	    /*R8RepositoryBase r8 = new R8RepositoryBase();
	    r8.setDomainName("zws");
	    r8.setServerName("node-0");
	    r8.setRepositoryName("TC-QA-server");
*/
	    try {
        r8 = repositoryClient.findRepository("ilink-8");
	      RepositoryBinarySource source = r8.materializeBinarySource();
	      QxContext ctx = new QxContext();
	      ctx.set(ctx.HOST_NAME, "10.10.10.208");
	      ctx.set(ctx.PORT, "80");
	      //ctx.set("instance-name", "Windchill");
	      //ctx.set(ctx.DOWNLOAD_SERVICES_PATH, "netmarkets/jsp/zws/downloadpdmcontent.jsp");
	      //return "http://" + getHostName() +":" + getPort() +"/" + getInstanceName() + "/" + this.getServicesPath();

	      Authentication id = new Authentication(username, password);
        //+++ need to convert to RemoteFile
	      //File f = source.download(ctx, name, new File("c:/tmp"), id);
        //RemoteFile f = source.fetchNativeFile(ctx, origin, id);
	      //PrintUtil.print(f);
	    }
	    catch (Exception e) {
	      {} //System.out.println(e.getMessage());
	      e.printStackTrace();
	    }
	    {} //System.out.println("");
	    {} //System.out.println("-------Search Test End--------------------");
  }

  public void testILSearching() {
	    {} //System.out.println("");
	    {} //System.out.println("-------Search R8 Intralink Test------------------------");
	    try {
	    	//R8ILSearchAgent.queryMetaData();
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    {} //System.out.println("");
	    {} //System.out.println("-------Search R8 Intralink Test End--------------------");
	  }

  public void testILRetrieveContent() {
	    {} //System.out.println("");
	    {} //System.out.println("-------Retrieve Content R8 Intralink Test------------------------");
	    String number = "69-1776-01.PRT";
		String version = "A";
		String className = "wt.epm.EPMDocument";

		String url = "http://vm-ilink-8/Windchill/netmarkets/jsp/ssi/intralink/downloadpdmcontent.jsp";
		String username = "admin";
		String password = "agile";
	    try {
	    	R8ILRetrieveContentAgent.downloadContent(url, number, version, className, username, password);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    {} //System.out.println("");
	    {} //System.out.println("-------Search R8 Intralink Test End--------------------");
	  }


  public void reportReferences() throws Exception{
    String number = "69-1776-01.prt";
    Repository r = getRepository();
    QxContext runningCtx = new QxContext();
    RepositoryStructureSource structure = r.materializeStructureSource();
    RepositoryMetadataSource source = r.materializeMetadataSource();
    //String returnAttributes = "name,number,state.state,versionInfo.identifier.versionId,containerName,iterationInfo.identifier.iterationId,thePersistInfo.modifyStamp,wt.epm.EPMDocument,depType";
    //runningCtx.set("return_attributes", returnAttributes);
    //R8Origin o = (R8Origin) OriginMaker.materialize("zws|node-0|ilink-8|PDMLink|-1|VR:wt.epm.EPMDocument:78299|0000000001.ASM|null|A|4|0000000001.asm|N/A|N/A|N/A|N/A");
    Metadata data = source.findLatest(runningCtx, number, auth());
    Collection references = structure.reportDependencies(runningCtx, data.getOrigin(), auth());
    {} //System.out.println("References " + references);
  }

  public static long oid = 88888888;
  private static String delim = Origin.delim;
  RepositoryService repositoryClient = null;
  Repository r8 = null;
}
