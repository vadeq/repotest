package zws.mapping.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 2:31 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class CopyAttribute extends RenameAttribute {
  public String getNewFieldName() { return getFieldName(); }
}
