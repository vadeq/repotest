package zws.action.condition;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 1:08 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.ActionList;
import zws.action.ActionListBase;
import zws.op.Op;

public abstract class Conditional extends ActionListBase{

  public abstract boolean isTrue() throws Exception;

  public void execute() throws Exception {
    ActionList actions = falseActions;
    if (isTrue()) actions=trueActions;
    if (null==actions) { setResult(null); return; }
    actions.setContext(getContext());
    actions.setBreakOnException(getBreakOnException());
    actions.execute();
    setResult(actions.getResults());
  }

  public void setTrueActions(ActionList list) { trueActions=list; }
  public void setFalseActions(ActionList list) { falseActions=list; }
  public ActionList getTrueActions() { return trueActions; }
  public ActionList getFalseActions() { return falseActions; }

  public void addTrueAction(Op op) { trueActions.add(op); }
  public void addFalseAction(Op op) { falseActions.add(op); }

  private ActionList trueActions = new ActionListBase();
  private ActionList falseActions = new ActionListBase();
}
