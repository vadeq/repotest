package zws.replication.policy;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 2, 2004, 12:37 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.Datasource;
import zws.space.DataSpace;

import java.util.*;
public class MultiSynchPolicy extends ReplicationPolicyBase {
  public void addSourceSpace(DataSpace sourceSpace) { sourceSpaces.put(key(sourceSpace), sourceSpace); }
  public Collection getSourceSpaces() { 
    DataSpace source;
    Iterator i = sourceSpaces.values().iterator();
    while (i.hasNext()) {
      source = (DataSpace)i.next();
      source.setSpaceCriteria(getSpaceCriteria(source));
      source.setIncludeHistory(getIncludeHistory());
      source.setIncludeDependencies(getIncludeDependencies());
    }
    return sourceSpaces.values(); 
  }
  public String getSpaceCriteria(DataSpace space){ 
    String criteria = super.getSpaceCriteria(space); 
    if (null==criteria || criteria.trim().equals("")) 
      return ((DataSpace)sourceSpaces.get(key(space))).getSpaceCriteria(); 
    return criteria;
  }

  public void addSource(Datasource source) { sources.put(key(source), source); }
  public Collection getSources() { return sources.values(); }
  
  public boolean getIncludeHistory() { return includeHistory; }
  public void setIncludeHistory(boolean b) { includeHistory=b; }
  public boolean getIncludeDependencies() { return includeDependencies; }
  public void setIncludeDependencies(boolean b) { includeDependencies=b; }

  private Map sources = new HashMap();
  private Map sourceSpaces = new HashMap();
  private boolean includeHistory=true;
  private boolean includeDependencies=false;
  
}
