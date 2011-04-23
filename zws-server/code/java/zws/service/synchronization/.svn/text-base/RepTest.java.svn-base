package zws.service.synchronization;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 31, 2004, 9:32 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.database.MySQL;
import zws.datasource.intralink.IntralinkSource;
import zws.origin.Origin;
import zws.util.KeyValue;
//impoer zws.util.Logwriter;
import zws.util.Pair;

import java.util.*;

public class RepTest {
  public RepTest() { }
  public static void main(String[] args) {
    RepTest test = new RepTest();
    test.run();
  }

  static MySQL db = new MySQL();
  public IntralinkSource ilink(String name, String host) {
    /*
    Properties.set(Names.EXE_ILINK_SEARCH, "C:\\ZWS\\bin\\native\\windows\\ptc\\search\\ilinksearch.bat");
    Properties.set(Names.EXE_ILINK_IMPORT, "C:\\ZWS\\bin\\native\\windows\\ptc\\import\\ilinkimport.bat");
    Properties.set(Names.EXE_ILINK_EXPORT, "C:\\ZWS\\bin\\native\\windows\\ptc\\export\\ilinkExport.bat");
    Properties.set(Names.EXE_ILINK_CHECKIN, "C:\\ZWS\\bin\\native\\windows\\ptc\\checkin\\ilinkcheckin.bat");
    Properties.set(Names.EXE_ILINK_CHECKOUT, "C:\\ZWS\\bin\\native\\windows\\ptc\\checkout\\ilinkcheckout.bat");
    Properties.set(Names.EXE_ILINK_DESTROY_WORKSPACE, "C:\\ZWS\\bin\\native\\windows\\ptc\\destroyWorkspace\\ilinkDestroyWorkspace.bat");
    Properties.set(Names.EXE_ILINK_SET_ATTRIBUTE, "C:\\ZWS\\bin\\native\\windows\\ptc\\setAttribute\\ilinksetattribute.bat");
     **/
    IntralinkSource ilink = new IntralinkSource();
    zws.Server.debugMode();

    ilink.setName(name);
    ilink.setDefaultUsername("INTRALINK");
    ilink.setDefaultPassword("INTRALINK");
    ilink.setBinPath("\\ptc\\"+host+"-proitkenv3.2\\bin");
    ilink.setILLIBPath("\\ptc\\"+host+"-proitkenv3.2\\pro_intlnk_tk\\i486_nt\\illib");
    ilink.setEXEToolkitEnv("env.bat");
    //ilink.setWorkspacePath("C:\\zws\\data\\workspace\\ilink\\"+host+"\\.proi");
    return ilink;
//    ilink0.setTNSNamesPath();
//    ilink0.setDataServerPassword();
//    ilink0.setDataServerUsername();
//    SearchAgent agent = new SearchAgent();
//    agent.setDatasource(ilink0);
//    agent.setCriteria("Name=zws*");
//    agent.search();
//    print (agent.getResults());
//    {}//Logwriter.printOnConsole("done");
  }
  public void run() {
  try{
    /*

    {}//Logwriter.printOnConsole("starting run...");
      Properties.set(Names.DEBUG_MODE, "true");
      Properties.set(Names.PATH_PACKAGE_ROOT, "\\zws\\data\\pkg2");
      Properties.add(Names.DOMAIN_NAME, "zws");
      Properties.add(Names.SERVER_NAME, "node-zero");
      Properties.add(Names.SYNCHRONIZATION_DATABASE, "synchLog");
      db.setHost("localhost");
      db.setName("synchLog");
      db.setDatabaseName("zwsSynchronizationLog");
      DB.add(db);
    FileSystemSource source0 = new FileSystemSource();
    FileSystemSource source1 = new FileSystemSource();
    FileSystemSource source2 = new FileSystemSource();
    FileSystemSource source3 = new FileSystemSource();
    FileSystemSource source4 = new FileSystemSource();
    IntralinkSource zwsNT4 = ilink("ilink-zero", "zws-nt4");
    IntralinkSource zeroDEP2 = ilink("ilink-zero", "zero-dep2");
    source0.setName("source-zero");
    source1.setName("source-one");
    source2.setName("source-two");
    source3.setName("source-three");
    source4.setName("source-four");
    zwsNT4.setName("zwsNT4");
    zeroDEP2.setName("zeroDEP2");
    zeroDEP2.setLowerCasedFilenames(false);
    zeroDEP2.setUpperCasedFilenames(false);
    source0.setRoot("C:\\temp\\reptest\\source0");
    source1.setRoot("C:\\temp\\reptest\\source1");
    source2.setRoot("C:\\temp\\reptest\\source2");
    source3.setRoot("C:\\temp\\reptest\\source3");
    source4.setRoot("C:\\temp\\reptest\\source4");

    DataSpaceBase space0 = new DataSpaceBase();
    DataSpaceBase space1 = new DataSpaceBase();
    DataSpaceBase space2 = new DataSpaceBase();
    DataSpaceBase space3 = new DataSpaceBase();
    DataSpaceBase space4 = new DataSpaceBase();
    DataSpaceBase zwsNT4Space = new DataSpaceBase();
    DataSpaceBase zeroDEP2Space = new DataSpaceBase();
    space0.setDatasource(source0);
    space1.setDatasource(source1);
    space2.setDatasource(source2);
    space3.setDatasource(source3);
    space4.setDatasource(source4);
    zwsNT4Space.setDatasource(zwsNT4);
    zeroDEP2Space.setDatasource(zeroDEP2);
    space0.setName("space-" + source0.getName());
    space1.setName("space-" + source1.getName());
    space2.setName("space-" + source2.getName());
    space3.setName("space-" + source3.getName());
    space4.setName("space-" + source4.getName());
    zwsNT4Space.setName("space-" + zwsNT4.getName());
    zeroDEP2Space.setName("space-" + zeroDEP2.getName());

    //Collection nt4 = zwsNT4Space.listComponents("ReplicationSource",true,null);
    //Collection dep2 = zeroDEP2Space.listComponents("RepSource",true,null);
    print("---------------------------------------------");
    //print (nt4);
    print("---------------------------------------------");
    //print(dep2);
    print("---------------------------------------------");
    //Origin oNT4 = ((Metadata)nt4.toArray()[0]).getOrigin();
    //Origin oDEP2 = ((Metadata)dep2.toArray()[0]).getOrigin();
    //zwsNT4.checkout(oNT4,"tmp",null);
    //zeroDEP2.checkout(oDEP2,"tmp",null);
*/
    /*
    BroadcastPolicy oneNT4Way = new BroadcastPolicy();
    oneNT4Way.setSourceSpace(zwsNT4Space);
    oneNT4Way.addTargetSpace(space1);
    SynchronizationSvc.synchronizeSpace(oneNT4Way);

    BroadcastPolicy oneDEP2Way = new BroadcastPolicy();
    oneDEP2Way.setSourceSpace(zeroDEP2Space);
    oneDEP2Way.addTargetSpace(space2);
    SynchronizationSvc.synchronizeSpace(oneDEP2Way);
    */
    /*
    BroadcastPolicy oneWay = new BroadcastPolicy();
    oneWay.setSourceSpace(space0);
    oneWay.addTargetSpace(space1);
    oneWay.addTargetSpace(space2);
    //oneWay.addTargetSpace(space5);
    SynchronizationSvc.synchronizeSpace(oneWay);
    */
   /*
    IntralinkDataSpace space0 = new IntralinkDataSpace();
    space0.setDatasource(ilink0);
    space0.setName("ilink-space-0");
    space0.chooseRevision("F");
    //space0.chooseReleaseLevel("");
    space0.chooseDocumentType("pdf");
    space0.search();
    print(space0.getResults());
    */
/*
    BroadcastPolicy oneWay2Ilink = new BroadcastPolicy();
    oneWay2Ilink .setSource(source0);
    oneWay2Ilink .addTarget(ilink0);
    SynchronizationSvc.synchronize(oneWay2Ilink );
 */
    /*
    BroadcastPolicy oneWay = new BroadcastPolicy();
    oneWay.setSource(source0);
    oneWay.addTarget(source1);
    oneWay.addTarget(source2);
//    oneWay.addTarget(ilink0);
    SynchronizationSvc.synchronize(oneWay);
    */
  /*
    MultiSynchPolicy twoWay = new MultiSynchPolicy();
    twoWay.addSource(source0);
    twoWay.addSource(source1);
    twoWay.addSource(source2);
    //twoWay.addSource(source3);
    //twoWay.addSource(source4);
    SynchronizationSvc.synchronize(twoWay);
    */

    //print(getOrigins(source0.listComponents(null, null)));
    //print(getOrigins(source1.listComponents(null, null)));
    //Map originPairs = matchLatestOrigins(source0.listComponents(null, null), source1.listComponents(null, null));
    //print (originPairs);
    //DatasourceSynchronizer synker = new DatasourceSynchronizer();
    //synker.setMetadataList0(source0.listComponents(null, null));
    //synker.setMetadataList1(source1.listComponents(null, null));
    //synker.execute();
    /*
    Collection updates0 = getAvailableUpdates(source0, "node", source1.getName());
    Collection updates1 = getAvailableUpdates(source1, "node", source0.getName());
    //print (updates0);

    PackageSource pkg0 = createPackage(source0, "zero-updates", updates0);
    PackageSource pkg1 = createPackage(source1, "one-updates", updates1);
    //print(pkg.listComponents(null, null));
    source1.synchronizePackage(pkg0);
    source0.synchronizePackage(pkg1);
    */
    {}//Logwriter.printOnConsole("!done.");
    //Collection origins0 = getOrigins(source0.listComponents(null, null)));
    //Collection origins1 = getOrigins(source1.listComponents(null, null)));
  }
  catch (Exception e) { e.printStackTrace(); }
  }

/*
  public PackageSource createPackage(FileSystemSource src, String pkgName, Collection updates) throws Exception {
    PackageSource pkg = new PackageSource();
    pkg.setName(pkgName);
    Iterator i = updates.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      pkg.addMetadata(m, null);
      pkg.storeMetadata(m, null);
      Origin o = m.getOrigin();
      InputStream stream = src.findBinary(m.getOrigin(),null);
      long len = src.getBinaryLength(m.getOrigin(), null);
      pkg.storeBinary(o,stream,len,null);
    }
    return pkg;
  }

  public Collection getAvailableUpdates(FileSystemSource src0, String toNode, String toDatasource) throws Exception {
    Collection c = src0.listComponents(null, true, null);
    if (null==c || c.size()<1) return null;
    Collection updates = new Vector();
    Iterator i = c.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      if (!SynchronizationSvc.isSynchronizedToDatasource(m.getOrigin(), toNode, toDatasource)) updates.add(m);
    }
    return updates;
  }
  */
  public boolean empty(Collection c) { if (null==c || c.size()<1) return true; else return false; }

