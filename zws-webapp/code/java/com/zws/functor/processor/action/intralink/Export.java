package com.zws.functor.processor.action.intralink;

import com.zws.datasource.IntralinkSource;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;
import com.zws.util.FileNameUtil;

public class Export extends Action  {

  public void execute () throws Exception {//todo: move the checkout action to IntralinkDatasource
    Exception ex=null;
    IntralinkSource datasource = (IntralinkSource)DataSourceService.find(getDocument().get(datasourceMetadata));
    com.zws.functor.intralink.Export action = new com.zws.functor.intralink.Export();
    action.setComponentName(getComponentName());
    action.setWorkspaceName(getWorkspace());
    action.setOutputDirectory(getOutputDirectory());
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

  private String getWorkspace() {
    if (null!= workspaceName) return workspaceName;
    if ("name".equalsIgnoreCase(workspaceMetadata)) return getDocument().getName();
    else return getDocument().get(workspaceMetadata);
  }

  private String getOutputDirectory() {
    if (null!= outputPath) return outputPath;
    else if (null!= outputPathMetadata) return getDocument().get(outputPathMetadata);
    else return getActionLog().getProperty(outputPathLogProperty);
  }

  public String getOutputPath() { return outputPath; }
  public void setOutputPath(String s) { outputPath=s; }
  public String getOutputPathMetadata() { return outputPathMetadata; }
  public void setOutputPathMetadata(String s) { outputPathMetadata=s; }
  public String getOutputPathLogProperty() { return outputPathMetadata; }
  public void setOutputPathLogProperty(String s) { outputPathMetadata=s; }

  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName=s; }
  public String getWorkspaceMetadata() { return workspaceMetadata; }
  public void setWorkspaceMetadata(String s) { workspaceMetadata=s; }
  public String getComponentMetadata() { return componentMetadata; }
  public void setComponentMetadata(String s) { componentMetadata=s; }
  public String getDatasourceMetadata() { return datasourceMetadata; }
  public void setDatasourceMetadata(String s) { datasourceMetadata=s; }

  private String workspaceName=null;
  private String workspaceMetadata="name";
  private String outputPath=null;
  private String outputPathMetadata=null;
  private String outputPathLogProperty=null;
  private String componentMetadata="name";
  private String datasourceMetadata="Constants.METADATA_DATA_SOURCE";
}
