package zws.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 5:23 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidValue;

public class zwsFloat extends AttributeBase {
  public String getType() { return FLOAT; }
  protected String createValue(String s) throws InvalidValue {
    try {
      double d = Double.valueOf(s).doubleValue();
      return ""+d;
    }
    catch (NumberFormatException e) { throw new InvalidValue(s + " is not readable as a Double"); }
  }
  
  protected int compareValueTo(String s) {    
    double val = Double.valueOf(getValue()).doubleValue();
    try {
      double comp = Double.valueOf(s).doubleValue();
      if (val<comp) return -1;
      if (val==comp) return 0;
      return 1;
    }
    catch (NumberFormatException e) { e.printStackTrace(); return 1; }
  }
}
