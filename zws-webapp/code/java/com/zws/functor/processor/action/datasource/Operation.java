package com.zws.functor.processor.action.datasource;

import com.zws.datasource.DataSource;
import com.zws.domo.document.Document;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;

public abstract class Operation extends Action {

  public final void execute () throws Exception { setResult(doOperation()); }

  protected abstract Document doOperation() throws Exception;

  protected DataSource getDatasource() throws Exception {
    String sourceName=null;
    if (null!=datasourceMetadata) sourceName = getDocument().get(datasourceMetadata);
    else sourceName = datasourceName;
    return DataSourceService.find(sourceName);
  }

  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public String getDatasourceMetadata() { return datasourceMetadata; }
  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }

  String datasourceName = null;
  String datasourceMetadata = null;
}
