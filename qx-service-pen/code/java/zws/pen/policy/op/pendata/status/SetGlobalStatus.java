package zws.pen.policy.op.pendata.status;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.pen.policy.op.pendata.PENDataOpBase;

//public class SetItemStatusOP extends PENDataProcessListOpBase {
  public class SetGlobalStatus extends PENDataOpBase {

  public void execute() throws Exception {
    String value = getStatusValue();
    if (null==value) value="";
    value += concatenateDoOps();    
    getPenData().setStatus(getStatusName(), value);
  }
  
  public String getStatusValue() {return statusValue;}
  public void setStatusValue(String s) {statusValue = s;  }
  public String getStatusName() {return statusName;  }
  public void setStatusName(String s) {statusName = s;  }
  private String statusName = null;
  private String statusValue = null;
 }
