package zws.event;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 10:34 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.util.RoutedEventBase;

public class ReleaseEventBase extends RoutedEventBase implements ReleaseEvent {
	public String getOldReleaseLevel(Metadata data) { return data.get(OLD_RELEASE); }
	public String getNewReleaseLevel(Metadata data) { return data.get(NEW_RELEASE); }
	 
	private static String OLD_RELEASE= "old-release-level";
	private static String NEW_RELEASE= "new-release-level";

  public static String FIELD_BINARY_NAME="binary";
  public static String FIELD_BINARY_BRANCH="binary-branch";
  public static String FIELD_BINARY_REV="binary-rev";
  public static String FIELD_BINARY_VER="binary-ver";  
}
