package zws.space;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 4, 2004, 12:35 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.data.*;
import zws.data.filter.ListFilter;
import zws.data.filter.UnitFilter;
import zws.datasource.ProxyEJBSearchAgent;
import zws.exception.*;
import zws.exception.UnsupportedConstraint;
import zws.exception.UnsupportedOperation;
import zws.mapping.MetadataMappingInstructions;
import zws.mapping.MetadataMappingSet;
import zws.search.criteria.modifier.CriteriaModifier;
import zws.util.*;

import java.util.*;

public abstract class DataSpaceBase extends RoutedDataBase implements DataSpace {
  public DataSpaceBase() {
    setDomainName(Server.getDomainName());
    setServerName(Server.getName());
  }

  public abstract String getTimeOfCreationField();
  public String getSpaceCriteria() { return criteria; }
  public void setSpaceCriteria(String s) { criteria=s; }
  public Collection getUnitFilters(){ return unitFilters; }
  public Collection getListFilters() { return listFilters; }
  public Collection getCriteriaModifiers() { return criteriaModifiers; }
  
  public String getSearchAgentName() { return getDatasourceName() + "data-space" + getName(); }

  public void add(CriteriaModifier m) {
    if (null==criteriaModifiers) criteriaModifiers=new Vector();
    criteriaModifiers.add(m);
  }
  public void add(UnitFilter f) {
    if (null==unitFilters) unitFilters=new Vector();
    unitFilters.add(f);
  }
  public void add(ListFilter f) {
    if (null==listFilters) listFilters = new Vector();
    listFilters.add(f);
  }
  public void remove(CriteriaModifier m) {
    if (null==criteriaModifiers || null==m ) return;
    criteriaModifiers.remove(m);
  }
  public void remove(UnitFilter f) {
    if (null==unitFilters || null==f) return;
    unitFilters.remove(f);
  }
  public void remove(ListFilter f) {
    if (null==listFilters || null==f) return;
    listFilters.remove(f);
  }
  
  public MetadataMappingSet getMappingSet() { return mappings; }
  
  private void initializeContext(){
    if (null==ctx) ctx = new DomainContext();
    ctx.set(DomainContext.TARGET_DOMAIN_NAME, getDomainName());
    ctx.set(DomainContext.TARGET_SERVER_NAME, getServerName());
    ctx.set(DomainContext.TARGET_REPOSITORY_NAME, getDatasourceName());
    if (null==mappings) mappings = new MetadataMappingSet();
    if (null==mappings.getContext()) mappings.setContext(new DomainContext());
    mappings.getContext().setParent(getContext());
  }

  public void add(MetadataMappingInstructions maps){
    initializeContext();
    if (null==maps.getContext()) maps.setContext(new DomainContext());
    mappings.add(maps);
  }
  
  public String map(Metadata data, String fieldName) throws InvalidMapping {
    MetadataBase out = new MetadataBase();
    out.setName(data.getName());
    getMappingSet().setSource(data);
    getMappingSet().setTarget(out);
    getMappingSet().map(fieldName);
    return out.get(fieldName);
  }

/*
  public Collection getAvailableUpdates(DataSpace targetSpace) throws Exception {
    DataSpaceService service = EJBLocator.findService(getServerName());
    return service.getAvailableUpdates(this, targetSpace);
  }
  public DataPackage createPackage(String packageName, Collection updates, Authentication id) throws Exception{
    DataSpaceService service = EJBLocator.findService(getServerName());
    return service.createPackage(this, packageName, updates, id);
  }
  public Collection synchronizePackage(DataPackage dPkg, Authentication id) throws Exception{
    DataSpaceService service = EJBLocator.findService(getServerName());
    return service.synchronizePackage(this, dPkg, id);
  }
 */
  public Map getSpaceConstraints() { return spaceConstraints; }
  public List getSpaceConstraintsForKey(String key) { return MapUtil.getListFromMap(spaceConstraints, key); }
  public void addSpaceConstraint(String field, String value) throws UnsupportedConstraint { MapUtil.getListFromMap(spaceConstraints, field).add(value); }

  public DomainContext getContext() { return ctx; }
  public void setContext(DomainContext x) { ctx=x; }
  
  public final Object copy() {
    if (supportsDeepCopy()) return deepCopy();
    return shallowCopy();
  }

  public Object shallowCopy(){
    try {return clone(); }
    catch (CloneNotSupportedException e){throw new RuntimeException(e.getMessage()); }
  }

  public Object deepCopy(){throw new UnsupportedOperation("deepCopy", "use shallowCopy() or an implementation that supports deepCopy()");}
  public boolean supportsDeepCopy() { return false; }
  public boolean getChooseOnlyBinaries() { return chooseOnlyBinaries; }
  public void setChooseOnlyBinaries(boolean b) { chooseOnlyBinaries=b; }
  
