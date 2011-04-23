package com.zws.functor;

import java.util.Collection;

public abstract class ListFunctor extends Functor {
  public void bind(Object o) { bindingList = (Collection) o; }
  public void bind(Collection c ) { bindingList = c; }
  public Object getBinding() { return bindingList; }
  public Collection getBindingList() { return bindingList; }

  public Object getResult() { return resultList; }
  public void setResult(Object c) { resultList = (Collection) c; }

  public Collection getResults(){ return resultList; }
  public void setResults(Collection c) { resultList = c; }


  private Collection bindingList;
  private Collection resultList;
}
