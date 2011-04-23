package com.zws.functor.util.file;

import com.zws.functor.Functor;
import com.zws.functor.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Call extends Functor  {
  public void execute() throws Exception {
    File f = new File(getExecutablePath());
    if (!f.exists()) throw new FileNotFoundException("executable file does not exist! " + f.getAbsoluteFile());
    ExecShell shell = new ExecShell();
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    Iterator i = arguments.iterator();
    while (i.hasNext()) {
      shell.addCommandLineArgument(i.next().toString());
    }
    shell.execute();
    exitCode = shell.waitFor();
  }

  public String getExecutablePath() { return executablePath; }
  public void setExecutablePath(String s) { executablePath=s; }
  public void addArgument(String arg) { arguments.add(arg); }

  public int getExitCode() { return exitCode; }
  private int exitCode = -999;
  private String executablePath=null;
  private Collection arguments = new Vector();
}
