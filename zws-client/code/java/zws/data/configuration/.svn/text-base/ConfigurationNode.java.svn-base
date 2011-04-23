package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 9:53 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.exception.MismatchedKey;
import zws.origin.Origin;
import zws.util.tree.TreeNode;

public interface ConfigurationNode extends Metadata, TreeNode {
  public void select(Origin o) throws Exception; //finds the metadata associated with this Origin and adapts to it.
  public void selectDefault() throws Exception; //finds the metadata associated with this Origin and adapts to it.
  public Metadata getFirst();
  public boolean contains(Origin o);  //returns true if origin is in this configuration
  public void place(Metadata metadata) throws MismatchedKey, Exception;
  
  public void defineKeyFields(String parentLevelKeyFields, String nodeLevelKeyFieldname, String childLevelKeyFieldname);
  public void defineKey(Metadata data);
  public boolean keyMatches(Metadata data);
  public boolean keyMatchesConfiguration(Metadata data);

  public String getKey();
  public String getNodeLevelKeyFieldname();
  public String getChildLevelKeyFieldname();
  public String getParentLevelKeyFields();
  public String getNodeLevelKeyFields();
  public String getChildLevelKeyFields();

  //public String getSortKeyFields();
  public boolean getAscendingOrder();
  public void setAscendingOrder(boolean b);
}
