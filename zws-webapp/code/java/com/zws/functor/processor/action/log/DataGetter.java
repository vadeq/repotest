package com.zws.functor.processor.action.log;

import com.zws.functor.processor.action.Action;

public class DataGetter extends Action {

  public void execute() throws Exception { setResult(getActionLog().getData(name)); }

  public String getName() { return name; }
  public void setName(String s) { name=s; }

  private String name=null;
}
