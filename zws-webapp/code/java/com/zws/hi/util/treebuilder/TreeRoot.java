package com.zws.hi.util.treebuilder;

/**
 * <p>Title: DesignState</p>
 * <p>Description: Design Compression Technology</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Zero Wait-State</p>
 * @author not attributable
 * @version 1.0
 */

public class TreeRoot extends TreeFolder{

  public TreeRoot(String name, String link) {
    super(name, link);

  }

  protected void setId(){
    id = TreeNode.TREETOP;
  }

  protected String makeMyString(){

    String myString = TreeNode.TREETOP +  TreeNode.EQLS + TreeNode.GFLD +
           name + "', '" + link + "')" +
           TreeNode.STEND;
//"', 'javascript:undefined')"
    return myString;
  }


}