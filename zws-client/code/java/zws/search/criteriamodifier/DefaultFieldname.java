package zws.search.criteriamodifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 26, 2004, 3:51 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class DefaultFieldname extends CriteriaModifierBase {
  public String modify(String crit) {
    if (-1 < crit.indexOf('=')) return crit;
    return getFieldname() + "=" + crit.trim();
  }

  public String getFieldname() { return fieldname; }
  public void setFieldname(String s) { fieldname=s; }  
  private String fieldname="Name";
}
