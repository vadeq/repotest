package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 9, 2004, 3:11 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DocumentAccess;
import zws.op.Op;
import zws.origin.Origin;
import zws.util.FileNameUtil;

public class Import extends WorkspaceAction {
  public void execute() throws Exception {
    Origin o = grabOrigin();
    String serverName = o.getServerName();
    String datasourceName = o.getDatasourceName();
    String file = getRequiredString("filename");
    String type = getString("fileType");
    if (null!=type) file = FileNameUtil.convertType(file, type);
    String location = getRequiredString("location");
    getDatasourceAccess().importToWorkspace(serverName, datasourceName, getRequiredString("workspace"), location, file, null);
    if (getRemoveSourcefile()) DocumentAccess.deleteFile(serverName, location, file);
  }

  public String getFilename() { return filename; }
  public void setFilename(String s) { filename=s; }
  public String getMetaFilename() { return metaFilename; }
  public void setMetaFilename(String s) { metaFilename=s; }
  public String getCtxFilename() { return ctxFilename; }
  public void setCtxFilename (String s) { ctxFilename=s; }
  public Op getFilenameOp() { return filenameOp; }
  public void setFilenameOp(Op op) { filenameOp = op; }
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

  public boolean getRemoveSourcefile() {return removeSourcefile; }
  public void setRemoveSourcefile(boolean b) { removeSourcefile=b; }

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
  
  private boolean removeSourcefile = false;
}
