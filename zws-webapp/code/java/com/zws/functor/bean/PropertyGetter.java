package com.zws.functor.bean;
//todo remove this class - moved to com.zws.functor.java.bean

import com.zws.functor.Functor;

import java.lang.reflect.Method;
public class PropertyGetter extends Functor {

  public void execute() throws Exception {
    Class[] noArgs=null;
    Object[] noObjs=null;
    String name ="get" + propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
    Method getter = getBinding().getClass().getMethod(name, noArgs);
    setResult(getter.invoke(getBinding(), noObjs));
  }

  public String getPropertyName() { return propertyName; }
  public void setPropertyName(String s) { propertyName=s; }

  private String propertyName=null;
}
