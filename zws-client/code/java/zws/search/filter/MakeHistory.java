package zws.search.filter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 11:21 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.configuration.Branch;
import zws.data.configuration.BranchBase;
import zws.data.filter.ListFilterBase;
import zws.search.sortcomparator.SortMetadataByKey;

import java.util.*;

//assumes that data is sorted by "Name | Branch | [historyFields]"
//creates a uniqueKey using "Name | Branch"
//for each uniqueKey, creates a tree sorted by [historyFields]
public class MakeHistory extends ListFilterBase{
  public void filter(Collection results) throws Exception {
    resetStorage();
    Collection list = preSort(results);
    Branch history=null;
    SortMetadataByKey comparator=new SortMetadataByKey();
    //String sortKeyFields=nameFieldname + "|" + branchFieldname;
    String sortKeyFields=nameFieldname;
    comparator.setKeyFields(sortKeyFields);
    initializeStorage(new TreeSet(comparator));
    Iterator i = list.iterator();
    Metadata data;
    String key=null,nextKey=null;
    int ss = list.size();
    {} //System.out.println("list has " + ss +" element"); 
    while (i.hasNext()) {
      data=(Metadata)i.next();
      if (null==key) key=makeKey(data);
      if (null==history) history = createBranch(data);
      nextKey=makeKey(data);
      if (!key.equals(nextKey)){
        history.selectDefault();
        store(history);
        history = createBranch(data);
        key=nextKey;
      }
      history.place(data);
      {} //System.out.println(history);
    }
    history.selectDefault();
    if (null!=history) store(history);
  }
  
  private Collection preSort(Collection c) {
    p(c);
    SortMetadataByKey comparator=new SortMetadataByKey();
    String sortKeyFields=nameFieldname + "|" + branchFieldname + "|" + revisionFieldname + "|" + versionFieldname;
    comparator.setKeyFields(sortKeyFields);
    comparator.setAscendingOrder(false);
    Collection list = new TreeSet(comparator);
    Iterator i = c.iterator();
    while (i.hasNext()) list.add(i.next());
    p(list);
    return list;
  }

  private void p(Collection c) { 
   {} //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
   Iterator i = c.iterator();
   while (i.hasNext()) {}{} //System.out.println(i.next());
   {} //System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
  }
  
//  private String makeKey(Metadata data) { return data.get(nameFieldname) + Names.DELIMITER + data.get(branchFieldname); }
  private String makeKey(Metadata data) { return data.get(nameFieldname); }

  public boolean keep(Object o) { return true; }

  private Branch createBranch(Metadata data) {
   Branch branch = new BranchBase();
   branch.defineKeyFields(nameFieldname, branchFieldname, revisionFieldname);
   branch.defineKey(data);
   branch.setAscendingOrder(ascendingOrder);
   return branch;
  }
  
  public String getNameFieldname() { return nameFieldname; }
  public void setNameFieldname(String s) { nameFieldname=s; }
  public String getBranchFieldname() { return branchFieldname; }
  public void setBranchFieldname(String s) { branchFieldname=s; }
  public String getRevisionFieldname() { return revisionFieldname; }
  public void setRevisionFieldname(String s) { revisionFieldname=s; }
  public String getVersionFieldname() { return versionFieldname; }
  public void setVersionFieldname(String s) { versionFieldname=s; }

  public boolean getAscendingOrder() { return ascendingOrder; }
  public void setAscendingOrder(boolean b) { ascendingOrder=b; }
  private String nameFieldname="Name";
  private String branchFieldname="Branch";
  private String revisionFieldname="Revision";
  private String versionFieldname="Version";
  private boolean ascendingOrder=false;
}
