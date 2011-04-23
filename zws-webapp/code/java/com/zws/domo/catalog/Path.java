package com.zws.domo.catalog;

import com.zws.domo.Domo;

import java.util.Collection;
import java.util.Vector;

public class Path extends Domo {

  public Path() {}

  public String getCatalogName() { return catalogName; }
  public void setCatalogName(String s) { catalogName=s; }

  public Collection getCategoryPath() { return categoryPath; }
  public void add(String name) {
    if (null==catalogName) catalogName = name;
    else categoryPath.add(name);
  }

  private String catalogName = null;
  private Collection categoryPath = new Vector();
}
