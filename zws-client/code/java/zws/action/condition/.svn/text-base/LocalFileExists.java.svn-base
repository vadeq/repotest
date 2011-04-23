package zws.action.condition;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 1:22 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Constants;
import zws.util.FileNameUtil;

import java.io.File;

public class LocalFileExists extends Conditional {
  public boolean isTrue() throws Exception {
    String file=getString("filename");
    String p = getString("path");
    String t = getString("type");
    if (null!=type) file = FileNameUtil.convertType(file, t);
    if (null!=p) file = p + Constants.PATH_SEPARATOR + file;
    File f = new File (file);
    return (f.exists() && f.length()>minimumSize);
  }

  public String getFilename() { return filename; }
  public void setFilename(String s) { filename=s; }
  public String getMetaFilename() { return metaFilename; }
  public void setMetaFilename(String s) { metaFilename=s; }
  public String getCtxFilename(String s) { return ctxFilename; }
  public void setCtxFilename(String s) { ctxFilename=s; }
  public String getCtxDefaultFilename() { return ctxDefaultFilename; }
  public void setCtxDefaultFilname(String s) { ctxDefaultFilename=s; }
  public String getPath() { return path; }
  public void setPath(String s) { path=s; }
  public String getMetaPath() { return metaPath; }
  public void setMetaPath(String s) { metaPath=s; }
  public String getCtxPath() { return ctxPath; }
  public void setCtxPath(String s) { ctxPath=s; }
  public String getCtxDefaultPath() { return ctxDefaultPath; }
  public void setCtxDefaultPath(String s) { ctxDefaultPath=s; }
  public String getType() { return type; }
  public void setType(String s) { type=s; }
  public int getMinimumSize() { return minimumSize; }
  public void setMinimumSize(int i) { minimumSize=i; }

  private String filename=null;
  private String metaFilename=null;
  private String ctxFilename=null;
  private String ctxDefaultFilename=null;
  private String path=null;
  private String metaPath=null;
  private String ctxPath;
  private String ctxDefaultPath;
  private String type=null;
  //private String metaType=null;
  //private String ctxType=null;
  private int minimumSize=-1;
}