package com.zws.functor.java;

public class MethodNotFound extends java.lang.Exception {
  public MethodNotFound(String methodName, String className) { super("Method "+ methodName+"not found in class "+className); }
  public MethodNotFound(String methodName, String className, String message) { super("Method "+ methodName+"not found in class "+className+" - " + message); }
}
