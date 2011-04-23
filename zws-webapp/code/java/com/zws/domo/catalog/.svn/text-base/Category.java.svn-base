package com.zws.domo.catalog;

import com.zws.domo.Domo;
import com.zws.domo.document.Document;

import java.util.Collection;
import java.util.Vector;

public class Category extends Domo {
  public Category() {}

  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getDescription() { return description; }
  public void setDescription(String s) { description = s; }
  public String getParentID() { return parentID; }
  public void setParentID(String s) { parentID = s; }

  public Collection getDocuments(){ return documents; }

  public void add(Document doc) { documents.add(doc); }

  private String name=null;
  private String description=null;
  private String parentID;
  public Collection documents = new Vector();
}
