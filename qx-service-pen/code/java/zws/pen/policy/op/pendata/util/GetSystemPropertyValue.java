/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Dec 17, 2007 7:51:03 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.util;

import zws.pen.policy.op.pendata.util.string.StringResult;
import zws.recorder.util.RecorderUtil;
////import zws.util.{}//Logwriter;

public class GetSystemPropertyValue extends StringResult {
  private static final long serialVersionUID = -6119321075121766872L;
  
  public void execute() throws Exception {      
    String s = zws.application.Properties.get(getPropertyName());
    {}//Logwriter.printOnConsole("System property " + getPropertyName() + " value is " + s);
    RecorderUtil.logActivity(getQxCtx(), "System property", getPropertyName() + "=" + s);
    setStringResult(s);
  }

  private String propertyName = null;
  public String getPropertyName() {return propertyName;}
  public void setPropertyName(String propName) {propertyName = propName;}
}
