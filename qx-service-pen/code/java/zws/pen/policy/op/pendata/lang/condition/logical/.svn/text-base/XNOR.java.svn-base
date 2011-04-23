/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.logical;


import zws.pen.policy.op.pendata.PENDataOp;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import java.util.Collection;
import java.util.Iterator;

public class XNOR extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {
    PENDataOp op = null;
    int count = 0;
    Boolean opResult = null;
    Collection opList = getOps();
    Iterator opItr = opList.iterator();
    while(opItr.hasNext()) {
      op  = (PENDataOp) opItr.next();
      passConfiguration(op);
      op.execute();
      opResult = (Boolean) op.getResult();
      if(opResult.booleanValue()) count++;
    }
    if(count == 1) return new Boolean(FALSE);
    else return new Boolean(TRUE);
  }
}
