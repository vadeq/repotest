package com.zws.functor.custom.deere;

import com.zws.application.Config;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;

import java.io.File;

public class DRPExport extends Functor {
  public void execute() throws Exception{
    ExecShell shell=new ExecShell();
    File f = new File(Config.getProperty(Config.EXE_DEERE_DRP_EXPORT));
    if (!f.exists()) throw new Exception("executable file does not exist! " + Config.getProperty(Config.EXE_ILINK_SEARCH));
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    shell.addCommandLineArgument(getImageName());
    shell.addCommandLineArgument(getOutputFileName());
    shell.setQuoteArguments(false);
    shell.execute();
    setExitCode(shell.waitFor());
  }
  public String getImageName() { return imageName; }
  public void setImageName(String s) { imageName = s; }
  public String getOutputFileName() { return outputFileName; }
  public void setOutputFileName(String s) {  outputFileName= s; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }

  private String imageName=null;
  private String outputFileName=null;
  private int exitCode=0;
}
