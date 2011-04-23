package zws.pen.policy.op.pendata.lang.condition.comparison;

import zws.pen.policy.op.pendata.PENDataOp;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;



/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class CompareStringsOpBase extends ConditionOPBase {
  public static String STRING = "string";
  public  static String INT = "int";

  public abstract Boolean compare(String lValue, String rValue, boolean ignoreCase) throws Exception;
  public Boolean compare(Integer lValue, Integer rValue) throws Exception { throw new zws.exception.UnsupportedOperation("compare(Integer, Integer)"); }

  public Boolean evaluateCondition() throws Exception{
    String lValue = evaluate(getLeftOperand());
    String rValue = getValue();
    if(null==rValue) rValue=evaluate(getRightOperand());
    
    Boolean result;
    if (STRING.equalsIgnoreCase(getType())) result = compare(lValue, rValue, isIgnoreCase());
    else if (INT.equalsIgnoreCase(getType())) result = compare(Integer.valueOf(lValue), Integer.valueOf(rValue));
    else throw new zws.exception.InvalidConfiguration("type="+getType()+" is invalid. Valid types are '"+STRING+"' and '"+INT+"'");
    return result;
  }

  private String evaluate(PENDataOp op) throws Exception {
    String resultString = null;
    Object result = null;
    passConfiguration(op);
    op.execute();
    result = op.getResult();
    if (null!=result) resultString = result.toString();
    return resultString;
  }
  
  public void add(PENDataOp op) throws Exception {
    if (null==getLeftOperand()) setLeftOperand(op);
    else if (null==getRightOperand()) setRightOperand(op);
    else throw new Exception("To Many operands! Left and Right operand are already set");
  }
  
  public String getValue() { return value; }
  public void setValue(String s) { value=s; }

  public PENDataOp getLeftOperand() {return leftOperand;}
  public void setLeftOperand(PENDataOp operand) {leftOperand = operand;}
  public PENDataOp getRightOperand() {return rightOperand;}
  public void setRightOperand(PENDataOp operand) {rightOperand = operand;}
  private PENDataOp leftOperand = null;
  private PENDataOp rightOperand = null;
  String value=null;

  
  public String getType() { return type; }
  public void setType(String s) { type=s; }
  
  public boolean isIgnoreCase() { return ignoreCase; }
  public void setIgnoreCase(boolean b) { ignoreCase = b; }

  private boolean ignoreCase = true;
  private String type = STRING;
  
}
