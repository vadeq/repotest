package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.ListOpBase;

import java.util.Collection;

public abstract class ListTransformerBase extends ListOpBase implements ListTransformer {
  public void execute() throws Exception {
    resetStorage();
    adapt(getBindingList());
    initializeStorage(transformList()); 
  }

  public final void adapt(Object list) throws Exception { adapt ((Collection) list); }

  public abstract void adapt(Collection list) throws Exception;
  public abstract Collection transformList() throws Exception;

  public final Object transform() throws Exception { return transformList(); }
  public final Collection transformList(Collection list) throws Exception { adapt(list); return transformList(); }
}
