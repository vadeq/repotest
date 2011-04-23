/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Dec 5, 2007 7:35:16 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.lang.condition.util;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.service.pen.PENDataElement;

public class ItemSourceHasBinaryFiles extends ConditionOPBase {

  private static final long serialVersionUID = -5024870806958241832L;

  public Boolean evaluateCondition() throws Exception {
    boolean status = false;  
    PENDataElement penElement = getPenData().lookupPENDataElement(getCurrentItem());
    status = penElement.getSourceDataElement().getBinaryCollection().size() > 0;
    return new Boolean(status);
  }

}
