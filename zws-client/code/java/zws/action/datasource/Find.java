package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 27, 2004, 6:33 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Find extends WorkspaceAction {
  public void execute() throws Exception {
   setResult(getDatasourceAccess().find(grabOrigin(), authenticationID()));
  }
}