package zws.report;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.data.transformer.metadata.CriteriaGrammar;
import zws.data.transformer.metadata.MetadataRemappingSpec;
import zws.exception.InitializationError;
import zws.log.failure.Failure;
import zws.log.warning.Warning;
import zws.report.modifier.CriteriaModifier;
import zws.search.SearchAgent;
import zws.search.SearchAgentBase;
import zws.util.*;
import zws.xml.util.XMLString;

import java.io.*;
import java.util.*;

public class ReportBase extends SearchAgentBase implements Report, Serializable {
  public final void generate() throws Exception { search(); }


  public void search() throws Exception {
    initialize();
    prepareStorage();
    getResults().clear();
    if (getModifiersEnabled()) modifyCriteria();
    executeQuery();
    if (getFiltersEnabled()) filterResults();
    if (getTransformersEnabled()) transformResults();
  }

  public void initialize() throws InitializationError {
  	resetStorage();
    clearLogs();
    //try { loadAgents(); }
    //catch (NameNotFound e) { throw new InitializationError("Name not found: " + e.getMessage()); }
  }

  public void prepareStorage() throws Exception {
  	resetStorage();
    if (null!=getSortComparator()) initializeStorage(new TreeSet(getSortComparator()));
    else initializeStorage(new Vector());
  }
  public void addSystemAttributes(Metadata data) {;}
  public void executeQuery() throws Exception {
    clearLogs();
    Iterator a = searchAgents.values().iterator();
    {} //System.out.println("starting with "+searchAgents.size()+"agents");
    SearchAgent agent;
    String crit = getCriteria().toString();
    try { crit = transformCriteria(crit); }
    catch (Exception e) {
      crit = getCriteria().toString();
      log(new Warning("Report ["+ getName()+"] could not transform criteria. Using unmodified criteria ="+getCriteria()));
    }
    WaitForThreads waiter = new WaitForThreads();
    TransformerCollection set=null;
    while (a.hasNext()){
      agent = (SearchAgent)a.next();
      MetadataRemappingSpec mapping = (MetadataRemappingSpec)agentMappings.get(agent.getName().trim());
      agent.setCriteria(CriteriaGrammar.reverseMapCriteria(crit,mapping));
      agent.setSelect(combine(mapping.getMappings().values()));
      agent.setOrderBy(getOrderBy());
      agent.setAscending(getAscending());
      agent.setSkipCount(getSkipCount());
      agent.setMaxCount(getMaxCount());
      agent.setAuthenticationToken(getAuthenticationToken());


  	  TransformerCollection agentSet=null;
  	  if (null!=agent.getSortComparator()) agentSet = new TransformerTreeSet(agent.getSortComparator());
  	  else agentSet = new TransformerVector();
  	  agentSet.setTransformer(mapping);
  	  agent.resetStorage();
  	  agent.initializeStorage(agentSet);

      try {
        //Collection c = agent.getResults();
        //if (null==c) {} //System.out.println("c is null");
        //else {} //System.out.println("c is not nullllllllllllllllllll");
        //!!!!!!1agent.execute();
        //!!!!!!11waiter.addThread(agent.getThread());
        agent.search();
        //c = agent.getResults();
        //if (null==c) {} //System.out.println("c is null");
        //else {} //System.out.println("c is not nullllllllllllllllll");
        //c = c;
      }
      catch (Throwable e) { {} //System.out.println("caught error: " + e.getMessage()); e.printStackTrace(); log(new Failure("error.could.not.wait.for.threads", "Report", getName(), new Exception(e.getMessage()))); }
//      catch (Throwable e) { e.printStackTrace(); log(new Warning("warning.could.not.start.search", agent.getName(), new Exception(e.getMessage()))); }
    }
    throwOnFailure(true);
    try {
      {} //System.out.println("waiting..");
      //!!!!!!!11waiter.execute();
      //!!!!!!!!11if (/*waitForResults*/ true) waiter.getThread().join();
      {} //System.out.println("done agent Count=" +searchAgents.size());
      a = searchAgents.values().iterator();
      Collection results = new Vector();
      while (a.hasNext()) {
        agent = (SearchAgent) a.next();
        if (null!=agent.getFailures()) logFailures(agent.getFailures());
        if (null!=agent.getWarnings()) logWarnings(agent.getWarnings());
        if (null!=agent.getResults()) {
          {} //System.out.println("adding " + agent.getResults().size());
          results.addAll(agent.getResults());
        }
        //agent.clearThread();
        //++collect all status, warnings and errors from agents into report
      }
      resetStorage();
      initializeStorage(results);
    }
    catch (Throwable e) { e.printStackTrace(); 
    {} //System.out.println("caught error: " + e.getMessage()); 
    e.printStackTrace(); 
    log(new Failure("error.could.not.wait.for.threads", "Report", getName(), new Exception(e.getMessage()))); 
    }
    }
    {} //System.out.println("COMPLETED SEARCH: FOUND " + getResults().size());
  }

