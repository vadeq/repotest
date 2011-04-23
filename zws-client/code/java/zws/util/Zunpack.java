package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2004, 4:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

import java.io.File;

public class Zunpack extends OpBase {
  
  public static void main(String args[]){
   Zunpack op = new Zunpack();
   op.setWorkingDirectory("C:\\zpack");
   op.setTarget("stuff");
   try { op.execute(); }
   catch (Exception e) { e.printStackTrace(); }
  }

  public void execute() throws Exception {
    Gunzip zip = new Gunzip();
    zip.setWorkingDirectory(workingDirectory);
    zip.setTarget(target+".tar");
    zip.execute();

    TarUncompress tar = new TarUncompress();
    tar.setWorkingDirectory(workingDirectory);
    tar.setTarget(target);
    tar.execute();
    //File t = new File(workingDirectory, target);
    if (deleteTarget) {
      //File tarball = new File(workingDirectory, target+".tar");
      //if (tarball.exists()) { tarball.delete(); }
    }
  }

  public String getTarget() { return target; }
  public void setTarget(String s) { target=s; }
  public File getWorkingDirectory() { return workingDirectory; }
  public void setWorkingDirectory(File f) { workingDirectory = f; }
  public void setWorkingDirectory(String s) { workingDirectory = new File(s); }
  public boolean getDeleteTarget() { return deleteTarget; }
  public void setDeleteTarget(boolean b) { deleteTarget = b; }

  private File workingDirectory = null;
  private String target=null;
  boolean deleteTarget = true;
}
