/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Mar 31, 2008 7:05:59 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.lang.condition.util;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

public class ItemHasDocuments extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {   
    return new Boolean(0<lookupPENDataElement(getCurrentItem()).getDocumentElement().getDocuments().size());
  }

}
