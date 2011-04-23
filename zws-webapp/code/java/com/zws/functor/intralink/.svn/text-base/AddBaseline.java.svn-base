/*
 * AddBaseline.java
 *
 * Created on August 18, 2003, 12:41 PM
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
public class AddBaseline extends Functor {
    
public void execute() throws Exception{
    File f = new File(Properties.get(Properties.iLinkAddBaseline));
    if (!f.exists()) throw new FileNotFoundException("executable file does not exist: " + f.getAbsolutePath());
    ExecShell shell=new ExecShell();
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    shell.addCommandLineArgument(getBaselineName());
    shell.addCommandLineArgument(getBaselineLocation());
    shell.addCommandLineArgument(getReleaseLevel());
    shell.addCommandLineArgument(getBaselineFilesList());
    shell.addCommandLineArgument(getUsername());
    shell.addCommandLineArgument(getPassword());
    shell.addCommandLineArgument(proiTkEnv);
    shell.execute();
    setExitCode(shell.waitFor());
}

/*
 *REM %1 - baseline name
REM %2 - baseline folder location
REM %3 - baseline release level
REM %4 - text file specifing list of baseline files
REM %5 - username
REM %6 - password
REM %7 - Pro/I toolkit env.bat file
 */
  public String getBaselineName() { return baselineName; }
  public void setBaselineName(String s) { baselineName = s; }
  public String getBaselineLocation() { return baselineLocation; }
  public void setBaselineLocation(String s) { baselineLocation = s; }
  public String getReleaseLevel() { return releaseLevel; }
  public void setReleaseLevel(String s) { releaseLevel = s; }
  public String getBaselineFilesList() { return baselineFilesList; }
  public void setRtBaselineFilesList(String s) { baselineFilesList = s; }
  public String getUsername() { return username; }
  public void setUsername(String s) {  username= s; }
  public String getPassword() { return password; }
  public void setPassword(String s) {  password = s; }
  
  public String getEXEToolkitEnv() { return proiTkEnv; }
  public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }

  private String baselineName=null;
  private String baselineLocation=null;
  private String releaseLevel=null;
  private String baselineFilesList=null;
  private String username=null;
  private String password=null;
  
  private String proiTkEnv=null;
  private int exitCode=0;
  
  private static int count=0;
  public static synchronized int getNewCount() { return ++count; }


}
