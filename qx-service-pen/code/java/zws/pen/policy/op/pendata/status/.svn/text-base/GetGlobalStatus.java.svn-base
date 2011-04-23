package zws.pen.policy.op.pendata.status;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.service.pen.StatusElement;
////import zws.util.{}//Logwriter;

public class GetGlobalStatus extends PENDataOpBase {

  public void execute() throws Exception {
    String status = getPenData().getStatus(getStatusName());
    setResult(status);
  }
  
  public String getStatusName() {return statusName;  }
  public void setStatusName(String s) {statusName = s;  }
  private String statusName = null;
 }
