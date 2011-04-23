package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 10:18 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.search.sortcomparator.SortMetadataByKey;
import zws.util.tree.TreeNode;

import java.util.*;

public class RevisionSetBase extends ConfigurationNodeBase implements RevisionSet {  
  
  public SortedSet getBranches() { return ((Metadata)getRootNode()).getBranches(); }
  protected void placeInConfiguration(Metadata data) throws Exception {
    if (null==getChildren()) setChildren(new TreeSet(getComparator()));    
    TreeNode node = new MetadataNodeBase(data);
    node.setParent(this);
    getChildren().add(node); 
  }
  
  public Metadata getFirst() {
    if (null==getChildren()) return null;
    return (Metadata)getChildren().iterator().next();
  }
  
  public String getRevisionLevel() { return getNodeLevelKeyValue(); }
 
  public SortedSet getVersionNodes() { return getChildren(); }
  public Collection getVersions() {
   if (null==getChildren()) return null;
   Collection c = new Vector();
   Iterator i = getChildren().iterator(); 
   while (i.hasNext()) c.add(((Metadata)i.next()).get(getChildLevelKeyFieldname()));
   return c;
  }

  protected Comparator getComparator() { 
    comparator.setKeyFields(getChildLevelKeyFields()); 
    comparator.setAscendingOrder(getAscendingOrder());
    return comparator; 
  }

  public String toString() {
    String s = "  <revision-tree revision-level=\""+getRevisionLevel()+"\">"+NEWLINE;
    Iterator i = getVersionNodes().iterator();
    Metadata d;
    while(i.hasNext()) {
      d = (Metadata)i.next();
      s+="    <version number=\""+d.get(getChildLevelKeyFieldname())+"\" origin=\""+d.getOrigin()+"\"/>"+NEWLINE;
    }
    s += "  </revision-tree>"+NEWLINE;
    return s;
  }
  private static String NEWLINE=zws.application.Names.NEW_LINE;
  private SortMetadataByKey comparator = new SortMetadataByKey();
}
