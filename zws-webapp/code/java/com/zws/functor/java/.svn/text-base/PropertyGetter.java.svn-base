package com.zws.functor.java;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyGetter extends JavaMethod {
  private static String GET = "get";
  public void execute() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Object[] noArgs=null;
    Class[] noArgTypes=null;
    Method getter = getJavaClass().getMethod(functionName(), noArgTypes);
    setResult(getter.invoke(getBinding(), noArgs));
  }
  public String functionName() {
    return GET + propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
  }

  public String getPropertyName() { return propertyName; }
  public void setPropertyName(String s) { propertyName=s; }

  private String propertyName=null;
}
