package com.zws.functor.processor.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on November 11, 2003, 1:49 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import com.zws.datasource.SQLSource;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;

public class StoredProcedure extends Action {

  public void execute() throws Exception {
    SQLSource datasource = (SQLSource)DataSourceService.find(getDatasourceName());
    datasource.call(getProcedureName());
    //+++ todo set results from stored procedure call
  }

  public String getProcedureName() { return procedureName; }
  public void setProcedureName(String s){ procedureName=s; }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  private String datasourceName = null;
  private String procedureName=null;
}
