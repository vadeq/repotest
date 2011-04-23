package zws.mapping;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 18, 2004, 11:19 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.exception.InvalidMapping;
import zws.op.OpBase;
import zws.util.RoutedData;

public abstract class MetadataMappingBase extends OpBase implements MetadataMapping {
  public void execute() throws Exception { 
    if (isMapped()) map();
    setResult(getTarget());
  }
  
  protected abstract boolean isMapped();
  protected abstract void mapSourceToTarget() throws InvalidMapping;
  
  public final void map() throws InvalidMapping {
    if(!isMapped()) return;
    mapSourceToTarget();
  }

  public void setSource(Metadata m) { source=m; }
  public Metadata getSource() { return source; }
  public Metadata getTarget() { return target; }
  public void setTarget(Metadata m) { target=m; }
  
  public RoutedData getSourceRouting() {
    if (null==getContext()) return null;
    return getContext().getSourceRouting();
  }
  
  public RoutedData getTargetRouting() {
    if (null==getContext()) return null;
    return getContext().getTargetRouting();
  }

  private Metadata source=null;
  private Metadata target=null;
}
