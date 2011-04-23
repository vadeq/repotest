/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.comparison;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

import java.util.Collection;
import java.util.Iterator;

public class isEmpty extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception{
    Collection c = doOps();
    boolean isEmpty = true;
    if (null==c) return new Boolean(isEmpty);
    Iterator i = c.iterator();
    Object dataO;
    String dataS;
    while (i.hasNext() && isEmpty) {
      dataO = i.next();
      if (null==dataO) continue;
      dataS = dataO.toString();
      if ("".equals(dataS.trim())) continue;
      isEmpty=false;
    }
    return new Boolean (isEmpty);
  }
}

