package zws.pen.policy.op.pendata.element.status.item;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.StatusElement;
//impoer zws.util.{}//Logwriter;

//public class SetItemStatusOP extends PENDataProcessListOpBase {
  public class SetItemStatusOP extends PENDataOpBase {

  public void execute() throws Exception {
    StatusElement statusElement = this.lookupStatusElement(getCurrentItem());
    String value = getStatusValue();
    if (null==value) value="";
    value += concatenateDoOps();
    statusElement.setItemStatus(this.getStatusName(), value);
    RecorderUtil.logActivity(getQxCtx(), "set status " + getCurrentItem(), getStatusName() + "=" + value);
  }

  public String getStatusValue() {return statusValue;}
  public void setStatusValue(String s) {statusValue = s;  }
  public String getStatusName() {return statusName;  }
  public void setStatusName(String s) {statusName = s;  }
  private String statusName = null;
  private String statusValue = null;
 }
