package zws.xml.op.call; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class PropertyGetter extends BeanAccessor {
  public PropertyGetter() { super(); }

  public PropertyGetter(String propertyName) { super(propertyName); }

  public PropertyGetter(Object target, String propertyName) { super(target, propertyName); }
  public PropertyGetter(Object target, String propertyName, Object parameter) { super(target, propertyName, parameter); }

  // +++ reimplement get() for performance and overrid invoke when done: ..at
  // public Object invoke() {return get(); }
  public Object get() throws CallException { return invoke(); }
  protected String getMethodPrefix() { return "get"; }
}