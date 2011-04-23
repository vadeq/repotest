package com.zws.functor.processor.action.intralink;

import com.zws.datasource.IntralinkSource;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;

public class DestroyWorkspace extends Action  {

  public void execute () throws Exception {
    Exception ex=null;
    IntralinkSource datasource = (IntralinkSource)DataSourceService.find(getDocument().get(datasourceMetadata));
    com.zws.functor.intralink.DestroyWorkspace action = new com.zws.functor.intralink.DestroyWorkspace();
    action.setWorkspaceName(getWorkspace());
    action.setUsername(datasource.getUsername());
    action.setPassword(datasource.getPassword());
    action.setEXEToolkitEnv(datasource.getEXEToolkitEnv());
    try {
      action.execute();
      getActionLog().log("ok: destroyed workspace: "+getWorkspace());
    }
    catch(Exception e) {
      getActionLog().log("Failed: destroying workspace: "+getWorkspace());
      ex = e;
    }
    if (null!=ex) throw ex;
  }

  private String getWorkspace() {
    if (null!= workspaceName) return workspaceName;
    if ("name".equalsIgnoreCase(workspaceMetadata)) return getDocument().getName();
    else return getDocument().get(workspaceMetadata);
  }

  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName=s; }
  public String getWorkspaceMetadata() { return workspaceMetadata; }
  public void setWorkspaceMetadata(String s) { workspaceMetadata=s; }
  public String getDatasourceMetadata() { return datasourceMetadata; }
  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }

  private String workspaceName=null;
  private String workspaceMetadata="name";
  private String datasourceMetadata="Constants.METADATA_DATA_SOURCE";
}
