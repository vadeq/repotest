package zws.exception;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class DuplicateName extends Exception {
 public DuplicateName(String name, String nameSpace) { super(name + ": name already exists for " + nameSpace); }
 public DuplicateName(String name) { super(name + ": already exists"); }
}