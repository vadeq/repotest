package zws.action.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 12, 2004, 3:33 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.ActionBase;
import zws.op.Op;

public class PrintLine extends ActionBase {
  public void execute() throws Exception {
    Object o = get("line");
    String s="";
    if (null!=line) s="DesignState: ";
    else if (null!=metaLine) s = "meta-"+metaLine+"=";
    else if (null!=ctxLine) s = "ctx-"+ctxLine+"=";
    else if (null!=lineOp) s = "op: ";
    if (o==null) s += "NULL";
    else s += o.toString();
    {} //System.out.println(parse(s));
    setResult(o);
  }

  
  public void add(Op op) { lineOp=op; }
  
  public Object getLine() { return line; }
  public void setLine(Object s) { line=s; }
  public String getMetaLine() { return metaLine; }
  public void setMetaLine(String s) { metaLine=s; }
  public String getCtxLine() { return ctxLine; }
  public void setCtxLine(String s) { ctxLine=s; }
  public Op getLineOp() { return lineOp; }
  public void setLineOp(Op op) { lineOp=op; }

  private Object line=null;
  private String metaLine=null;
  private String ctxLine=null;
  private Op lineOp=null;
}
