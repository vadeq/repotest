package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 11:19 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class MismatchedKey extends Exception {
 public MismatchedKey(String badKey, String goodKey) { super(badKey + " does not match " + goodKey); }
}