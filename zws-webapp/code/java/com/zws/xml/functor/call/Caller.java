package com.zws.xml.functor.call;

import com.zws.xml.functor.XMLFunctor;
import com.zws.xml.functor.XMLFunctorException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


public class Caller extends XMLFunctor {
  public Caller() { }
  public Caller(Object target) {setTargetObject(target); }
  public Caller(String commandName) {setCommandName(commandName); }
  public Caller(Object target, String commandName) {setTargetObject(target); setCommandName(commandName); }
  public Caller(Object target, String commandName, Object[] parameters)
      {setTargetObject(target); setCommandName(commandName); resetParameters(parameters); }

  // +++ modify this class so that it can take a variable number of parameters..
  //     istead of just one.  change parameter:Object to parameters:Collection
  //     replace setParameter with addParameter.
  // ..at

  public Object invoke() throws CallException {
    String targetS ="";
    String parameterS = "";
    if (null != targetObject) targetS = targetObject.toString();
    if (null!=parameters) parameterS = parameters.toString();
    {} //System.out.println("Invoking command: "+ commandName + " on "+targetS+" with parameter="+parameterS);
    Class[] parameterTypes = getParameterTypeArray();
    Collection commands = locateCommandsForName();
    if (null==commands) throw new CallException("Command not found: " + targetObject.getClass().getName()+"."+getCommandName()+" taking "+ 1 +" parameter(s)");
    Iterator i = commands.iterator();
    Collection attempts = new Vector();
    while (i.hasNext()){
      Method m = (Method)i.next();
      Object result = null;
      try{ return m.invoke(targetObject, getParameterArray()); }
      catch (InvocationTargetException e){
        attempts.add(m.getDeclaringClass() + "." + m.getName() +"(..)->"+ m.getReturnType().getName()+" :underlying method threw an exception:\n"+ e.getMessage());
        e.printStackTrace();
      }
      catch (Exception e){
        attempts.add(m.getDeclaringClass() + "." + m.getName() +"(..)->"+ m.getReturnType().getName()+" : "+ e.getMessage()+"["+e.getClass().getName()+"]");
      }
    }
    String message = "Command not found: " + targetObject.getClass().getName()+"."+getCommandName()+" taking a parameter of type "+getParameterArray()[0].getClass().getName()+"\n";
    message += "Failure messages:\n";
    Iterator attempt = attempts.iterator();
    while (attempt.hasNext()) {
      message += attempt.next()+ "\n";
    }
    message += "End failure messages:\n";
    throw new CallException(message);
  }

  public void addParameter(Object parameter) { if (null==parameters)parameters=new Vector(); parameters.add(parameter); }

  public void resetParameters(Object [] parms) {
    clearParameters();
    for (int i = 0; i < parms.length; addParameter(parms[i++]));
  }

  public void clearParameters() { if (null!= parameters) parameters.clear(); }

  //+++ don't.. have time.. got to keep.. moving.  please unit test.. ..at
  public Object parameter(int idx) {
    if (null==parameters) return null;
    Object parm = null;
    Iterator i = parameters.iterator();
    int count = idx;
    while (count-- >= 0 && i.hasNext()) parm = i.next();
    return (count < 0)?  parm:null;
  }

  // Note: SubClasses should override invoke to eliminate
  //       the performance overhead which comes with reflection - but,
  //       until then this will do your job for add, set, get, whatever...
  // ..at

  private Collection locateCommandsForName(){
    if (null==targetObject) return null;
    Collection commands = new Vector();
    Method [] allMethods = targetObject.getClass().getMethods();
    for (int i = 0; i < allMethods.length; i++){
      if (allMethods[i].getName().equals(methodName())) commands.add(allMethods[i]);
    }
    return commands;
  }

  protected String methodName() { return getCommandName(); }

  private Class [] getParameterTypeArray() {
    if (null==parameters) return null;
    Class[] parmTypes = new Class[parameters.size()];
    Iterator i = parameters.iterator();
    int idx = 0;
    while(i.hasNext()) parmTypes[idx++] = i.next().getClass();
    return parmTypes;
  }
  private Object [] getParameterArray() {
    if (null==parameters) return null;
    Object[] parms = new Object[parameters.size()];
    Iterator i = parameters.iterator();
    int idx = 0;
    while(i.hasNext()) parms[idx++] = i.next();
    return parms;
  }

  public Object getTargetObject(){ return targetObject; }
  public void setTargetObject(Object targetObject){ this.targetObject = targetObject; }
  public String getCommandName(){ return commandName; }
  public void setCommandName(String commandName){ this.commandName = commandName; }

  public final Object process() throws XMLFunctorException { return invoke(); }

  protected Collection getParameters(){ return parameters; }
  protected void setParameters(Collection parameters){ this.parameters = parameters; }

  //Remember - this is cloneable, so do not initialize attributes here,
  //           else all clones will share the same Collection.
  //           that is probably not the desired effect.
  //  ..at



  protected Collection parameters;
  protected String commandName;
  protected Object targetObject;
}
