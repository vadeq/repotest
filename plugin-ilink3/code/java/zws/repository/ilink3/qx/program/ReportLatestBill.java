/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 15, 2004, 7:44 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

package zws.repository.ilink3.qx.program;

import zws.qx.program.QxInstruction;
//impoer zws.util.Logwriter;

/**
 * The Class ReportBill.
 */
public class ReportLatestBill extends QxInstruction {

  /**
   * The Constructor.
   *
   * @param origin the origin
   */
  public ReportLatestBill(String name) {
    setName("report-latest-bill");
    set("name", name);
    {}//Logwriter.printOnConsole("set report latest bill" + name);
  }
}
