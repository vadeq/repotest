package zws.pen.policy.op.pendata.element.status.eco.target;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.service.pen.ECOElement;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class GetTargetECO extends PENDataOpBase {

  public void execute() throws Exception {
    ECOElement ecoElement = this.getPenData().lookupECOElement(getCurrentItem());
    String ecoName = ecoElement.getTargetECO();
    setResult(ecoName);
  }

}
