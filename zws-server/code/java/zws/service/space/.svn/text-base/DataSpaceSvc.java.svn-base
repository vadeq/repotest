package zws.service.space;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 14, 2004, 1:05 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.data.Metadata;
import zws.data.filter.ListFilter;
import zws.data.filter.UnitFilter;
import zws.datasource.Datasource;
import zws.datasource.intralink.SearchAgent;
import zws.exception.*;
import zws.pkg.DataPackage;
import zws.replication.report.ConflictReport;
import zws.search.SearchAgentBase;
import zws.search.criteria.modifier.CriteriaModifier;
import zws.search.filter.LatestByKey;
import zws.security.Authentication;
import zws.service.PrototypeSvc;
import zws.service.datasource.DatasourceSvc;
import zws.service.search.SearchAgentSvc;
import zws.service.synchronization.SynchronizationSvc;
import zws.space.*;
import zws.util.PrototypeCollection;

import java.util.*;

public class DataSpaceSvc { 
  public static String NAMESPACE = "zws-data-space-service";
  public static String getNamespace() { return NAMESPACE; }
  public static DataSpace find(String name) throws NameNotFound { return (DataSpace)PrototypeSvc.lookup(NAMESPACE, name); }  
  public static Collection getDataSpaceNames() { return PrototypeSvc.getPrototypeNames(NAMESPACE); }
  public static PrototypeCollection findAll() { return PrototypeSvc.findAll(NAMESPACE); }
  public static void add(DataSpace op) throws DuplicateName, InvalidConfiguration {
    PrototypeSvc.add(NAMESPACE, op);
    configure(op);
  }

  public static void update(DataSpace op) { remove(op.getName()); try{add(op);} catch (Exception a) {} }
  public static void remove(DataSpace op) { remove(op.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }

  public static Collection getAvailableUpdates(DataSpace sourceSpace, DataSpace targetSpace) throws Exception {
    Datasource source = DatasourceSvc.find(sourceSpace.getDatasourceName());
    //SearchAgentBase agent = prepareAgent(sourceSpace);
    SearchAgentBase agent = (SearchAgentBase)SearchAgentSvc.find(sourceSpace.getSearchAgentName());
    if (null!=sourceSpace.getSpaceCriteria()) agent.setCriteria(sourceSpace.getSpaceCriteria());
    agent.initializeStorage();
    prepareAgent(sourceSpace, agent);
    agent.search();
    agent.setIncludeDependencies(true);
    agent.setDependencyConfiguration(source.getReplicationDependencyConfiguration());
    Collection c = agent.getResults();
    if (null==c || c.size()<1) return null;
    Collection updates = new Vector();
    Iterator i = c.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      if (!SynchronizationSvc.isSynchronizedToDatasource(m.getOrigin(), targetSpace)) {
        if (source.mayHaveSubComponents(m)) source.bindFirstLevelSubComponents(m,true);
        updates.add(m);
      }
    }
    return updates;
  }

  private static void configure(DataSpace space) throws InvalidConfiguration {
    if (!space.getServerName().equals(Server.getName())) return;
    SearchAgentBase agent = null;
    //register a search agent if dataspace points to local repository
    try { agent = prepareAgent(space); }
    catch (InvalidSyntax e) { throw new InvalidConfiguration("Loading Data Space ["+space.getName()+"]: " + e.getMessage()); }
    catch (NameNotFound e) { throw new InvalidConfiguration("Datasource ["+space.getDatasourceName()+"] not found for Data Space " + space.getName()); }
    agent.setName(space.getSearchAgentName());
    try { SearchAgentSvc.add(agent); }
    catch (Exception e) { throw new InvalidConfiguration("Loading Data Space ["+space.getName()+"]: " + e.getMessage()); }

    try { agent = prepareLatestAgent(space); }
    catch (InvalidSyntax e) { throw new InvalidConfiguration("Loading Data Space ["+space.getName()+"]: " + e.getMessage()); }
    catch (NameNotFound e) { throw new InvalidConfiguration("Datasource ["+space.getDatasourceName()+"] not found for Data Space " + space.getName()); }
    agent.setName(space.getSearchAgentName()+"-latest");
    try { SearchAgentSvc.add(agent); }
    catch (Exception e) { throw new InvalidConfiguration("Loading Data Space ["+space.getName()+"]: " + e.getMessage()); }

    try { agent = prepareLatestRevAgent(space); }
    catch (InvalidSyntax e) { throw new InvalidConfiguration("Loading Data Space ["+space.getName()+"]: " + e.getMessage()); }
    catch (NameNotFound e) { throw new InvalidConfiguration("Datasource ["+space.getDatasourceName()+"] not found for Data Space " + space.getName()); }
    agent.setName(space.getSearchAgentName()+"-latest-rev");
    try { SearchAgentSvc.add(agent); }
    catch (Exception e) { throw new InvalidConfiguration("Loading Data Space ["+space.getName()+"]: " + e.getMessage()); }
  }

  public static SearchAgentBase prepareAgent(DataSpace space) throws NameNotFound, InvalidSyntax {
    Datasource source = DatasourceSvc.find(space.getDatasourceName());
    SearchAgentBase agent = (SearchAgentBase)source.materializeSearchAgent();
    return prepareAgent(space, agent);
  }
  

