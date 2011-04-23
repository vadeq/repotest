package zws.service.synchronization;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 30, 2004, 7:23 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class SynchTest {
  /*
  public SynchTest() { }
  public static void main(String[] args) { 
    SynchTest test = new SynchTest();
    test.run();
  }  
  static MySQL db = new MySQL();
  public void run() {
    try {
      Properties.add(Names.SYNCHRONIZATION_DATABASE, "synchLog");
      db.setHost("localhost");
      db.setName("synchLog");
      db.setDatabaseName("zwsSynchronizationLog");
      DB.add(db);
      synchronize();
      analyze();
    }
    catch (Exception e) { e.printStackTrace(); }
    finally { {}//Logwriter.printOnConsole("done"); }
  }
 
  Calendar c99, c01, c02, c03, c04, c05;
  Origin a123n, a123, a123a, a456, a789, aABC, aDEF, b123, b123a, b456, b789, bABC, bDEF, c123, c456, c789, cABC, cDEF;
  Collection set123 = new Vector();
  Collection set456 = new Vector();
  Collection set789 = new Vector();
  Collection setABC = new Vector();
  Collection setDEF = new Vector();
  private void synchronize() throws Exception {
    c99 = time(1999,2,8,4,4,4);
    c01 = time(2001,4,8,4,4,4);
    c02 = time(2002,1,8,4,4,4);
    c03 = time(2003,1,8,4,4,4);
    c04 = time(2004,8,2,8,8,8);
    c05 = time(2005,10,15,7,7,7);
    
    a123 = origin("zero", "00", "main", "123.prt", "A", "8",  c99);
    a123a = origin("zero", "00", "main", "123.prt", "A", "9",  c99);
    a123n = origin("zero", "11", "main", "123.prt", "C", "7",  c99);
    a456 = origin("zero", "00", "main", "456.prt", "B", "9",  c99);
    a789 = origin("zero", "00", "main", "789.prt", "C", "10",  c01);
    aABC = origin("zero", "00", "main", "ABC.prt", "E", "5",  c04);
    aDEF = origin("zero", "00", "main", "DEF.prt", "H", "7",  c04);
    
    b123 = origin("one", "zz", "main", "123.prt", "X0", "8",  c99);
    b123a = origin("one", "zz", "main", "123.prt", "X0", "9",  c99);
    b456 = origin("one", "zz", "main", "456.prt", "X0", "9",  c99);
    b789 = origin("one", "zz", "main", "789.prt", "X2", "9",  c01);
    bABC = origin("one", "zz", "main", "ABC.prt", "X10", "0",  c04);
    bDEF = origin("one", "zz", "main", "DEF.prt", "X12", "0",  c04);
    
    c123 = origin("two", "xx", "main", "123.prt", "tI", "8",  c99);
    c456 = origin("two", "xx", "main", "456.prt", "tJ", "9",  c99);
    c789 = origin("two", "xx", "main", "789.prt", "tT", "9",  c01);
    cABC = origin("two", "xx", "main", "ABC.prt", "tY", "0",  c04);
    cDEF = origin("two", "xx", "main", "DEF.prt", "tY", "0",  c04);
    
    set123.add(a123);
    set123.add(b123);
    set123.add(c123);
    set456.add(a456);
    set456.add(b456);
    set456.add(c456);
    set789.add(a789);
    set789.add(b789);
    set789.add(c789);
    setABC.add(aABC);
    setABC.add(bABC);
    setABC.add(cABC);
    setDEF.add(aDEF);
    setDEF.add(bDEF);
    setDEF.add(cDEF);
    
    sync(a123a, b123a);
    sync(a123a, a123n);
    sync(set123);
    sync(set456);
    sync(set789);
   }
  
   private void analyze() throws Exception {
     checkSync();
     find();
   }
   
   private void find() throws Exception {
    print(SynchronizationSvc.findAllSynchronizationRecords("123.prt"));
    print(SynchronizationSvc.findAllSynchronizationRecords(a123));
    print(SynchronizationSvc.findNameSynchronization("zero", "00", "123.prt"));
    print(SynchronizationSvc.findNameSynchronization("one", "zz", "123.prt"));
    print(SynchronizationSvc.findNameSynchronization("two", "xx", "123.prt"));
    print(SynchronizationSvc.findDatasourceSynchronization("zero", "00"));
    print(SynchronizationSvc.findNodeSynchronization("zero"));
    println(SynchronizationSvc.lastNameSynchronization("123.prt"));
    println(SynchronizationSvc.lastDatasourceSynchronization("zero", "00"));
    println(SynchronizationSvc.lastDatasourceSynchronization("zero", "11"));
    println(SynchronizationSvc.lastDatasourceSynchronization("one", "zz"));
    println(SynchronizationSvc.lastDatasourceSynchronization("two", "xx"));
    println(SynchronizationSvc.lastNodeSynchronization("zero"));
    println(SynchronizationSvc.lastNodeSynchronization("one"));
    println(SynchronizationSvc.lastNodeSynchronization("two"));
   }
   
   private void checkSync() throws Exception {
     isInSync(a123,b123);
     isInSync(a123,c123);
     isInSync(c123,b123);
     
     isInSync(b123,a123);
     isInSync(c123,a123);
     isInSync(b123,c123);
     
     isInSync(b456,c123);

     isInSync(set123);
     isInSync(set456);
     isInSync(set789);
     
     isInSync(setABC);
     Collection c = new Vector();
     c.add(a123);
     isInSync(c);
     c.add(b123);
     isInSync(c);
     c.add(a123);
     isInSync(c);
     c.add(b456);
     isInSync(c);
   }
   
   private void isInSync(Origin a, Origin b) throws Exception {
     if (SynchronizationSvc.isSynchronized(a, b)) System.out.print("In Sync: ");
     else System.out.print("NOT IN SYNC: ");
     {}//Logwriter.printOnConsole(a.toString() + " == " + b.toString());
   }
  
   private void isInSync(Collection origins) throws Exception {
     if (SynchronizationSvc.isSynchronized(origins)) {}//Logwriter.printOnConsole("Collection In Sync ");
     else {}//Logwriter.printOnConsole("COLLECTION NOT IN SYNC: ");
   }
  
  
  private void sync(Origin a, Origin b) throws Exception {
    SynchronizationSvc.record(createRecord(a, b));
  }
  
  private void sync(Collection origins) throws Exception {
    SynchronizationSvc.record(origins);
  }
  
  
  private Calendar time(int y, int m, int d, int h, int min, int s) throws Exception {
    return new GregorianCalendar(y, m, d, h, min, s);
  }
  public Origin origin(String node, String source, String branch, String name, String revision, String version, Calendar time) throws Exception {
    String delim = Names.ORIGIN_DELIMITER;
    Origin o = new Origin(node, source, Origin.ILINK, time.getTimeInMillis(), branch+delim+name+delim+revision+delim+version);
    o.setName(name);
    o.setDateCreated(time);
    return o;
  }  
  private SynchronizationRecord createRecord(Origin a, Origin b) throws Exception {
    SynchronizationRecordBase sync = new SynchronizationRecordBase();
    sync.setData(a, b);
    return sync;
  }
  
  public static void print(String s) { System.out.print(s); }
  public static void print(KeyValue pair) { System.out.print(pair.toXML()); }
  public static void print(Object o) { print(o.toString()); }
  public static void print(Calendar c) throws Exception { System.out.print(db.formatDate(c)); }
  public static void println(String s) { {}//Logwriter.printOnConsole(s); }
  public static void println(Object o) { println(o.toString()); }
  public static void println(Calendar c) throws Exception { {}//Logwriter.printOnConsole(db.formatDate(c)); }
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
  */
}  