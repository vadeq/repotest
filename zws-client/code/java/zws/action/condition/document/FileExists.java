package zws.action.condition.document;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 18, 2004, 5:17 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DocumentAccess;
import zws.action.condition.Conditional;
import zws.application.Constants;
import zws.op.Op;
import zws.util.FileNameUtil;

import java.io.File;

public class FileExists extends Conditional {
  public boolean isTrue() throws Exception {
    if (null==getString("location")) return localFileExists();
    else return remoteFileExists();
  }
  
  public boolean localFileExists() throws Exception {
    String p = getString("localPath");
    String file=getRequiredString("filename");
    String type = getString("fileType");
    if (null!=type) file = FileNameUtil.convertType(file, type);
    if (null!=p) file = p + Constants.PATH_SEPARATOR + file;
    File f = new File (file);
    return (f.exists());
  }
  
  public boolean remoteFileExists() throws Exception {
    String server = getString("serverName");
    if (null==server) server = grabOrigin().getServerName();
    String file = getRequiredString("filename");
    String type = getString("fileType");
    if (null!=type) file = FileNameUtil.convertType(file, type);
    return DocumentAccess.fileExists(server, getRequiredString("location"), file);
  }

  public String getLocalPath() { return localPath; }
  public void setLocalPath(String s) { localPath=s; }
  public String getMetaLocalPath() { return metaLocalPath; }
  public void setMetaLocalPath(String s) { metaLocalPath=s; }
  public String getCtxLocalPath() { return ctxLocalPath; }
  public void setCtxLocalPath(String s) { ctxLocalPath=s; }
  public Op getLocalPathOp() { return localPathOp; }
  public void setLocalPathOp(Op op ) { localPathOp=op; }
  public String getCtxDefaultLocalPath() { return ctxDefaultLocalPath; }
  public void setCtxDefaultLocalPath(String s) { ctxDefaultLocalPath=s; }

  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }
  public String getMetaServerName() { return metaServerName; }
  public void setMetaServerName(String s) { metaServerName=s; }
  public String getCtxServerName() { return ctxServerName; }
  public void setCtxServerName(String s) { ctxServerName=s; }
  public Op getServerNameOp() { return serverNameOp; }
  public void setServerNameOp(Op op) { serverNameOp=op; }
  public String getCtxDefaultServerName() { return ctxDefaultServerName; }
  public void setCtxDefaultServerName(String s) { ctxDefaultServerName=s; }
  
  public String getLocation() { return location; }
  public void setLocation(String s) { location=s; }
  public String getMetaLocation() { return metaLocation; }
  public void setMetaLocation(String s) { metaLocation=s; }
  public String getCtxLocation() { return ctxLocation; }
  public void setCtxLocation(String s) { ctxLocation=s; }
  public Op getLocationOp() { return locationOp; }
  public void setLocationOp(Op op) { locationOp=op; }
  public String getCtxDefaultLocation() { return ctxDefaultLocation; }
  public void setCtxDefaultLocation(String s) { ctxDefaultLocation=s; }
  
  public String getFilename() { return filename; } 
  public void setFilename(String s) { filename=s; }
  public String getMetaFilename() { return metaFilename; }
  public void setMetaFilename(String s) { metaFilename=s; }
  public String getCtxFilename() { return ctxFilename; }
  public void setCtxFilename(String s) { ctxFilename=s; }
  public Op getFilenameOp() { return filenameOp; }
  public void setFilenameOp(Op op) { filenameOp=op; }
  public String getCtxDefaultFilename() { return ctxDefaultFilename; }
  public void setCtxDefaultFilename(String s) { ctxDefaultFilename=s; }

  public String getFileType() { return fileType; }
  public void setFileType(String s) { fileType=s; }
  public String getMetaFileType() { return metaFileType; }
  public void setMetaFileType(String s) { metaFileType=s; }
  public String getCtxFileType() { return ctxFileType; }
  public void setCtxFileType(String s) { ctxFileType=s; }
  public Op getFileTypeOp() { return fileTypeOp; }
  public void setFileTypOp(Op op) { fileTypeOp=op; }
  public String getCtxDefaultFileType() { return ctxDefaultFileType; }
  public void setCtxDefaultFileType(String s) { ctxDefaultFileType=s; }

  private String localPath=null;
  private String metaLocalPath=null;
  private Op localPathOp=null;
  private String ctxLocalPath;
  private String ctxDefaultLocalPath;
  
  private String serverName=null;
  private String metaServerName=null;
  private String ctxServerName=null; 
  private Op serverNameOp=null;
  private String ctxDefaultServerName = "server-name";
  
  private String location=null;
  private String metaLocation=null;
  private String ctxLocation=null;
  private Op locationOp=null;
  private String ctxDefaultLocation="location";
  
  private String filename=null;
  private String metaFilename=null; 
  private String ctxFilename=null;
  private Op filenameOp=null;
  private String ctxDefaultFilename=null;
  
  private String fileType=null;
  private String metaFileType=null;
  private String ctxFileType=null;
  private Op fileTypeOp=null;
  private String ctxDefaultFileType="file-type";  
}
