package zws.search.criteria;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 28, 2004, 4:24 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.op.Op;

import java.util.List;
public class Grouping implements Expression {
  public boolean isComparison() { return false; }

  public List getComparisons() { try { return getExpression().getComparisons();} catch (Exception e) { e.printStackTrace(); return null;} }
  public Expression getExpression() throws Exception { 
    if (null!= expressionOp) { expressionOp.execute(); expression = (Expression)expressionOp.getResult(); }
    return expression;
  }
  public Op getExpressionOp() {  return expressionOp; }
  public void setExpression(Expression e) { expression=e; }
  public void setExpressionOp(Op op) { expressionOp = op; }

  public String toString() {
    try { 
      String expression = getExpression().toString();
      if (null==expression) return null;
      return GROUP_START+SPACE+expression+SPACE+GROUP_END; 
    }
    catch (Exception e) { e.printStackTrace(); return e.getMessage(); }
  }
  
  public Expression copy() throws Exception {
    Grouping copy = new Grouping();
    copy.setExpression(getExpression().copy());
    return copy;
  }
  
  private Expression expression;
  private Op expressionOp;
  
  private static String SPACE = " ";
  private static String GROUP_START = Names.CRITERIA_GROUP_START_BLOCK;
  private static String GROUP_END = Names.CRITERIA_GROUP_END_BLOCK;  
}

