package com.zws.functor.java;

import com.zws.functor.Functor;

public class GetClass extends Functor {
  public void execute () throws ClassNotFoundException {
    setResult(Class.forName(getClassName()));
  }

  public void bind(Object o) { setClassName(o.getClass().getName()); }
  public void bind(Class c) { setClassName(c.getName()); }
  public void bind(String s) { setClassName(s); }

  public String getClassName() { return className; }
  public void setClassName(String s) { className=s; }

  private String className;
}