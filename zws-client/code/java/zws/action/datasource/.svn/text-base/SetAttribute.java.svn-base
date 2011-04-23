package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 18, 2004, 1:29 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.Op;
import zws.origin.Origin;
import zws.util.FileNameUtil;

public class SetAttribute extends WorkspaceAction {
  public void execute() throws Exception {
    Origin o = grabOrigin();
    String serverName = o.getServerName();
    String datasourceName = o.getDatasourceName();
    String name = getString("filename");
    if (null==name) name = grabMetadata().getName();
    String type = getString("fileType");
    if (null!=fileType) name=FileNameUtil.convertType(name, type);
    getDatasourceAccess().setAttribute(serverName, datasourceName, getRequiredString("workspace"), name,  getRequiredString("attribute"), getRequiredString("value"), authenticationID());
  }
  
  public String getFilename() { return filename; }
  public void setFilename(String s)  { filename=s; }
  public String getMetaFilename() { return metaFilename; }
  public void setMetaFilename(String s) { metaFilename=s; }
  public String getCtxFilename() { return ctxFilename; }
  public void setCtxFilename(String s) { ctxFilename=s; }
  public Op getFilenameOp () { return filenameOp; }
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

  public String getAttribute() { return attribute; }
  public void setAttribute(String s) { attribute=s; }
  public String getMetaAttribute() { return metaAttribute; }
  public void setMetaAttribute(String s) { metaAttribute=s; }
  public String getCtxAttribute() { return ctxAttribute; }
  public void setCtxAttribute(String s) { ctxAttribute=s; }
  public Op getAttributeOp() { return attributeOp; }
  public void setAttributeOp(Op op) { attributeOp=op; }
  public String getCtxDefaultAttribute() { return ctxDefaultAttribute; }
  public void setCtxDefaultAttribute(String s) { ctxDefaultAttribute=s; }
  
  public String getValue() { return value; }
  public void setValue(String s) { value=s; }
  public String getMetaValue() { return metaValue; }
  public void setMetaValue(String s) { metaValue=s; }
  public String getCtxValue() { return ctxValue; }
  public void setCtxValue(String s) { ctxValue=s; }
  public Op getValueOp () { return valueOp; }
  public void setValueOp(Op op) { valueOp=op; }
  public String getCtxDefaultValue() { return ctxDefaultValue; }
  public void setCtxDefaultValue(String s) { ctxDefaultValue=s; }
  
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
  
  private String attribute=null;
  private String metaAttribute=null;
  private String ctxAttribute=null;
  private Op attributeOp=null;
  private String ctxDefaultAttribute=null;
  
  private String value=null;
  private String metaValue=null;
  private String ctxValue=null;
  private Op valueOp=null;
  private String ctxDefaultValue=null;
}
