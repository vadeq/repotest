package com.zws.xml.functor.call;

public class PropertySetter extends BeanAccessor {
  public PropertySetter() { super(); }

  public PropertySetter(String propertyName) { super(propertyName); }

  public PropertySetter(Object target, String propertyName) { super(target, propertyName); }
  public PropertySetter(Object target, String propertyName, Object parameter) { super(target, propertyName, parameter); }

  // +++ reimplement get() for performance and uncomment the invoke overrid when done: ..at
  // public Object invoke() {return set(); }
  public Object set() throws CallException { return invoke(); }
  protected String getMethodPrefix() { return "set"; }
}