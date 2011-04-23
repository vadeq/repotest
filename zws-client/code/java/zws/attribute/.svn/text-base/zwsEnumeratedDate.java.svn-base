package zws.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 5:33 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidValue;

public class zwsEnumeratedDate extends EnumeratedAttributeBase {
  public String getType() { return DATE; }
  protected String createValue(String s) throws InvalidValue { 
    return s; //+++ todo - add date parser and formatter too.
  }
  
  protected int compareValueTo(String s) {
    try {
      String d =createValue(s);
      return getValue().compareTo(d); //+++ todo
    }
    catch (Exception e) { e.printStackTrace(); return 1; }
  }
  
  //DateParser parser=null;  use to setValue(String)
  //DateFormatter formatter = null; used to getValue()
}
