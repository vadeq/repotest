package zws.search.sortcomparator;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 31, 2004, 3:02 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.util.MetadataKeyMaker;

public class SortMetadataByKey extends SortByKey {
  public int compare (Object data0, Object data1) { return compare((Metadata)data0, (Metadata)data1); }
  public int compare (Metadata data0, Metadata data1) { return comparator.compare(makeKey(data0), makeKey(data1)); }

  public String makeKey(Metadata data) { return keyMaker.makeKey(data); }
  public String getKeyFields() { return keyMaker.getKeyFields(); }
  public void setKeyFields(String s) { keyMaker.setKeyFields(s); }

  private MetadataKeyMaker keyMaker = new MetadataKeyMaker();
}
