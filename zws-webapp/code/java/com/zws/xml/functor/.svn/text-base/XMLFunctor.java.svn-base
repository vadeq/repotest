package com.zws.xml.functor;

abstract public class XMLFunctor implements Cloneable {
  public abstract Object process() throws Exception;

  public void initialize() throws Exception {};
  public void initialize(Object arg0) throws Exception {};
  public void initialize(Object arg0, Object arg1) throws Exception {};
  public void initialize(Object arg0, Object arg1, Object arg2) throws Exception {};

  public Object duplicate() {
    try {return this.clone();}
      catch (Exception e){e.printStackTrace(); return null;} }
}