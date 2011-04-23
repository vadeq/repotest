/*
 * DeleteBaseline.java
 *
 * Created on August 19, 2003, 1:06 PM
 */

package com.zws.functor.intralink;

import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;
/**
 *
 * @author  jyelizarov
 */
public class DeleteBaseline extends Functor {
    
public void execute() throws Exception{
    File f = new File(Properties.get(Properties.iLinkDeleteBaseline));
    if (!f.exists()) throw new FileNotFoundException("executable file does not exist: " + f.getAbsolutePath());
    ExecShell shell=new ExecShell();
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    shell.addCommandLineArgument(getBaselineName());
    shell.addCommandLineArgument(getUsername());
    shell.addCommandLineArgument(getPassword());
    shell.addCommandLineArgument(proiTkEnv);
    shell.execute();
    setExitCode(shell.waitFor());
}

/*
REM %1 - baseline name
REM %2 - username
REM %3 - password
REM %4 - Pro/I toolkit env.bat
 */
  public String getBaselineName() { return baselineName; }
  public void setBaselineName(String s) { baselineName = s; }
  
  public String getUsername() { return username; }
  public void setUsername(String s) {  username= s; }
  public String getPassword() { return password; }
  public void setPassword(String s) {  password = s; }
  
  public String getEXEToolkitEnv() { return proiTkEnv; }
  public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }

  private String baselineName=null;
  private String username=null;
  private String password=null;
  private String proiTkEnv=null;
  private int exitCode=0;
}

