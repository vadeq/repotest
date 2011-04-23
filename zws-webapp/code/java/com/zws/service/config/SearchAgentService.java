package com.zws.service.config;

import com.zws.functor.search.SearchAgent;
import com.zws.functor.util.WaitForThreads;
import com.zws.util.stream.StreamableCollection;

import java.util.*;

public class SearchAgentService {
  private static String RESOURCE_TYPE = "Search Agent";
  private static SearchAgentService service=null;

//  todo: make constructor private when xml processor is finished
//  private SearchAgentService() {}
  public static SearchAgentService getInstance() {
    if (null==service) service = new SearchAgentService();
    return service;
  }
  public static Collection getSearchAgentNames(){ return agentPrototypes.keySet(); }

  public static void search(String criteria, Collection agents, String memberID, StreamableCollection resultBuffer, boolean waitForResults) throws Exception {
    Iterator i, j;
    Collection threads = new Vector();
    SearchAgent agent;
    Thread thread;
    i = agents.iterator();
    while (i.hasNext()) {
      agent = (SearchAgent)i.next();
      agent.setMemberID(memberID);
      agent.configureSearch(resultBuffer, criteria);
      thread = new Thread(agent);
      thread.start();
      threads.add(thread);
    }
    WaitForThreads waitForAgents = new WaitForThreads();
    waitForAgents.bindThreads(threads);
    waitForAgents.bindStream(resultBuffer);
    Thread t = new Thread(waitForAgents);
    waitForAgents.setThread(t);
    waitForAgents.execute();
    if (waitForResults) waitForAgents.getThread().join();
  }

  public static SearchAgent find(String name) throws ConfigurationNotFound {
    SearchAgent agent = (SearchAgent)agentPrototypes.get(name);
    if (null==agent) throw new ConfigurationNotFound(RESOURCE_TYPE, name);
    return (SearchAgent) agent.copy();
  }
  public static Collection findAll() {
    Collection c = new Vector();
    Iterator i = agentPrototypes.values().iterator();
    while (i.hasNext()) c.add(((SearchAgent)i.next()).copy());
    return c;
  }

  public static void add(SearchAgent agent) throws DuplicateConfigurationName{
    if (null==agentPrototypes.get(agent.getName()))
      agentPrototypes.put(agent.getName(), agent);
    else throw new DuplicateConfigurationName(RESOURCE_TYPE, agent.getName());
  }

  public static SearchAgent remove(SearchAgent agent) { return remove(agent.getName()); }

  public static SearchAgent remove(String agentName) { return (SearchAgent) agentPrototypes.remove(agentName); }

  public static void update(SearchAgent agent) {
    agentPrototypes.remove(agent.getName());
    agentPrototypes.put(agent.getName(), agent);
  }

  public static void unload() { agentPrototypes.clear(); }

  private static Map agentPrototypes = new HashMap();
}