package zws.event;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 1:07 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;

public class DesignEventBase extends RoutedEventBase implements DesignEvent  {

  public boolean itemIsBinary() {
    String s = (String)get(FIELD_BINARY_NAME);
    if (null==s || "".equals(s.trim())) return true;
    else return false;
  }

  public static String FIELD_BINARY_NAME="binary";
  public static String FIELD_BINARY_BRANCH="binary-branch";
  public static String FIELD_BINARY_REV="binary-rev";
  public static String FIELD_BINARY_VER="binary-ver";    
}
