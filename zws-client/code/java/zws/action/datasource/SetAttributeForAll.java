package zws.action.datasource;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 18, 2004, 1:33 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.UnsupportedOperation;
import zws.op.Op;

public class SetAttributeForAll extends WorkspaceAction {
  public void execute() throws Exception {
    throw new UnsupportedOperation("SetAttributeForAll");
    /*
    Origin o = grabOrigin();
    String serverName = o.getServerName();
    String datasourceName = o.getDatasourceName();
    getDatasourceAccess().setAttributeForAll(serverName, datasourceName, getRequiredString("workspace"),  getRequiredString("attribute"), getRequiredString("value"), authenticationID());
     */
  }
  
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
