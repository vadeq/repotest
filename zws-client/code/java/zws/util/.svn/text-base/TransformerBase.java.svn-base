package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

public abstract class TransformerBase extends OpBase implements Transformer {
  public void execute() throws Exception { adapt(getBinding()); setResult(transform()); }
  
  public void adapt(Object o) throws Exception { bind(o); }
  public abstract Object transform() throws Exception;

  public final Object transform(Object o) throws Exception { adapt(o); return transform(); }
}
