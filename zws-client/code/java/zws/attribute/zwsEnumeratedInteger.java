package zws.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 5:23 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidValue;

public class zwsEnumeratedInteger extends EnumeratedAttributeBase {
  public String getType() { return INTEGER; }
  protected String createValue(String s) throws InvalidValue { 
    try {
      int i = Integer.valueOf(s).intValue();
      return "" + i;
    }
    catch (NumberFormatException e) { throw new InvalidValue(s + " is not readable as an Integer"); }
  }

  protected int compareValueTo(String s) {
    int val = Integer.valueOf(getValue()).intValue();
    try {
      int comp = Integer.valueOf(s).intValue();
      if (val<comp) return -1;
      if (val==comp) return 0;
      return 1;
    }
    catch (NumberFormatException e) { e.printStackTrace(); return 1; }
  }
}
