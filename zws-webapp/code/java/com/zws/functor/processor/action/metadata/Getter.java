package com.zws.functor.processor.action.metadata;

import com.zws.functor.processor.action.Action;

public class Getter extends Action {

  public final void execute () { getValue(); }

  private String getValue() {
    String value = getDocument().get(metaData);
    if (null==value) value="";
    setResult(value);
    return value;
  }

  public String getMetaData() { return metaData; }
  public void setMetaData(String s) { metaData=s; }

  private String metaData=null;
}
