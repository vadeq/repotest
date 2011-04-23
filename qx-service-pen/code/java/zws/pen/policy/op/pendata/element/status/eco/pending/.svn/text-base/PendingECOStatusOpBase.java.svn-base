package zws.pen.policy.op.pendata.element.status.eco.pending;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.pen.policy.op.pendata.element.status.eco.ECOStatusOpBase;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.ECOElement;
import zws.service.pen.PENDataElement;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class PendingECOStatusOpBase extends ECOStatusOpBase {

  public void process() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    analyzeECOStatus(penData);
  }

  public abstract void analyzeECOStatus(PENDataElement penData) throws Exception;

  public void addPendingECO(String ecoName) throws Exception {
    ECOElement ecoElement = this.getPenData().lookupECOElement(getCurrentItem());
    ecoElement.addPendingECO(ecoName);
    RecorderUtil.logActivity(getQxCtx(), "added pending ECO",  " ECO No." + ecoName+ " Item No." + getCurrentItem());
  }
}
