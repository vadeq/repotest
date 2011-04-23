package zws.pen.policy.op.pendata.element.status.item;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/



import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.service.pen.StatusElement;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class ItemStatusOpBase extends PENDataProcessor {

  public void process() throws Exception {
    StatusElement statusElement = this.lookupStatusElement(getCurrentItem());
    setItemStatus(statusElement);
  }
  public abstract void setItemStatus(StatusElement statusElement) throws Exception;
}
