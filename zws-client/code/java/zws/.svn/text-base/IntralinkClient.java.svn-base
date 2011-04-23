package zws; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 27, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.util.*;
import zws.util.MapUtil;
import zws.data.transformer.metadata.MetadataRemappingSpec;
import zws.datasource.ProxyEJBSearchAgent;
import zws.exception.InitializationError;
import zws.folder.IntralinkFolder;
import zws.report.*;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.space.DataSpace;
import zws.util.comparator.AlphaNumericComparator;

import java.util.*;

import javax.naming.NameNotFoundException;

public class IntralinkClient {

  public void initialize(Authentication id) { auth=id; }

  public IntralinkClient() {
    SortedSet servers = getServerList();
    serverMap = new HashMap();

   Iterator i = servers.iterator();
   while (i.hasNext()) { 
	   try { loadServer((String)i.next()); }
	   catch(Exception e) { e.printStackTrace(); }
   }
   if(0<servers.size()) {
     try {
       setSelectedServer(servers.first().toString());
       SortedSet r = getRepositoryList();
       if(0<r.size()) setSelectedRepository(r.first().toString());
     }
     catch(InitializationError ignore) {ignore.printStackTrace(); }
   }
  }

  private void loadServer(String server) throws Exception {
	  SortedSet repositories = MapUtil.getSortedSetFromMap(serverMap, server, comparator);
	  try {
	    IntralinkAccess client = IntralinkAccess.getAccess();
 	    repositories.addAll(client.listIntralinkRepositories(server));
	  }
	  catch (NameNotFoundException e) { {} //System.out.println(e.getMessage()); }
	    
	  }
	}

  public SortedSet getServerList() { return Server.getServerList(); }
  public SortedSet getRepositoryList() {
	  return MapUtil.getSortedSetFromMap(serverMap, getSelectedServer(), comparator);
	}  
  public SortedSet getRepositories(String server) {
	  return MapUtil.getSortedSetFromMap(serverMap, server, comparator);
	}
  
  public String getSelectedServer() { return selectedServer; }
  public void setSelectedServer(String s) throws InitializationError {
    if (!getServerList().contains(s)) throw new InitializationError(s + " is not one of the available servers");      
    selectedServer=s;
    selectedRepository=null; 
  }
  
  public String getSelectedRepository() { return selectedRepository; }  
  public void setSelectedRepository(String s) throws InitializationError {
    if (null==selectedServer) throw new InitializationError("Server not selected");
    if (!getRepositoryList().contains(s)) throw new InitializationError(s + " is not one of the repositories in the selected server: " + selectedServer ); 
    selectedRepository=s;
  }

  public void validateSelection() throws InitializationError {
    if (null==selectedServer) throw new InitializationError("Server not selected");
    if (null==selectedRepository) throw new InitializationError("Repository not selected");
    if (!getServerList().contains(selectedServer)) throw new InitializationError(selectedServer+ " is not one of the available servers");
    if (!getRepositoryList().contains(selectedRepository)) throw new InitializationError(selectedRepository + " is not one of the repositories in the selected server: " + selectedServer ); 
  }

  public void pauseEventListener()  throws Exception { 
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.pauseEventListener(selectedServer, selectedRepository);
	}

