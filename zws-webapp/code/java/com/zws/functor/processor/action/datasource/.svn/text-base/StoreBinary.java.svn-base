package com.zws.functor.processor.action.datasource;

import com.zws.application.Constants;
import com.zws.domo.document.Document;

import java.io.*;

public class StoreBinary extends Operation {

  protected Document doOperation() throws Exception {
    File file = null;
    FileInputStream inputStream = null;
    String path = getPath();
    String filename = getDocument().get(getFilenameMetadata());
    if (null==path) path=getDocument().get(getPathMetadata());
    if (null!=path && !"".equals(path)) file = new File( path + Constants.FILE_SEPARATOR + filename);
    else file = new File(filename);
    if (!file.exists()) throw new FileNotFoundException("Binary file: " +path+"\\" + filename + " does not exist for storing into " + getDatasource().getName());
    int len = (int)file.length();
    inputStream = new FileInputStream(file);
    getDatasource().storeBinary(getDocument(), path, filename, inputStream, len);
    {} //System.out.println("storing " + path + "\\" + filename + " to "+ getDatasourceName());
    return getDocument();
  }

  public String getPath() { return path; }
  public void setPath(String s) { path=s; }
  public String getPathMetadata() { return pathMetadata; }
  public void setPathMetadata(String s) { pathMetadata=s; }
  public String getFilenameMetadata() { return filenameMetadata; }
  public void setFilenameMetadata(String s) { filenameMetadata=s; }

  private String path=null;
  private String pathMetadata=null;
  private String filenameMetadata=null;
}
