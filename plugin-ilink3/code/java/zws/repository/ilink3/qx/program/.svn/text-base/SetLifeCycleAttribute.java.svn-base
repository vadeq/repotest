package zws.repository.ilink3.qx.program;
/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 11, 2003, 9:45 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.qx.program.QxInstruction;

/**
 * The Class SetLifeCycleAttribute.
 */
public class SetLifeCycleAttribute extends QxInstruction {


  /**
   * @param name name
   * @param branch branch
   * @param revision revision
   * @param version version
   * @param parameter attribute
   * @param val new value
   */
  public SetLifeCycleAttribute(String name, String branch, String revision, String version,
                              String parameter, String val) {
    setName("set-life-cycle-attribute");
    set("name", name);
    set("branch", branch);
    set("revision", revision);
    set("version", version);
    set("attribute", parameter);
    if (null != val) {
      set("value", val);
    } else {
      set("value", "");
    }
  }
}
