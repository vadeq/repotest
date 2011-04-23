package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 10:48 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.MetadataBase;

public class Test {
  public Test() { }
  public static void main(String[] args) { 
    Test test = new Test();
    try { test.run(); }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  public void run() throws Exception {
    Metadata d0,d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11;
    d0=c("data", "main", "C");
    d1=c("data", "main", "B");
    d2=c("data", "main", "A");
    d3=c("data", "main", "C");
    d4=c("data", "main", "B");
    d5=c("data", "main", "A");
    d6=c("data", "main/abc", "D");
    d7=c("data", "main/abc", "A");
    d8=c("data", "main/abc", "C");
    d9=c("data", "main/main2", "B");
    d10=c("data", "main/main2/main3", "A");
    d11=c("data", "main/main2/main3", "B");

    BranchBase tree = new BranchBase();
    tree.defineKeyFields("Name", "Branch", "Revision");

    //History tree = new HistoryBase();
    //tree.defineKeyFields("Name", "Branch", "Revision");
    
    //RevisionTree tree=new RevisionTreeBase();
    //tree.defineKeyFields("Name|Branch", "Revision", "Version");
    
    tree.defineKey(d0);
    try {tree.place(d10);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d0);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d1);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d2);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d3);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d4);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d5);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d6);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d7);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d8);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d9);} catch (Exception e) {e.printStackTrace();} print(tree);
    try {tree.place(d11);} catch (Exception e) {e.printStackTrace();} print(tree);
   // {} //System.out.println(tree);
    tree.selectDefault();
    MetadataBase m = new MetadataBase();
    m.merge(tree);
    {} //System.out.println("------------------");
    {} //System.out.println(m);
    
 }
  
  
  protected void print(Object o) {
    {} //System.out.println("-------------------------------------------------------");
    {} //System.out.println(o);
    {} //System.out.println("-------------------------------------------------------");
  }
  
  public Metadata c(String name, String branch, String revision) {
    /*
    String uid = getUID();
    MetadataBase m=new MetadataBase();
    OriginBase o = (OriginBase)OriginMaker.materialize("domain|DesignState-node-0|none|type|0|"+uid);
    o.setName(name);
    m.setOrigin(o);
    m.setName(name);
    m.set("Branch", branch);
    m.set("Revision", revision);
    m.set("Version", uid);
    return m;
     */
    return null;
  }
  private static int i=0;
  private static String getUID() { return "id-" + i++; }
}
