package zws.search.criteriamodifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 12:07 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Star extends CriteriaModifierBase {
  
  public String modify(String crit) {
    String c = crit.trim();
    if (c.startsWith(getFieldname()+"=") && !c.endsWith("*") && c.length()>getFieldname().length()+2) return c + "*";
    else return c;
    //+++todo add star to specified fieldname
  }

  public String getFieldname() { return fieldname; }
  public void setFieldname(String s) { fieldname=s; }
  
  private String fieldname=null;
}
