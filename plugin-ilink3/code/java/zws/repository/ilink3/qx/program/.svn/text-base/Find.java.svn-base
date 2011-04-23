/*
 * DesignState - Design Compression Technology
 * @author: athakur @version: 1.0
 * Created on February 14, 2005, 12:21 AM
 * Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.ilink3.qx.program;

import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.qx.program.QxInstruction;
////import zws.util.{}//Logwriter;

/**
 * FindLatest instruction.
 *
 * @author ptoleti
 */

public class Find extends QxInstruction {
  /**
   * constructor.
   * @param name
   * object name
   */
  public Find(Origin origin) {
    IntralinkOrigin ilinkOrigin  = (IntralinkOrigin) origin;
    setName("find");
    set("name", ilinkOrigin.getName());
    set("branch", ilinkOrigin.getBranch());
    set("revision", ilinkOrigin.getRevision());
    set("version", String.valueOf(ilinkOrigin.getVersion()));
    {}//Logwriter.printOnConsole("set report bill" + ilinkOrigin.toString());
    {}//Logwriter.printOnConsole("set report bill" + ilinkOrigin.toXML());
  }
}
