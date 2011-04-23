package zws.xml.op.call; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

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