package zws.op.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 19, 2004, 12:34 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;
import zws.util.ExecShell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Call extends OpBase {
  public void execute() throws Exception {
    File f = new File(getExecutablePath());
    if (!f.exists()) throw new FileNotFoundException("executable file does not exist! " + f.getAbsoluteFile());
    File work=null;
    if (null==getWorkingDirectory()) work = f.getParentFile();
    else work = new File(getWorkingDirectory());
    
    ExecShell shell = new ExecShell();
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(work);
    Iterator i = arguments.iterator();
    while (i.hasNext()) shell.addCommandLineArgument(i.next().toString());
    shell.execute();
    if (getWaitForShell()) exitCode = shell.waitFor();
  }

  public String getExecutablePath() { return executablePath; }
  public void setExecutablePath(String s) { executablePath=s; }
  public String getWorkingDirectory() { return workingDirectory; }
  public void setWorkingDirectory(String s) { workingDirectory=s; }
  public boolean getWaitForShell() { return waitForShell; }
  public void setWaitForShell(boolean b) { waitForShell=b; }
  public void addArgument(String arg) { arguments.add(arg); }

  public int getExitCode() { return exitCode; }
  private int exitCode = -999;
  private String executablePath=null;
  private String workingDirectory=null;
  private boolean waitForShell=true;
  private Collection arguments = new Vector();
}
