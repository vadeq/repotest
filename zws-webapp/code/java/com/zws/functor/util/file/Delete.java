package com.zws.functor.util.file;

import com.zws.functor.Functor;

import java.io.File;

public class Delete extends Functor {

  public void execute() throws Exception { delete (getFile()); }

  private boolean delete(File path) throws Exception {
    boolean success;
    if (path.isDirectory())  {
      String[] subDirectories = path.list();
      if (!deleteIfNotEmpty && subDirectories.length > 0) return false;
      for (int i=0; i<subDirectories.length; i++) {
        success = delete(new File(path, subDirectories[i]));
        if (!success) return false;
      }
    }
    return path.delete();
  }

  public String getPathName() { return pathName; }
  public void setPathName(String s) { pathName=s; }

  public File getFile(){
    if (null==file && null!=getPathName()) file = new File(getPathName());
    return file;
  }
  public void setFile(File f) { file=f; }

  public boolean getDeleteIfNotEmpty() { return deleteIfNotEmpty; }
  public void setDeleteIfNotEmpty(boolean b) { deleteIfNotEmpty=b; }

  private String pathName=null;
  private File file=null;
  private boolean deleteIfNotEmpty = false;
}
