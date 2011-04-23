package com.zws.functor.processor.action.file;

import com.zws.functor.processor.action.Action;

public class Call extends Action  {

  public void execute () throws Exception {
    Exception ex=null;
    com.zws.functor.util.file.Call action = new com.zws.functor.util.file.Call();
    action.setExecutablePath(getExecutablePath());
    try {
      action.execute();
      getActionLog().log("ok: Called " + getExecutablePath());
    }
    catch(Exception e) {
      getActionLog().log("Failed: Calling " + getExecutablePath());
      ex = e;
    }
    if (null!=ex) throw ex;
  }

  public String getExecutablePath() { return executablePath; }
  public void setExecutablePath(String s) { executablePath=s; }

  private String executablePath=null;
}
