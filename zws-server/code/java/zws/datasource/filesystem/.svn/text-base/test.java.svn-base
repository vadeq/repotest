package zws.datasource.filesystem;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 5, 2004, 3:09 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.application.Properties;
import zws.search.criteria.*;
import zws.search.criteria.modifier.EnsureComparison;
import zws.util.KeyValue;
//import zws.util.{}//Logwriter;

import java.io.File;
import java.util.*;

public class test {
  public test() { }
  public static void main(String[] args) {
    try {
      Properties.set(Names.DOMAIN_NAME, "zws-domain");
      Properties.set(Names.SERVER_NAME, "zws-node");
      FileSystemSource source0 = new FileSystemSource();
      source0.setRoot("C:\\zws\\data\\G-Drive");
      source0.setName("source-0");
      SearchAgent agent = new SearchAgent();
      agent.setDatasource(source0);
      //agent.setCriteria("[type=pdf & type=zip & size>100]");
      //agent.setCriteria("[name=* + folder=1 + folder=2 + folder=3 & date>2004.1.1]");
      //agent.setCriteria("[name=* + folder=1 + folder=2 + folder=3]");
      agent.setCriteria("( name='*' + type='dwg' + folder='78' + folder='79')");
/*
      EnsureComparison x = new EnsureComparison();
      x.setFieldName("date");
      x.setOperator(">");
      x.setValue("2004.5.1");
      agent.add(x);

      EnsureComparison y = new EnsureComparison();
      y.setFieldName("date");
      y.setOperator("<");
      y.setValue("2004.9.1");
      //agent.add(y);
*/
      agent.setSearchRecursively(false);
      agent.search();
      print(agent.getResults());

      //Metadata data = (Metadata)agent.getResults().toArray()[0];
      //print(source0.find(data.getOrigin(), null));
    }
    catch (Exception e) { e.printStackTrace(); }
/*    
    Criteria cc = new Criteria();
    Expression e;
    Grouping g;
    ANDOperator andOp;
    OROperator orOp;
    Comparison c;
    g = grouping();
    andOp = andOperator();
    c = name("test");
    andOp.addExpression(c);
    c = folder("\\path");
    andOp.addExpression(c);
    e = g;
    g.setExpression(andOp);
    
    
    cc.setExpression(e);
    {}//Logwriter.printOnConsole(cc);
 */
  }
  
  private static  Comparison type(String type) {
    Comparison c = new Comparison();
    c.setFieldName(type);
    c.setOperator("=");
    c.setValue(type);
    return c;
  }    
  private static  Comparison name(String s) {
    Comparison c = new Comparison();
    c.setFieldName(name);
    c.setOperator("=");
    c.setValue(s);
    return c;
  }    
  private static  Comparison root(String s) {
    Comparison c = new Comparison();
    c.setFieldName(root);
    c.setOperator("=");
    c.setValue(s);
    return c;
  }    
  private static  Comparison folder(String s) {
    Comparison c = new Comparison();
    c.setFieldName(folder);
    c.setOperator("=");
    c.setValue(s);
    return c;
  }    
  private static  ANDOperator andOperator() { return new ANDOperator(); }    
  private static  OROperator orOperator() { return new OROperator(); }    
  private static  Grouping grouping() { return new Grouping(); }    
  
  
  
  private void print (File[] list) {
    if (null==list) { {}//Logwriter.printOnConsole("No Files"); return; }
      
    }
    File f;
    for (int i=0;i<list.length;i++){
      f=list[i];
     {}//Logwriter.printOnConsole(f.getName()); 
    }
    {}//Logwriter.printOnConsole("-----------------------------------");
    {}//Logwriter.printOnConsole(list.length + " files.");
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
  
  private static  String type="type";
  private static  String name="name";
  private static  String folder="folder";
  private static  String root="root-folder";
}
