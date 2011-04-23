package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 4:29 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class StringSequenceTest {
  private StringSequenceTest() { }
  public static void main(String[] args) { 
    StringSequenceTest test = new StringSequenceTest();
    test.run();
  }
  
  public void run() {
    try {
     StringSequence s = new StringSequence();
     s.add("TVS");
     s.add("C");
     s.add("Obsolete");
     s.add("A");
     s.add("B");
 
     {} //System.out.println(s.itemBefore("Obsolete"));
     {} //System.out.println(s.itemAfter("A"));
     compare (s, "C", "TVS");
     compare (s, "C", "A");
     compare (s, "A", "B");
     compare (s, "Obsolete", "Obsolete");
     compare (s, "B", "TVS");
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  private void compare (StringSequence s, String a, String b) throws Exception  {
    int idx = s.compare(a,b);
    if (idx==-1) {} //System.out.println(a + " comes before " + b);
    else if (idx==0) {} //System.out.println(a + " and " + b + " are equal");
    else if (idx==1) {} //System.out.println(a + " comes after " + b);
  }
  
}
