package com.zws.xml.functor.call;

abstract public class BeanAccessor extends Caller {
  public BeanAccessor() { super(); }
  public BeanAccessor(String propertyName) { setPropertyName(propertyName); }
  public BeanAccessor(Object target, String commandName) { super(target, commandName); }
  public BeanAccessor(Object target, String commandName, Object value) { super(target, commandName, new Object[] {value}); }

  //++help performance by implementing access to use BeanUtil instead of invoke.
  public Object access() throws CallException { return invoke(); }

  public String getPropertyName() { return propertyName; }
  public void setPropertyName(String s) { propertyName=s; setCommandName(methodName()); }
  protected abstract String getMethodPrefix();
  protected String methodName() {
    if (null==getPropertyName() || getPropertyName().equals("")) return null;
    if (1 == getPropertyName().length()) return getMethodPrefix()+getPropertyName().toUpperCase();
    else return getMethodPrefix()+getPropertyName().substring(0,1).toUpperCase() + getPropertyName().substring(1);
  }

  private String propertyName;
}