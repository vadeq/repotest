package com.zws.functor.java;

import com.zws.functor.Functor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

// Class is named JavaMethod to avoid name collision w/ reflection package.
// To call a static method, make sure to setJavaClass(javaClass)
// To call a method on an instance, make sure to setInstance(object)
public class JavaMethod extends Functor {
  public void execute() throws Exception {
    Object result = null;
    Method m=null;
    Class[] argTypes = getArgumentTypes();
    Collection methods = getMethods();
    if (0==methods.size()) throw new MethodNotFound( getMethodName(), instance.getClass().getName());
    Iterator i = methods.iterator();
    while (i.hasNext()){
      m = (Method)i.next();
      try {
        setResult(m.invoke(getInstance(), args.toArray()));
        return;
      }
      catch (InvocationTargetException e) { throw e; }
      catch (Exception e){}
    }
    throw new MethodNotFound( getMethodName(), instance.getClass().getName(), "Invalid Signature:" + argTypes.toString());
  }

  private Collection getMethods(){
    Collection methods = new Vector();
    Method []  functions = getJavaClass().getMethods();
    for (int i = 0; i < functions.length; i++){
      if (functions[i].getName().equals(functionName())) methods.add(functions[i]);
    }
    return methods;
  }

  protected String functionName() { return getMethodName(); }

  private Class [] getArgumentTypes() {
    if (0==args.size()) return null;
    Class[] types = new Class[args.size()];
    int idx = 0;
    Iterator i = args.iterator();
    while(i.hasNext()) types[idx++] = i.next().getClass();
    return types;
  }

  public void addArgument(Object arg) { args.add(arg); }
  public void setArguments(Object [] arguments) {
    args.clear();
    for (int i = 0; i < arguments.length; addArgument(arguments[i++]));
  }
  public void clearArguments(){ args.clear(); }

  public void bind(Object o) { setInstance(o); }
  public void bind(Class c) { setJavaClass(c); }

  public Class getJavaClass() { return javaClass; }
  public void setJavaClass(Class c) { javaClass = c; }
  public Object getInstance(){ return instance; }
  public void setInstance(Object o){ instance = o; setJavaClass(o.getClass()); }
  public String getMethodName(){ return methodName; }
  public void setMethodName(String s){ methodName=s; }
  protected Collection getArguments(){ return args; }
  protected void setArguments(Collection c){ args = c; }

  protected Class javaClass=null;
  protected Object instance=null;
  protected String methodName=null;
  protected Collection args=new Vector();
}
