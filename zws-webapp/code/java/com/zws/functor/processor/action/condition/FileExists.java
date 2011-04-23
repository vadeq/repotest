package com.zws.functor.processor.action.condition;
import com.zws.application.Constants;
import com.zws.util.FileNameUtil;

import java.io.File;

public class FileExists extends Conditional {
  public boolean isTrue() {
    String file;
    if (null!= fileNameMetaData) {
      if ("name".equalsIgnoreCase(fileNameMetaData)) file = getDocument().getName();
      else file= getDocument().get(fileNameMetaData);
    }
    else file=fileName;
    if (null!=type) file = FileNameUtil.convertType(file, type);
    if (null!= pathMetaData) file= getDocument().get(pathMetaData) + Constants.FILE_SEPARATOR + file;
    else if (null!= path) file= path + Constants.FILE_SEPARATOR + file;
    File f = new File (file);
    return (f.exists() && f.length()>minimumSize);
  }

  public String getFileName() { return fileName; }
  public void setFileName(String s) { fileName=s; }
  public String getPath() { return path; }
  public void setPath(String s) { path=s; }
  public String getFileNameMetaData() { return fileNameMetaData; }
  public void setFileNameMetaData(String s) { fileNameMetaData=s; }
  public String getPathMetaData() { return pathMetaData; }
  public void setPathMetaData(String s) { pathMetaData=s; }
  public String getType() { return type; }
  public void setType(String s) { type=s; }
  public int getMinimumSize() { return minimumSize; }
  public void setMinimumSize(int i) { minimumSize=i; }

  private String fileName=null;
  private String path=null;
  private String fileNameMetaData=null;
  private String pathMetaData=null;
  private String type=null;
  private int minimumSize=-1;
}