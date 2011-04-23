package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 1, 2004, 4:30 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class NotAString extends zwsException { 
  public NotAString(Object o) { super(o.toString()); }
}
