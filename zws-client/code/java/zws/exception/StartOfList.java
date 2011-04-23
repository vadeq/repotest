package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 4:18 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class StartOfList extends Exception {
  public StartOfList(Object o) { super(o.toString() + " is the first item in the list"); }
}
