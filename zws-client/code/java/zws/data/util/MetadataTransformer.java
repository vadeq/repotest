package zws.data.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 12, 2003, 3:48 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.util.Transformer;

public interface MetadataTransformer extends Transformer {
  public abstract void adapt(Metadata data) throws Exception;
  public abstract Object transform() throws Exception;
}
