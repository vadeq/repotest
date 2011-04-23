package com.zws.functor.processor.action;

public class ActionLogDataSetter extends Action {

  public void execute() throws Exception {
    target.setActionLog(getActionLog());
    target.execute();
    getActionLog().setData(name, target.getResult());
    setResult(target.getResult());
  }

  public void addAction(Action a) { target=a; }
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public Action getTarget() { return target; }
  public void setTarget(Action a) { target = a; }

  private String name=null;
  private Action target=null;
}
