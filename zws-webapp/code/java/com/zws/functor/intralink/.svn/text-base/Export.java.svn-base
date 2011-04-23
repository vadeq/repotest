package com.zws.functor.intralink;

import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;

public class Export extends Functor {
  public void execute() throws Exception{
    ExecShell shell=new ExecShell();
    File f = new File(Properties.get(Properties.iLinkExport));
    if (!f.exists()) throw new FileNotFoundException("executable file does not exist! " + f.getAbsoluteFile());
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    shell.addCommandLineArgument(getComponentName());
    shell.addCommandLineArgument(getWorkspaceName());
    shell.addCommandLineArgument(getOutputDirectory());
    shell.addCommandLineArgument(proiTkEnv);
    shell.execute();
    setExitCode(shell.waitFor());
  }
  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }
  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName = s; }
  public String getOutputDirectory() { return outputDirectory; }
  public void setOutputDirectory(String s) {  outputDirectory= s; }
  public String getEXEToolkitEnv() { return proiTkEnv; }
  public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }

  private String componentName=null;
  private String workspaceName=null;
  private String outputDirectory=null;
  private String username=null;
  private String password=null;
  private String proiTkEnv=null;
  private int exitCode=0;
}
