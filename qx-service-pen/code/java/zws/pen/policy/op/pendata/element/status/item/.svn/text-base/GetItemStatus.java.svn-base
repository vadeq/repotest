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
////import zws.util.{}//Logwriter;

public class GetItemStatus extends PENDataOpBase {

  public void execute() throws Exception {
    StatusElement statusElement = this.lookupStatusElement(getCurrentItem());
    {}//Logwriter.printOnConsole(this,"analyzeItemStatus",getCurrentItem());
    String result = statusElement.getItemStatus(this.getStatusName());
    {}//Logwriter.printOnConsole("value found for " + getStatusName() + " is " + result);
    setResult(result);
    if(null != result)
    RecorderUtil.logActivity(getQxCtx(), "Item status for " + getCurrentItem(), getStatusName() + "=" + result);
  }
  
  public String getStatusName() {return statusName;  }
  public void setStatusName(String s) {statusName = s;  }
  private String statusName = null;
 }
