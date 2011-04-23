package zws.action;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 12:54 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.op.ListOpBase;
import zws.op.Op;
import zws.op.util.Getter;
import zws.origin.Origin;

import java.util.*;

public class ActionListBase extends ListOpBase implements ActionList {
  
  public void execute() throws Exception {
    resetStorage();
    initializeStorage(new Vector());
    Iterator i = actions.iterator();
    Action action;
    while (i.hasNext()){
      action = (Action)i.next();
      action.setContext(getContext());
      action.execute();
      store(action.getResult());
    }
  }

  public String getName() { return name; }
  public void setName(String s) { name=s; }

  public Metadata grabMetadata() { 
    Metadata data = (Metadata)get("metadata");
    if (null==data) data = (Metadata) getContext().get(Names.CTX_METADATA);
    return data;
  }
  
  public Origin grabOrigin() {
    Origin o = (Origin)get("origin");
    if (null==o){
      Metadata data = grabMetadata();
      if (null!=data) o = data.getOrigin();
    }
    return o;
  }

  public boolean getBreakOnException() { return breakOnException; }
  public void  setBreakOnException(boolean b) { breakOnException = b; }

  //public Object getResult() { return resultList; }
  //public void setResult(Object c) { resultList = (Collection) c; }
  //public Collection getResults(){ return resultList; }
  //public void setResults(Collection c) { resultList = c; }

  public void add(Op a) { actions.add(a); }

  private String capitalize(String s) { return s.substring(0,1).toUpperCase() + s.substring(1); }
  protected String getString(String property) { return (String)get(property); }  
  protected Object get(String property) { try {return getRequired(property); } catch (Throwable e) { return null; } }
  protected String getRequiredString(String property) throws Exception { return (String)getRequired(property); }
  protected Object getRequired(String property) throws Exception {
    String prop = capitalize(property);
    Object value = null;
    Getter getter = new Getter();
    getter.setObject(this);
    try {
//      System.out.print("looking for " + property+":");
      getter.setPropertyName(property);
      getter.execute();
      value = getter.getResult();
//      {} //System.out.println(value);
      if (null!=value) return value;
    }
    catch (Throwable e) { }
    try {
//      System.out.print("looking for " + "meta" + prop+":");
      getter.setPropertyName("meta"+ prop);
      getter.execute();
      value = getter.getResult();
//      {} //System.out.println(value);
      if (null!=value) value = grabMetadata().get((String)value);
//      {} //System.out.println(value+"~");
//      {} //System.out.println(value);
      if (null!=value) return value;      
    }
    catch (Throwable e) { }
    try {
//      System.out.print("looking for " + "ctx"+prop+":");
      getter.setPropertyName("ctx"+ prop);
      getter.execute();
      value = getter.getResult();
//      {} //System.out.println(value+"~");
      if (null!=value) value = getContext().get((String)value);
//      {} //System.out.println(value);
      if (null!=value) return value;
    }
    catch (Throwable e) { }
    try {
//      System.out.print("looking for " + property+"Op:");
      getter.setPropertyName(property+"Op");
      getter.execute();
      Op op = (Op)getter.getResult();
      op.setContext(getContext());
      op.execute();
      value = op.getResult();
//      {} //System.out.println(value);
      if (null!=value) return value;
    }
    catch (Throwable e) { }
    try {
//      System.out.print("looking for " + "ctxDefault" + prop+":");
      getter.setPropertyName("ctxDefault"+ prop);
      getter.execute();
      value = getter.getResult();
//      {} //System.out.println(value+"~");
      if (null!=value) value = getContext().get((String)value);
//      {} //System.out.println("Current Context");
//      getContext().dump(System.out);
//      {} //System.out.println(value);
      if (null!=value) return value;
    }
    catch (Throwable e) { }
    throw new Exception(property + " property not specified for action " + getClass().getName());
  }

  public String getCtxMetadata() { return ctxMetadata; }
  public void setCtxMetadata(String s) { ctxMetadata=s; }
  public Op getMetadataOp() { return metadataOp; }
  public void setMetadataOp(Op op) { metadataOp=op; }
  
  public String getCtxOrigin() { return ctxOrigin; }
  public void setCtxOrigin(String s) { ctxOrigin=s; }
  public Op getOriginOp() { return originOp; }
  public void setOriginOp(Op op) { originOp=op; }


  private String name;
  private Collection actions = new Vector();
  //private Collection resultList;
  private boolean breakOnException;

  private String ctxOrigin=null;
  private Op originOp=null;
  
  private String ctxMetadata=null;
  private Op metadataOp=null;
}
