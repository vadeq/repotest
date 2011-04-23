package zws.action.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 19, 2004, 12:32 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.ActionBase;
import zws.op.Op;

import java.util.*;

public class Call extends ActionBase {
  public void execute () throws Exception {
    Exception ex=null;
    zws.op.util.Call caller = new zws.op.util.Call();
    caller.setExecutablePath(getRequiredString("executable"));
    caller.setWorkingDirectory(getString("workingDirectory"));
    caller.setWaitForShell(getWaitForShell());
    Iterator i = arguments.iterator();
    Op op;
    while (i.hasNext()) {
      op = (Op) i.next();
      op.setContext(getContext());
      op.execute();
      caller.addArgument(op.getResult().toString());
    }
    caller.execute();
  }

  protected boolean getWaitForShell() {
    String waitForShell = getString("wait");
    if (null!=waitForShell) return Boolean.valueOf(waitForShell).booleanValue();
    else return true;
  }    
    
  public void add(Op op) { arguments.add(op); }
  
  public String getExecutable() { return executable; }
  public void setExecutable(String s) { executable=s; }
  public String getMetaExecutable() { return metaExecutable; }
  public void setMetaExecutable(String s) { metaExecutable=s; }
  public String getCtxExecutable() { return ctxExecutable; }
  public void setCtxExecutable(String s) { ctxExecutable=s; }
  public Op getExecutableOp() { return executableOp; }
  public void setExecutableOp(Op op) { executableOp = op; }
  public String getCtxDefaultExecutable() { return ctxDefaultExecutable; }
  public void setCtxDefaultExecutable(String s) { ctxDefaultExecutable=s; }

  public String getWorkingDirectory() { return workingDirectory; }
  public void setWorkingDirectory(String s) { workingDirectory=s; }
  public String getMetaWorkingDirectory() { return metaWorkingDirectory; }
  public void setMetaWorkingDirectory(String s) { metaWorkingDirectory=s; }
  public String getCtxWorkingDirectory() { return ctxWorkingDirectory; }
  public void setCtxWorkingDirectory(String s) { ctxWorkingDirectory=s; }
  public Op getWorkingDirectoryOp() { return workingDirectoryOp; }
  public void setWorkingDirectoryOp(Op op) { workingDirectoryOp = op; }
  public String getCtxDefaultWorkingDirectory() { return ctxDefaultWorkingDirectory; }
  public void setCtxDefaultWorkingDirectory(String s) { ctxDefaultWorkingDirectory=s; }

  public String getWait() { return wait; }
  public void setWait(String s) { wait=s; }
  public String getMetaWait() { return metaWait; }
  public void setMetaWait(String s) { metaWait=s; }
  public String getCtxWait() { return ctxWait; }
  public void setCtxWait(String s) { ctxWait=s; }
  public Op getWaitOp() { return waitOp; }
  public void setWaitOp(Op op) { waitOp = op; }
  public String getCtxDefaultWait() { return ctxDefaultWait; }
  public void setCtxDefaultWait(String s) { ctxDefaultWait=s; }

  private String executable=null;
  private String metaExecutable=null;
  private String ctxExecutable=null;
  private Op executableOp=null;
  private String ctxDefaultExecutable=null;
  
  private String workingDirectory=null;
  private String metaWorkingDirectory=null;
  private String ctxWorkingDirectory=null;
  private Op workingDirectoryOp=null;
  private String ctxDefaultWorkingDirectory=null;
  
  private String wait=null;
  private String metaWait=null;
  private String ctxWait=null;
  private Op waitOp=null;
  private String ctxDefaultWait=null;
  
  private Collection arguments=new Vector();
}
