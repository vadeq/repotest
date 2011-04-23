package zws.search.criteria;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 28, 2004, 5:26 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.Op;
import zws.util.StringMapper;

import java.util.List;
import java.util.Vector;
public class Comparison implements Expression {

  public void clear() { fieldName = operator = value= null; fieldNameOp = operatorOp = valueOp = null; }

  public boolean isComparison() { return true; }
  public List getComparisons() { List l = new Vector(); l.add(this); return l; }
  public String getFieldName() throws Exception { 
    if (null!=fieldNameOp) { fieldNameOp.execute(); fieldName=(String)fieldNameOp.getResult(); }
    return fieldName; 
  }
  public void setFieldName(String s) { fieldName=s; }
  public void setFieldName(Op op) { fieldNameOp=op; }
  public String getOperator() throws Exception{
    if (null!=operatorOp) { operatorOp.execute(); operator=(String)operatorOp.getResult(); }
    return operator; 
  }
  public void setOperator(String s) { operator=s; }
  public void setOperator(Op op) { operatorOp=op; }
  public String getValue() throws Exception {
    if (null!=valueOp) { valueOp.execute(); value=(String)valueOp.getResult(); }
    return value; 
  }
  public void setValue(String s) {
    value=trimSingleQuotes(s);
  }
  
  private String trimSingleQuotes(String s) {
    if (null==s) return s;
    String val = s.trim();
    if (val.length()<2) return s;
    if (0==val.indexOf('\'') && (val.length()-1) == val.lastIndexOf('\'')) return val.substring(1, val.length()-1);
    return val;
  }

  
  public void setValue(Op op) { valueOp=op; }

  public String toString() {
    try {
      String c=null;
      if (null==getFieldName()) return null;
      if   (isValueDisplayedInQuotes())
        c = lookupFieldName()+getOperator()+"'"+getValue()+"'"; 
      else
        c = lookupFieldName()+getOperator()+getValue();
      return c;
    }
    catch (Exception e) { e.printStackTrace(); return e.getMessage(); }
  }
  
  private String lookupFieldName() throws Exception {
    String fn = getFieldName();
    if (null!=getFieldNameMapper()) {
      getFieldNameMapper().setValue(fn);
      getFieldNameMapper().execute();
      fn = getFieldNameMapper().getMappedValue();
      if (null==fn) fn = getFieldName();
    }
    return fn;
  }
  
  
  public Expression copy() {
    Comparison copy = new Comparison();
    copy.setFieldName(fieldName);
    copy.setFieldName(fieldNameOp);
    copy.setOperator(operator);
    copy.setOperator(operatorOp);
    copy.setValue(value);
    copy.setValue(valueOp);
    return copy;
  }

  /**
   * @return the displayValuesInQuotes
   */
  public boolean isValueDisplayedInQuotes() {
    return displayValuesInQuotes;
  }
  /**
   * @param displayValuesInQuotes the displayValuesInQuotes to set
   */
  public void valueDisplayedInQuotes(boolean displayValuesInQuotes) {
    this.displayValuesInQuotes = displayValuesInQuotes;
  }

  public StringMapper getFieldNameMapper() {
    return fieldNameMapper;
  }
  
  public void setFieldNameMapper(StringMapper mappingOp) {
    fieldNameMapper = mappingOp;
  }
  
  
  private String fieldName=null;
  private String operator=null;
  private String value=null;
  private boolean displayValuesInQuotes=true;
  StringMapper fieldNameMapper = null;
  
  private Op fieldNameOp=null;
  private Op operatorOp=null;
  private Op valueOp=null;
}
