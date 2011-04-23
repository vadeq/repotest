package zws.pen.policy.op.pendata.element.status.subcomponent;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.PENDataElement;
import zws.service.pen.StatusElement;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class SubItemStatusOpBase extends PENDataProcessor {

  public void process() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    analyzeSubItemStatus(penData);
  }

  public abstract void analyzeSubItemStatus(PENDataElement penData) throws Exception;

  public void setSubcomponentStatus(String subcomponent,String statusName, String statusValue) throws Exception {
    StatusElement statusElement = this.lookupStatusElement(this.getCurrentItem());
    statusElement.setSubcomponentStatus(subcomponent, statusName, statusValue);
    RecorderUtil.logActivity(getQxCtx(), "Item status for " + subcomponent , statusName + "="+ statusValue);    
  }

  public String getSubcomponentStatus(String subcomponent,String statusName) throws Exception {
    StatusElement statusElement = this.lookupStatusElement(this.getCurrentItem());
    return statusElement.getSubcomponentStatus(subcomponent, statusName);
    
  }
}
