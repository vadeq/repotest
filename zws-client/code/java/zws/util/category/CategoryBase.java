package zws.util.category;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 2, 2004, 3:52 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.DuplicateName;
import zws.exception.NameNotFound;
import zws.util.tree.TreeNode;
import zws.util.tree.TreeNodeBase;

import java.util.Iterator;

public class CategoryBase extends TreeNodeBase implements Category {
  public String getType() {
    if (null==type) type="Category";
    return type; 
  }
  
  public void setType(String s) { type=s; }

  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getDescription() { return description; }
  public void setDescription(String s) { description=s; }  
  
  public void addSubCategory(String name, String description) throws DuplicateName, Exception {
    if (hasSubCategory(name)) throw new DuplicateName(name);
    CategoryBase node = new CategoryBase();
    node.setName(name);
    node.setDescription(description);
    add(node); 
  }
  
  public void addSubCategory(Category node) throws DuplicateName, Exception {
    if (hasSubCategory(node.getName())) throw new DuplicateName(node.getName());
    add((TreeNode)node);
  }
  
  public void removeSubCategory(String name) throws NameNotFound {
    if (!hasChildren()) throw new NameNotFound(name);
    Category c;
    Iterator i = getChildren().iterator();
    while (i.hasNext()) {
      c = (Category)i.next();
      if (c.getName().equals(name)) { i.remove(); return; };
    }
    throw new NameNotFound(name);
  }
  
  public boolean hasSubCategory(String name) {
    try { findSubCategory(name); return true;}
    catch (NameNotFound e) { return false; }
  }

  public Category findSubCategory(String name) throws NameNotFound {
    if (!hasChildren()) throw new NameNotFound(name);
    Category c;
    Iterator i = getChildren().iterator();
    while (i.hasNext()) {
      c = (Category)i.next();
      if (c.getName().equals(name)) return c;
    }
    throw new NameNotFound(name);
  }

  public String toString() {
    String s = "<"+getTagName()+" name=\""+getName()+"\"";
    if (!hasChildren()) return s + "/>" + Names.NEW_LINE;
    Iterator i = getChildren().iterator();
    s += ">"+Names.NEW_LINE;
    while (i.hasNext()) s += "  " + i.next().toString();
    s += "</"+getTagName()+">" + Names.NEW_LINE;
    return s;
  }
  
  protected String getTagName() { return getType(); }
  protected String type=null;
  private String name=null;
  private String description=null;
}
