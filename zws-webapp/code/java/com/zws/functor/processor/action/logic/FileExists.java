package com.zws.functor.processor.action.logic;

import com.zws.application.Constants;

import java.io.File;

public class FileExists extends Logical {

  public void execute () {
    String file="";
    if (!"".equals(pathMetaData)) file = getDocument().get(fileNameMetaData);
    else if (!"".equals(path)) file = path;
    if (!"".equals(fileNameMetaData)) file += file + Constants.FILE_SEPARATOR + getDocument().get(fileNameMetaData);
    else if (!"".equals(fileName)) file += Constants.FILE_SEPARATOR +fileName;
    File f = new File(file);
    setAnswer(f.exists());
  }

  public String getFileName() { return fileName; }
  public void setFileName(String s ) { fileName = s; }
  public String getFileNameMetaData() { return fileNameMetaData; }
  public void setFileNameMetaData(String s ) { fileNameMetaData = s; }
  public String getPath() { return path; }
  public void setPath(String s ) { path = s; }
  public String getPathMetaData() { return pathMetaData; }
  public void setPathMetaData(String s ) { pathMetaData = s; }

  private String fileName="";
  private String fileNameMetaData="";
  private String path="";
  private String pathMetaData="";
}
