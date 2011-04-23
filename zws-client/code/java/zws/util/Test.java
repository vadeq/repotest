package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 29, 2004, 10:16 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.MetadataBase;

public class Test {

  public Test() { }
  public static void main(String[] args) {
    Test t = new Test();
    t.runNamingConvention();
  }

  public void runNamingConvention() {
    try{
    MetadataBase data = new MetadataBase();
    data.setName("12345.prt");
    data.set("Rev", "C");
    data.set("Ver", "3");
    String convention ="[basename] | meta[Rev] |'_' | meta[Ver]| '.' | [type]";
    String s = MetadataUtil.parseNamingGrammar(data, convention);
    {} //System.out.println(s);
    }
    catch(Exception e) { e.printStackTrace(); }
  }

}
