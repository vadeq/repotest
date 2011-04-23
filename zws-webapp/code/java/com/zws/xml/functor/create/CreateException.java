package com.zws.xml.functor.create;

import com.zws.xml.functor.XMLFunctorException;
public class CreateException extends XMLFunctorException{
  public CreateException (String message) { super(message); }
  public Object getResult(){ return result; }
  public void setResult(Object o) { result = o; }
  private Object result = null;
}