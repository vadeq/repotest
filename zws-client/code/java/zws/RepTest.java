package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 16, 2004, 2:41 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.application.Properties;
import zws.datasource.ProxyEJBSearchAgent;
import zws.mapping.MetadataMappingInstructions;
import zws.mapping.attribute.*;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.replication.policy.BroadcastPolicy;
import zws.service.packaging.PackagingService;
import zws.service.replication.policy.ReplicationPolicyService;
import zws.space.DataSpace;
import zws.space.filesystem.FileSystemDataSpace;
import zws.space.intralink.IntralinkDataSpace;
import zws.util.KeyValue;
import zws.util.PrintUtil;

import java.util.*;

public class RepTest {
  public RepTest() { }
  public static void main(String[] args) {
    RepTest test = new RepTest();
    init();
    {} //System.out.println("starting..");
    long snap = System.currentTimeMillis();
    test.run();
    {} //System.out.println("done!");
    snap = System.currentTimeMillis()-snap;
    {} //System.out.println("Run Took " + snap/1000 + " seconds");
  }
  
  public void run() {
    try {
      load(1);
      load(0);
      replicate();
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public static void init() {
    Properties.set(Names.DOMAIN_NAME, "zwait");
    Properties.set(Names.SERVER_NAME, "DesignState-node-0");
    Properties.set(Names.SYNCHRONIZATION_DATABASE, "zws-synch-db");
  }
  
  private void load(int node) throws Exception {
   zws.service.PrototypeService service = zws.service.EJBLocator.findService("DesignState-node-"+node);
   service.load();
  }
  private void reload(int node) throws Exception {
   zws.service.PrototypeService service = zws.service.EJBLocator.findService("DesignState-node-"+node);
   service.reload();
  }
  private void unload(int node) throws Exception {
   zws.service.PrototypeService service = zws.service.EJBLocator.findService("DesignState-node-"+node);
   service.unloadAll();
  }
  
  private void replicate() throws Exception {
    ReplicationPolicyService service = zws.service.replication.policy.EJBLocator.findService("DesignState-node-0");
    PrintUtil.println(service.getPolicies());
    BroadcastPolicy oneWay = (BroadcastPolicy)service.getPolicy("Library Distribution");
    oneWay.getSourceSpace().getContext().dump(System.out);//getMappingSet().;
    Replicator replicator = Replicator.getClient("DesignState-node-0");
    replicator.replicate(oneWay);
  }
  
  private void synchronizePackage() throws Exception {
    ReplicationPolicyService service = zws.service.replication.policy.EJBLocator.findService("DesignState-node-0");
    PrintUtil.println(service.getPolicies());
    BroadcastPolicy oneWay = (BroadcastPolicy)service.getPolicy("Library Distribution");
    oneWay.getSourceSpace().getContext().dump(System.out);//getMappingSet().;
    
    Replicator replicator = Replicator.getClient("DesignState-node-0");
//    replicator.synchronizePackage(oneWay);
  }
  
  public void run_old() {
    try {
      
      //Configurator.load();
      /*
      String o = "zwait|DesignState-node-0|"+Origin.FROM_ILINK+"|dep2-ilink|0|main|A|0|100146.prt";
      zws.origin.IntralinkOrigin origin =  (zws.origin.IntralinkOrigin) zws.origin.OriginMaker.materialize(o);
      zws.datasource.intralink.IntralinkSource source = (zws.datasource.intralink.IntralinkSource)zws.service.datasource.DatasourceSvc.find("dep2-ilink");
      long time = System.currentTimeMillis();
      try { for (int idx=0; idx <1000; idx++) PrintUtil.println(idx+": "+source.getDataServer().lookupTimeOfCreation(origin)); }
      catch (java.sql.SQLException e) { PrintUtil.println(e.getMessage()); }
      float d = (System.currentTimeMillis()-time)/1000;
      {} //System.out.println("duration: " + d);
      */      
      zws.service.space.DataSpaceService service = zws.service.space.EJBLocator.findService("DesignState-node-0");
      DataSpace ilink = service.find("dep2-ilink");
      DataSpace fs = service.find("dep2-fs");
      PrintUtil.println(ilink);
      PrintUtil.println(fs);
      PrintUtil.println(service.findAll());
      PrintUtil.println(service.getAvailableUpdates(ilink, fs));
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void run__() {
    try {
      {} //System.out.println("starting..");
      PackagingService svc = zws.service.packaging.EJBLocator.findService("DesignState-node-1");
//      svc.retrievePackage("DesignState-node-0", "newpack2");
    }
    catch (Exception e) { e.printStackTrace(); }
    {} //System.out.println("done..");
  }
  
  public void run4() {
    try {
      FileSystemDataSpace s = new FileSystemDataSpace();
      s.setName("hi");
//      zws.service.space.DataSpaceSvc.add(s);
      reload(1);
//    reload(0);
      replicate();
      /*
      PrintUtil.println("Loaded Configs");
      

      DataSpaceService spaceService = zws.service.space.EJBLocator.findService("DesignState-node-0");
      PrintUtil.println(spaceService.findAll());
      Iterator i = spaceService.findAll().iterator();
      while (i.hasNext()) PrintUtil.println(((DataSpace)i.next()).getName());

      PrintUtil.println(service.getPolicies());
      i = service.getPolicies().iterator();
      while (i.hasNext()) PrintUtil.println(((ReplicationPolicy)i.next()).getName());
     PrintUtil.println(((DataSpaceProxy)oneWay.getSourceSpace()).getRepositoryRoute()); //.getActualSpace().getDatasourceName()); // 
     
     Replicator replicator = new Replicator("DesignState-node-0");
     replicator.replicate(oneWay);
//      PrintUtil.println(oneWay.getSourceSpace().getRepositoryRout());
      */
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  public void run3() {
    try {
      zws.IntralinkAccess ilink = zws.IntralinkAccess.getAccess();
      Origin o ;
      /*
      o = OriginMaker.materialize("zwait|DesignState-node-1|ilink|nt4-ilink|1093165500000|main|B|0|gearbox_motor.prt");
      {} //System.out.println(ilink.find(o,null));
      o = OriginMaker.materialize("zwait|DesignState-node-1|ilink|nt4-ilink|1093165500000|main|B|1|gearbox_motor.prt");
      {} //System.out.println(ilink.find(o,null));
      o = OriginMaker.materialize("zwait|DesignState-node-1|ilink|nt4-ilink|1093165500000|main|B|2|gearbox_motor.prt");
      {} //System.out.println(ilink.find(o,null));
      o = OriginMaker.materialize("zwait|DesignState-node-1|ilink|nt4-ilink|0|main|B|0|gearbox.asm");
      {} //System.out.println(ilink.find(o,null));
      {} //System.out.println(ilink.getBill(o).structuredXML());
      o = OriginMaker.materialize("zwait|DesignState-node-1|ilink|nt4-ilink|0|main|B|1|gearbox.asm");
      {} //System.out.println(ilink.find(o,null));
      {} //System.out.println(ilink.getBill(o).structuredXML());
      */

      o = OriginMaker.materialize("zwait|DesignState-node-0|ilink|dep2-ilink|0|main|C|0|gearbox.asm");
      {} //System.out.println(ilink.reportBill(o, null).structuredXML());
      o = OriginMaker.materialize("zwait|DesignState-node-0|ilink|dep2-ilink|0|main|C|1|gearbox.asm");
      {} //System.out.println(ilink.reportBill(o, null).structuredXML());
      o = OriginMaker.materialize("zwait|DesignState-node-0|ilink|dep2-ilink|0|main|C|2|gearbox.asm");
      {} //System.out.println(ilink.reportBill(o, null).structuredXML());
      o = OriginMaker.materialize("zwait|DesignState-node-0|ilink|dep2-ilink|0|main|C|3|gearbox.asm");
      {} //System.out.println(ilink.reportBill(o, null).structuredXML());
      
      
//      {} //System.out.println(o);
//      {} //System.out.println(ilink.getBill(o).structuredXML());
      //print (ilink.listWorkspaceContents("DesignState-node-0", "dep2-ilink", "PackageSpace", null));
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  public void run5() {
    try {
//      Replicator rep = new Replicator();
//      rep.setServerName(Server.getName());
/*
      FileSystemDataSpace space0 = new FileSystemDataSpace();
      space0.setDomainName(Server.getDomainName());
      space0.setServerName("DesignState-node-0");
      space0.setDatasourceName("source-0");
      space0.setName("space-0");
      
      FileSystemDataSpace space1 = new FileSystemDataSpace();
      space1.setDomainName(Server.getDomainName());
      space1.setServerName("DesignState-node-1");
      space1.setDatasourceName("source-1");
      space1.setName("space-1");
  */    
      
      IntralinkDataSpace ispace0 = new IntralinkDataSpace();
      ispace0.setDomainName(Server.getDomainName());
      ispace0.setServerName("DesignState-node-0");
      ispace0.setDatasourceName("dep2-ilink");
      ispace0.setName("ispace-0");
      ispace0.setChooseOnlyBinaries(true);
      ispace0.setSpaceCriteria("[Name=*.prt & Folder=*RepTarget] | [Name=*.prt & Folder=*Fam_Tbl] | [Name=* & Folder=*Assembly] ");
      
      IntralinkDataSpace ispace1 = new IntralinkDataSpace();
      ispace1.setDomainName(Server.getDomainName());
      ispace1.setServerName("DesignState-node-1");
      ispace1.setDatasourceName("nt4-ilink");
      ispace1.setName("ispace-1");
      ispace1.setChooseOnlyBinaries(true);
      ispace1.setSpaceCriteria("[Name=*.prt & Folder=*NT4_Rep] | [Name=*.prt & Folder=*Fam_Tbl] | [Name=* & Folder=*Assembly] ");
//      ispace1.setSpaceCriteria("[Name=*.prt & Folder=*NT4_Rep] | [Name=*.prt & Folder=*Fam_Tbl]");

//     zws.IntralinkAccess a = zws.IntralinkAccess.getAccess();
//     print(a.listWorkspaceContents(ispace0.getServerName(),ispace0.getDatasourceName(), "tmp", null));
      /*
      Collection xyz = ispace1.getAvailableUpdates(space0);
      print(xyz);
      zws.origin.Origin o = ((zws.data.Metadata)xyz.toArray()[0]).getOrigin();
      a.checkout(o,"newSpace",null);
*/

      //BroadcastPolicy oneWay = new BroadcastPolicy();
      println(ispace1.getDataRoute());
      println(ispace0.getDataRoute());
//      print(ispace1.getAvailableUpdates(ispace0));
 //     oneWay.setSourceSpace(ispace1);
 //     oneWay.addTargetSpace(ispace0);
      
      //need to reoder thesE !!!
      MetadataMappingInstructions maps;
      maps = createMappingFrom1();
      ispace0.add(maps);
      maps = createMappingFrom0();
      ispace1.add(maps);      
      
 //     rep.replicate(oneWay);
      /*
      MultiSynchPolicy twoWay = new MultiSynchPolicy();
      print(ispace1.getAvailableUpdates(ispace0));
      print(ispace0.getAvailableUpdates(ispace1));
      twoWay.addSourceSpace(ispace0);
      twoWay.addSourceSpace(ispace1);
      rep.replicate(twoWay);
      */
      
      /*
      try{ 
        DataSpaceService service;
        service = EJBLocator.findService("DesignState-node-1");
        service.add(space0);
      }
      catch (Exception e) { ; }
      try{ 
        DataSpaceService service;
        service = EJBLocator.findService(Server.getName());
        service.add(space1);
      }
      catch (Exception e) { ; }
      */
      /*
      try{ 
        zws.service.PrototypeService service;
        service = zws.service.EJBLocator.findService("DesignState-node-0");
        service.reload();
      }
      catch (Exception e) { ; }

      try{ 
        zws.service.PrototypeService service;
        service = zws.service.EJBLocator.findService("DesignState-node-1");
        service.reload();
      }
      catch (Exception e) { e.printStackTrace(); }
      BroadcastPolicy oneWay = new BroadcastPolicy();
      oneWay.setSourceSpace(space0);
      oneWay.addTargetSpace(space1);
      rep.replicate(oneWay);
*/      
      //print(space1.getAvailableUpdates(space0));
      //print(space0.getAvailableUpdates(space1));
      
      //space1.createPackage("newpack", space1.getAvailableUpdates(space0), null);
      //space0.createPackage("newpack2", space0.getAvailableUpdates(space1), null);
    }
    catch ( Exception e) { e.printStackTrace(); }
  }
  
  
  private MetadataMappingInstructions createMappingFrom1() {
      MetadataMappingInstructions maps = new MetadataMappingInstructions();
      
      MetadataAttributeMapping cMap;
      
      cMap = new CopyAttribute();
      cMap.setFieldName("Branch");
      maps.add(cMap);
      
      cMap = new CopyAttribute();
      cMap.setFieldName("Title");
      maps.add(cMap);
      
      cMap = new RenameAttribute();
      cMap.setFieldName("Partnumber");
      cMap.setNewFieldName("Part_Num");
      maps.add(cMap);
      
      EnumerationMapping eMap = new EnumerationMapping();
      eMap.setFieldName("Revision");
      eMap.addValueMapping("A", "G");
      eMap.addValueMapping("B", "C");
      eMap.addValueMapping("C ", "D");
      eMap.addValueMapping("D", "E");
      eMap.addValueMapping(" E  ", "F");
      eMap.setCopyIfUnmapped(true);
      maps.add(eMap);
      
      eMap = new EnumerationMapping();
      eMap.setFieldName("Release-Level");
      eMap.addValueMapping("In Approval", "In Progress");
      eMap.setCopyIfUnmapped(true);
      maps.add(eMap);
      
      LocationMapping lMap = new LocationMapping();
      lMap.setFieldName("Folder");
      lMap.addRootLocationMapping("Root Folder/NT4_Rep","Root Folder/RepTarget");
      maps.add(lMap);
      return maps;
  }

  private MetadataMappingInstructions createMappingFrom0() {
      MetadataMappingInstructions maps = new MetadataMappingInstructions();
      
      MetadataAttributeMapping cMap;
      
      cMap = new CopyAttribute();
      cMap.setFieldName("Branch");
      maps.add(cMap);
      
      cMap = new CopyAttribute();
      cMap.setFieldName("Title");
      maps.add(cMap);
      
      cMap = new RenameAttribute();
      cMap.setFieldName("Part_Num");
      cMap.setNewFieldName("Partnumber");
      maps.add(cMap);
      
      EnumerationMapping eMap = new EnumerationMapping();
      eMap.setFieldName("Revision");
      eMap.addValueMapping("A", "A");
      eMap.addValueMapping("B", "B");
      eMap.addValueMapping("C", "B");
      eMap.addValueMapping("D ", "C");
      eMap.addValueMapping("E", "D");
      eMap.addValueMapping(" F  ", "E");
      eMap.addValueMapping("G", "G");
      eMap.setCopyIfUnmapped(true);
      maps.add(eMap);
      
      eMap = new EnumerationMapping();
      eMap.setFieldName("Release-Level");
      eMap.addValueMapping("In Approval", "In Progress");
      eMap.setCopyIfUnmapped(true);
      maps.add(eMap);
      
      LocationMapping lMap = new LocationMapping();
      lMap.setFieldName("Folder");
      lMap.addRootLocationMapping("Root Folder/RepTarget","Root Folder/NT4_Rep");
      maps.add(lMap);
      return maps;
  }
  public void run_() {
    try {
      ProxyEJBSearchAgent agentDep2 = new ProxyEJBSearchAgent();
      agentDep2.setName("proxy-agent-dep2");
      agentDep2.setServerName("DesignState-node-1");
      agentDep2.setRemoteAgentName("agent-dep2");
      agentDep2.setCriteria("[ name=* ]");
      agentDep2.search();
      print(agentDep2.getResults());

      ProxyEJBSearchAgent agent88 = new ProxyEJBSearchAgent();
      agent88.setName("proxy-agent-88");
      agent88.setServerName("DesignState-node-0");
      agent88.setRemoteAgentName("agent-88");
      agent88.setCriteria("[ name=* ]");
      agent88.search();
      print(agent88.getResults());
    }
    catch (Exception e) {e.printStackTrace(); }
  }

  public static void print(String s) { System.out.print(s); }
  public static void print(KeyValue pair) { System.out.print(pair.toXML()); }
  public static void print(Object o) { print(o.toString()); }
  public static void println(String s) { {} //System.out.println(s); }
    
  }
  public static void println(Object o) { println(o.toString()); }
  public static void print(Collection c) {
    if (null==c) { print("{ NULL }"); return; }
    println("{");
    Iterator i = c.iterator();
    if (i.hasNext()) { print("--"); println(i.next()); }
    while (i.hasNext()) {
      print("--"); 
      println(i.next());
    }
    println("}");
  }
  public static void printPairs(Collection c) {
    if (null==c) print("Collection is NULL");
    print("{");
    Iterator i = c.iterator();
    KeyValue pair;
    if (i.hasNext()) {
      pair = (KeyValue)i.next();
      print("["+pair.getKey()+"."+pair.getValue()+"]");
    }
    while (i.hasNext()) {
      print(", ");
      pair = (KeyValue)i.next();
      print("["+pair.getKey()+"."+pair.getValue()+"]");
    }
    println("}");
  }
  public static void println(Collection c) {
    print(c);
    println(" ");
  }
  public static void print(Map m) {
    if (null==m) print("Map is NULL");
    println("==============================");
    Iterator i = m.keySet().iterator();
    String k;
    while (i.hasNext()) {
      k=(String)i.next();
      print(k + " => "); println(m.get(k));
    }
    println("==============================");
  }

}
