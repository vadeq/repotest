package com.zws.xml.functor.call;

public class StaticMethodCaller extends Caller {
  public StaticMethodCaller() { super(); }
  public StaticMethodCaller(Object target, String commandName) { super(target, commandName); }
  public StaticMethodCaller(Object target, String commandName, Object[] parameters) { super(target, commandName, parameters); }

  public Object call() throws CallException { return invoke(); }

  // Note: Invoke on a static method targets a Class.
  // ..at
  public void setTargetObject(Object targetObject) {
    try {setTargetClass((Class)targetObject); }
    catch (Exception e) { e.printStackTrace(); this.targetObject=null; }
  }
  public Object getTargetObject() { return getTargetClass(); }
  public Class getTargetClass(){ return targetClass; }
  public void setTargetClass(Class targetClass){
    try {
      this.targetObject = targetClass.newInstance();
      this.targetClass = targetClass;
    }
    catch (Exception e){this.targetObject = this.targetClass = null; }
  }

  protected Class targetClass;
}