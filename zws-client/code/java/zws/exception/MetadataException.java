package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 23, 2004, 2:46 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

public class MetadataException extends Exception {
  public MetadataException(Metadata data, String msg) {
    super(msg);
    metadata=data;
  }
  
  public Metadata getMetadata() { return metadata; }
  
  private Metadata metadata=null;
}
