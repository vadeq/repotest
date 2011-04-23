package zws.hi.test;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 9:47 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.tree.TreeNode;
import zws.util.tree.TreeNodeBase;

import org.apache.struts.action.ActionForm;

public class hiTreeNode extends ActionForm {
  
  public TreeNode getTree() throws Exception {
    if (null==tree) tree = createTree();
    return tree;
  }
  
  private TreeNode createTree() throws Exception {
    TreeNodeBase t = new TreeNodeBase();
    TreeNodeBase node, n;
    t.setData("0000");
    t.add(new TreeNodeBase("1000"));
    t.add(new TreeNodeBase("1001"));
    t.add(new TreeNodeBase("1002"));
    t.add(new TreeNodeBase("1003"));
    
    node = new TreeNodeBase("1004");
    t.add(node);
 
    node.add(new TreeNodeBase("2000"));
    node.add(new TreeNodeBase("2001"));
    node.add(new TreeNodeBase("2002"));
    node.add(new TreeNodeBase("2003"));
    
    n = new TreeNodeBase("2004");
    n.add(new TreeNodeBase("3040"));
    n.add(new TreeNodeBase("3041"));
    n.add(new TreeNodeBase("3042"));
    node.add(n);

    n = new TreeNodeBase("2005");
    n.add(new TreeNodeBase("3050"));
    n.add(new TreeNodeBase("3051"));
    n.add(new TreeNodeBase("3052"));
    node.add(n);
    
    n = new TreeNodeBase("2006");
    n.add(new TreeNodeBase("3060"));
    n.add(new TreeNodeBase("3061"));
    n.add(new TreeNodeBase("3062"));
    node.add(n);    
    n = new TreeNodeBase("3063");
    node.add(n);

    node=n;
    node.add(new TreeNodeBase("4630"));
    node.add(new TreeNodeBase("4631"));
    node.add(new TreeNodeBase("4632"));
    return t;
  }
    
  private TreeNodeBase newNode(String s) {
    TreeNodeBase n = new TreeNodeBase();
    n.setData(s);
    return n;
  }
private TreeNode tree;
}
