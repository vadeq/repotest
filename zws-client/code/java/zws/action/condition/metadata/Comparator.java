package zws.action.condition.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 2:57 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.condition.Conditional;
import zws.data.Metadata;
import zws.op.Op;

public abstract class Comparator extends Conditional {
  public boolean isTrue() throws Exception {
    Metadata compareToData = (Metadata) get("compareTo");
    return compare(compareToData);
  }

  public abstract boolean compare(Metadata data) throws Exception;

  public String getCtxCompareTo() { return ctxCompareTo; }
  public void setCtxCompareTo(String s) { ctxCompareTo=s; }
  public Op getCompareTo() { return compareTo; }
  public void setCompareTo(Op a) { compareTo=a; }
  
  public String metaCompareTo=null;
  public String ctxCompareTo=null;
  public Op compareTo=null;
}
