package zws.search.criteriamodifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 12:07 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
public class NoStar extends CriteriaModifierBase {
  
  public String modify(String crit) {
    String c = crit.trim();
    if ("*".equals(c)) return "==";   //invalidates criteria
    if (c.indexOf('=') == c.lastIndexOf('=') && c.endsWith("=*")) return "==";
    return crit;
  }

  private String message=null;
}