  public static SearchAgentBase prepareLatestAgent(DataSpace space) throws NameNotFound, InvalidSyntax {
    Datasource source = DatasourceSvc.find(space.getDatasourceName());
    SearchAgentBase agent = (SearchAgentBase)source.materializeLatestSearchAgent();
    return prepareAgent(space, agent);
  }

  public static SearchAgentBase prepareLatestRevAgent(DataSpace space) throws NameNotFound, InvalidSyntax {
    Datasource source = DatasourceSvc.find(space.getDatasourceName());
    SearchAgentBase agent = (SearchAgentBase)source.materializeLatestRevSearchAgent();
    return prepareAgent(space, agent);
  }
  
  public static SearchAgentBase prepareAgent(DataSpace space, SearchAgentBase agent) throws NameNotFound, InvalidSyntax {
    agent.setChooseOnlyBinaries(space.getChooseOnlyBinaries());
    addUnitFilters(space.getUnitFilters(), agent);
    addListFilters(space.getListFilters(), agent);
    addCriteriaModifiers(space.getCriteriaModifiers(), agent);
    agent.setOrderBy(space.getTimeOfCreationField());
    agent.setAscending(true);
    if (null!=space.getSpaceCriteria()) agent.setCriteria(space.getSpaceCriteria());
    agent.setIncludeHistory(space.getIncludeHistory());
    agent.setIncludeDependencies(space.getIncludeDependencies());
    agent.setDatedAfter(space.getDatedAfter());
    agent.setDatedBefore(space.getDatedBefore());
    return agent;
  }
  
  private static void addUnitFilters(Collection c, SearchAgentBase agent) {
    if (null==c) return;
    Iterator i = c.iterator();
    while (i.hasNext()) agent.add((UnitFilter)i.next());
  }
  private static void addListFilters(Collection c, SearchAgentBase agent) {
    if (null==c) return;
    Iterator i = c.iterator();
    while (i.hasNext()) agent.add((ListFilter)i.next());
  }
  private static void addCriteriaModifiers(Collection c, SearchAgentBase agent) {
    if (null==c) return;
    Iterator i = c.iterator();
    while (i.hasNext()) agent.add((CriteriaModifier)i.next());
  }
  
  public static DataPackage createDesignPackage(DataSpace sourceSpace, String packageName, Collection updates, String tarballName, Authentication id) throws Exception {
    if (!sourceSpace.getDomainName().equalsIgnoreCase(Server.getDomainName()) || !sourceSpace.getServerName().equalsIgnoreCase(Server.getName())) throw new InvalidRouting(sourceSpace.getDomainName(), sourceSpace.getServerName());
    Datasource source = DatasourceSvc.find(sourceSpace.getDatasourceName());
    return source.createDesignPackage(packageName, updates, tarballName, sourceSpace.getIncludeHistory(), id);
  }
  
  public static DataPackage createPackage(DataSpace sourceSpace, String packageName, Collection updates, String tarballName, Authentication id) throws Exception {
    if (!sourceSpace.getDomainName().equalsIgnoreCase(Server.getDomainName()) || !sourceSpace.getServerName().equalsIgnoreCase(Server.getName())) throw new InvalidRouting(sourceSpace.getDomainName(), sourceSpace.getServerName());
    Datasource source = DatasourceSvc.find(sourceSpace.getDatasourceName());
    return source.createPackage(packageName, updates, tarballName, sourceSpace.getIncludeHistory(), id);
  }
  
  public static Collection importDesignPackage(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws Exception {
    if (!targetSpace.getDomainName().equalsIgnoreCase(Server.getDomainName()) || !targetSpace.getServerName().equalsIgnoreCase(Server.getName())) throw new InvalidRouting(targetSpace.getDomainName(), targetSpace.getServerName());
    Datasource target = DatasourceSvc.find(targetSpace.getDatasourceName());
    return target.importDesignPackage(dPkg, targetSpace.getMappingSet(), targetSpace.getLockOnImport(), targetSpace.getOverwriteConflicts(), id);
  }
  
  public static Collection importPackage(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws Exception {
    if (!targetSpace.getDomainName().equalsIgnoreCase(Server.getDomainName()) || !targetSpace.getServerName().equalsIgnoreCase(Server.getName())) throw new InvalidRouting(targetSpace.getDomainName(), targetSpace.getServerName());
    Datasource target = DatasourceSvc.find(targetSpace.getDatasourceName());
    return target.importPackage(dPkg, targetSpace.getMappingSet(), targetSpace.getLockOnImport(), id);
  }
  public static Collection synchronizeFromPackage(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws Exception {
    if (!targetSpace.getDomainName().equalsIgnoreCase(Server.getDomainName()) || !targetSpace.getServerName().equalsIgnoreCase(Server.getName())) throw new InvalidRouting(targetSpace.getDomainName(), targetSpace.getServerName());
    Datasource target = DatasourceSvc.find(targetSpace.getDatasourceName());
    return target.synchronizePackage(dPkg, targetSpace.getMappingSet(), targetSpace.getLockOnImport(), id);
  }
  
  public static ConflictReport reportConflicts(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws Exception {
    if (!targetSpace.getDomainName().equalsIgnoreCase(Server.getDomainName()) || !targetSpace.getServerName().equalsIgnoreCase(Server.getName())) throw new InvalidRouting(targetSpace.getDomainName(), targetSpace.getServerName());
    Datasource target = DatasourceSvc.find(targetSpace.getDatasourceName());
    return target.reportConflicts(dPkg, id);
  }
}

