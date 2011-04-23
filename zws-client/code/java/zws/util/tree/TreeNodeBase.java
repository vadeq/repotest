package zws.util.tree;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 7:53 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.comparator.ObjectComparator;

import java.util.*;

public class TreeNodeBase implements TreeNode {
  public TreeNodeBase() {}
  public TreeNodeBase(Object o) { data=o; }
  public TreeNodeBase(Object o, TreeNode parentNode) { data=o; parent=parentNode; }
  public Object getData() { return data; }
  public void setData(Object o) { data=o; }
  public TreeNode getParent() { return parent; }
  public void setParent(TreeNode n) { parent=n; }
  public int getLevel() {
    if (ancestorsAreCircular()) return -1;
    TreeNode n=parent;
    int level=0;
    while (null!=n) { 
      n=n.getParent(); 
      level++; 
    }
    return level;
  }

  public boolean ancestorsAreCircular(){
    Map nodes = new HashMap();
    TreeNode n = parent;
    while (null!=n) {
      if (n==(TreeNode)nodes.get(n)) return true;
      nodes.put(n, n);
      n = n.getParent();
    }
    return false;
  }

  public int getHeight() { //Depth
    if (null==children) return 0;
    TreeNode p = parent;
    parent=null;
    int height=0;
    int maxHeight=height;
    Iterator i = children.iterator();
    while (i.hasNext()) {
      height = 1+((TreeNode)i.next()).getHeight();
      if (height>maxHeight) maxHeight=height;
    }
    parent=p;
    return maxHeight;
  }
  
  public void add(TreeNode node) throws Exception {
    if (null==children) { children = new TreeSet(getComparator()); }
    node.setParent(this);
    try { 
    children.add(node);
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  protected Comparator getComparator() { return new ObjectComparator(); }
  
  public void add(Object o) throws Exception { TreeNodeBase node = new TreeNodeBase(o); add(node); }
  public boolean hasChildren() { return (null!=children && 0<children.size()); }
  public SortedSet getChildren() { return children; }
  protected void setChildren(SortedSet s) { children=s; }

  public String toString() { if (null==getData()) return "<node/>"; else return "<node>"+getData().toString()+"</node>"; }

  public TreeNode getRootNode() {
    TreeNode root = this;
    TreeNode parent = getParent(); 
    while (null!=parent) {
      root=parent;
      parent = root.getParent();
    }
    return root;
  }
  
  protected void nullify() {
    data=null;
    parent=null;
    children=null;
  }
  
  protected void swap(TreeNodeBase node) {
    //Object d = data;
    //TreeNode p = parent;
    SortedSet k = children;
    
    data=node.getData();
    parent=node.getParent();
    children=node.getChildren();
    
    node.setData(data);
    node.setParent(parent);
    node.setChildren(k);
  }

  private Object data=null;
  private TreeNode parent=null;
  private SortedSet children=null;  
}
