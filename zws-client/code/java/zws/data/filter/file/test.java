package zws.data.filter.file;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 6, 2004, 3:26 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.KeyValue;

import java.io.File;
import java.util.*;

public class test {

  public static void main(String args[]) {
    test t = new test();
    t.run();
  }

  public void run() {
    try {
      /*
      File f = new File("C:\\temp\\filesearchtest");
      SimpleFileFilter filter = new SimpleFileFilter();
      //filter.setIncludeAllFileNames(true);
      //filter.addIgnoredFileType("prt");
      //filter.addIgnoredFileType("asm");
      //filter.addIncludedFileType("prt");
      //filter.addIncludedFileType("asm");
      //filter.addIncludedFileType("zip");
      filter.addIncludedFileNameWithPrefix("my");
      filter.addIncludedFileType("prt");
      //filter.addIncludedFileNameWithSuffix("me");
      //filter.addIncludedFileNameWithSubstring("you");
      //filter.setIgnoreFileSmallerThan(100);
      Calendar min = new GregorianCalendar(2004,Calendar.AUGUST,6,14,0,0);
      Calendar max = new GregorianCalendar(2004,Calendar.AUGUST,6,15,25,0);
      //filter.setLatestModifiedTime(max);
      filter.setEarliestModifiedTime(min);
      print (f.listFiles(filter));
       */
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  private void print (File[] list) {
    if (null==list) { }{} //System.out.println("No Files"); return; }
    File f;
    for (int i=0;i<list.length;i++){
      f=list[i];
     {} //System.out.println(f.getName()); 
    }
    {} //System.out.println("-----------------------------------");
    {} //System.out.println(list.length + " files.");
  }
  
  
  public static void print(String s) { System.out.print(s); }
  public static void print(KeyValue pair) { System.out.print(pair.toXML()); }
  public static void print(Object o) { print(o.toString()); }
  public static void println(String s) {  } {} //System.out.println(s); }
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