  public Collection getOrigins(Collection c) {
    Collection o = new Vector();
    Iterator i = c.iterator();
    while (i.hasNext()) o.add(((Metadata)i.next()).getOrigin());
    return o;
  }

  public Map matchLatestOrigins(Collection c0, Collection c1) {
    Map m0 = latestOrigins(c0);
    Map m1 = latestOrigins(c1);
    Origin o0, o1;
    Map match = new HashMap();
    Iterator i = m0.keySet().iterator();
    String key;
    while (i.hasNext()) {
      key = (String)i.next();
      o0 = (Origin)m1.get(key);
      o1 = (Origin)m1.get(key);
      if (null!=o0 && null !=o1) match.put(key, new Pair(o0, o1));
    }
    return match;
  }

  public Map latestOrigins(Collection c) {
    Map origins = new HashMap();
    Metadata m;
    Origin o;
    Iterator i = c.iterator();
    while (i.hasNext()) {
      m = (Metadata)i.next();
      o = (Origin)origins.get(m.getName());
      if (null==o) origins.put(m.getName(), m.getOrigin());
      else {
        if (o.isEarlier(m.getOrigin())) origins.put(m.getName().toLowerCase(), m.getOrigin());
      }
    }
    return origins;
  }

  public static void print(String s) { System.out.print(s); }
  public static void print(KeyValue pair) { System.out.print(pair.toXML()); }
  public static void print(Object o) { print(o.toString()); }
  public static void println(String s) { {}//Logwriter.printOnConsole(s); }
    
  }
  public static void println(Object o) { println(o.toString()); }
  public static void print(Collection c) {
    if (null==c) print("Collection is NULL");
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
