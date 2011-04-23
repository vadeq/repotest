package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 4, 2004, 12:01 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.configuration.comparator.RevisionComparator;

import java.util.*;

public class HistoryBase extends ConfigurationNodeBase implements History {
  
  public SortedSet getBranches() { return ((Metadata)getRootNode()).getBranches(); }
  public SortedSet getRevisionSets() { return getChildren(); }

  public Metadata getFirst() {
    if (null==getChildren()) return null;
    RevisionSet rTree = (RevisionSet)getChildren().iterator().next();
    return rTree.getFirst();
  }
  
  public Collection getRevisions() {
   if (null==getChildren()) return null;
   Collection c = new Vector();
   Iterator i = getChildren().iterator(); 
   while (i.hasNext()) c.add(((RevisionSet)i.next()).getRevisionLevel());
   return c;
  }

  public Collection getVersions(String revision) {
   if (null==getChildren()) return null;
   Collection c = new Vector();
   Iterator i = getChildren().iterator(); 
   RevisionSet tree;
   while (i.hasNext()) {
     tree=(RevisionSet)i.next();
     if (revision.equals(tree.getRevisionLevel())) return tree.getVersions() ;
   }
   return null;
  }    

  protected void placeInConfiguration(Metadata data) throws Exception {
   if (null==getChildren()) setChildren(new TreeSet(getComparator()));    
   Iterator i = getChildren().iterator();
   RevisionSet tree;
   while (i.hasNext()) {
     tree=(RevisionSet)i.next();
     if (tree.keyMatches(data)) {
       tree.place(data);
       return;
     }
   }
   RevisionSet child = new RevisionSetBase();
   child.defineKeyFields(getNodeLevelKeyFields(), getChildLevelKeyFieldname(), VERSION);
   child.defineKey(data);
   placeInNewChild(child, data);
  }
  
  protected Comparator getComparator() {  comparator.setAscendingOrder(getAscendingOrder()); return comparator; }
  
  public String toString() {
    Iterator i = getChildren().iterator();
    String s="";
    while(i.hasNext()) s+=i.next();
    return s;
  }
  
  private RevisionComparator comparator = new RevisionComparator();
  private static String REVISION="Revision";
  private static String VERSION="Version";
  private static String NEWLINE=zws.application.Names.NEW_LINE;
}
