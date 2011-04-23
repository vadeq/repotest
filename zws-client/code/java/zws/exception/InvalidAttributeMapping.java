package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 3, 2004, 11:31 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class InvalidAttributeMapping extends InvalidMapping {
  public InvalidAttributeMapping (String msg) { super(msg); }
  public InvalidAttributeMapping (String fieldName, String value) { super("For: "+fieldName+"="+value); }
}
