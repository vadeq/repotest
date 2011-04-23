package zws.data.filter;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.ListOpBase;

import java.util.Collection;
import java.util.Iterator;

public abstract class ListFilterBase extends ListOpBase implements ListFilter {
  public void execute() throws Exception {
    filter(getBindingList());
  }
  public Object filter(Object list) throws Exception { filter((Collection) list); return list; }
  public void filter(Collection list) throws Exception {
    resetStorage();
    Iterator i = list.iterator();
    Object o=null;
    while (i.hasNext()) {
      o = i.next();
      if (!keep(o)) i.remove();
    }
    initializeStorage(list);     
  }
  public abstract boolean keep(Object o) throws Exception;
}
