package zws.util; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Sep 18, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

public class Quadrouplet {
  public Quadrouplet() {}
  public Quadrouplet(Object oA, Object oB, Object oC, Object oD) { o0=oA; o1=oB; o2=oC; o3=oD; }

  public Object getObject0() { return o0;}
  public void setObject0(Object o) {o0=o;}

  public Object getObject1() {return o1;}
  public void setObject1(Object o) {o1=o;}

  public Object getObject2() {return o2;}
  public void setObject2(Object o) {o2=o;}

  public Object getObject3() {return o3;}
  public void setObject3(Object o) {o3=o;}

  
  public String toString() {
    String s0=null;
    String s1=null;
    String s2=null;
    String s3=null;
    if (null!=o0) s0=o0.toString();
    else s0="";    
    if (null!=o1) s1=o1.toString();
    else s1="";
    if (null!=o2) s2=o2.toString();
    else s2="";
    if (null!=o3) s3=o3.toString();
    else s3="";
    return "["+s0+", "+s1+", "+s2+", "+s3+"]";
  }
  
  private Object o0 = null;
  private Object o1= null;
  private Object o2= null;
  private Object o3= null;
}