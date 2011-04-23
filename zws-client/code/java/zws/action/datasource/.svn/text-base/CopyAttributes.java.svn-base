package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 18, 2004, 1:12 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.op.Op;
import zws.origin.Origin;
import zws.util.FileNameUtil;

public class CopyAttributes extends WorkspaceAction {

  public void execute() throws Exception {
    Origin o = grabOrigin();
    Metadata data = grabMetadata();
    String serverName = o.getServerName();
    String datasourceName = o.getDatasourceName();
    String name = getString("filename");
    if (null==name) name = data.getName();
    String type = getString("fileType");
    if (null!=fileType) name=FileNameUtil.convertType(name, type);
    getDatasourceAccess().setAttributes(serverName, datasourceName, getRequiredString("workspace"), name,  data.getAttributes(), authenticationID());
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
