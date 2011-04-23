package zws.search.criteria;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 30, 2004, 9:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Iterator;

public class OROperator extends LogicalOperator {
  public String getOperator() { return OR_OP; }

  public Expression copy() {
    OROperator copy = new OROperator();
    Iterator i = getExpressions().iterator();
    while (i.hasNext()) copy.addExpression((Expression)i.next());
    return copy;
  }

}
