package zws.space.intralink;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 4, 2004, 12:25 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.filter.metadata.MetadataValueFilter;
import zws.exception.UnsupportedConstraint;
import zws.space.DataSpaceBase;

public class IntralinkDataSpace extends DataSpaceBase {
  
  public String getTimeOfCreationField() { return "Created-On"; }
  
  public void chooseReleaseLevel(String s) throws UnsupportedConstraint { addSpaceConstraint(RELEASE_LEVEL,s); }
  public void chooseRevision(String s) throws UnsupportedConstraint { addSpaceConstraint(REVISION,s); }
  public void chooseDocumentType(String s) throws UnsupportedConstraint {
    if (s.startsWith("*.")) addSpaceConstraint(NAME,s);
    else addSpaceConstraint(NAME,"*."+s);
  }
  public void chooseRootFolder(String s) throws UnsupportedConstraint {
    String location = s.replace(' ', '*');
    if (!location.endsWith("*")) location = location+"*";
    addSpaceConstraint(FOLDER, location);
  }
  public void chooseAbsoluteFolder(String s) throws UnsupportedConstraint {
    String location = s.replace(' ', '*');
    addSpaceConstraint(FOLDER, location);
  }
  
  private MetadataValueFilter binaryFileFilter=null;

  public static String FALSE = "false";
  public static String NAME = "Name";
  public static String BRANCH = "Branch";
  public static String REVISION = "Revision";
  public static String VERSION = "Version";
  public static String CREATED_ON = "Created-On";
  public static String CREATED_BY = "Created-By";
  public static String RELEASE_LEVEL = "Release-Level";
  public static String FOLDER = "Folder";
  public static String GENERIC = "Generic";
  public static String INSTANCE = "Instance";
  public static String DESCRIPTION = "Description";
}