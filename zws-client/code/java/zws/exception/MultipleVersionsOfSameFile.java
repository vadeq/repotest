package zws.exception;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;

public class MultipleVersionsOfSameFile extends Exception {
 public MultipleVersionsOfSameFile(Origin o1, Origin o2) {    super("["+o1.getUniqueID()+"] : ["+o2.getUniqueID()+"]"); }
}