package com.zws.functor.filter;

import com.zws.functor.ListFunctor;

import java.util.*;

public class CollectiveFilter extends ListFunctor implements Filter {
  public final void execute() throws Exception { filter(); }
  public final void filter() throws Exception { setResults(executeFilter(getDataList())); }

  private final Collection executeFilter(Collection dataList)  throws Exception {
    initialize();
    Collection results = prepareData(dataList);
    results = filterData(results);
    results = finalizeResults(results);
    return results;
  }

  public void initialize() throws Exception {}
  public Collection prepareData(Collection c)  throws Exception { return c; }
  public Collection filterData(Collection dataList)  throws Exception  {
    Object data=null;
    Collection results = new Vector();
    Iterator i = dataList.iterator();
    while (i.hasNext()) {
      data = i.next();
      if (keep(data)) results.add(transform(data));
    }
    return results;
  }
  public Collection finalizeResults(Collection c)  throws Exception { return c; }

  public boolean keep(Object data)  throws Exception { return true; }
  public Object transform(Object data)  throws Exception { return data; }

  public void bind(Collection c) { bind((Object)c); }
  public Collection getDataList() { return (Collection)getBinding(); }
  public void setDataList(Collection c) { bind(c); }

  public Collection getResults() { return (Collection) getResult(); }
  public void setResults(Collection c) { setResult(c); }
}
