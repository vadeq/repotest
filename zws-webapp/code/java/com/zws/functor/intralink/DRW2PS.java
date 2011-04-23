package com.zws.functor.intralink;

import com.zws.application.Constants;
import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;
import com.zws.util.FileNameUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class DRW2PS extends Functor  {

  public void execute() throws Exception {
    File f = new File(Properties.get(Properties.drw2ps));
    if (!f.exists()) throw new FileNotFoundException ("executable file does not exist: " + f.getAbsolutePath());
    ExecShell shell=new ExecShell();
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    shell.addCommandLineArgument(getComponentName());
    shell.addCommandLineArgument(getWorkspaceName());
    shell.addCommandLineArgument(Properties.get(Properties.exeProCommMsg));
    shell.addCommandLineArgument(Properties.get(Properties.exeProE));
    shell.execute();
    if (0<getTimeout()) {
      f = new File(Properties.get(Properties.drw2psTimeout));
      ExecShell timeoutFunctor = new ExecShell();
      timeoutFunctor.setExecutable(f.getAbsolutePath());
      timeoutFunctor.setWorkingDirectory(f.getParent());
      timeoutFunctor.addCommandLineArgument(getComponentName());
      shell.setTimeoutFunctor(timeoutFunctor);
      shell.setSleepPeriod(getSleepPeriod());
      setExitCode(shell.timeout(getTimeout()));
    }
    else {
      setExitCode(shell.waitFor());
    }
    String outFile = FileNameUtil.getBaseName(getComponentName());
    String outputType = Properties.get(Properties.drw2psType);
    if (null!=outputType && !"".equals(outputType)) outFile+= "." + outputType;
    String outPath = Properties.get(Properties.drw2psOutputDrive);
    outputFileName = outPath + Constants.FILE_SEPARATOR + outFile;
    File out =  new File(outputFileName);
    if (!out.exists()) {
      File out31 = new File (outPath, outFile.substring(0, 31));
      {} //System.out.println("looking for output file: " + out31.getName());
      if (out31.exists()) {
        out31.renameTo(out);
        {} //System.out.println("Renaming outputfile from " + out31.getName() + " to " + out.getName());
      }
    }
  }

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }
  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName = s; }
  public String getOutputFileName() { return outputFileName; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }

  public long getSleepPeriod() { return sleepPeriod; }
  public void setSleepPeriod(long l) { sleepPeriod=l; }
  public long getTimeout() { return timeout; }
  public void setTimeout(long l) { timeout=l; }

  private String workspaceName=null;
  private String componentName=null;
  private String outputFileName=null;

  private long sleepPeriod=200; //ms
  private long timeout=-1;

  private int exitCode=0;
}