  public void resumeEventListener() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.resumeEventListener(selectedServer, selectedRepository);
	}

  public void stopEventListener()  throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.stopEventListener(selectedServer, selectedRepository);
	}

  public void ignoreEventListenerEvents() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.ignoreEventListenerEvents(selectedServer, selectedRepository);
	}

  public void fireEventListenerEvents() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.fireEventListenerEvents(selectedServer, selectedRepository);
	}

  public void startEventListener() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.startEventListener(selectedServer, selectedRepository);
	}

  public String getEventListenerRunningState() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  String s = client.getEventListenerRunningState(selectedServer, selectedRepository);
	  return s;
	}

  public String getEventListenerEventFiringState() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  String s = client.getEventListenerEventFiringState(selectedServer, selectedRepository);
	  return s;
	}

  public Collection getEventListenerHistoryLog() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  Collection c = client.getEventListenerHistoryLog(selectedServer, selectedRepository);
	  return c;
	}

  public int getEventListenerHistoryLogDuration() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  int i = client.getEventListenerHistoryLogDuration(selectedServer, selectedRepository);
	  return i;
	}

  public void setEventListenerHistoryLogDuration(int hours) throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.setEventListenerHistoryLogDuration(selectedServer, selectedRepository, hours);
	}

  public int getEventListenerRunPeriod() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  int i = client.getEventListenerRunPeriod(selectedServer, selectedRepository);
	  return i;
	}

  public void setEventListenerRunPeriod(int seconds) throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.setEventListenerRunPeriod(selectedServer, selectedRepository, seconds);
	}

  public Collection getEventListenerTargetQueueNodes() throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  return client.getEventListenerTargetQueueNodes(selectedServer, selectedRepository);
	}

  public void addEventListenerTargetQueueNode(String serverNode) throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.addEventListenerTargetQueueNode(selectedServer, selectedRepository, serverNode);
	}

  public void removeEventListenerTargetQueueNode(String serverNode) throws Exception {
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.removeEventListenerTargetQueueNode(selectedServer, selectedRepository, serverNode);
	}

  public void rename(String name, String newName) throws Exception { 
	  validateSelection(); 
	  IntralinkAccess client = IntralinkAccess.getAccess();
	  client.rename(selectedServer, selectedRepository, name, newName, auth);
	}
  
  public Collection listNames() throws Exception {
    validateSelection(); 
    IntralinkAccess client = IntralinkAccess.getAccess();
    Collection c = client.listNames(selectedServer, selectedRepository, auth);
    return c;
  }
  
  public Collection getUserDefinedAttributes() throws Exception {
    validateSelection(); 
    Collection x = MapUtil.getCollectionFromMap(intralinkConfigs, repositoryKey(USER_ATTRIBUTES));
    if (x.isEmpty()) {
      IntralinkAccess client = IntralinkAccess.getAccess();
      Collection c = client.getUserDefinedAttributes(selectedServer, selectedRepository, auth);
      if (null!=c) x.addAll(c);
    }
    return x;
  }

  public Collection getRevisionSequence() throws Exception {
    validateSelection(); 
	  Collection x = MapUtil.getCollectionFromMap(intralinkConfigs, repositoryKey(REVISIONS));
	  if (x.isEmpty()) {
	    IntralinkAccess client = IntralinkAccess.getAccess();
	    Collection c = new Vector();
	    //Collection c = client.getRevisionSequence(selectedServer, selectedRepository, auth);
	    if (null!=c) x.addAll(c);
	  }
	  return x;
  }

  public Collection getReleaseSequence(String releaseScheme) throws Exception {
    validateSelection(); 
	  Collection x = MapUtil.getCollectionFromMap(intralinkConfigs, repositoryKey(releaseScheme, RELEASES));
	  if (x.isEmpty()) {
	    IntralinkAccess client = IntralinkAccess.getAccess();
	    Collection c = new Vector();
	    //Collection c = client.getReleaseSequence(selectedServer, selectedRepository, releaseScheme, auth);
	    if (null!=c) x.addAll(c);
	  }
	  return x;
  }

  public IntralinkFolder getRootFolder() throws Exception {
    validateSelection(); 
	  IntralinkFolder x = (IntralinkFolder)intralinkConfigs.get(repositoryKey(FOLDER_TREE));
	  if (null==x) {
	    IntralinkAccess client = IntralinkAccess.getAccess();
	    x = client.getRootFolder(selectedServer, selectedRepository, auth);
	    intralinkConfigs.put(repositoryKey(FOLDER_TREE), x);
	  }
	  return x;
  }

  public Report getReport() { return getReport(REPORT_DEFAULT); }

  public Report materializeOwnershipReport() throws Exception {
	  ReportBase report = createOwnershipReport();
	  report.setName("Ownership");
	  String server;
	  String repository;
	  Collection servers = getServerList();
	  Iterator s = servers.iterator();

	  report.resetStorage();
	  while(s.hasNext()) {
	    server = (String)s.next();
	    setSelectedServer(server);
		  Collection repositories = this.getRepositoryList();
		  Iterator r = repositories.iterator();
		  while(r.hasNext()) {
		    repository = (String) r.next();
			  setSelectedRepository(repository);

			  TransformerCollection set = new TransformerVector();
			  ProxyEJBSearchAgent agent = materializeLatestSearchAgent();
			  MetadataRemappingSpec mapping = createOwnershipMapping();
			  set.setTransformer(mapping);
			  agent.initializeStorage(set);			  
			  report.validate(mapping);
			  report.getAgentMappings().put(agent.getName(), mapping);
			  report.add(agent); 
		  }
	  }
	  report.setAuthenticationRequired(true);
	  return report;
	}

  public Report materializeReport(DataSpace space) throws Exception {
    ReportBase report = createReport();
    report.setName(space.getDataRoute());
    ProxyEJBSearchAgent agent = space.materializeSearchAgent();
    MetadataRemappingSpec mapping = createMapping();
    configureReport(report, agent, mapping);
    return report;
  }

  public Report materializeLatestReport(DataSpace space) throws Exception {
    ReportBase report = createReport();
    report.setName(space.getDataRoute()+"-latest");
    ProxyEJBSearchAgent agent = space.materializeLatestSearchAgent();
    MetadataRemappingSpec mapping = createMapping();
    configureReport(report, agent, mapping);
    return report;
  }

  public Report materializeLatestRevReport(DataSpace space) throws Exception {
    ReportBase report = createReport();
    report.setName(space.getDataRoute()+"-latest-rev");
    ProxyEJBSearchAgent agent = space.materializeLatestRevSearchAgent();
    MetadataRemappingSpec mapping = createMapping();
    configureReport(report, agent, mapping);
    return report;
  }
  
  
  public Report getReport(String name) {
    Map reports = MapUtil.getMapFromMap(intralinkConfigs, repositoryKey(REPORTS));
    try { if (reports.isEmpty()) loadReports(); }
    catch (Exception e) { e.printStackTrace(); }
    Report r = (Report)reports.get(name);
    return r; 
  }
  
  private void loadReports() throws Exception {
    Map reports = MapUtil.getMapFromMap(intralinkConfigs, repositoryKey(REPORTS));
    ReportBase report = createReport();
    ReportBase latestReport = createReport();
    ReportBase historyReport = createReport();

    ProxyEJBSearchAgent agent = materializeSearchAgent();
    ProxyEJBSearchAgent latestAgent = materializeLatestSearchAgent();
    ProxyEJBSearchAgent historyAgent = materializeSearchAgent();
    latestAgent.setName(latestAgent.getName()+"-"+REPORT_LATEST);
    historyAgent.setName(latestAgent.getName()+"-"+REPORT_HISTORY);

    MetadataRemappingSpec mapping = createMapping();
    configureReport(report, agent, mapping);
    configureReport(latestReport, latestAgent, mapping);
    configureReport(historyReport, historyAgent, mapping);
    reports.put(REPORT_DEFAULT, report);
    reports.put(REPORT_LATEST, latestReport);
    reports.put(REPORT_HISTORY, historyReport);
  }

  private void configureReport(ReportBase report, SearchAgent agent, MetadataRemappingSpec mapping) throws Exception {
    report.validate(mapping);
	  report.getAgentMappings().put(agent.getName(), mapping);
	  TransformerCollection set=null;
	  if (null!=agent.getSortComparator()) set = new TransformerTreeSet(agent.getSortComparator());
	  else set = new TransformerVector();
	  set.setTransformer(mapping);
	  report.resetStorage();
	  agent.initializeStorage(set);
	  report.add(agent); 
    report.setAuthenticationRequired(true);	  
  }

  private ReportBase createReport() {
		ReportBase report = new ReportBase();
		report.add(ATT_NAME);
		report.add(ATT_BRANCH);
		report.add(ATT_REV);
		report.add(ATT_VER);
		report.add(ATT_RELEASE);
		report.add(ATT_AUTHOR);
		report.add(ATT_DATASOURCE);
		return report;
  }

  private MetadataRemappingSpec createMapping() {
    MetadataRemappingSpec mapping = new MetadataRemappingSpec();
		mapping.add(new KeyValue(ATT_NAME, att_NAME));
		mapping.add(new KeyValue(ATT_BRANCH, att_BRANCH));
		mapping.add(new KeyValue(ATT_REV, att_REV));
		mapping.add(new KeyValue(ATT_VER, att_VER));
		mapping.add(new KeyValue(ATT_RELEASE, att_RELEASE));
		mapping.add(new KeyValue(ATT_AUTHOR, att_AUTHOR));
		mapping.add(new KeyValue(ATT_DATASOURCE, att_DATASOURCE));
		return mapping;
  }


  private ReportBase createOwnershipReport() {
		ReportBase report = new ReportBase();
		report.add(ATT_NAME);
		report.add(ATT_LOCK_OWNER);
		report.add(ATT_LOCK_OWNER_EMAIL);
		report.add(ATT_LOCK_DATE);
		return report;
  }
  
  private MetadataRemappingSpec createOwnershipMapping() {
    MetadataRemappingSpec mapping = new MetadataRemappingSpec();
		mapping.add(new KeyValue(ATT_NAME, att_NAME));
		mapping.add(new KeyValue(ATT_LOCK_OWNER, att_LOCK_OWNER));
		mapping.add(new KeyValue(ATT_LOCK_OWNER_EMAIL, att_LOCK_OWNER_EMAIL));
		mapping.add(new KeyValue(ATT_LOCK_DATE, att_LOCK_DATE));
		return mapping;
  }

  private ProxyEJBSearchAgent materializeSearchAgent() {
    ProxyEJBSearchAgent agent = new ProxyEJBSearchAgent();
    agent.setServerName(getSelectedServer());
    agent.setRemoteAgentName(getSelectedRepository());
    agent.setName(getSelectedRepository());
    return agent;
  }

  private ProxyEJBSearchAgent materializeLatestSearchAgent() {
    ProxyEJBSearchAgent agent = new ProxyEJBSearchAgent();
    agent.setServerName(getSelectedServer());
    agent.setRemoteAgentName(getSelectedRepository()+"-latest");
    agent.setName(getSelectedRepository()+"-latest");
    return agent;
  }
  private String repositoryKey(String seed) { return selectedServer + DOT + selectedRepository + DOT + seed; }
  private String repositoryKey(String seed0, String seed1) { return selectedServer + DOT + selectedRepository + DOT + seed0 + DOT + seed1; }
  
  private Map serverMap=null;
  
  private Authentication auth=null;
  private String selectedServer=null;
  private String selectedRepository=null;

  private Map intralinkConfigs = new HashMap();

  private static String DOT = ".";
  private static AlphaNumericComparator comparator = new AlphaNumericComparator();
  
  private static String RELEASES = "release-sequence";
  private static String REVISIONS = "revision-sequence";
  private static String FOLDER_TREE= "folder-tree";
  private static String USER_ATTRIBUTES = "user-defined-attributes";
  
  private static String REPORTS = "reports";
  private static String REPORT_DEFAULT = "default";
  private static String REPORT_LATEST = "latest";
  private static String REPORT_HISTORY = "history";
  
  //Report Metadata names
  private static String ATT_NAME = "Name";
  private static String ATT_BRANCH = "Branch";
  private static String ATT_REV = "Rev";
  private static String ATT_VER = "Ver";
  private static String ATT_RELEASE = "Release";
  private static String ATT_AUTHOR = "Author";
  private static String ATT_DATASOURCE= "Datasource";
  private static String ATT_LOCK_OWNER = "Locked-By";
  private static String ATT_LOCK_DATE = "Lock-Time";
  private static String ATT_LOCK_OWNER_EMAIL = "Lock-Email";

  //native search-agent attribute names
  private static String att_NAME = "name";
  private static String att_BRANCH = "branch";
  private static String att_REV = "rev";
  private static String att_VER = "ver";
  private static String att_RELEASE = "release-level";
  private static String att_AUTHOR = "created-by";
  private static String att_DATASOURCE= "zws-datasource-name";
  private static String att_LOCK_OWNER = "locked-by";
  private static String att_LOCK_DATE = "lock-time";
  private static String att_LOCK_OWNER_EMAIL = "lock-email";
}
