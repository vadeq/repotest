package com.zws.functor.processor.action.condition;

import com.zws.functor.processor.action.Action;
public class IsNull extends Conditional {

  public boolean isTrue() throws Exception {
    compareTo.setActionLog(getActionLog());
    compareTo.execute();
    return null==compareTo.getResult();
  }

  public Action getCompareTo() { return compareTo; }
  public void setCompareTo(Action a) { compareTo=a; }
  public Action compareTo=null;
}
