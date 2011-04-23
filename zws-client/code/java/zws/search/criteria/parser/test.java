package zws.search.criteria.parser;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 5, 2004, 12:02 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.search.criteria.Criteria;


public class test {
  public static void main(String[] args) {
   {} //System.out.println("---------------");    
   run("(A=1 & C=3 & D=4) & (c=3 | e=5 ) | (d=4 ) & (g!<5 | f=6 & h=7) & (i=8 | j!= 9 | k=10) | (l=5) | (m>6 & n<=7)");
   run("(A=1) & (c=3 & e=5 ) & (d=4 )");
   run("A=1");
   run("A=1 & B=2");
   run("A=1 & B=2 | C=3");
   run("(A=1)");
   run("(A=1 & B=2)");
   run("(A=1 & B=2 | C=3 )");
  }
  public static void run(String s) {
  try {
   CriteriaParserBase parser = new CriteriaParserBase();
   Criteria c = parser.parse(s);
   {} //System.out.println(s + "==>" +c);
  }
  catch(Exception e) { e.printStackTrace(); }
  }
  
}
