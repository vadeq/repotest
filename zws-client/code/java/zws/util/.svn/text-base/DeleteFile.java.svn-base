package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 17, 2003, 12:38 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotDo;
import zws.op.OpBase;

import java.io.File;

public class DeleteFile extends OpBase{

  public void execute() throws Exception { setResult(new Boolean(delete(getFile()))); }

  private boolean delete(File f) throws Exception {
    boolean success;
    if (null==f.getParent()) throw new CanNotDo("delete file", "Parent is not a directory: " + f.getAbsolutePath());      
    if (f.isDirectory())  {
      String[] subDirectories = f.list();
      if (!deleteIfNotEmpty && subDirectories.length > 0) throw new Exception ("Directory not empty");
      for (int i=0; i<subDirectories.length; i++) {
        success = delete(new File(f, subDirectories[i]));
        if (!success) return false;
      }
    }
    return f.delete();
  }

  public String getPath() { return path; }
  public void setPath(String s) { path=s; }

  public File getFile(){
    if (null==file && null!=getPath()) file = new File(getPath());
    return file;
  }
  public void setFile(File f) { file=f; }

  public boolean getDeleteIfNotEmpty() { return deleteIfNotEmpty; }
  public void setDeleteIfNotEmpty(boolean b) { deleteIfNotEmpty=b; }

  private String path=null;
  private File file=null;
  private boolean deleteIfNotEmpty = false;
}
