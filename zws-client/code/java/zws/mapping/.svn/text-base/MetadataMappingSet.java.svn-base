package zws.mapping;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 1:32 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.s;
import zws.exception.InvalidMapping;
import zws.util.DomainContext;
import zws.util.RoutedData;

import java.util.*;

//stores a mapping instructions for data comming in from a routable location.
//looks up mapping instructions for a given source and executes it.
public class MetadataMappingSet extends MetadataMappingBase {
  
  protected boolean isMapped(){ return (null!=lookupMappingInstructions(makeKey(getSource().getOrigin()))); }

  public final void map(String fieldName) throws InvalidMapping {
    mapSourceToTarget(fieldName);
  }

  protected void mapSourceToTarget(String fieldName) throws InvalidMapping {
    MetadataMappingInstructions maps = lookupMappingInstructions(makeKey(getSource().getOrigin()));
    maps.setSource(getSource());
    maps.setTarget(getTarget());
    maps.map(fieldName);
    setTarget(maps.getTarget());
  }

  protected  void mapSourceToTarget() throws InvalidMapping {
    MetadataMappingInstructions maps = lookupMappingInstructions(makeKey(getSource().getOrigin()));
    maps.setSource(getSource());
    maps.setTarget(getTarget());
    maps.map();
    setTarget(maps.getTarget());
  }
  
  //mappings can not be hashed initially because their context may not be set until runtime
  private MetadataMappingInstructions lookupMappingInstructions(String key) {
    //hash these on the first lookup, then just lookup from the hash
    MetadataMappingInstructions maps=null;
    Iterator i = mappings.iterator();
    while(i.hasNext()) {
      maps = (MetadataMappingInstructions)i.next();
      if (key.equalsIgnoreCase(makeKey(maps.getSourceRouting()))) return maps;
    }
    return null;
  }
  
  public void add(MetadataMappingInstructions maps) {
    if (null==maps.getContext())  maps.setContext(new DomainContext());
    maps.getContext().setParent(getContext());
    mappings.add(maps);
  }

  private String makeKey(RoutedData source) { return makeKey(source, getTargetRouting()); }
  private String makeKey(RoutedData source, RoutedData target) { return source.getRepositoryRoute()+s.dot+target.getRepositoryRoute(); }

  Collection mappings = new Vector();  //mappings can not be initially hashed because their context may not be set until runtime
}
