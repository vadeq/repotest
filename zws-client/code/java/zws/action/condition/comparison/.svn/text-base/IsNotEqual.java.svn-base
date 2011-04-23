package zws.action.condition.comparison;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 28, 2004, 11:14 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class IsNotEqual extends Comparison {
  public boolean compare(String value1, String value2) {
    if (getIgnoreCase()) return !value1.equalsIgnoreCase(value2);
    return !value1.equals(value2);
  }
  public boolean compare(int value1, int value2) { return value1!=value2; }

}
