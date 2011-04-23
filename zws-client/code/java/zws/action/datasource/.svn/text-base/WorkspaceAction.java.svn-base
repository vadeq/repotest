package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 9, 2004, 1:22 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DatasourceAccess;
import zws.action.ActionBase;
import zws.op.Op;
import zws.security.Authentication;

public abstract class WorkspaceAction extends ActionBase {
  public abstract void execute() throws Exception;
  
  protected DatasourceAccess getDatasourceAccess() { return DatasourceAccess.getDatasourceAccess(grabOrigin()); }

  protected Authentication authenticationID() { 
    try { return (Authentication) get("authentication");  }
    catch (Exception e) { return null; }
  }
  public String getWorkspace() { return workspace; }
  public void setWorkspace(String s) { workspace=s; }
  public String getMetaWorkspace(String s) { return metaWorkspace; }
  public void setMetaWorkspace(String s) { metaWorkspace=s; }
  public String getCtxWorkspace(String s) { return ctxWorkspace;}
  public void setCtxWorkspace(String s) { ctxWorkspace=s; }
  public Op getWorkspaceOp(Op op) { return  workspaceOp; }
  public void setWorkspaceOp(Op op) { workspaceOp=op; }
  public String getCtxDefaultWorkspace() { return ctxDefaultWorkspace; }
  public void setCtxDefaultWorkspace(String s) { ctxDefaultWorkspace=s; }
  
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

  public Authentication getAuthentication() { return authentication; }
  public void setAuthentication(Authentication id) { authentication=id; }
  public String getCtxAuthentication(String s) { return ctxAuthentication; }
  public void setCtxAuthentication(String s) { ctxAuthentication=s; }
  public Op getAuthenticationOp(Op op) { return authenticationOp; }
  public void setAuthenticationOp(Op op) { authenticationOp=op; }
  public String getCtxDefaultAuthentication() { return ctxDefaultAuthentication; }
  public void setCtxDefaultAuthentication(String s) { ctxDefaultAuthentication=s; }

  private String workspace=null;
  private String metaWorkspace=null;
  private String ctxWorkspace=null;
  private Op workspaceOp=null;
  private String ctxDefaultWorkspace="workspace";

  //location provided in base class as a convenience - as many workspace actions handle files between workspace and another location
  private String location=null;
  private String metaLocation=null;
  private String ctxLocation=null;
  private Op locationOp=null;
  private String ctxDefaultLocation="location";
  
  private Authentication authentication=null;
  private String ctxAuthentication=null;
  private Op authenticationOp=null;
  private String ctxDefaultAuthentication="authentication";
}
