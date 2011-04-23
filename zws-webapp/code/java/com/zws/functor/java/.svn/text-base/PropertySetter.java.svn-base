package com.zws.functor.java;

public class PropertySetter extends JavaMethod {
  private static String SET = "set";
  public String functionName() {
    return SET + propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
  }

  public void setValue(Object o) { clearArguments(); addArgument(o); }
  public String getPropertyName() { return propertyName; }
  public void setPropertyName(String s) { propertyName=s; }

  private String propertyName=null;
}
