package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 18, 2004, 10:45 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Unmodifyable extends Exception {
  public Unmodifyable(String name) { super(name + " is not modifyable"); }
}
