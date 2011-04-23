package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 24, 2004, 12:07 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class UnsupportedConstraint extends Exception {
  public UnsupportedConstraint(String s) { super(s); }
  public UnsupportedConstraint(String source, String field, String value) { super("For "+source+": "+field+"="+value); }
}
