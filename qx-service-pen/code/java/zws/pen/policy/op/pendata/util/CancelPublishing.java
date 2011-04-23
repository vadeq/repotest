package zws.pen.policy.op.pendata.util;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;

public class CancelPublishing extends PENDataOpBase {
  
  private boolean notification=true;
  
  public void execute() throws Exception { 
    getPenData().setCancelStatus(true);
    getPenData().setNotification(notification);
    RecorderUtil.logActivity(getQxCtx(), "Cancel publishing ", getCurrentItem()+" cancelled");
    }
  
  public void setNotify(boolean value)  { notification = value; }
  public boolean getNotify()            { return notification; }
}
