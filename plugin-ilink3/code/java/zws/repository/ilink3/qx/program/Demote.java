/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 15, 2004, 7:44 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

package zws.repository.ilink3.qx.program;

import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.Ilink3Constants;

/**
 * The Class ReportBill.
 */
public class Demote extends QxInstruction {

  /**
   * The Constructor.
   *
   * @param origin the origin
   */
  public Demote(Origin origin, String demoteTo, String formFolderLocation, String dependencies, String configuration) {
    IntralinkOrigin ilinkOrigin  = (IntralinkOrigin) origin;
    String deps = dependencies;
    String conf = configuration;
    if (null==deps) deps = Ilink3Constants.DEPENDENCIES_NONE;
    if (null==configuration) conf = Ilink3Constants.AS_STORED;
    
    setName("demote");
    set("name", ilinkOrigin.getName());
    set("branch", ilinkOrigin.getBranch());
    set("revision", ilinkOrigin.getRevision());
    set("version", String.valueOf(ilinkOrigin.getVersion()));
    set("demote-to", demoteTo);
    set("dependencies", deps);
    set("configuration", conf);
    if (null!=formFolderLocation) set("folder", formFolderLocation);
  }
}