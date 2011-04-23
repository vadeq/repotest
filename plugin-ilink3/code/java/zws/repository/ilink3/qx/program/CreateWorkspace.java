package zws.repository.ilink3.qx.program;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.qx.program.QxInstruction;

public class CreateWorkspace extends QxInstruction  {
  public CreateWorkspace() { setName(Tags.CREATE_WORKSPACE); }
  
  public CreateWorkspace(String workspaceName) {
    setName(Tags.CREATE_WORKSPACE);
    setWorkspaceName(workspaceName);
  }
  
  public String getWorkspaceName() { return get(Tags.WORKSPACE); }
  public void setWorkspaceName(String s) { set(Tags.WORKSPACE, s); }

}
