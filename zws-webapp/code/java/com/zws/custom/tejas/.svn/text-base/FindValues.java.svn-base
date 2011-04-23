package com.zws.custom.tejas;

import com.zws.application.Constants;
import com.zws.datasource.SQLServerSource;
import com.zws.functor.Functor;
import com.zws.service.config.DataSourceService;

public class FindValues extends Functor {

  public void execute() throws Exception {
    setResult(lookupDatasource().getUserDefinedAttributes(Constants.DELIMITER));
  }

  public SQLServerSource lookupDatasource() throws Exception {
    return (SQLServerSource) DataSourceService.find(datasourceName);
  }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }

  private String datasourceName=null;
}
