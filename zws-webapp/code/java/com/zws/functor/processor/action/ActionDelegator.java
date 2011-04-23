package com.zws.functor.processor.action;


public class ActionDelegator extends Action {
  public void execute() throws Exception {
    target.setActionLog(getActionLog());
    target.execute();
    setResult(target.getResult());
  }

  public void addAction(Action a) { setTarget(a); }
  public void setTarget(Action a) { target=a; }
  public Action getTarget() { return target; }

  private Action target;
}
