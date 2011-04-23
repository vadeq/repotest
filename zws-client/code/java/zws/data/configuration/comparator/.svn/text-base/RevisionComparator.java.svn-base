package zws.data.configuration.comparator;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 5, 2004, 3:56 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.configuration.RevisionSet;

import java.util.Comparator;

public class RevisionComparator implements Comparator {
  public int compare(Object rev0, Object rev1) { return compare((RevisionSet)rev0, (RevisionSet)rev1); }

  public int compare(RevisionSet rev0, RevisionSet rev1) {
    if (ascendingOrder) return rev0.getRevisionLevel().compareTo(rev1.getRevisionLevel()); 
    else return rev1.getRevisionLevel().compareTo(rev0.getRevisionLevel()); 
  }

  public void setAscendingOrder(boolean b) { ascendingOrder=b; }
  public boolean getAscendingOrder() { return ascendingOrder; }

  private boolean ascendingOrder=false;
}
