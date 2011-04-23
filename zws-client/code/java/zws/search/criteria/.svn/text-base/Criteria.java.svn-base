package zws.search.criteria;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 28, 2004, 5:16 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.Op;
import zws.search.criteria.parser.CriteriaParser;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

public class Criteria implements Serializable {
  public Criteria() {}
  public Criteria(CriteriaParser parser) { criteriaParser=parser; }

  public Expression getExpression() throws Exception {
    if(null!=expressionOp) {
      expressionOp.execute();
      expression = (Expression)expressionOp.getResult();
    }
    return expression;
  }
  public void setExpression(Expression e) { expression=e; }
  public void setExpression(Op op) { expressionOp=op; }
  public CriteriaParser getParser() { return criteriaParser; }

  public String toString() {  return expression.toString(); }

  public Criteria copy() throws Exception {
    Criteria copy = new Criteria((CriteriaParser)criteriaParser.copy());
    copy.setExpression(getExpression().copy());
    return copy;
  }

  private CriteriaParser criteriaParser = null;
  private Op expressionOp;
  private Expression expression;
  private Collection comparisons = new Vector();
}