  private String combine(Collection c) {
    String s=null, field;
    Iterator i = c.iterator();
    if (i.hasNext()) s = (String)i.next();
    while (i.hasNext()) {
      field = (String)i.next();
      if (!field.startsWith("zws-")) s += "," + field;
    }
    return s;
  }

  public String transformCriteria(String c) throws Exception  {
    if (true) return c;
    String newCriteria = c;
    PrototypeIterator i = criteriaModifiers.prototypeIterator();
    CriteriaModifier m;
    while (i.hasNext()) {
      m = (CriteriaModifier)i.shallowCopyNext();
      m.setCriteria(newCriteria);
      m.modify();
      newCriteria = m.getCriteria();
    }
    return newCriteria;
  }

  public String[] getMetadataFields(){
    String[] fields = new String[metadataFields.size()];
    int idx=0;
    Iterator i = metadataFields.iterator();
    while (i.hasNext()) fields[idx++] = (String) i.next();
    return fields;
  }

  public void add(String metadataField) { metadataFields.add(metadataField); metadataFieldsMap.put(metadataField, metadataField); }
  public void add(StringValue metadataField) { metadataFields.add(metadataField.getValue()); metadataFieldsMap.put(metadataField.getValue(), metadataField.getValue()); }
  public void add(SearchAgent agent) { searchAgents.put(agent.getName(), agent); }

  /*
  private void loadAgents() throws NameNotFound, InitializationError {
    if (null==getAgents() || 0 < searchAgents.size()) return;
    StringTokenizer tokens = new StringTokenizer(getAgents(), Names.DELIMITER);
    while (tokens.hasMoreTokens()) addAgent(tokens.nextToken().trim());
  }

  private void addAgent(String spec) throws NameNotFound, InitializationError {
    SearchAgent agent=null;
    MetadataRemappingSpec mapping=null;
    StringTokenizer tokens = new StringTokenizer(spec, Properties.get(Names.DELIMITER_REPORT_AGENT_MAPPING));
    if (tokens.hasMoreTokens()) agent = SearchAgentSvc.find(tokens.nextToken().trim());
    else throw new InitializationError("No Agent specified: '"+spec+"'");
    if (tokens.hasMoreTokens()) mapping = (MetadataRemappingSpec)TransformerSvc.find(tokens.nextToken().trim());
    else throw new InitializationError("Report ["+getName()+"]: No mapping specified: " + spec);
    validate(mapping);
    agentMappings.put(agent.getName(), mapping);
    TransformerCollection set=null;
    if (null!=agent.getSortComparator()) set = new TransformerTreeSet(agent.getSortComparator());
    else set = new TransformerVector();
    set.setTransformer(mapping);
    agent.setResults(set);
    add(agent);
  }


  private void validate(MetadataRemappingSpec mapping) throws InitializationError {
    Iterator i = mapping.getMappings().keySet().iterator();
    String field;
    while (i.hasNext()) {
      field = (String) i.next();
      if (null==metadataFieldsMap.get(field)) throw new InitializationError("Metadata field ["+field+"]specified in mapping ["+mapping.getName()+"]does not exist in report ["+ getName()+"]");
    }
  }


  private void add(String agentName) throws NameNotFound {
    SearchAgent agent = SearchAgentSvc.find(agentName);
    searchAgents.put(agentName, agent);
    String s =new String();
  }
*/

