package com.zws.xml.functor.create;

import java.lang.reflect.Method;

public class SingletonInstance extends CreateFunctor {
  public SingletonInstance() { }
  public SingletonInstance(String fqcn) { setFqcn(fqcn); }

  public final Object create() throws CreateException { return getInstance(); }
  public Object getInstance() throws CreateException {
    Class singleton;
    try{
      singleton = Class.forName(getFqcn());
      Class[] noParamTypes = null;
      Method getInstance = singleton.getMethod(getMethodName(), noParamTypes);
    }
    catch (ClassNotFoundException e) { throw new CreateException("Could not load class fqcn=\""+fqcn+"\""); }
    catch (NoSuchMethodException e) { throw new CreateException("Could get instance using method \""+getMethodName()+"\" for =\""+fqcn+"\""); }
    return null;
  }

  public String getMethodName(){ return methodName; }

  public void setMethodName(String methodName){ this.methodName = methodName; }

  public String getFqcn(){ return fqcn; }
  public void setFqcn(String fqcn){ this.fqcn = fqcn; }

  private String methodName = "getInstance";
  private String fqcn;
}