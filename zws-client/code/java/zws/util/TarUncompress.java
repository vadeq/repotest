package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2004, 4:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;
import zws.op.OpBase;

import java.io.File;

//executes:
//tar -xvf {TARGET}.tar {TARGET}
public class TarUncompress extends OpBase {
  
  public static void main(String args[]){
   TarUncompress op = new TarUncompress();
   op.setWorkingDirectory("C:\\zpack");
   op.setTarget("stuff");
   try { op.execute(); }
   catch (Exception e) { e.printStackTrace(); }
  }
  public void execute() throws Exception {
    int exitCode;
    if (!workingDirectory.exists()) throw new NotADirectory(workingDirectory);
    File tarball = new File(workingDirectory, target+".tar");
    if (!tarball.exists()) throw new PathDoesNotExist(tarball.getAbsolutePath());
    ExecShell shell = new ExecShell();
    shell.setExecutable("tar");
    shell.setWorkingDirectory(workingDirectory);
    shell.addCommandLineArgument("-xf", false);
    shell.addCommandLineArgument(tarball.getName(), true);
    try {
      shell.execute();
      exitCode = shell.waitFor();
    }
    catch(Exception e) { throw new FailedToUntar(workingDirectory, target); }
    //File targetFile = new File(workingDirectory, target);
    //if (!targetFile.exists()) throw new FailedToUntar(workingDirectory, target);
    //setResult(target);
  }
  public void setDeleteOutput(boolean b) { deleteOutput=b; }
  public boolean getDeleteOutput() {return deleteOutput; }
  
  public void setTarget(String s) { target = s; }
  public String getTarget() { return target; }
  
  public void setWorkingDirectory(String s) { setWorkingDirectory(new File(s)); }
  public void setWorkingDirectory(File f) { workingDirectory=f; }
  public File getWorkingDirectory() { return workingDirectory; }
  public void setStorable(Storable s) { storable=s; }
  
  private Storable storable=null;
  private  boolean deleteOutput=false;
  private File workingDirectory=null;  
  private String target;
}
