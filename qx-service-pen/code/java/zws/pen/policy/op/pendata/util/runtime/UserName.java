/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.runtime;

import zws.pen.policy.op.pendata.util.string.StringResult;
import zws.security.Authentication;

public class UserName extends StringResult {
  public void execute() throws Exception {
    String x = null;
    Authentication id = getAuthentication();
    if (null == id) x = "";
    else x = id.getUsername();
    setStringResult(x);
  }
}
