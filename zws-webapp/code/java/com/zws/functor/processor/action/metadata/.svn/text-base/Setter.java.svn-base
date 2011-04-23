package com.zws.functor.processor.action.metadata;

import com.zws.functor.processor.action.Action;

public class Setter extends Action {
  public final void execute () throws Exception { set(); }

  private void set() throws Exception {
    if (null!=target){
      target.setActionLog(getActionLog());
      target.execute();
      value = (String)target.getResult();
    }
    if ("name".equalsIgnoreCase(metaData)) getDocument().setName(value);
    else getDocument().set(metaData,value);
  }

  public String getMetaData() { return metaData; }
  public void setMetaData(String s) { metaData=s; }
  public String getValue() { return value; }
  public void setValue(String s) { value=s; }

  public void addAction(Action a) { target=a; }

  public Action getAction() { return target; }
  public void setAction(Action a) { target=a; }

  private String metaData=null;
  private String value="";
  private Action target = null;
}