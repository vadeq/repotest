package com.zws.functor.java;

import com.zws.functor.Functor;

public class NewString extends Functor {
  public void execute() throws Exception { setResult(getValue()); }

  public void bind(Object o) { setValue(o.toString()); }
  public void bind(String s) { setValue(s); }

  public String getValue(){ return value; }
  public void setValue(String s){ value=s; }

  private String value=null;
}