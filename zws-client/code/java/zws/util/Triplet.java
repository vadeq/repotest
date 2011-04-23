package zws.util; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Triplet {
  public Triplet() {}
  public Triplet(Object oA, Object oB, Object oC) { o0=oA; o1=oB; o2=oC;}

  public Object getObject0() { return o0;}
  public void setObject0(Object o) {o0=o;}

  public Object getObject1() {return o1;}
  public void setObject1(Object o) {o1=o;}

  public Object getObject2() {return o2;}
  public void setObject2(Object o) {o2=o;}

  
  public String toString() {
    String s0=null;
    String s1=null;
    String s2=null;
    if (null!=o0) s0=o0.toString();
    else s0="";    
    if (null!=o1) s1=o1.toString();
    else s1="";
    if (null!=o2) s2=o2.toString();
    else s2="";
    return "["+s0+", "+s1+", "+s2+"]";
  }
  
  private Object o0 = null;
  private Object o1= null;
  private Object o2= null;
}