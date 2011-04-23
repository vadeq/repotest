package zws.util.category;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 2, 2004, 3:50 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.DuplicateName;
import zws.exception.NameNotFound;
import zws.util.tree.TreeNode;

public interface Category extends TreeNode {
  public String getType();
  public String getName();
  public void setName(String s);
  public String getDescription();
  public void setDescription(String s);
  
  public boolean hasSubCategory(String name);
  public Category findSubCategory(String name) throws NameNotFound;
  public void removeSubCategory(String name) throws NameNotFound;
  public void addSubCategory(Category node) throws DuplicateName, Exception;
  public void addSubCategory(String name, String description) throws DuplicateName, Exception;
}
