package zws.replication.policy;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 2, 2004, 12:36 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidConfiguration;
import zws.space.DataSpace;
import zws.util.DomainContext;
import zws.util.RoutedData;

import java.util.*;

public class BroadcastPolicy extends ReplicationPolicyBase {
  public DataSpace getSourceSpace() {
    if (null==sourceSpace) return null;
    sourceSpace.setSpaceCriteria(getSpaceCriteria());
    sourceSpace.setIncludeDependencies(getIncludeDependencies());
    sourceSpace.setIncludeHistory(getIncludeHistory());
    return sourceSpace; 
  }
  public String getSpaceCriteria() {
    if (null==sourceSpace) return null;
    String criteria = super.getSpaceCriteria(sourceSpace);
    if (null==criteria || criteria.trim().equals("")) return sourceSpace.getSpaceCriteria();
    return criteria;
  }
  public void setSpaceCriteria(String criteria) {
    if (null==sourceSpace) return;
    setSpaceCriteria(sourceSpace, criteria); 
  }
  
  public String getSpaceCriteria(DataSpace space){ 
    return getSpaceCriteria();
  }

  private void initializeContext(RoutedData source) {
    if (null==getContext()) setContext(new DomainContext());
    getContext().setSourceRouting(source);
  }

  public void setSourceSpace(DataSpace space) {
    initializeContext(space);
    if (null==space.getContext()) space.setContext(getContext());
    else space.getContext().setParent(getContext());
    space.setIncludeHistory(getIncludeHistory());
    space.setIncludeDependencies(getIncludeDependencies());
    sourceSpace=space;
  }
  
  public void addTargetSpace(DataSpace target) throws InvalidConfiguration { 
    if (null==sourceSpace) throw new InvalidConfiguration("Source DataSpace must be set before adding a target DataSpace");
    if (null==target.getContext()) target.setContext(new DomainContext());
    target.getContext().setParent(getContext());
    
    target.getContext().setSourceRouting(sourceSpace);
    target.getContext().setTargetRouting(target);

    targetSpaces.put(key(target), target); 
  }
  public Collection getTargetSpaces() { return targetSpaces.values(); }

  public boolean getIncludeHistory() { return includeHistory; }
  public void setIncludeHistory(boolean b) { includeHistory=b; }
  public boolean getIncludeDependencies() { return includeDependencies; }
  public void setIncludeDependencies(boolean b) { includeDependencies=b; }
  
//  public Datasource getSource() { return source ; }
//  public void setSource(Datasource d) { source = d; }
//  public void addTarget(Datasource target) { targets.put(key(target), target); }
//  public Collection getTargets() { return targets.values(); }
  
  private DataSpace sourceSpace=null;
  private Map targetSpaces = new HashMap();

  private Map targets = new HashMap();
  private boolean includeHistory=true;
  private boolean includeDependencies=true;
}
