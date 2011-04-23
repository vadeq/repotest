package zws.action.condition.document;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 18, 2004, 5:08 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DocumentAccess;
import zws.action.condition.Conditional;
import zws.op.Op;

public class LocationExists extends Conditional {
  public boolean isTrue() throws Exception {
    String server = getString("serverName");
    if (null==server) server = grabOrigin().getServerName();
    return DocumentAccess.locationExists(server, getRequiredString("location"));
  }

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
}
