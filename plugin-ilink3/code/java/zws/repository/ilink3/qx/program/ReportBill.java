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
//impoer zws.util.Logwriter;

/**
 * The Class ReportBill.
 */
public class ReportBill extends QxInstruction {

  /**
   * The Constructor.
   *
   * @param origin the origin
   */
  public ReportBill(Origin origin) {
    IntralinkOrigin ilinkOrigin  = (IntralinkOrigin) origin;
    setName("report-bill");
    set("name", ilinkOrigin.getName());
    set("branch", ilinkOrigin.getBranch());
    set("revision", ilinkOrigin.getRevision());
    set("version", String.valueOf(ilinkOrigin.getVersion()));
    {}//Logwriter.printOnConsole("set report bill" + ilinkOrigin.toString());
    {}//Logwriter.printOnConsole("set report bill" + ilinkOrigin.toXML());
  }



  //protected String getOutputFileName(File f) { return f.getAbsolutePath() + Names.PATH_SEPARATOR + "bill.xml"; } //override

 /* public IntralinkResultHandler getXMLResultHandler() {
    xmlHandler = new GetBillHandler();
    return xmlHandler;
  }*/

  /**
   * Gets the component name.
   *
   * @return the component name
   */
  public String getComponentName() { return componentName; }

  /**
   * Sets the component name.
   *
   * @param s the component name
   */
  public void setComponentName(String s) { componentName = s; }

  /**
   * Gets the branch.
   *
   * @return the branch
   */
  public String getBranch() { return branch; }

  /**
   * Sets the branch.
   *
   * @param s the branch
   */
  public void setBranch(String s) { branch= s; }

  /**
   * Gets the revision.
   *
   * @return the revision
   */
  public String getRevision() { return revision; }

  /**
   * Sets the revision.
   *
   * @param s the revision
   */
  public void setRevision(String s) { revision= s; }

  /**
   * Gets the version.
   *
   * @return the version
   */
  public String getVersion() { return version; }

  /**
   * Sets the version.
   *
   * @param s the version
   */
  public void setVersion(String s) { version= s; }

 // public BillOfMaterials getBillOfMaterials() { return xmlHandler.getBillOfMaterials(); }
  //public Object getResult() { return xmlHandler.getBillOfMaterials(); }

  /** The component name. */
 private String componentName=null;

  /** The branch. */
  private String branch=null;

  /** The revision. */
  private String revision=null;

  /** The version. */
  private String version=null;

  //private GetBillHandler xmlHandler=null;
}
