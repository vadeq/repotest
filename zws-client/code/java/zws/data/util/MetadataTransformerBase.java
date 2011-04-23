package zws.data.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 12, 2003, 3:48 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.util.TransformerBase;

public abstract class MetadataTransformerBase extends TransformerBase implements MetadataTransformer {
  public void adapt(Object data) throws Exception { adapt((Metadata) data); }
  public Object transform() throws Exception { return transformMetadata(); }

  public abstract void adapt(Metadata data) throws Exception;
  public abstract Metadata transformMetadata() throws Exception;

  public final Metadata transform(Metadata data) throws Exception { adapt(data); return transformMetadata(); }
}
