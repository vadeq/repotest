package zws.event;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 1:07 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.util.RoutedEventBase;

public class VersionedDesignEventBase extends RoutedEventBase implements VersionedDesignEvent {
	public Origin getOrigin() { return origin; }
	public void setOrigin(Origin o) { origin =o; }
	
	private Origin origin = null;

  public static String FIELD_BINARY_NAME="binary";
  public static String FIELD_BINARY_BRANCH="binary-branch";
  public static String FIELD_BINARY_REV="binary-rev";
  public static String FIELD_BINARY_VER="binary-ver";  
}
