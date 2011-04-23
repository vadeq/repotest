package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 10:20 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class SecureSpace extends Namespace implements SecureSpaced {
  
  public SecureSpace() {}
  
  public SecureSpace(String s) { 
   namespace = new Namespace(s.substring(0, s.lastIndexOf('.')));
   name = s.substring(s.lastIndexOf('.')+1);
  }
  public SecureSpace(String ns, String nm) {
    namespace = new Namespace(ns);
    name = nm;
  }
  
  public String getSpace() { return getNamespace().asString() + "." + getName(); }
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public Namespace getNamespace() { return namespace; }
  public void setNamespace(Namespace ns) { namespace=ns; }
  public void setNamespace(String s) { namespace = new Namespace(s); }
  public String getDescription() { return description; }
  public void setDescription(String s) { description=s; }
  public String getType() { return type; }
  public void setType(String s) { type=s; }

  private String name=null;
  private Namespace namespace=null;
  private String description=null;
  private String type=null;
}
