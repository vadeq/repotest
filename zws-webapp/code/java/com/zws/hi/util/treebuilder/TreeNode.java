package com.zws.hi.util.treebuilder;
/**
 * <p>Title: DesignState</p>
 * <p>Description: Design Compression Technology</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Zero Wait-State</p>
 * @author not attributable
 * @version 1.0
 */

public abstract class TreeNode {

  static int counter = 0;
  protected String name = "";
  protected String id;
  protected String link;
  protected TreeNode parent = null;

  public TreeNode() {
  }

  public TreeNode(String name, String link) {

    this.name = name;
    if(link == null)
      link = DEFLINK;
    else
      this.link = link;
    setId();
  }

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  protected TreeNode getParent(){
    return parent;
  }

  protected String getId(){
    return id;
  }

  protected abstract void setId();


  protected abstract String makeMyString();

  public String toString(){
    return makeMyString();
  }


  final static String TREETOP = "foldersTree";
  final static String ENDST = ");";
  final static String INSDOC = "insDoc(";
  final static String INSFLD = "insFld(";
  final static String GFLD = "gFld('";
  final static String GLNK = "gLnk('S', '";
  //final static String GEND = "', 'javascript:undefined')";
  //final static String GEND = "', 'javascript:undefined')";
  final static String STEND = ";";
  final static String EQLS = " = ";
  public final static String DEFLINK = "javascript:undefined";

}