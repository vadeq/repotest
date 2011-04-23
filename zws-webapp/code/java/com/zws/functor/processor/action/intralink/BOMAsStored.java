package com.zws.functor.processor.action.intralink;

import com.zws.application.Constants;
import com.zws.application.Properties;
import com.zws.datasource.IntralinkSource;
import com.zws.domo.document.*;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;

import java.util.Iterator;

public class BOMAsStored extends Action  {

  public void execute () throws Exception {//todo: move the getbom action to IntralinkDatasource
    Exception ex=null;
    IntralinkSource datasource;
    if (null!=getDatasourceName()) datasource = (IntralinkSource)DataSourceService.find(getDatasourceName());
    else datasource = (IntralinkSource)DataSourceService.find(getDocument().get(datasourceMetadata));
    com.zws.functor.intralink.BOMAsStored action = new com.zws.functor.intralink.BOMAsStored();
    action.setAssemblyName(getAssemblyName());
    action.setRevision(getRevision());
    action.setVersion(getVersion());
    action.setUsername(datasource.getUsername());
    action.setPassword(datasource.getPassword());
    action.setEXEToolkitEnv(datasource.getEXEToolkitEnv());
    OriginMaker originMaker = new OriginMaker();
    originMaker.setDatasourceName(datasource.getName());
    originMaker.setServiceName(Properties.get(Constants.SERVICE_NAME));
    originMaker.setOriginFields(getOriginFields());
    action.setOriginMaker(originMaker);
    try {
      action.execute();
      DocumentInterface d = action.getDocument();
      if (null!= d.getChildren()) {
        Iterator i = d.getChildren().iterator();
        while (i.hasNext()) getDocument().add((Reference)i.next());
      }
      getActionLog().log("ok: generated bom report for  "+getAssemblyName());
    }
    catch(Exception e) {
      getActionLog().log("Failed: to generate bom report for "+getAssemblyName());
      ex = e;
    }
    if (null!=ex) throw ex;
  }

  private String getAssemblyName() { return getDocument().get(assemblyMetadata); }
  private String getRevision() { return getDocument().get(revisionMetadata); }
  private String getVersion() { return getDocument().get(versionMetadata); }

  public String getAssemblyMetadata() { return assemblyMetadata; }
  public void setAssemblyMetadata(String s) { assemblyMetadata=s; }
  public String getRevisionMetadata() { return revisionMetadata; }
  public void setRevisionMetadata(String s) { revisionMetadata=s; }
  public String getVersionMetadata() { return versionMetadata; }
  public void setVersionMetadata(String s) { versionMetadata=s; }
  public String getOriginFields() { return originFields; }
  public void setOriginFields(String s) { originFields=s; }

  public String getDatasourceMetadata() { return datasourceMetadata; }
  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public boolean getDeleteOutputFile() { return deleteOutputFile; }
  public void setDeleteOutputFile(boolean b) { deleteOutputFile=b; }

  private String assemblyMetadata="name";
  private String revisionMetadata="revision";
  private String versionMetadata="version";
  private String datasourceMetadata="Constants.Metadata_DATA_SOURCE";
  private String datasourceName=null;
  private String originFields=null;
  private boolean deleteOutputFile = true;
}
