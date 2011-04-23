package zws.converter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 20, 2004, 9:08 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.application.Properties;
import zws.op.OpBase;
import zws.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class ConverterBase extends OpBase {
  protected String getOpType() { 
    String type = getClass().getName();
    type = type.substring(type.lastIndexOf('.')+1);
    return "gs-" + type; 
  }
  protected abstract String getEXEName();
  protected String getBinPath() { return Properties.get(Names.BIN_DIR) + Names.PATH_SEPARATOR +"pdf"; }
  protected String getEXEPath() { return getBinPath() + Names.PATH_SEPARATOR + getEXEName(); }

  protected abstract void setArguments(ExecShell shell) throws Exception;
  protected void initExecution() throws Exception { }
  protected void finishExecution() throws Exception { }
  protected void handleResponse() throws Exception { }
  public void execute() throws Exception {
    getNewCount();
    initExecution();
    File executable = new File(getEXEPath());
    if (!executable.exists()) throw new FileNotFoundException("executable file does not exist! " + executable);
    workingDir = getWorkingDirectory(executable);
    //Profiler pofiler = new Profiler();
    //profiler.start("exec-ilink-search " + xmlFileName, "exec-ilink-search " + xmlFileName);
    ExecShell shell = new ExecShell();
    shell.setExecutable(executable.getAbsolutePath());
    shell.setWorkingDirectory(workingDir);
    shell.addCommandLineArgument(executable.getParent());
    setArguments(shell);
    shell.execute();
    //profiler.start("exec-ilink-search " + xmlFileName, "shell-wait-for");
    exitCode = shell.waitFor();
    //profiler.stop("exec-ilink-search " + xmlFileName, "shell-wait-for");
    //instructionFile.delete();
    handleResponse();
    finishExecution();
    if (zws.Server.debugMode()) return;
    //cleanup
    File[] contents = workingDir.listFiles();
    for (int idx=0; idx<contents.length; contents[idx++].delete())
      ;
    workingDir.delete();
   }

  private File getWorkingDirectory(File f) {
    File workingDir = new File (Properties.get(Names.WORKING_DIR) + Names.PATH_SEPARATOR + getOpType() + Names.PATH_SEPARATOR + "work"+getNewCount());
    if (!workingDir.exists()) workingDir.mkdirs();
    return workingDir;
  }

  private static int count=0;
  private static synchronized int getNewCount() { return ++count; }
  public int getExitCode() { return exitCode; }

  public void setDeleteOutput(boolean b) { deleteOutput=b; }
  public boolean getDeleteOutput() {return deleteOutput; }

  protected File workingDir=null;  
  private boolean deleteOutput=!Boolean.valueOf(Properties.get(Names.DEBUG_MODE)).booleanValue();
  private int exitCode = -999;
}
