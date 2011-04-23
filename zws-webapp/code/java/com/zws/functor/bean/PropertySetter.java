package com.zws.functor.bean;
//todo remove this class - moved to com.zws.functor.java.bean

import com.zws.functor.Functor;

import java.lang.reflect.Method;

public class PropertySetter extends Functor {

  public void execute() throws Exception {
    Object[] noArgs=null;
    Class[] noArgTypes=null;
    String name ="get" + propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
    Method setter = getBinding().getClass().getMethod(name, noArgTypes);
    setResult(setter.invoke(getBinding(), noArgs));
  }

  public String getPropertyName() { return propertyName; }
  public void setPropertyName(String s) { propertyName=s; }

  private String propertyName=null;
}
