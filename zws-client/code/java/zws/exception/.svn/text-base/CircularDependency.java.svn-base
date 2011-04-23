package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 23, 2004, 9:59 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class CircularDependency extends Exception {
  public CircularDependency(String msg) { super(msg); }
  public CircularDependency(Object parent, Object child) {
   super(parent.toString() + " as parent of " + child.toString() + " creates a circular Dependency");
   p=parent;
   c=child;
  }
  
  public Object getParent() { return p; }
  public Object getChild() { return c; }
  
  private Object p=null, c=null;
}
