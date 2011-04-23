package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2004, 12:41 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class RequiresDependency extends Exception {
  public RequiresDependency() { super(); }
  public RequiresDependency(String msg) { super(msg); }
}
