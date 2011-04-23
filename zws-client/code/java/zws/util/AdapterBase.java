package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

public abstract class AdapterBase extends OpBase implements Adapter {
  public void execute() throws Exception { adapt(getBinding()); }
  public abstract void adapt(Object o)throws Exception;
}
