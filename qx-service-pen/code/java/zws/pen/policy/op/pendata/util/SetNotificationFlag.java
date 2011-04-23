/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Apr 24, 2008 8:21:30 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.util;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;

public class SetNotificationFlag extends PENDataOpBase {

  public void execute() throws Exception {
    getPenData().setNotification(notification);
    RecorderUtil.logActivity(getQxCtx(), "Notification Flag", "Notification = "+Boolean.toString(notification));    
  }
 
  public void setNotify(boolean value)  { notification = value; }
  public boolean getNotify()            { return notification; }  
  
  private boolean notification=true;
}
