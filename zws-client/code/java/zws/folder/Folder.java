package zws.folder;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 2, 2004, 3:55 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.DuplicateName;
import zws.util.category.CategoryBase;

public class Folder extends CategoryBase {
  public String getType() {
    if (null==type) type="Folder";
    return type; 
  }

  public String getPath() { return parentPath+getPathSeparator()+getName(); }

  public String getParentPath() { return parentPath; }
  public void setParentPath(String s) { parentPath=s; }

  public String getPathSeparator() {
    if (null==pathSeparator) return Names.PATH_SEPARATOR;
    return pathSeparator; 
  }
  public void setPathSeparator(String s) { pathSeparator=s; }

  public boolean getIsReadOnly() { return isReadOnly; }
  public void setIsReadOnly(boolean b) { isReadOnly=b; }  

  public void add(Folder f) throws DuplicateName, Exception { addSubCategory(f); } 
  public void addSubFolder(Folder f) throws DuplicateName, Exception { addSubCategory(f); }   
  
  private String parentPath=null;
  private boolean isReadOnly=false;
  private String pathSeparator=null;
}

