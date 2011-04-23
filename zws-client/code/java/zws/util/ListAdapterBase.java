package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.ListOpBase;

import java.util.Collection;

public abstract class ListAdapterBase extends ListOpBase implements ListAdapter {
  public void execute() throws Exception { adapt(getBindingList()); }  

  public final void adapt(Object list)throws Exception { adapt((Collection)list); }

  public abstract void adapt(Collection list) throws Exception;
}
