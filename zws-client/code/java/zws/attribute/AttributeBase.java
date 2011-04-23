package zws.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 7, 2004, 10:56 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidValue;

public abstract class AttributeBase implements Attribute {
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getValue() { return value; }
  public void setValue(String s) throws InvalidValue { value = createValue(s); }
  public boolean getIsEnumerated() { return false; }
  public int compareTo(String s) { return compareValueTo(s); }

  public abstract String getType();
  protected abstract String createValue(String s) throws InvalidValue;
  protected abstract int compareValueTo(String s);
  
  private String name=null;
  protected String value=null;

  public static String BOOLEAN="boolean";
  public static String INTEGER="integer";
  public static String STRING="string";
  public static String FLOAT="float";
  public static String DATE="date";
  public static String TIME="time";
  public static String TIME_OF_DAY = "time-of-day";

  public static String TRUE = "true";
  public static String FALSE = "false";
}
