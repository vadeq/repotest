package zws.data.filter;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

public abstract class UnitFilterBase extends OpBase implements UnitFilter {
  public void execute() throws Exception { setResult(filter(getBinding())); }
  public Object filter(Object o) throws Exception {
    if (keep(o)) return o;
    return null;
  }
  public abstract boolean keep(Object o) throws Exception;
}
