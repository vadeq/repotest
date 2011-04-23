package com.zws.functor.processor.action.condition;

import com.zws.domo.document.DocumentInterface;
import com.zws.functor.processor.action.Action;
import com.zws.functor.processor.action.ActionList;
import com.zws.util.comparator.metadata.HistoryOrder;

public abstract class MetaDataDiscriminator extends Conditional {

  public void execute() throws Exception {
    compareTo.setActionLog(getActionLog());
    compareTo.execute();
    comparator.setDoc((DocumentInterface)getDocument());
    comparator.setCompareTo((DocumentInterface) compareTo.getResult());
    ActionList actions;
    if (isTrue()) actions=getTrueActions();
    else actions=getFalseActions();

    actions.setActionLog(getActionLog());
    actions.setHaltOnTimeout(getHaltOnTimeout());
    actions.setContinueOnTimeout(getContinueOnTimeout());
    actions.execute();
    setActionLog(actions.getActionLog());
    setResult(actions.getResults());
  }

  public String getRevisionMetaData() { return comparator.getRevisionMetaData(); }
  public void setRevisionMetaData(String s) { comparator.setRevisionMetaData(s); }
  public String getVersionMetaData() { return comparator.getVersionMetaData(); }
  public void setVersionMetaData(String s) { comparator.setVersionMetaData(s); }
  public HistoryOrder getComparator() { return comparator; }

  public Action getCompareTo() { return compareTo; }
  public void setCompareTo(Action a) { compareTo=a; }
  public Action compareTo=null;
  public HistoryOrder comparator = new HistoryOrder();
}