  public String getAgents() { return agentNames; }
  public void setAgents(String s) { agentNames=s; }


  public void validate(MetadataRemappingSpec mapping) throws InitializationError {
    Iterator i = mapping.getMappings().keySet().iterator();
    String field;
    while (i.hasNext()) {
      field = (String) i.next();
      if (null==getMetadataFieldsMap().get(field)) throw new InitializationError("Metadata field ["+field+"]specified in mapping ["+mapping.getName()+"]does not exist in report ["+ getName()+"]");
    }
  }


  public String toString(){
    try {
      StringBuffer xml= new StringBuffer();
      write(xml);
      return xml.toString();
    }
    catch (Exception e) { e.printStackTrace(); return e.getMessage();}
  }

  public String toXML(){ return toString(); }


  public void write(StringBuffer xml) throws Exception {
    //++remove all calls to an toString() methods();
    xml.append("<report name=\"");
    XMLString.writeValue(getName(), xml);
    xml.append("\" >");
    Iterator i;
    if (null!=getResults()) {
      i = getResults().iterator();
      while (i.hasNext()) {
        xml.append(Names.NEW_LINE);
        ((Metadata)i.next()).write(xml, metadataFields);
      }
    }
    if (null!=getStatusLog() && 0 < getStatusLog().size() ) {
      xml.append(Names.NEW_LINE).append("<status-log>");
      i = getStatusLog().iterator();
      while(i.hasNext()) {
        xml.append(Names.NEW_LINE).append(START_STATUS);
        XMLString.writeValue(i.next().toString(), xml);
        xml.append(END_MESSAGE);
      }
      xml.append(Names.NEW_LINE).append("</status-log>");
    }
    if (null!=getWarnings()) {
      xml.append(Names.NEW_LINE).append("<warning-log>");
      i = getWarnings().iterator();
      while(i.hasNext()) {
        xml.append(Names.NEW_LINE).append(START_WARNING);
        XMLString.writeValue(i.next().toString(), xml);
        xml.append(END_MESSAGE);
      }
      xml.append(Names.NEW_LINE).append("</warning-log>");
    }
    if (null!=getFailures()) {
      xml.append(Names.NEW_LINE).append("<failure-log>");
      i = getFailures().iterator();
      while(i.hasNext()) {
        xml.append(Names.NEW_LINE).append(START_FAILURE);
        XMLString.writeValue(i.next().toString(), xml);
        xml.append(END_MESSAGE);
      }
      xml.append(Names.NEW_LINE).append("</failure-log>");
    }
    xml.append(Names.NEW_LINE).append("</report>\n");
  }

  public Map getSearchAgents() { return searchAgents; }
  public Map getMetadataFieldsMap() { return metadataFieldsMap; }
  public Map getAgentMappings(){ return agentMappings; }
  private String agentNames=null;
  private Map searchAgents = new HashMap();
  private Map agentMappings = new HashMap();
  private Collection metadataFields = new Vector();
  private Map metadataFieldsMap = new HashMap();
  private PrototypeCollection unitFilters = new PrototypeVector();
  private PrototypeCollection collectiveFilters = new PrototypeVector();
  private PrototypeCollection criteriaModifiers = new PrototypeVector();
  private static String START_STATUS="<status message=\"";
  private static String START_WARNING="<warning message=\"";
  private static String START_FAILURE="<failure message=\"";
  private static String END_MESSAGE="\"/>";
}
