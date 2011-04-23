package zws.folder;

import zws.application.Names;
import zws.util.StringUtil;

import java.util.Iterator;

/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 3:05 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class IntralinkFolder extends Folder {

  public String getPathSeparator() { return "/"; }
  
  public String getType() { return TYPE; }
  protected String getTagName() { return TAG_NAME; }
  
  public String getReleaseScheme() { return releaseScheme; }
  public void setReleaseScheme(String s) { releaseScheme=s; }
  
  public String getFileSpace() { return fileSpace; }
  public void setFileSpace(String s) { fileSpace=s; }
  
  public String toString() {
    StringBuffer s = new StringBuffer(); 
    s.append("<").append(getTagName()).append(" name=");
    StringUtil.appendXMLValue(s,getName());
    s.append(" release-scheme=");
    StringUtil.appendXMLValue(s, releaseScheme);
    //s.append(" parent=");
    //StringUtil.appendXMLValue(s, getParentPath());
    s.append(" path=");
    StringUtil.appendXMLValue(s, getPath());
    
    if (!hasChildren()) {
      s.append("/>").append(Names.NEW_LINE);
      return s.toString();
    }
    Iterator i = getChildren().iterator();
    s.append(">").append(Names.NEW_LINE);
    while (i.hasNext()) s.append("  ").append(i.next().toString());
    s.append("</").append(getTagName()).append(">").append(Names.NEW_LINE);
    return s.toString();
  }
  
  private String releaseScheme=null;
  private String fileSpace=null;
  
  private static String TYPE="Intralink Folder";
  private static String TAG_NAME = "folder";
}
