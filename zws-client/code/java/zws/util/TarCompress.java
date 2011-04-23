package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2004, 4:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.FailedToTar;
import zws.exception.NotADirectory;
import zws.op.OpBase;

import java.io.File;

//executes:
//tar -cvf {TARGET}.tar {TARGET}
public class TarCompress extends OpBase {
  
  public static void main(String args[]){
   TarCompress op = new TarCompress();
   op.setWorkingDirectory("C:\\zpack");
   op.setTarget("stuff");
   try { op.execute(); }
   catch (Exception e) { e.printStackTrace(); }
  }
  public void execute() throws Exception {
    int exitCode;
    if (!workingDirectory.exists()) throw new NotADirectory(workingDirectory);
    File targetFile = new File(workingDirectory, target);
    if (!targetFile.exists()) throw new zws.exception.PathDoesNotExist(targetFile.getAbsolutePath());
    File tarball = new File(workingDirectory, getTarballName()+".tar");
    if (tarball.exists()) {
      tarball.delete();
      {} //System.out.println("Deleted existing tarball: " + tarball.getAbsolutePath());
    }
    ExecShell shell = new ExecShell();
    shell.setExecutable("tar");
    shell.setWorkingDirectory(workingDirectory);
    shell.addCommandLineArgument("-cf", false);
    shell.addCommandLineArgument(tarball.getName(), true);
    shell.addCommandLineArgument(target, true);
    try {
      shell.execute();
      exitCode = shell.waitFor();
    }
    catch (Exception e) { throw new FailedToTar(workingDirectory, target); }
    if (!tarball.exists()) throw new FailedToTar(workingDirectory, target);
    tarFilename = tarball.getName();
    setResult(tarball.getName());
  }
  public void setTarget(String s) { target = s; }
  public String getTarget() { return target; }
  public void setTarballName(String s) { tarballName = s; }
  public String getTarballName() { 
    if (null==tarballName) return target; 
    return tarballName;
  }
  public String getTarFilename() { return tarFilename; } 
  
  public void setWorkingDirectory(String s) { setWorkingDirectory(new File(s)); }
  public void setWorkingDirectory(File f) { workingDirectory=f; }
  public File getWorkingDirectory() { return workingDirectory; }

  private String target = null;
  private File workingDirectory=null;  
  private String tarballName = null;
  private String tarFilename = null;
}
