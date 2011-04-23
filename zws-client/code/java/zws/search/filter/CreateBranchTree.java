package zws.search.filter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 11:21 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.MetadataBase;

import java.util.TreeSet;

//assumes that collection is sorted by "Name"
//creates a uniqueKey using "Name"
//for each uniqueKey, creates a tree categorized and sorted by [branchFieldname]
public class CreateBranchTree extends CreateMetadataTreeByKey {
  public void initialize() {
    setSortInReverse(false);
    setCategorizeInReverse(false);
    setKeyFields("Name");
    setCategoryFields(getBranchFieldname());
  }
  protected void setTree(MetadataBase data, TreeSet tree) { /*data.setBranchTree(tree); */ }
 
  public String getBranchFieldname() { return branchFieldname; }
  public void setBranchFieldname(String s) { branchFieldname=s; }
  
  private String branchFieldname="Branch";
}
