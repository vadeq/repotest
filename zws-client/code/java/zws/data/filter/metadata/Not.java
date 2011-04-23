package zws.data.filter.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 27, 2004, 12:54 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

public class Not extends MetadataUnitFilterBase {

  public boolean keep(Metadata data) throws Exception {
    return !filterOp.keep(data);
  }

  public void add(MetadataUnitFilter op) { filterOp=op; }
  private MetadataUnitFilter filterOp=null;
}
