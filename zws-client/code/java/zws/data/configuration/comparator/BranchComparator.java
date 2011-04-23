package zws.data.configuration.comparator;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 5, 2004, 3:56 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.configuration.Branch;

import java.util.Comparator;

public class BranchComparator implements Comparator {
  public int compare(Object branch0, Object branch1) { return compare((Branch)branch0, (Branch)branch1); }
  public int compare(Branch branch0, Branch branch1) { 
    if (ascendingOrder) return branch0.getBranchName().compareTo(branch1.getBranchName()); 
    else return branch1.getBranchName().compareTo(branch0.getBranchName());
  }

  public void setAscendingOrder(boolean b) { ascendingOrder=b; }
  public boolean getAscendingOrder() { return ascendingOrder; }

  private boolean ascendingOrder=true;
}
