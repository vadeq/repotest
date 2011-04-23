/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.pen.policy.op.pendata.util;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;

public class GetEnvValue extends PENDataOpBase {

  public void execute() throws Exception {
    setResult(System.getenv(getEnvVariable()));
    RecorderUtil.logActivity(getQxCtx(), "Environment varialbe", getEnvVariable() + "="+ System.getenv(getEnvVariable()));
  }
  
  public String getEnvVariable() {return envVariable; }
  public void setEnvVariable(String s) {envVariable = s;}  

  private String envVariable=null;
 }
