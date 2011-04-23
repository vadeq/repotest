package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 11, 2004, 10:03 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.Op;
import zws.origin.Origin;
import zws.util.FileNameUtil;

public class Link extends WorkspaceAction {
  public void execute() throws Exception { 
    Origin o = grabOrigin();
    String serverName = o.getServerName();
    String datasourceName = o.getDatasourceName();
    String parent = getString("parentFilename");
    String child = getRequiredString("childFilename");
    String parentType = getString("parentFileType");
    String childType = getString("childFileType");
    if (null==parent) parent = grabMetadata().getName();
    if (null!=parentType) parent = FileNameUtil.convertType(parent, parentType);
    if (null!=childType) child = FileNameUtil.convertType(child, childType); 
    if (getLinkInReverse()) { String temp=parent; parent=child; child=temp; } //swap
    getDatasourceAccess().link(serverName, datasourceName, getRequiredString("workspace"), parent, child, authenticationID());
  }

  public String getParentFilename(){ return parentFilename; }
  public void setParentFilename(String s) { parentFilename=s; }
  public String getMetaParentFilename() { return metaParentFilename; }
  public void setMetaParentFilename(String s) { metaParentFilename=s; }
  public String getCtxParentFilename() { return ctxParentFilename; }
  public void setCtxParentFilename(String s) { ctxParentFilename=s; }
  public Op getParentFilenameOp() { return parentFilenameOp; }
  public void setParentFilenameOp(Op op) { parentFilenameOp=op; }
  public String getCtxDefaultParentFilename() { return ctxDefaultParentFilename; }
  public void setCtxDefaultParentFilename(String s) { ctxDefaultParentFilename=s; }

  public String getParentFileType(){ return parentFileType; }
  public void setParentFileType(String s) { parentFileType=s; }
  public String getMetaParentFileType() { return metaParentFileType; }
  public void setMetaParentFileType(String s) { metaParentFileType=s; }
  public String getCtxParentFileType() { return ctxParentFileType; }
  public void setCtxParentFileType(String s) { ctxParentFileType=s; }
  public Op getParentFileTypeOp() { return parentFileTypeOp; }
  public void setParentFileTypeOp(Op op) { parentFileTypeOp=op; }
  public String getCtxDefaultParentFileType() { return ctxDefaultParentFileType; }
  public void setCtxDefaultParentFileType(String s) { ctxDefaultParentFileType=s; }
   
  public String getChildFilename() { return childFilename; }
  public void setChildFilename(String s) { childFilename=s; }
  public String getMetaChildFilename() { return metaChildFilename; }
  public void setMetaChildFilename(String s) { metaChildFilename=s; }
  public String getCtxChildFilename() { return ctxChildFilename; }
  public void setCtxChildFilename(String s) { ctxChildFilename=s; }
  public Op getChildFilenameOp() { return childFilenameOp; }
  public void setChildFilenameOp(Op op) { childFilenameOp=op; }
  public String getCtxDefaultChildFilename() { return ctxDefaultChildFilename; }
  public void setCtxDefaultChildFilename(String s) { ctxDefaultChildFilename=s; }
  
  public String getChildFileType() { return childFileType; }
  public void setChildFileType(String s) { childFileType=s; }
  public String getMetaChildFileType() { return metaChildFileType; }
  public void setMetaChildFileType(String s) { metaChildFileType=s; }
  public String getCtxChildFileType() { return ctxChildFileType; }
  public void setCtxChildFileType(String s) { ctxChildFileType=s; }
  public Op getChildFileTypeOp() { return childFileTypeOp; }
  public void setChildFileTypeOp(Op op) { childFileTypeOp=op; }
  public String getCtxDefaultChildFileType() { return ctxDefaultChildFileType; }
  public void setCtxDefaultChildFileType(String s) { ctxDefaultChildFileType=s; }

  public boolean getLinkInReverse() { return linkInReverse; }
  public void setLinkInReverse(boolean b) { linkInReverse=b; }
  private String parentFilename=null;
  private String metaParentFilename=null;
  private String ctxParentFilename=null;
  private Op parentFilenameOp=null;
  private String ctxDefaultParentFilename;

  private String parentFileType=null;
  private String metaParentFileType=null;
  private String ctxParentFileType=null;
  private Op parentFileTypeOp=null;
  private String ctxDefaultParentFileType;
  
  private String childFilename=null;
  private String metaChildFilename=null;
  private String ctxChildFilename=null;
  private Op childFilenameOp=null;
  private String ctxDefaultChildFilename;

  private String childFileType=null;
  private String metaChildFileType=null;
  private String ctxChildFileType=null;
  private Op childFileTypeOp=null;
  private String ctxDefaultChildFileType;
  
  private boolean linkInReverse = false;
}
