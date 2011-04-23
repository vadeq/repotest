package com.zws.functor.util.file;

import com.zws.application.Properties;
import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;

public class PS2PDF extends Functor {

  public void execute() throws Exception {
    File f,exe;

    String exePDF = Properties.get(Properties.exeGhostScript);
    f = new File(exePDF);
    if (!f.exists()) throw new FileNotFoundException ("PDF executable file does not exist: " + f.getAbsolutePath());

    f = new File(getInputFileName());
    if (!f.exists()) throw new FileNotFoundException ("Input file does not exist: " + f.getPath());

    if (inColor) exe = new File(Properties.get(Properties.ps2colorpdf));
    else exe = new File(Properties.get(Properties.ps2pdf));
    if (!exe.exists()) throw new FileNotFoundException ("PS2PDF executable file does not exist: " + f.getAbsolutePath());

    ExecShell shell=new ExecShell();
    shell.setExecutable(exe.getAbsolutePath());
    shell.setWorkingDirectory(exe.getParent());
    shell.addCommandLineArgument(exePDF);
    shell.addCommandLineArgument(getInputFileName());
    shell.addCommandLineArgument(getOutputFileName());
    if (getInColor())
      shell.addCommandLineArgument(Properties.get(Properties.colorPDF));
    else
      shell.addCommandLineArgument(Properties.get(Properties.blackWhitePDF));
    shell.execute();
    setExitCode(shell.waitFor());
  }

  public boolean getInColor() { return inColor; }
  public void setInColor(boolean b) { inColor=b; }
  public String getInputFileName() { return inputFileName; }
  public void setInputFileName(String s) { inputFileName = s; }
  public String getOutputFileName() { return outputFileName; }
  public void setOutputFileName(String s) { outputFileName=s; }
  public int getExitCode() { return exitCode; }
  private void setExitCode(int code) { exitCode = code; }


  private String inputFileName=null;
  private String outputFileName=null;
  private boolean inColor=false;
  private int exitCode=0;
}
