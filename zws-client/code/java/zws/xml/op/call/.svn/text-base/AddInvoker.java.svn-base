package zws.xml.op.call; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class AddInvoker extends Caller {
  public AddInvoker() { super();}
  public AddInvoker(Object target, String commandName) { super(target, commandName);}
  public AddInvoker(Object target, String commandName, Object[] parameters) { super(target, commandName, parameters); }

  // Note: if we close the system to operate only on Linkables objects, then we
  //       can simply call add on the Linkables object.
  //       This is much faster than our superclasses reflective invokation.
  // ..at
  // public Object invoke() { return add(); }
  public Object add() throws CallException { return invoke(); }

  protected String methodName() { return "add"; }
}