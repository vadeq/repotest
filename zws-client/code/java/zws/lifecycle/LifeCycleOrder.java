package zws.lifecycle; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 7, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.exception.InvalidValue;

import java.util.*;
import java.io.Serializable;

public class LifeCycleOrder implements Serializable {

  public List getLifeCycles() { return lifeCycles; }
  public void add(String lifeCycle) { lifeCycles.add(lifeCycle); }
  public void add(Collection lifeCycles) { lifeCycles.addAll(lifeCycles); }
  
  public boolean comesAfter(String lifeCycle, String compareToLifeCycle) throws InvalidValue {
    return 1==compare(lifeCycle, compareToLifeCycle);
  }
  
  public boolean comesBefore(String lifeCycle, String compareToLifeCycle) throws InvalidValue {
    return -1==compare(lifeCycle, compareToLifeCycle);
  }
  
  public int compare(String lifeCycle, String compareToLifeCycle) throws InvalidValue {
    //String l;
    //Iterator i = lifeCycles.iterator();
    //String lco0=null, lco1=null;
    int idxLifeCycle = lifeCycles.indexOf(lifeCycle);
    int idxCompareTo = lifeCycles.indexOf(compareToLifeCycle);
    if (-1 == idxLifeCycle) throw new InvalidValue(lifeCycle);
    if (-1 == idxCompareTo) throw new InvalidValue(compareToLifeCycle);
    if (idxLifeCycle == idxCompareTo) return 0;
    if (idxLifeCycle > idxCompareTo) return 1;
    return -1;
  }
    
  public String getPhaseAfter(String release) {
    int idx=0;
    for (idx=0; idx<lifeCycles.size(); idx++) {
      if(release.equalsIgnoreCase((String)lifeCycles.get(idx))) break;
    }
    idx++;
    if (idx<lifeCycles.size()) return (String)lifeCycles.get(idx);
    else return null;
  }
  
  List lifeCycles = new ArrayList();
}
