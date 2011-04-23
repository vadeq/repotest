package zws.action.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 4:42 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.ActionBase;
import zws.op.Op;

public class GetValue extends ActionBase {
  public final void execute () throws Exception { setResult(getValue()); }
  private String getValue() throws Exception { return grabMetadata().get(getString("fieldname")); }

  public String getFieldname() { return fieldname; }
  public void setFieldname(String s) { fieldname=s; }
  public String getMetaFieldname() { return metaFieldname; }
  public void setMetaFieldname(String s) { metaFieldname=s; }
  public String getCtxFieldname() { return ctxFieldname; }
  public void setCtxFieldname(String s) { ctxFieldname=s; }
  public Op getFieldnameOp() { return fieldnameOp; }
  public void setFieldnameOp(Op op) { fieldnameOp=op; }
  public String getCtxDefaultFieldname() { return ctxDefaultFieldname; }
  public void setCtxDefaultFieldname(String s) { ctxDefaultFieldname=s; }

  private String fieldname=null;
  private String metaFieldname=null;
  private String ctxFieldname=null;
  private Op fieldnameOp=null;
  private String ctxDefaultFieldname=null;
}
