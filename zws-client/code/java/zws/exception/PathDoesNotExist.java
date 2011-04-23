package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 8, 2004, 11:05 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class PathDoesNotExist extends Exception {
  public PathDoesNotExist(String path) { super(path); }
}
