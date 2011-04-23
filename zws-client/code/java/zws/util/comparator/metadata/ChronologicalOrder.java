package zws.util.comparator.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 21, 2004, 7:47 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

import java.util.Comparator;

public class ChronologicalOrder implements Comparator {

  
  public int compare(Object data0, Object data1) { return compare ((Metadata)data0, (Metadata)data1); }

  //never return 0 for chronological ordering - objects created at the same time does not mean that the objects are equal
  public int compare(Metadata data0, Metadata data1) {
    long ta;
    long tb;
    
    if (ascendingOrder) {
      ta = data0.getOrigin().getTimeOfCreationInMillis();
      tb = data1.getOrigin().getTimeOfCreationInMillis();
    }
    else {
      ta = data1.getOrigin().getTimeOfCreationInMillis();
      tb = data0.getOrigin().getTimeOfCreationInMillis();
    }
    if (ta < tb) return -1;
    if (ta > tb) return 1;
    //check for subcomponents if ta==tb. assemblies should be greater in order than parts
    return 1;
  }

  public void setAscendingOrder(boolean b) { ascendingOrder=b; }
  public boolean getAscendingOrder() { return ascendingOrder; }
  
  private boolean ascendingOrder=true;
}
