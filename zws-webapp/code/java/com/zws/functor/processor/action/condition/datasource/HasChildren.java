package com.zws.functor.processor.action.condition.datasource;

import com.zws.datasource.DataSource;
import com.zws.functor.processor.action.condition.Conditional;
import com.zws.service.config.DataSourceService;

public class HasChildren extends Conditional {
  public boolean isTrue() throws Exception {
    return lookupDatasource().hasChildren(getDocument().getOrigin());
  }

  private DataSource lookupDatasource() throws Exception {
    if (null!=datasource) return datasource;
    if (null!=datasourceName) return DataSourceService.find(datasourceName);
    if (null!=datasourceMetadata) return DataSourceService.find(getDocument().get(datasourceMetadata));
    return null;
  }

  public DataSource getDatasource() { return datasource; }
  public void setDatasource(DataSource s) { datasource=s; }

  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }

  public String getDatasourceMetadata() { return datasourceMetadata; }
  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }


  private DataSource datasource=null;
  private String datasourceName=null;
  private String datasourceMetadata=null;
}
