package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 9, 2004, 3:05 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Checkin  extends WorkspaceAction {
  public void execute() throws Exception {
   getDatasourceAccess().checkin(grabOrigin(), getRequiredString("workspace"), authenticationID());
  }
}