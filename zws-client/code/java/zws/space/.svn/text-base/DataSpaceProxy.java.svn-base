package zws.space;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 23, 2004, 11:15 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.filter.ListFilter;
import zws.data.filter.UnitFilter;
import zws.datasource.ProxyEJBSearchAgent;
import zws.exception.*;
import zws.exception.UnsupportedConstraint;
import zws.exception.UnsupportedOperation;
import zws.mapping.MetadataMappingInstructions;
import zws.mapping.MetadataMappingSet;
import zws.search.criteria.modifier.CriteriaModifier;
import zws.service.space.DataSpaceService;
import zws.service.space.EJBLocator;
import zws.util.DomainContext;
import zws.util.RoutedDataBase;

import java.util.*;

public class DataSpaceProxy extends RoutedDataBase implements DataSpace {
  private void tryToBind() {
    if (null==getServerName() || null==getName()) return;
    try { lookupSpace(); }
    catch (Exception e) { e.printStackTrace(); }
  }

  private DataSpace lookupSpace() throws Exception {
    if (null==actualDataSpace) {
      DataSpaceService service = EJBLocator.findService(getServerName());
      actualDataSpace = service.find(getName());
      super.setDatasourceName(actualDataSpace.getDatasourceName());
    }
    return actualDataSpace;
  }

  public void setDomainName(String s) { super.setDomainName(s); tryToBind();}
  public void setServerName(String s) { super.setServerName(s); tryToBind(); }
  public String getDatasourceName(String s) { return actualDataSpace.getDatasourceName(); }
  public void setDatasourceName(String s) {
    throw new UnsupportedOperation("setDatasourceName(s) can not be called on a proxy DataSpace: "+getDomainName()+"."+getServerName()+"."+getName()); }
  public void setName(String s) { super.setName(s); tryToBind(); }
  public String getSearchAgentName() { return actualDataSpace.getSearchAgentName(); }  
  
  public ProxyEJBSearchAgent materializeSearchAgent() { return actualDataSpace.materializeSearchAgent(); }
  public ProxyEJBSearchAgent materializeLatestSearchAgent() { return actualDataSpace.materializeLatestSearchAgent(); }
  public ProxyEJBSearchAgent materializeLatestRevSearchAgent() { return actualDataSpace.materializeLatestRevSearchAgent(); }

  public boolean getChooseOnlyBinaries() { return actualDataSpace.getChooseOnlyBinaries(); }
  public boolean getIncludeHistory() { return actualDataSpace.getIncludeHistory(); }
  public void setIncludeHistory(boolean b) { actualDataSpace.setIncludeHistory(b); }
  public boolean getIncludeDependencies() { return actualDataSpace.getIncludeDependencies(); }
  public void setIncludeDependencies(boolean b) { actualDataSpace.setIncludeDependencies(b); }
  public String getDatedAfter() { return actualDataSpace.getDatedAfter(); }
  public void setDatedAfter(String s) { actualDataSpace.setDatedAfter(s); }
  public String getDatedBefore() { return actualDataSpace.getDatedBefore(); }
  public void setDatedBefore(String s) { actualDataSpace.setDatedBefore(s); }

  public boolean getLockOnImport() { return actualDataSpace.getLockOnImport(); }
  public boolean getOverwriteConflicts() { return actualDataSpace.getOverwriteConflicts(); }
  public void setOverwriteConflicts(boolean  b) { actualDataSpace.setOverwriteConflicts(b);}
  public void setLockOnImport(boolean b) { actualDataSpace.setLockOnImport(b); }

  public String getTimeOfCreationField() { return actualDataSpace.getTimeOfCreationField(); }
  public String getSpaceCriteria() { return actualDataSpace.getSpaceCriteria(); }
  public void setSpaceCriteria(String s) { actualDataSpace.setSpaceCriteria(s); }
  public Collection getUnitFilters(){ return actualDataSpace.getUnitFilters(); }
  public Collection getListFilters() { return actualDataSpace.getListFilters(); }
  public Collection getCriteriaModifiers() { return actualDataSpace.getCriteriaModifiers(); }
  
  public void add(CriteriaModifier m) { actualDataSpace.add(m); }
  public void add(UnitFilter f) { actualDataSpace.add(f); }
  public void add(ListFilter f) { actualDataSpace.add(f); }
  public void remove(CriteriaModifier m) { actualDataSpace.add(m); }
  public void remove(UnitFilter f) { actualDataSpace.add(f); }
  public void remove(ListFilter f) { actualDataSpace.add(f); }

  public MetadataMappingSet getMappingSet() { return actualDataSpace.getMappingSet(); }

  public String map(Metadata data, String fieldName) throws InvalidMapping {
    return actualDataSpace.map(data, fieldName);
  }

  public void add(MetadataMappingInstructions maps){ actualDataSpace.add(maps); }

  /*
  public Collection getAvailableUpdates(DataSpace targetSpace) throws Exception { 
    return actualDataSpace.getAvailableUpdates(targetSpace); }
  
  public DataPackage createPackage(String packageName, Collection updates, Authentication id) throws Exception {
    return actualDataSpace.createPackage(packageName, updates, id); }
  
  public Collection synchronizePackage(DataPackage dPkg, Authentication id) throws Exception {
    return actualDataSpace.synchronizePackage(dPkg, id); }
  */
  
  public Map getSpaceConstraints() { return actualDataSpace.getSpaceConstraints(); }
  public List getSpaceConstraintsForKey(String key) { return actualDataSpace.getSpaceConstraintsForKey(key); }
  public void addSpaceConstraint(String field, String value) throws UnsupportedConstraint { actualDataSpace.addSpaceConstraint(field, value); }

  public DomainContext getContext() { return actualDataSpace.getContext(); }
  public void setContext(DomainContext x) { actualDataSpace.setContext(x); }
  
  public final Object copy() {
    if (supportsDeepCopy()) return deepCopy();
    return shallowCopy();
  }

  public Object shallowCopy(){
    try {return clone(); }
    catch (CloneNotSupportedException e){throw new RuntimeException(e.getMessage()); }
  }
  public Object deepCopy(){ return shallowCopy(); }
  public boolean supportsDeepCopy() { return true; }

  public DataSpace getActualSpace() { return actualDataSpace; }
  private DataSpace actualDataSpace=null;
}
