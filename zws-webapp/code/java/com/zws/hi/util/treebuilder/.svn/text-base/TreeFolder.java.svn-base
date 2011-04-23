package com.zws.hi.util.treebuilder;

import java.util.ArrayList;
import java.util.Iterator;
/**
 * <p>Title: DesignState</p>
 * <p>Description: Design Compression Technology</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Zero Wait-State</p>
 * @author not attributable
 * @version 1.0
 */

public class TreeFolder extends TreeNode{

  protected ArrayList nodes = new ArrayList();
  public TreeFolder(String name, String link) {
    super(name, link);
  }

  protected void setId(){
     this.id = "f" + counter++;
  }

  public void addNode(TreeNode node){
    if(!(node instanceof TreeRoot)){
      node.parent = this;
      nodes.add(node);
    }
  }

  protected String makeMyString(){
    String myString = getId() +
           TreeNode.EQLS + TreeNode.INSFLD + getParent().getId() + ", " +
           TreeNode.GFLD +
           name + "', '" + link + "')" + ")" + TreeNode.STEND;

     return myString;
  }

  public String toString(){

    String nodesString = "";
    Iterator nodesI = nodes.iterator();
    while(nodesI.hasNext()){
      String nodeString = nodesI.next().toString();
      nodesString += nodeString;
    }
    return makeMyString() + nodesString;

  }



}