package zws.search.criteria.modifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 30, 2004, 4:53 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.search.criteria.Comparison;
import zws.search.criteria.Criteria;

import java.util.*;

public class Prefix extends CriteriaModifierBase {
  
  protected void modifyExpression(Criteria newCriteria, Comparison exp) throws Exception{
    Map index = getIndex();
    String field = exp.getFieldName();
    if (!caseSensitive) field = field.toLowerCase();
    if (null!=index.get(field)) prefix(exp);
  }
  
  private void prefix(Comparison exp) throws Exception { 
    if (caseSensitive && exp.getValue().startsWith(prefixValue)) return;
    if (!caseSensitive && exp.getValue().toLowerCase().startsWith(prefixValue.toLowerCase())) return;
    exp.setValue(prefixValue+exp.getValue());
  }

  private Map getIndex() { 
    if (null==idx) { 
      idx = new HashMap();
      idx = parseFieldNames();
    }
    return idx;
  }
  
  private Map parseFieldNames(){
    String field;
    Map index = new HashMap();
    if (fieldNames.indexOf(delimiter)<0){
      field = fieldNames.trim();
      if (!caseSensitive) field= field.toLowerCase();
      index.put(field, field);
      return index;
    }
    StringTokenizer tokens = new StringTokenizer(fieldNames, delimiter);
    while (tokens.hasMoreTokens()) {
      field = tokens.nextToken().trim();
      if (!caseSensitive) field= field.toLowerCase();
      index.put(field, field);
    }
    return index;
  }

  public String getFieldNames() { return fieldNames; }
  public void setFieldNames(String s) { fieldNames=s; }
  public String getPrefixValue() { return prefixValue; }
  public void setPrefixValue(String s) { prefixValue=s; }
  public String getDelimiter() { return delimiter; }
  public void setDelimiter(String s) { delimiter=s; }
  public boolean getCaseSensitive() { return caseSensitive; }
  public void setCaseSensitive(boolean b) { caseSensitive=b; }
  
  private String fieldNames=null;
  private String prefixValue=null;
  private String delimiter=Names.DELIMITER;
  private boolean caseSensitive = false;
  
  private Map idx = null;
}
