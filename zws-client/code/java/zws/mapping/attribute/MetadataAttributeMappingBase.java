package zws.mapping.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 3, 2004, 11:10 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidMapping;
import zws.mapping.MetadataMappingBase;

public abstract class MetadataAttributeMappingBase extends MetadataMappingBase implements MetadataAttributeMapping {
  protected abstract boolean isMapped();
  protected abstract void mapSourceToTarget() throws InvalidMapping;
  
  public String getFieldName() { return fieldName; }
  public void setFieldName(String s) { fieldName= s.trim(); }

  public String getNewFieldName() { if (null==newFieldName) return fieldName; else return newFieldName; }
  public void setNewFieldName(String s) { newFieldName= s.trim(); }

  
  private String fieldName=null;
  private String newFieldName=null;
  
}
