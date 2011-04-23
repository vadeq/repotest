package zws.search.filter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 22, 2004, 12:45 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.filter.ListFilterBase;
import zws.data.util.MetadataKeyMaker;

import java.io.Serializable;
import java.util.Collection;

public abstract class KeyFilter extends ListFilterBase implements Serializable {
  public abstract void filter(Collection list) throws Exception;

  public boolean keep(Object o) throws Exception { return true;}

  protected String makeKey(Metadata data) { return keyMaker.makeKey(data); }
  public String getKeyFields() { return keyMaker.getKeyFields(); }
  public void setKeyFields(String s) { keyMaker.setKeyFields(s); }

  private MetadataKeyMaker keyMaker = new MetadataKeyMaker();
}
