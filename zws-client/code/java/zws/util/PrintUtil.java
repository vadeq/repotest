package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 30, 2004, 7:58 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.*;

public class PrintUtil {

  public static void print(String s) { System.out.print(s); }
  public static void print(KeyValue pair) { System.out.print(pair.toXML()); }
  public static void print(Object o) { if (null==o) print("null"); else print(o.toString()); }
  public static void println(String s) { System.out.println(s); }

  public static void println(Object o) { if (null==o) println("null"); else println(o.toString()); }
  public static void print(Collection c) {
    if (null==c) print("Collection is NULL");
    println("[--");
    Iterator i = c.iterator();
    if (i.hasNext()) print(i.next());
    while (i.hasNext()) {
      println("");
      print(i.next());
    }
    println("");
    print("--]");
  }
  public static void println(Collection c) {
    println(" ");
    print(c);
    println(" ");
  }
  public static void print(int number){ System.out.print("["+number+"] "); }
  public static void println(int number){ System.out.println("["+number+"] ");

  }
  public static void println(int[] numbers){ print(numbers); }
  public static void print(int[] numbers){
    System.out.print("{ ");
    if (numbers.length>0) System.out.print("["+numbers[0]+"] ");
    for (int idx=1; idx<numbers.length; System.out.print("["+numbers[idx++]+"] "));
    System.out.println("}");
  }
  public static void print(byte[] bytes){
    System.out.print("{ ");
    if (bytes.length>0) System.out.print("["+bytes[0]+"] ");
    for (int idx=1; idx<bytes.length; System.out.print("["+bytes[idx++]+"] "));
    System.out.println("}");
  }
  public static void print(long number){ System.out.print("["+number+"] "); }
  public static void println(long number){ System.out.println("["+number+"] ");

  }
  public static void println(long[] numbers){ print(numbers); }
  public static void print(long[] numbers){
    System.out.print("{ ");
    if (numbers.length>0) System.out.print("["+numbers[0]+"] ");
    for (int idx=1; idx<numbers.length; System.out.print("["+numbers[idx++]+"] "));
    System.out.println("}");
  }
  public static void print(String heading, Map m) {
    print("==============================");
    print(m);
  }
  public static void print(Map m) {
    if (null==m) print("Map is NULL");
    println("==============================");
    Iterator i = m.keySet().iterator();
    String k;
    while (i.hasNext()) {
      k=(String)i.next();
      print(k + " => ");
      if (m.get(k) instanceof Collection) print((Collection)m.get(k));
      else if (m.get(k) instanceof Map) print((Map)m.get(k));
      else if (m.get(k) instanceof KeyValue) print((KeyValue)m.get(k));
      else println(m.get(k));
    }
    println("==============================");
  }
}
