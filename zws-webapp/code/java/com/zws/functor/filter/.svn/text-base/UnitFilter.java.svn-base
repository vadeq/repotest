package com.zws.functor.filter;

import com.zws.functor.Functor;

public abstract class UnitFilter extends Functor implements Filter {
  public void execute() throws Exception { filter(); }
  public final void filter() throws Exception { setResult(executeFilter(getBinding())); }
  public final Object executeFilter(Object o) throws Exception {
    initialize();
    if (keep(o)) return transform(o);
    return null;
  }
  public void initialize() throws Exception {}
  public boolean keep(Object o) throws Exception { return true; } //override this
  public Object transform(Object o) throws Exception { return o; } //override this
}
