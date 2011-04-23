/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.service.pen.ECOElement;

import java.util.Collection;

public class HasMultiplePendingECOs extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {
    boolean r;
    ECOElement ecoElement = lookupECOElement(getCurrentItem());
    Collection c = ecoElement.getPendingECOList();
    if (null==c || c.isEmpty() || 1==c.size()) r = false;
    else r = true;
    return new Boolean(r);
  }
 }
