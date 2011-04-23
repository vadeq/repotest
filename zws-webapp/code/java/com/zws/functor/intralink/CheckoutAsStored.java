package com.zws.functor.intralink;

import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;

public class CheckoutAsStored extends Functor {

  public void execute() throws Exception{
    File f = new File(Properties.get(Properties.iLinkCheckoutAsStored));
    if (!f.exists()) throw new FileNotFoundException("executable file does not exist: " + f.getAbsolutePath());
    ExecShell shell=new ExecShell();
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    shell.addCommandLineArgument(getComponentName());
    shell.addCommandLineArgument(getRevision());
    shell.addCommandLineArgument(getVersion());
    shell.addCommandLineArgument(getWorkspaceName());
    shell.addCommandLineArgument(getUsername());
    shell.addCommandLineArgument(getPassword());
    shell.addCommandLineArgument(proiTkEnv);
    shell.execute();
    setExitCode(shell.waitFor());
  }

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }
  public String getRevision() { return revision; }
  public void setRevision(String s) { revision = s; }
  public String getVersion() { return version; }
  public void setVersion(String s) { version = s; }
  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName = s; }
  public String getUsername() { return username; }
  public void setUsername(String s) {  username= s; }
  public String getPassword() { return password; }
  public void setPassword(String s) {  password = s; }
  public String getEXEToolkitEnv() { return proiTkEnv; }
  public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }

  private String componentName=null;
  private String revision=null;
  private String version=null;
  private String workspaceName=null;
  private String username=null;
  private String password=null;
  private String proiTkEnv=null;
  private int exitCode=0;
}