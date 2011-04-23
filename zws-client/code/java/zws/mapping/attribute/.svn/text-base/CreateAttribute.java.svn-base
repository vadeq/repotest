package zws.mapping.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 2:37 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class CreateAttribute extends MetadataAttributeMappingBase {

  protected boolean isMapped() { return true; }
  
  protected  void mapSourceToTarget() { getTarget().set(getFieldName(), value); }
  
  public String getValue() { return value; }
  public void setValue(String s) { value=s; }
  private String value;
}
