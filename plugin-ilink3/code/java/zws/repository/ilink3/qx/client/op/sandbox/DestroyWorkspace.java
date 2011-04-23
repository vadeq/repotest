package zws.repository.ilink3.qx.client.op.sandbox;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class DestroyWorkspace extends IntralinkSandboxOp {

  protected void createOpInstructionXML() {
    instruction.write("   <open-sandbox ldb-path='" + xmlValue(getLDBPath()) + "'>" + endl);
    instruction.write("    <remove-workspace workspace='" + xmlValue(getWorkspaceName()) + "'/>" + endl);
    instruction.write("   </open-sandbox>" + endl);
  }
  
  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName=s; }
  
  private String workspaceName;
  
}
