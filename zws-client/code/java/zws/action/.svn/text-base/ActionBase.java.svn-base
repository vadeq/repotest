package zws.action;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 4:42 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.op.Op;
import zws.op.OpBase;
import zws.op.util.Getter;
import zws.origin.Origin;
import zws.util.Named;

import java.util.StringTokenizer;

public abstract class ActionBase extends OpBase implements Action, Named {
  public abstract void execute() throws Exception; 	

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

  /*
  public void set(KeyValue pair) {
   //+++ todo create a zws.op.util.Setter
    Setter setter = new Setter();
    setter.setProperty(pair.getKey());
    setter.setValue(pair.getValue());
    setter.execute();
  }
  */
  
  private String capitalize(String s) { return s.substring(0,1).toUpperCase() + s.substring(1); }
  protected String getString(String property) { return (String)get(property); }  
  protected Object get(String property) { try {return getRequired(property); } catch (Exception e) { return null; } }
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
    catch (Exception e) {}
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
    catch (Exception e) {}
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
    catch (Exception e) {}
    Op op=null;
    try {
//      System.out.print("looking for " + property+"Op:");
      getter.setPropertyName(property+"Op");
      getter.execute();
      op = (Op)getter.getResult();
    }
    catch (Exception e) {}
    if (null!=op) {
      op.setContext(getContext());
      op.execute(); 
      value = op.getResult();
      if (null!=value) return value;
    }
//        {} //System.out.println(value);   
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
    catch (Exception e) {}
    throw new Exception(property + " property not specified for action " + getClass().getName());
  }

  protected String parse(String identifiers) throws Exception { return parse(identifiers, false); }
  protected String parse(String identifiers, boolean preserveDelimiter) throws Exception {
    if (null==identifiers) return "";
    StringTokenizer tokens = new StringTokenizer(identifiers, Names.DELIMITER);
    if (1>tokens.countTokens()) return extractIdentifier(identifiers);
    String s="";
    if (tokens.hasMoreTokens()) s += extractIdentifier(tokens.nextToken().trim());
    while (tokens.hasMoreTokens()) {
      if (preserveDelimiter) s+=Names.DELIMITER;
      s += extractIdentifier(tokens.nextToken().trim());
    }
    return s;
  }
  
  private String extractIdentifier(String identifier) throws Exception {
    String s=null;
    if (identifier.startsWith("'") && identifier.endsWith("'")){
      s = identifier.substring(1,identifier.length()-1);
      if (null==s) throw new Exception ("Invalid identifer: " + identifier);
      s=s.trim();
      return s;
    }
    else if (identifier.startsWith("meta") && identifier.endsWith("]") && 3 < identifier.indexOf("[")) {
      s = identifier.substring(identifier.indexOf("[")+1,identifier.length()-1);
      if (null==s) throw new Exception ("Invalid identifer: " + identifier);
      s=s.trim();      
      return grabMetadata().get(s);
    }
    else if (identifier.startsWith("ctx") && identifier.endsWith("]") && 2 < identifier.indexOf("[")) {
      s = identifier.substring(identifier.indexOf("[")+1,identifier.length()-1);
      if (null==s) throw new Exception ("Invalid identifer: " + identifier);
      s=s.trim();
      Object o = getContext().get(s);
      if (null==o) throw new Exception("Context value for " + s + " is not Defined");
      s = o.toString().trim();
      return s;
    }
    else return identifier;
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
  
  private String ctxOrigin=null;
  private Op originOp=null;
  
  private String ctxMetadata=null;
  private Op metadataOp=null;
}
