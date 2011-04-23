package zws.util.tree;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 7:51 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.util.SortedSet;

public interface TreeNode extends Serializable {  
  public Object getData();
  public int getLevel();
  public int getHeight();
  public TreeNode getParent();
  public void setParent(TreeNode node); 
  public boolean hasChildren();
  public SortedSet getChildren();
  public TreeNode getRootNode();
  public void add(TreeNode node) throws Exception;
  public void add(Object node) throws Exception;
}