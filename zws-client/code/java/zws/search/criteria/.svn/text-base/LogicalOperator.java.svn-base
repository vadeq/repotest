package zws.search.criteria;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 28, 2004, 4:29 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.op.Op;

import java.util.*;

public abstract class LogicalOperator implements Expression {
  public boolean isComparison() { return false; }
  
  public abstract String getOperator() throws Exception;
  public abstract Expression copy();
  
  public List getComparisons() {
    List l = new Vector();
    Iterator i = expressionList.iterator();
    Expression e;
    while (i.hasNext()) {
      e = (Expression)i.next();
      if (e.isComparison()) l.add(e);
      else l.addAll(e.getComparisons());
    }
    return l;
  }
  public void addExpression(Expression e) { expressionList.add(e); }
  public void addExpressions(Collection c) { expressionList.addAll(c); }

  public String toString() { 
    try {
      Iterator i = expressionList.iterator();
      String s=null;
      while(null==s && i.hasNext()) s=i.next().toString();
      if (null==s) return null;
      if (1==expressionList.size()) return getOperatorDisplay()+SPACE+s; //unary operator
      String expression;
      while (i.hasNext()) { //binary operator
        expression=i.next().toString();
        if (null!=expression) s += SPACE+getOperatorDisplay()+SPACE+expression;
      }
      return s;
    }
    catch (Exception e) { e.printStackTrace(); return e.getMessage(); }
  }
  
  public String getOperatorDisplay() throws Exception {
    if(null==operatorDisplay) return getOperator();
    return operatorDisplay;
  }
  
  public void setOperatorDisplay(String s) {
    operatorDisplay = s;
  }

  public Collection getExpressions() { return expressionList; }  
  
  private String operator=null;
  private Op operatorOp=null; 
  private String operatorDisplay=null;

  private Collection expressionList = new Vector();
  public static String AND_OP = Names.CRITERIA_AND_OP;
  public static String OR_OP = Names.CRITERIA_OR_OP;

  private static String SPACE = " ";
}
