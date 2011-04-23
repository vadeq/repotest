package com.zws.functor.processor.action.file;

import com.zws.application.Constants;
import com.zws.functor.intralink.DRW2PS;
import com.zws.functor.processor.action.Action;
import com.zws.util.FileNameUtil;

public class WorkspaceDRW2PS extends Action  {

  public void execute () throws Exception {
    Exception ex=null;
    DRW2PS action = new DRW2PS();
    action.setComponentName(getComponentName());
    action.setWorkspaceName(getWorkspace());
    action.setSleepPeriod(getSleepPeriod());
    action.setTimeout(getTimeout()*60*1000); //convert timout in minutes to timeout in milliseconds
    try {
      action.execute();
      if (Constants.PROCESS_TIMED_OUT == action.getExitCode())
        getActionLog().log("Conversion timed out: "+getComponentName()+".drw to PostScript: "+action.getOutputFileName());
      else
        getActionLog().log("ok: converted "+getComponentName()+".drw to PostScript: "+action.getOutputFileName());
      getActionLog().setProperty(postscriptFileName, action.getOutputFileName());
    }
    catch(Exception e) {
      getActionLog().log("Failed to convert "+getComponentName()+".drw from workspace "+getWorkspace()+" to PostScript: "+action.getOutputFileName());
      ex = e;
    }
    setExitCode(action.getExitCode());
    if (null!=ex) throw ex;
  }

  private String getComponentName() {
    if ("name".equalsIgnoreCase(componentMetaData)) return getDocument().getName();
    else return FileNameUtil.getBaseName(getDocument().get(componentMetaData));
  }

  private String getWorkspace() {
    if (null!= workspaceName) return workspaceName;
    if ("name".equalsIgnoreCase(workspaceMetaData)) return getDocument().getName();
    else return getDocument().get(workspaceMetaData);
  }

  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName=s; }
  public String getWorkspaceMetaData() { return workspaceMetaData; }
  public void setWorkspaceMetaData(String s) { workspaceMetaData=s; }
  public String getComponentMetaData() { return componentMetaData; }
  public void setComponentMetaData(String s) { componentMetaData=s; }

  public long getSleepPeriod() { return sleepPeriod; }
  public void setSleepPeriod(long l) { sleepPeriod=l; }
  public long getTimeout() { return timeout; }
  public void setTimeout(long l) { timeout=l; }

  //todo: add outputFileNameLogProperty so ouotput filename is stored as specified property in the actionlog

  private String workspaceName=null;
  private String workspaceMetaData="name";
  private String componentMetaData="name";
  private long sleepPeriod; //in milliseconds
  private long timeout=-1; // in minutes
}
