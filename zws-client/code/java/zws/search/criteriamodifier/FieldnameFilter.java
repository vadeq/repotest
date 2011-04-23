package zws.search.criteriamodifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 26, 2004, 3:01 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;

import java.util.StringTokenizer;

public class FieldnameFilter extends CriteriaModifierBase {
  
  public String modify(String crit) {
    String c = crit.trim();
    if (! c.endsWith(GROUP_END)) return c + " & " + fieldname + "=" + filterValue;
    StringTokenizer tokens = new StringTokenizer(crit, GROUP_END);
    c="";
    while (tokens.hasMoreTokens()) 
      c += tokens.nextToken() + " & " + fieldname + "=" + filterValue + " " + GROUP_END;
    return c;
  }

  public String getFieldname() { return fieldname; }
  public void setFieldname(String s) { fieldname=s; }
  public String getFilterValue() { return filterValue; }
  public void setFilterValue(String s) { filterValue=s; }
  
  private String fieldname=null;
  private String filterValue=null;
  private static String GROUP_END = Names.CRITERIA_GROUP_END_BLOCK;
}
