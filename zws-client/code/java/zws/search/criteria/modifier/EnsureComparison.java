package zws.search.criteria.modifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 30, 2004, 3:41 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.Op;
import zws.search.criteria.*;

public class EnsureComparison extends CriteriaModifierBase {
  
  protected void modifyExpression(Criteria newCriteria, ANDOperator exp) throws Exception {
    Comparison c = defineComparison();
    exp.addExpression(c);
    registerNewComparison(newCriteria, c);
  }
  
  protected void modifyExpression(Criteria newCriteria, Grouping exp) throws Exception {
    if (exp.getExpression() instanceof zws.search.criteria.Comparison) {
      Comparison c = defineComparison();
      ANDOperator and = new ANDOperator();
      and.addExpression(exp.getExpression());
      and.addExpression(c);
      exp.setExpression(and);
      registerNewComparison(newCriteria, c);
      newCriteria.getParser().getANDList().add(and);
    }
    else modifyExpression(newCriteria, exp.getExpression());
  }
  
  protected void modifyExpression(Criteria newCriteria, Comparison exp) throws Exception {
    if (newCriteria.getExpression()==exp) {
      Comparison c = defineComparison();
      ANDOperator and = new ANDOperator();
      and.addExpression(exp);
      and.addExpression(c);
      newCriteria.setExpression(and);
      registerNewComparison(newCriteria, c);
      newCriteria.getParser().getANDList().add(and);
    }
  }

  
  public String getFieldName() { return fieldName; }
  public void setFieldName(String s) { fieldName=s; }
  public void setFieldName(Op op) { fieldNameOp = op; }
  public String getOperater() { return operator; }
  public void setOperator(String s ) { operator=s; }
  public void setOperator(Op op) { operatorOp=op; }
  public String getValue() { return value; }
  public void setValue(String s) { value=s; }
  public void setValue(Op op) { valueOp=op; }
  
  private Comparison defineComparison() throws Exception { 
    if(null!=fieldNameOp){
      fieldNameOp.execute();
      fieldName = (String)fieldNameOp.getResult();
    }
    if(null!=operatorOp){
      operatorOp.execute();
      operator = (String)operatorOp.getResult();
    }
    if(null!=valueOp){
      valueOp.execute();
      value = (String)valueOp.getResult();
    }
    Comparison c = new Comparison();
    c.setFieldName(fieldName);
    c.setOperator(operator);
    c.setValue(value);
    return c;
  }
  private String fieldName=null;
  private Op fieldNameOp=null;
  private String operator="=";
  private Op operatorOp=null;
  private String value=null;
  private Op valueOp=null;
}
