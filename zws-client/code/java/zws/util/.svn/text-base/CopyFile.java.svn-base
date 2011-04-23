package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 17, 2003, 12:38 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

import java.io.File;
import java.io.*;
public class CopyFile extends OpBase{

  public void execute() throws Exception { copy(getSource(), getToDir()); }

  private void copy(File source, File toDir) throws Exception {
    {} //System.out.println("copying " + source.getAbsolutePath() + " to " + toDir.getAbsolutePath());
    if (!source.exists()) return;
    if (!toDir.exists()) toDir.mkdirs();
    if (source.isFile()) copyFile(source, toDir);
    if (source.isDirectory()) copyDirectory(source, toDir);
  }
  
  private void copyFile(File source, File toDir) throws Exception {
    FileReader in = new FileReader(source);
    File target = new File(toDir, source.getName());
    FileWriter out = new FileWriter(target);
    int length = (int)source.length();
    char buf[] = new char[length];
    if (in.ready()) {
      in.read(buf,0,length);
      in.close();
      out.write(buf,0, length);
      out.close();
    }
  }

  private void copyDirectory(File source, File toDir) throws Exception {
    File newDir = new File(toDir, source.getName());
    if (!newDir.exists()) newDir.mkdirs();
    if (!copyRecursively) return;
    File[] files = source.listFiles();
    for (int idx = 0; idx<files.length; idx++) copy(files[idx], newDir);
  }

  public void setSource(File f) { source=f; }
  public File getSource() { return source; }
  public void setToDir(File f) { toDir = f; }
  public File getToDir() { return toDir; }

  public void setCopyRecursively(boolean b) { copyRecursively=b; }
  public boolean getCopyRecursively() { return copyRecursively; }
  
  private File source=null;
  private File toDir=null;
  private boolean copyRecursively=true;
  
  /*
  public static void main(String[] args) {
      CopyFile op = new CopyFile();
      try {
        op.run();
      }
      catch(Exception e) {
          e.printStackTrace();
      }
    }
    
    private void run() throws Exception {
      File source = new File("C:\\temp\\copy\\.proi");
      File dir  = new File("C:\\temp\\copy\\LDB");
      copy(source, dir);
    }
    
*/  
}
