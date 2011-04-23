/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.logical;


import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

import java.util.Iterator;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class OR extends LogicalOPBase {

  /**
   * @throws Exception Exception
   * @see zws.pen.policy.old.op.PENDataOpBase#execute()
   */
  public Boolean evaluateCondition() throws Exception {
    Boolean opResult = null;
    ConditionOPBase op = null;
    boolean finalValue = false;
    Iterator itr = getOps().iterator();
    while (itr.hasNext() && !finalValue) {
      op = (ConditionOPBase) itr.next();
      passConfiguration(op);
      op.execute();
      opResult = (Boolean) op.getResult();
      finalValue = finalValue || opResult.booleanValue();
    }
    return new Boolean(finalValue);
 }
}
