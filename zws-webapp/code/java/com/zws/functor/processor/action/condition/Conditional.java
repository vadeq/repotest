package com.zws.functor.processor.action.condition;

import com.zws.functor.processor.action.Action;
import com.zws.functor.processor.action.ActionList;

public abstract class Conditional extends ActionList {

  public void execute() throws Exception {
    ActionList actions;
    if (isTrue())
      actions=trueActions;
    else
      actions=falseActions;
    if (null==actions) { setResult(null); return; }

    actions.setActionLog(getActionLog());
    actions.setHaltOnTimeout(getHaltOnTimeout());
    actions.setContinueOnTimeout(getContinueOnTimeout());
    actions.execute();
    setActionLog(actions.getActionLog());
    setResult(actions.getResults());
  }

  public abstract boolean isTrue() throws Exception;

  public void setTrueActions(ActionList list) { trueActions=list; }
  public void setFalseActions(ActionList list) { falseActions=list; }
  public ActionList getTrueActions() { return trueActions; }
  public ActionList getFalseActions() { return falseActions; }

  public void addTrueAction(Action action) { trueActions.addAction(action); }
  public void addFalseAction(Action action) { falseActions.addAction(action); }

  private ActionList trueActions = null;
  private ActionList falseActions = null;
}
