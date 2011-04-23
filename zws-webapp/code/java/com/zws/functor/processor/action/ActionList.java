package com.zws.functor.processor.action;

import com.zws.application.Constants;

import java.util.*;

public class ActionList extends Action {

  public void execute() throws Exception {
    Collection c = new Vector();
    Iterator i = actions.iterator();
    Action action;
    while (i.hasNext()){
      action = (Action)i.next();
      action.setActionLog(getActionLog());
      action.execute();
      setActionLog(action.getActionLog());
      if (Constants.PROCESS_TIMED_OUT==action.getExitCode()){
        {} //System.out.println("***Action timed out on document " + action.getDocument().getName() + ":" + action.getClass().getName());
        if (getHaltOnTimeout()) return;
        if (!getContinueOnTimeout()) break;
      }
      c.add(action.getResult());
/*
      action.setActionLog(getActionLog());
      action.execute(); //todo: add conditional to continue on exceptions
      c.add(action.getResult());
*/
    }
    setResults(c);
  }

  public Object getResult() { return resultList; }
  public void setResult(Object c) { resultList = (Collection) c; }

  public Collection getResults(){ return resultList; }
  public void setResults(Collection c) { resultList = c; }

  public boolean getHaltOnTimeout() { return haltOnTimeout; }
  public void setHaltOnTimeout(boolean b) { haltOnTimeout=b; }
  public boolean getContinueOnTimeout() { return continueOnTimeout; }
  public void setContinueOnTimeout(boolean b) { continueOnTimeout=b; }


  public void addAction(Action a) { actions.add(a); }

  private Collection resultList;
  private Collection actions = new Vector();

  private boolean haltOnTimeout = false;
  private boolean continueOnTimeout = false;
}
