package zws.search.criteriamodifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 28, 2004, 6:16 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;

import java.util.StringTokenizer;

public class PostfixFieldname extends CriteriaModifierBase {
  public String modify(String crit) {
    String c = crit.trim();
    if (! c.endsWith(GROUP_END)) return modifyExpression(c);
    StringTokenizer tokens = new StringTokenizer(crit, GROUP_END);
    c="";
    while (tokens.hasMoreTokens()) 
      c += modifyExpression(tokens.nextToken())+ " " +GROUP_END; 
    return c;
  }

  private String modifyExpression(String s) {
    int idx=s.indexOf(getFieldname());
    if (-1==idx) return s;
    int idxSpace = 0;
    String rVal;
    String expression="";
    int idxStart=0;
    while (idx>-1) {
      idx += getFieldname().length()+1; //+1 is for the '=' in fieldname=rVal
      idxSpace = s.indexOf(" ", idx+1);
      if (-1==idxSpace) idxSpace=s.length()-1;
      rVal=s.substring(idx,idxSpace+1).trim();
      if (!rVal.endsWith(getValue())) rVal += getValue();
      expression += s.substring(idxStart,idx);
      expression+=rVal + " ";
      idx=s.indexOf(getFieldname(), idxSpace);
    }
    return expression;
  }
  
  public String getFieldname() { return fieldname; }
  public void setFieldname(String s) { fieldname=s; }
  public String getValue() { return value; }
  public void setValue(String s) { value=s; }
  
  private String fieldname=null;
  private String value=null;
  private static String GROUP_END = Names.CRITERIA_GROUP_END_BLOCK;
}
