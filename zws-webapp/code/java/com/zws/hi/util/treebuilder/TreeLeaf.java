package com.zws.hi.util.treebuilder;

/**
 * <p>Title: DesignState</p>
 * <p>Description: Design Compression Technology</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Zero Wait-State</p>
 * @author not attributable
 * @version 1.0
 */

public class TreeLeaf extends TreeNode {
  public TreeLeaf(String name, String link) {
    super(name, link);
  }

  protected void setId(){
   this.id = "a" + counter++;
}

  protected String makeMyString() {
    String myString = TreeNode.INSDOC + getParent().getId() + ", " +
           TreeNode.GLNK +
           name + "', '" + link + "')" + ")" + TreeNode.STEND;

     return myString;

     //"insDoc(aux4, gLnk('S', '[2] x [#569-310-d17485.prt]', 'javascript:undefined'));"

  }

}