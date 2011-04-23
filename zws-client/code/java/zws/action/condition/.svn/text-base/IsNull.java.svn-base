package zws.action.condition;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 1:35 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.Op;

public class IsNull extends Conditional {
  public boolean isTrue() throws Exception {
    return null==get("compareTo");
  }

  public String getMetaCompareTo() { return metaCompareTo; }
  public void setMetaCompareTo(String s) { metaCompareTo=s; }
  public String getCtxCompareTo() { return ctxCompareTo; }
  public void setCtxCompareTo(String s) { ctxCompareTo=s; }
  public Op getCompareToOp() { return compareToOp; }
  public void setCompareToOp(Op a) { compareToOp=a; }
  
  public String metaCompareTo=null;
  public String ctxCompareTo=null;
  public Op compareToOp=null;
}
