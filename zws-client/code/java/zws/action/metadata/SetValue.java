package zws.action.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 4:42 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.ActionBase;
import zws.op.Op;

public class SetValue extends ActionBase {
  public final void execute () throws Exception { setResult(setValue()); }

  private Object setValue() throws Exception {
    String field = getString("fieldname");
    String val = getString("value");
    grabMetadata().set(field, val);
    return val;
  }
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

  public String getValue() { return value; }
  public void setValue(String s) { value = s; }
  public String getMetaValue() { return metaValue; }
  public void setMetaValue(String s) { metaValue = s; }
  public String getCtxValue() { return ctxValue; }
  public void setCtxValue(String s) { ctxValue = s; }
  public Op getValueOp() { return valueOp; }
  public void setValueOp(Op op) { valueOp = op; }
  public String getCtxDefauleValue() { return ctxDefaultValue; }
  public void setCtxDefaultValue(String s) { ctxDefaultValue=s; }
  
  public void add(Op op) { 
    if (null==fieldname && null==metaFieldname && null==ctxFieldname && null==fieldnameOp && null==ctxDefaultFieldname) fieldnameOp=op;
    else valueOp = op; 
  }
    
  private String fieldname=null;
  private String metaFieldname=null;
  private String ctxFieldname=null;
  private Op fieldnameOp=null;
  private String ctxDefaultFieldname=null;
  
  private String value=null;
  private String metaValue=null;
  private String ctxValue= null;
  private Op valueOp = null;
  private String ctxDefaultValue=null;
}
