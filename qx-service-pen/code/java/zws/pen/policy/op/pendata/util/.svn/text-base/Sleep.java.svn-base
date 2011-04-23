/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.pen.policy.op.pendata.util;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;

public class Sleep extends PENDataOpBase {

  public void execute() throws Exception {
    if(seconds <1) return;
    RecorderUtil.logActivity(getQxCtx(), "sleep", (seconds*1000)+"");    
    Thread.sleep(seconds*1000);
  }
  public long getSeconds() {return seconds;}
  public void setSeconds(long s) {seconds = s; }
  private long seconds =0;  
 }
