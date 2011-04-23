package com.zws.functor.util.file;

import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;

public class Office2PDF extends Functor {

  public void execute() throws Exception {
    File f,exe=null;

    exe = new File(Properties.get(Properties.Office2pdf));
    if (!exe.exists()) throw new FileNotFoundException ("executable file does not exist: " + exe.getAbsolutePath());

    f = new File(getInputFileName());
    if (!f.exists()) throw new FileNotFoundException ("Input file does not exist: " + f.getPath());

    ExecShell shell=new ExecShell();
    shell.setExecutable(exe.getAbsolutePath());
    shell.setWorkingDirectory(exe.getParent());
    shell.addCommandLineArgument(getInputFileName());
    shell.addCommandLineArgument(getOutputPath());
    shell.addCommandLineArgument(Properties.get(Properties.exeGhostScript));
    shell.addCommandLineArgument(String.valueOf(getInColor())); //future us by executable
    shell.execute();
    setExitCode(shell.waitFor());
  }

  public boolean getInColor() { return inColor; }
  public void setInColor(boolean b) { inColor=b; }
  public String getInputFileName() { return inputFileName; }
  public void setInputFileName(String s) { inputFileName = s; }
  public String getOutputPath() { return outputPath; }
  public void setOutputPath(String s) { outputPath=s; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }

  private String inputFileName=null;
  private String outputPath=null;
  private boolean inColor=false;
  private int exitCode=0;
}
