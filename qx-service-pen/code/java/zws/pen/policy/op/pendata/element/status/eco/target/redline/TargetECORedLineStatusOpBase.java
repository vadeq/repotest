package zws.pen.policy.op.pendata.element.status.eco.target.redline;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.pen.policy.op.pendata.element.status.eco.ECOStatusOpBase;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.PENDataElement;
import zws.service.pen.StatusElement;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class TargetECORedLineStatusOpBase extends ECOStatusOpBase {

  public void process() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    analyzeECOStatus(penData);
  }

  public abstract void analyzeECOStatus(PENDataElement penData) throws Exception;

  public void setItemStatus(String statusName, String statusValue) throws Exception {
    StatusElement statusElement = this.lookupStatusElement(this.getCurrentItem());
    statusElement.setItemStatus(statusName, statusValue);
    RecorderUtil.logActivity(getQxCtx(), "set target ECO redline status:", statusName + "="+ statusValue  );
  }

  public String getItemStatus(String statusName) throws Exception {
    StatusElement statusElement = this.lookupStatusElement(this.getCurrentItem());
    return statusElement.getItemStatus(statusName);
  }
}
