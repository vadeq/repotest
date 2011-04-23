package zws.xml.op.call; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class StringConcatenator extends Caller {
  public StringConcatenator() { }
  private void init() { /* set method to concat strings */}
  // +++ re-write concat and overload invoke to call it: ..at
  //public Object invoke() { return concat(); }
  public Object concat() throws CallException { return invoke(); }

  public String getDataString() { return (String)parameter(0); }
  public void setDataString(String dataString) { addParameter(dataString); }
  public String getTargetString() { return (String)getTargetObject(); }
  public void setTargetString(String targetString) { setTargetObject(targetString); }
  public void setParameter(Object parameter) {
    if (null == parameter) return;
    addParameter(parameter.toString());
  }
  public void setTargetObject(Object targetObject) {
    if (null == targetObject) this.targetObject = null;
    this.targetObject = targetObject.toString();
  }
}