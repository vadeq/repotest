package zws.mapping.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 3, 2004, 11:05 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.exception.InvalidMapping;
import zws.mapping.MetadataMapping;

public interface MetadataAttributeMapping extends MetadataMapping {
  public void map() throws InvalidMapping; 
  
  public Metadata getSource();
  public void setSource(Metadata m);

  public Metadata getTarget();
  public void setTarget(Metadata m);
  
  public String getFieldName();
  public void setFieldName(String s);

  public String getNewFieldName();
  public void setNewFieldName(String s);
}
