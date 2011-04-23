package zws.space.filesystem;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 4, 2004, 11:42 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.UnsupportedConstraint;
import zws.space.DataSpaceBase;

public class FileSystemDataSpace extends DataSpaceBase {
//  protected CriteriaParser getCriteriaParser(){
//    return null;
//    List folders = getSpaceConstraintsForKey(FOLDER);
//    List rootFolders = getSpaceConstraintsForKey(ROOT_FOLDER);
//    List documentTypes = getSpaceConstraintsForKey(NAME);
//    CriteriaParser parser = new FileSystemCriteriaParser(rootFolders, folders, documentTypes);
//  }
  
  
  public String getTimeOfCreationField() { return ""; }
  
  public void chooseDocumentType(String extention) throws UnsupportedConstraint {
    String s=extention.trim();
    if (s.startsWith("*.")) addSpaceConstraint(NAME,s);
    else addSpaceConstraint(NAME,"*."+s);
  }
  public void chooseRootFolder(String location) throws UnsupportedConstraint {
    addSpaceConstraint(ROOT_FOLDER, location.trim());
  }
  public void chooseAbsoluteFolder(String location) throws UnsupportedConstraint { 
    addSpaceConstraint(FOLDER, location.trim());
  }
  
  public static String NAME = "name"; //FileSystemSource.NAME;
  public static String FOLDER = "folder"; //FileSystemSource.FOLDER;
  public static String ROOT_FOLDER = "root"; //FileSystemSource.ROOT_FOLDER;
}
