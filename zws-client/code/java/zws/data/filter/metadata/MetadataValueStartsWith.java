package zws.data.filter.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 27, 2004, 12:54 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class MetadataValueStartsWith extends MetadataValueBase {
  protected boolean testValue(String metadataValue, String value) throws Exception {
    return metadataValue.startsWith(value);
  }
}
