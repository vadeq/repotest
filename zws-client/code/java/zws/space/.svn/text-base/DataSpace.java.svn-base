package zws.space;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 3, 2004, 10:59 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.filter.ListFilter;
import zws.data.filter.UnitFilter;
import zws.datasource.ProxyEJBSearchAgent;
import zws.exception.InvalidMapping;
import zws.exception.UnsupportedConstraint;
import zws.mapping.MetadataMappingInstructions;
import zws.mapping.MetadataMappingSet;
import zws.search.criteria.modifier.CriteriaModifier;
import zws.util.*;

import java.io.Serializable;
import java.util.*;
//import zws.event.Event;
//import zws.event.Handler;

public interface DataSpace extends RoutedData, Prototype, Serializable, Cloneable {
  public DomainContext getContext();
  public void setContext(DomainContext x);

  public String getDomainName();
  public String getServerName();
  public String getDatasourceName();
  public String getName();
  public String getSpaceCriteria();
  public void setSpaceCriteria(String criteria);

  public ProxyEJBSearchAgent materializeSearchAgent();
  public ProxyEJBSearchAgent materializeLatestSearchAgent();
  public ProxyEJBSearchAgent materializeLatestRevSearchAgent();

  public boolean getIncludeHistory();
  public void setIncludeHistory(boolean b); 
  public boolean getIncludeDependencies();
  public void setIncludeDependencies(boolean b);
  public boolean getLockOnImport();
  public boolean getOverwriteConflicts();
  public void setOverwriteConflicts(boolean b);
  public void setLockOnImport(boolean b);
  public String getDatedAfter();
  public void setDatedAfter(String s);
  public String getDatedBefore();
  public void setDatedBefore(String s);

  public String getTimeOfCreationField();
  public String getSearchAgentName();  
  public Collection getUnitFilters();
  public Collection getCriteriaModifiers();
  public Collection getListFilters();
  public String map(Metadata data, String fieldName) throws InvalidMapping;
  public void add(MetadataMappingInstructions maps);
  public MetadataMappingSet getMappingSet();

  public boolean getChooseOnlyBinaries();
  public void add(CriteriaModifier m);
  public void add(UnitFilter f);
  public void add(ListFilter f);
  public void remove(CriteriaModifier m);
  public void remove(UnitFilter f);
  public void remove(ListFilter f);

  public Map getSpaceConstraints();
  public List getSpaceConstraintsForKey(String key);
  public void addSpaceConstraint(String field, String value) throws UnsupportedConstraint;

 /*  
  public Collection getAvailableUpdates(DataSpace targetSpace) throws Exception;
  public DataPackage createPackage(String packageName, Collection updates, Authentication id) throws Exception;
  public Collection synchronizePackage(DataPackage dPkg, Authentication id) throws Exception;
 */
}

//  public Collection getResults();
//  public void search() throws Exception;
//  public boolean isInSpace(Origin o);
//  public boolean isInSpace(Metadata data);
//  public void fire(Event events);
//  public void handleEvents(List events);
//  public void registerHandler(Handler h); //(DataSpaceHandler h);

//  public Collection search(Criteria c) throws Exception;
//  public Collection search(Criteria c, Calendar sinceTime) throws Exception;
//  public Collection search(Criteria c, Calendar sinceTime, Calendar untillTime) throws Exception;  

//  public Collection synchronizePackage(PackageSource pkg, Authentication id) throws Exception;
//  public Collection listComponents(String location, boolean recursively, Authentication id) throws PathDoesNotExist, Exception;
//  public InputStream findBinary(Origin origin, Authentication id) throws Exception;
//  public long getBinaryLength(Origin origin, Authentication id) throws Exception;
//  public PackageSource createPackage(String pkgName, Collection updates, Authentication id) throws Exception;
