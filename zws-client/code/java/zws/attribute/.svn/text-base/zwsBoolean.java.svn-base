package zws.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 5:24 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class zwsBoolean extends AttributeBase {
  public String getType() { return BOOLEAN; }

  protected String createValue(String s) {
    if(Boolean.valueOf(s).booleanValue()) return TRUE; 
    else return FALSE; 
  }

  protected int compareValueTo(String s) {
    boolean b = Boolean.valueOf(s).booleanValue();
    boolean comp = Boolean.valueOf(b).booleanValue();
    if (b==comp) return 0;
    if (b==true) return 1;
    return -1;
  }
}
