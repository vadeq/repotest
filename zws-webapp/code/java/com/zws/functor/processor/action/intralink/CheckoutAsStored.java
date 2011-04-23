package com.zws.functor.processor.action.intralink;

import com.zws.datasource.IntralinkSource;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;
import com.zws.util.FileNameUtil;

public class CheckoutAsStored extends Action  {

  public void execute () throws Exception {//todo: move the checkout action to IntralinkDatasource
    Exception ex=null;
    IntralinkSource datasource = (IntralinkSource)DataSourceService.find(getDocument().get(datasourceMetadata));
    com.zws.functor.intralink.CheckoutAsStored action = new com.zws.functor.intralink.CheckoutAsStored();
    action.setComponentName(getComponentName());
    action.setRevision(getRevision());
    action.setVersion(getVersion());
    action.setWorkspaceName(getWorkspace());
    action.setUsername(datasource.getUsername());
    action.setPassword(datasource.getPassword());
    action.setEXEToolkitEnv(datasource.getEXEToolkitEnv());
    try {
      action.execute();
      getActionLog().log("ok: checked out "+getComponentName()+"to workspace: "+getWorkspace());
    }
    catch(Exception e) {
      getActionLog().log("Failed: checking out "+getComponentName()+"to workspace: "+getWorkspace());
      ex = e;
    }
    if (null!=ex) throw ex;
  }

  private String getComponentName() {
    if ("name".equalsIgnoreCase(componentMetadata)) return getDocument().getName();
    else return FileNameUtil.getBaseName(getDocument().get(componentMetadata));
  }
  private String getRevision() { return getDocument().get(revisionMetadata); }
  private String getVersion() { return getDocument().get(versionMetadata); }

  private String getWorkspace() {
    if (null!= workspaceName) return workspaceName;
    if ("name".equalsIgnoreCase(workspaceMetadata)) return getDocument().getName();
    else return getDocument().get(workspaceMetadata);
  }

  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName=s; }
  public String getWorkspaceMetadata() { return workspaceMetadata; }
  public void setWorkspaceMetadata(String s) { workspaceMetadata=s; }
  public String getComponentMetadata() { return componentMetadata; }
  public void setComponentMetadata(String s) { componentMetadata=s; }
  public String getRevisionMetadata() { return revisionMetadata; }
  public void setRevisionMetadata(String s) { revisionMetadata=s; }
  public String getVersionMetadata() { return versionMetadata; }
  public void setVersionMetadata(String s) { versionMetadata=s; }

  public String getDatasourceMetadata() { return datasourceMetadata; }
  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }

  private String workspaceName=null;
  private String workspaceMetadata="name";
  private String componentMetadata="name";
  private String revisionMetadata="revision";
  private String versionMetadata="version";
  private String datasourceMetadata="Constants.METADATA_DATA_SOURCE";
}
