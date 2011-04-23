package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 9, 2004, 10:47 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Checkout extends WorkspaceAction {
  public void execute() throws Exception {
   getDatasourceAccess().checkout(grabOrigin(), getRequiredString("workspace"), authenticationID());
  }
}