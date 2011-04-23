package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 9, 2004, 3:08 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class DestroyWorkspace  extends WorkspaceAction {
  public void execute() throws Exception {
   getDatasourceAccess().destroyWorkspace(grabOrigin(), getRequiredString("workspace"), authenticationID());
  }
  
}