package zws.repository.ilink3.qx.program;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Apr 8, 2007 6:12:51 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.qx.program.QxInstruction;

/**
 * @author arbind
 *   <open-repository username='user' password='pwd'>"
 *   </open-repository>"
 */
public class OpenSandbox extends QxInstruction {
  public OpenSandbox(String ldbPath) {
    setName(Tags.OPEN_SANDBOX);
    set(Tags.LDB_PATH, ldbPath);
  }
}
