/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on May 14, 2008 9:13:23 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.repository.ilink3.qx.program;

import zws.qx.program.QxInstruction;

public class CreateSandbox extends QxInstruction {
  public CreateSandbox(String ldbPath) {
    setName(Tags.CREATE_SANDBOX);
    set(Tags.LDB_PATH, ldbPath);
  }
}
