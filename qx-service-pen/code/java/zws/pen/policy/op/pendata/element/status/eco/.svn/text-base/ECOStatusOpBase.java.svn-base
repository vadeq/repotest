package zws.pen.policy.op.pendata.element.status.eco;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.service.pen.PENDataElement;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class ECOStatusOpBase extends PENDataProcessor {

  public void process() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    analyzeECOStatus(penData);
  }

  public abstract void analyzeECOStatus(PENDataElement penData) throws Exception;


}
