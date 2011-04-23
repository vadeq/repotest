/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.logical;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

import java.util.Iterator;

public class NAND extends LogicalOPBase {

  public Boolean evaluateCondition() throws Exception {
    ConditionOPBase op = null;
    Boolean opResult = null;
    boolean finalValue = true;
    Iterator itr = getOps().iterator();
    while (itr.hasNext() && finalValue) {
      op = (ConditionOPBase) itr.next();
      passConfiguration(op);
      op.execute();
      opResult = (Boolean) op.getResult();
      finalValue = finalValue && opResult.booleanValue();
    }
    finalValue = !finalValue; // NOT AND
    return new Boolean (finalValue);
 }
}
