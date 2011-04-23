package zws.search.criteria.modifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 30, 2004, 2:34 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;
import zws.search.criteria.*;

import java.util.Iterator;

public class CriteriaModifierBase extends OpBase implements CriteriaModifier {
  public void execute() throws Exception { setResult(modify(getCriteria())); }
  
  public Criteria modify(Criteria c) throws Exception {
    Criteria newCriteria= c.copy();
    modifyExpression(newCriteria, newCriteria.getExpression());
    return newCriteria;
  }

  protected void registerNewComparison(Criteria newCriteria, Comparison c) {
    newCriteria.getParser().getComparisons().add(c);
  }

  protected final void modifyExpression(Criteria newCriteria, Expression exp) throws Exception {
   if (exp instanceof zws.search.criteria.ANDOperator) modifyExpression(newCriteria, (ANDOperator)exp);
   else if (exp instanceof zws.search.criteria.OROperator) modifyExpression(newCriteria, (OROperator)exp);
   else if (exp instanceof zws.search.criteria.Grouping) modifyExpression(newCriteria, (Grouping) exp);
   else if (exp instanceof zws.search.criteria.Comparison) modifyExpression(newCriteria, (Comparison) exp);
   else throw new Exception("Expression not supported: " + exp.getClass().getName());
  }
  
  protected void modifyExpression(Criteria newCriteria, ANDOperator exp) throws Exception {
    Iterator i = exp.getExpressions().iterator();
    while(i.hasNext()) modifyExpression(newCriteria, (Expression)i.next());
  }
  protected void modifyExpression(Criteria newCriteria, OROperator exp) throws Exception {
    Iterator i = exp.getExpressions().iterator();
    while(i.hasNext()) modifyExpression(newCriteria, (Expression)i.next());
  }
  protected void modifyExpression(Criteria newCriteria, Grouping exp) throws Exception {
    modifyExpression(newCriteria, exp.getExpression());
  }
  protected void modifyExpression(Criteria newCriteria, Comparison exp) throws Exception {}

  public Criteria getCriteria() { return crit; }
  public void setCriteria(Criteria c) { crit=c; }
  private Criteria crit=null;
}
