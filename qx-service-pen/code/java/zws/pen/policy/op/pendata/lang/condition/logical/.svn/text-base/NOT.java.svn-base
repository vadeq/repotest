/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.logical;


import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

public class NOT extends LogicalOPBase {

  public Boolean evaluateCondition() throws Exception {
    Boolean opResult = null;
    passConfiguration(operand);
    operand.execute();
    opResult = (Boolean) operand.getResult();
    return new Boolean(!opResult.booleanValue());
 }

  public void add(ConditionOPBase op) throws Exception {
    if (null==getOperand()) setOperand(op);
    else throw new Exception("To Many operands! operand is already set");
  }

  public ConditionOPBase getOperand() {return operand;}
  public void setOperand(ConditionOPBase o) {operand = o;}
  private ConditionOPBase operand = null;
}
