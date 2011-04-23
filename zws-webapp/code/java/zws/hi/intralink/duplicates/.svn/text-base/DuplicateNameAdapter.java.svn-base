package zws.hi.intralink.duplicates;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2005, 12:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

public class DuplicateNameAdapter {
  public void adapt(Metadata metadata0, Metadata metadata1) {
    data0=metadata0;
    data1=metadata1;
  }

  public String getName() { return data0.getName(); }
  public String getAuthor0() { return data0.get(AUTHOR); }
  public String getAuthor1() { return data1.get(AUTHOR); }
  public String getTimeOfLastModification0() { return data0.get(LAST_MODIFIED); }
  public String getTimeOfLastModification1() { return data1.get(LAST_MODIFIED); }
  
  public int getMostRecent() {
    if (0<getTimeOfLastModification0().compareTo(getTimeOfLastModification1())) return 0;
    else return 1;
  }
  
  private Metadata data0=null;
  private Metadata data1=null;
  
  static String AUTHOR = "author";
  static String LAST_MODIFIED = "last-modified";
}
