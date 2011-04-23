package com.zws.functor.processor.action.tejas;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 28, 2003, 2:39 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import com.zws.datasource.SQLServerSource;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;

public class Truncate extends Action {

  public void execute() throws Exception { getDatasource().truncate(getTableName()); }
  
  public String getTableName() { return tableName; }
  public void setTableName(String s) { tableName=s;}
  public SQLServerSource getDatasource() throws Exception { return (SQLServerSource)DataSourceService.find(datasourceName); }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  private String datasourceName = null;
  private String tableName=null;
}
