package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2004, 4:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.FailedToGzip;
import zws.exception.NotADirectory;
import zws.op.OpBase;

import java.io.File;

//executes:
//gzip {TARGET}
public class Gzip extends OpBase {
  
  public static void main(String args[]){
   Gzip op = new Gzip();
   op.setWorkingDirectory("C:\\zpack");
   op.setTarget("stuff.tar");
   try { op.execute(); }
   catch (Exception e) { e.printStackTrace(); }
  }
  public void execute() throws Exception {
    int exitCode;
    if (!workingDirectory.exists()) throw new NotADirectory(workingDirectory);
    File targetFile = new File(workingDirectory, target);
    if (!targetFile.exists()) throw new zws.exception.PathDoesNotExist(targetFile.getAbsolutePath());
    File gzip = new File(workingDirectory, target+".gz");
    if (gzip.exists()) {
      gzip.delete();
      {} //System.out.println("Deleted existing gzip file: " + gzip.getAbsolutePath());
    }
    ExecShell shell = new ExecShell();
    shell.setExecutable("gzip");
    shell.setWorkingDirectory(workingDirectory);
    shell.addCommandLineArgument(target, true);
    try {
      shell.execute();
      exitCode = shell.waitFor();
    }
    catch (Exception e) { throw new FailedToGzip(workingDirectory, target); }
    if (!gzip.exists()) throw new FailedToGzip(workingDirectory, target);
    gzipFilename = gzip.getName();
    setResult(gzip.getName());
  }
  public void setTarget(String s) { target = s; }
  public String getTarget() { return target; }
  
  public void setWorkingDirectory(String s) { setWorkingDirectory(new File(s)); }
  public void setWorkingDirectory(File f) { workingDirectory=f; }
  public File getWorkingDirectory() { return workingDirectory; }

  public String getGzipFilename() { return gzipFilename; }
  
  private String target=null;
  private File workingDirectory=null;  
  private String gzipFilename=null;
}
