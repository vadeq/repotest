package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2004, 4:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

import java.io.File;

public class Zpack extends OpBase {
  
  public static void main(String args[]){
   Zpack op = new Zpack();
   op.setWorkingDirectory("C:\\zpack");
   op.setTarget("stuff");
   try { op.execute(); }
   catch (Exception e) { e.printStackTrace(); }
  }
  
  public void execute() throws Exception {
    TarCompress tar = new TarCompress();
    tar.setWorkingDirectory(workingDirectory);
    tar.setTarget(getTarget());
    tar.setTarballName(getTarballName());
    tar.execute();

    Gzip zip = new Gzip();
    zip.setWorkingDirectory(workingDirectory);
    zip.setTarget(tar.getTarFilename());
    zip.execute();

    zpackFilename = zip.getGzipFilename();
    
    //File gziptarball = new File(workingDirectory, getTarballName()+".tar"+".gz");
    File gziptarball = new File(workingDirectory, zpackFilename);
    if (deleteTarget && gziptarball.exists()) {
      File t = new File(workingDirectory, target);
      DeleteFile op = new DeleteFile();
      op.setDeleteIfNotEmpty(true);
      op.setFile(t);
      op.execute();
    }    
  }

  public String getTarget() { return target; }
  public void setTarget(String s) { target=s; }
  public String getTarballName() { 
    if (null==tarballName) return target;
    return tarballName; 
  }
  public void setTarballName(String s) { tarballName=s; }
  public String getZpackFilename() { return zpackFilename; }
  public File getWorkingDirectory() { return workingDirectory; }
  public void setWorkingDirectory(File f) { workingDirectory = f; }
  public void setWorkingDirectory(String s) { workingDirectory = new File(s); }
  //public String getZpackFilename() { return zPackFilename; }
  //public void setZpackFilename(String s) { zPackFilename=s; }
  public boolean getDeleteTarget() { return deleteTarget; }
  public void setDeleteTarget(boolean b) { deleteTarget = b; }

  private File workingDirectory = null;
  //private String zPackFilename=null;
  private String target=null;
  private String tarballName=null;
  private String zpackFilename = null;
  boolean deleteTarget = true;
  
}
