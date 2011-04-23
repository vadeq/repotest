package com.zws.functor;


public abstract class Functor implements Cloneable {
  public abstract void execute() throws Exception;
  public void bind(Object o ){binding=o;}
  public Object getResult() { return result; }
  public void setResult(Object r) { result=r; }

  public Object getBinding() { return binding; }

  public Functor copy(){
    try {return (Functor) clone(); } //cloaning should be supported
    catch (CloneNotSupportedException e){throw new RuntimeException(e.getMessage()); }
  }
  public Object clone() throws CloneNotSupportedException
  { return super.clone(); }

  public Exception getException() { return exception; }
  public void setException(Exception e) { exception = e; }

  private Object binding=null;
  private Object result=null;
  private Exception exception = null;
}
