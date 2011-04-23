package zws.data.filter.metadata;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.filter.UnitFilterBase;

public abstract class MetadataUnitFilterBase extends UnitFilterBase implements MetadataUnitFilter {
  public Metadata filter(Metadata data) throws Exception{
    if (keep(data)) return data;
    return null;
  }
  public abstract boolean keep(Metadata data) throws Exception;

  public Metadata getMetadata() { return metadata; }
  public void setMetadata(Metadata data) { metadata=data; }

  // hook overrides
  public final Object filter(Object data) throws Exception { return filter((Metadata)data); }
  public final boolean keep(Object data) throws Exception { return keep((Metadata)data); }
  public Object getBinding() { return getMetadata(); }
  public void bind(Object data) { setMetadata((Metadata) data); }

  //typed binding
  private Metadata metadata;
}
