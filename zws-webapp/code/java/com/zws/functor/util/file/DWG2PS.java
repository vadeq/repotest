package com.zws.functor.util.file;

import com.zws.application.Constants;
import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;
import com.zws.util.FileNameUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class DWG2PS extends Functor {

  public void execute() throws Exception {
    File f,exe=null;
    exe = new File(Properties.get(Properties.dwg2ps));
    if (!exe.exists()) throw new FileNotFoundException ("executable file does not exist: " + exe.getAbsolutePath());

    f = new File(getInputFileName());
    if (!f.exists()) throw new FileNotFoundException ("Input file does not exist: " + f.getPath());

    ExecShell shell=new ExecShell();
    shell.setExecutable(exe.getAbsolutePath());
    shell.setWorkingDirectory(exe.getParent());
    shell.addCommandLineArgument(getInputFileName());
    shell.addCommandLineArgument(getOutputPath());
    shell.addCommandLineArgument(Properties.get(Properties.spoolerOutDirectory));
    shell.execute();
    if (0<getTimeout()) {
      f = new File(Properties.get(Properties.dwg2psTimeout));
      ExecShell timeoutFunctor = new ExecShell();
      timeoutFunctor.setExecutable(f.getAbsolutePath());
      timeoutFunctor.setWorkingDirectory(f.getParent());
      shell.setTimeoutFunctor(timeoutFunctor);
      shell.setSleepPeriod(getSleepPeriod());
      setExitCode(shell.timeout(getTimeout()));
    }
    else {
      setExitCode(shell.waitFor());
    }
  }

  public String getOutputFileName(){
    return getOutputPath() + Constants.FILE_SEPARATOR + FileNameUtil.getBaseName(getInputFileName()) + ".ps";
  }

  public boolean getInColor() { return inColor; }
  public void setInColor(boolean b) { inColor=b; }
  public String getInputFileName() { return inputFileName; }
  public void setInputFileName(String s) { inputFileName = s; }
  public String getOutputPath() { return outputPath; }
  public void setOutputPath(String s) { outputPath=s; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }

  public long getSleepPeriod() { return sleepPeriod; }
  public void setSleepPeriod(long l) { sleepPeriod=l; }
  public long getTimeout() { return timeout; }
  public void setTimeout(long l) { timeout=l; }

  private String inputFileName=null;
  private String outputPath=null;
  private boolean inColor=false;
  private int exitCode=0;

  private long sleepPeriod=200; //ms
  private long timeout=-1;

}
