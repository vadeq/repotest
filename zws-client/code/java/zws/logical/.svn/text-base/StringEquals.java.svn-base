package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class StringEquals extends LogicalOpBase {
  public boolean isTrue() throws Exception{
    if (null==leftValue) return null==rightValue;
    if (ignoreCase) return leftValue.equalsIgnoreCase(rightValue);
    return leftValue.equals(rightValue);
  }
 
  public String getLeftValue() { return leftValue; }
  public void setLeftValue(String s) { leftValue=s; }
  public String getRightValue() { return rightValue; }
  public void setRightValue(String s) { rightValue=s; }
  public boolean getIgnoreCase() { return ignoreCase; }
  public void setIgnoreCase(boolean b) { ignoreCase = b; }
  
  private String leftValue=null;
  private String rightValue=null;
  private boolean ignoreCase = true;
}