  public boolean getIncludeHistory() { return includeHistory; }
  public void setIncludeHistory(boolean b) { includeHistory=b; }
  public boolean getIncludeDependencies() { return includeDependencies; }
  public void setIncludeDependencies(boolean b) { includeDependencies=b; }
  public String getDatedAfter() { return datedAfter; }
  public void setDatedAfter(String s) { datedAfter=s; }
  public String getDatedBefore() { return datedBefore; }
  public void setDatedBefore(String s) { datedBefore=s; }
  
  public boolean getLockOnImport() { return lockOnImport; }
  public boolean getOverwriteConflicts() { return overwriteConflicts; }
  public void setOverwriteConflicts(boolean  b) { overwriteConflicts=b; }
  public void setLockOnImport(boolean b) { lockOnImport=b; }

  public ProxyEJBSearchAgent materializeSearchAgent() {
    ProxyEJBSearchAgent agent = new ProxyEJBSearchAgent();
    agent.setServerName(getServerName());
    agent.setRemoteAgentName(getSearchAgentName());
    agent.setName(getSearchAgentName());
    agent.setChooseOnlyBinaries(getChooseOnlyBinaries());
    agent.setOrderBy(getTimeOfCreationField());
    agent.setAscending(true);
    try { if (null!=getSpaceCriteria()) agent.setCriteria(getSpaceCriteria()); }
    catch (Exception ignore) { ignore.printStackTrace(); }
    agent.setIncludeHistory(getIncludeHistory());
    agent.setIncludeDependencies(getIncludeDependencies());
    agent.setDatedAfter(getDatedAfter());
    agent.setDatedBefore(getDatedBefore());
    return agent;
  }

  public ProxyEJBSearchAgent materializeLatestSearchAgent() {
	  ProxyEJBSearchAgent agent = new ProxyEJBSearchAgent();
	  agent.setServerName(getServerName());
	  agent.setRemoteAgentName(getSearchAgentName()+"-latest");
	  agent.setName(getSearchAgentName()+"-latest");
	  agent.setChooseOnlyBinaries(getChooseOnlyBinaries());
	  agent.setOrderBy(getTimeOfCreationField());
	  agent.setAscending(true);
	  try { if (null!=getSpaceCriteria()) agent.setCriteria(getSpaceCriteria()); }
	  catch (Exception ignore) { ignore.printStackTrace(); }
	  agent.setIncludeHistory(getIncludeHistory());
	  agent.setIncludeDependencies(getIncludeDependencies());
	  agent.setDatedAfter(getDatedAfter());
	  agent.setDatedBefore(getDatedBefore());
	  return agent;
	}

  public ProxyEJBSearchAgent materializeLatestRevSearchAgent() {
	  ProxyEJBSearchAgent agent = new ProxyEJBSearchAgent();
	  agent.setServerName(getServerName());
	  agent.setRemoteAgentName(getSearchAgentName()+"-latest-rev");
	  agent.setName(getSearchAgentName()+"-latest-rev");
	  agent.setChooseOnlyBinaries(getChooseOnlyBinaries());
	  agent.setOrderBy(getTimeOfCreationField());
	  agent.setAscending(true);
	  try { if (null!=getSpaceCriteria()) agent.setCriteria(getSpaceCriteria()); }
	  catch (Exception ignore) { ignore.printStackTrace(); }
	  agent.setIncludeHistory(getIncludeHistory());
	  agent.setIncludeDependencies(getIncludeDependencies());
	  agent.setDatedAfter(getDatedAfter());
	  agent.setDatedBefore(getDatedBefore());
	  return agent;
	}
  

  public void inactivate() {} 

//  private String name=null;
//  private String domainName=null;
//  private String serverName=null;
//  private String datasourceName=null;

  private String criteria=null;
  private boolean chooseOnlyBinaries=false;
  private Map spaceConstraints = new HashMap();
  private Collection criteriaModifiers = null;
  private Collection unitFilters = null;
  private Collection unitTransformers = null;
  private Collection listFilters = null;
  private Collection listTransformers = null;
  private MetadataMappingSet mappings=null;
  private DomainContext ctx=null;
  private boolean includeHistory=false;
  private boolean includeDependencies=false;
  private String datedAfter=null;
  private String datedBefore=null;
  private boolean lockOnImport=true;
  private boolean overwriteConflicts=false;
}

//  public void fire(Event events);
//  public void handleEvents(List events);
//  public void registerHandler(Handler h); //(DataSpaceHandler h);
/*  
  protected String defineCriteriaComparison(String field, String value) { return field+"="+value; }
  private Criteria createCriteria(){
    CriteriaParser parser = getCriteriaParser();
    Criteria c = new Criteria(parser);
    Iterator i = getSpaceConstraints().keySet().iterator();
    String key;
    List list;
    String criteria="";
    while (i.hasNext()){
      key = (String)i.next();
      list = getSpaceConstraintsForKey(key);
      if(list.size()==1) {
        if (!criteria.equals("")) criteria += " & ";
        criteria+=defineCriteriaComparison(key,(String)list.get(0));
      }
    }
    {} //System.out.println(criteria);
    try { return c.getParser().parse(criteria); }
    catch (InvalidSyntax e) {return null;} // should always be valid syntax
  }
 */

