package com.zws.functor.report;

import com.zws.application.Constants;
import com.zws.domo.document.DocumentInterface;
import com.zws.functor.ListFunctor;
import com.zws.functor.filter.CollectiveFilter;
import com.zws.functor.report.modifier.CriteriaModifier;
import com.zws.functor.search.SearchAgent;
import com.zws.functor.util.FunctorIterator;
import com.zws.functor.util.FunctorVector;
import com.zws.service.config.DataReportService;
import com.zws.service.config.SearchAgentService;
import com.zws.util.stream.StreamableCollection;

import java.util.*;

public class DataReport extends ListFunctor {

  public void execute() {
    try { generate(); filter();}
    catch (Exception e) {
      setException(e);
      e.printStackTrace();
    }
  }

  public void generate() throws Exception {
    SearchAgent agent=null;
    Collection agents = new Vector();
    loadAgents();
    FunctorIterator i = searchAgents.copyIterator();
    while (i.hasNext()) {
      agent = (SearchAgent)i.copyNext();
      agent.setOriginFields(getOriginFields());
      agent.bind(DataReportService.findMetaDataMapping(getName(), agent.getName()));
      agents.add(agent);
    }
    setCriteria(modifyCriteria(getCriteria()));
    SearchAgentService.search(getCriteria(), agents, getMemberID(), (StreamableCollection)getResultBuffer(), !(getStreamingEnabled() && getStreaming()));
  }


  public String modifyCriteria(String c) throws Exception  {
    String newCriteria = c;
    FunctorIterator i = criteriaModifiers.copyIterator();
    CriteriaModifier m;
    while (i.hasNext()) {
      m = (CriteriaModifier)i.copyNext();
      m.setCriteria(newCriteria);
      m.modify();
      newCriteria = m.getCriteria();
    }
    return newCriteria;
  }

  public void filter() throws Exception  {
    FunctorIterator i = filters.copyIterator();
    while (i.hasNext()) filter((CollectiveFilter)i.copyNext());
    {} //System.out.println(toXML());
  }

  public void filter(CollectiveFilter f) throws Exception  {
    f.bind(getResults());
    f.filter();
    setResults(f.getResults());
  }

  private void loadAgents() throws Exception {
    searchAgents.clear();
    StringTokenizer tokens = new StringTokenizer(getAgentNames(), Constants.DELIMITER);
    while (tokens.hasMoreTokens()) {
      searchAgents.add(SearchAgentService.find(tokens.nextToken()));
    }
  }

  public void addDisplayField(DisplayField field) throws Exception{
    if (null!=displayFieldMap.get(name)) throw new Exception("Display field '"+field.getName()+"' already added to "+ getName()+" Data Report");
    displayFieldMap.put(field.getName(), field);
    displayFields.add(field);
  }

  public void clearVisibleDisplayFields(){
    Iterator i = displayFields.iterator();
    while (i.hasNext()) ((DisplayField)i.next()).setVisible(false);
  }
  public void setVisible(String fieldName) {
    DisplayField f = (DisplayField)displayFieldMap.get(fieldName);
    f.setVisible(true);
  }

  public void add(CollectiveFilter f) { filters.add(f); }
  public void add(CriteriaModifier f) { criteriaModifiers.add(f); }

  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getOriginFields() { return originFields; }
  public void setOriginFields(String s) { originFields=s; }
  public String getDisplayKey() { return displayKey; }
  public void setDisplayKey(String s) { displayKey=s; }
  public String getMemberID() { return memberID; }
  public void setMemberID(String s) { memberID=s; }
  public String getCriteria() { return criteria; }
  public void setCriteria(String s) { criteria=s; }
  public String getAgentNames() { return agentNames; }
  public void setAgentNames(String s) { agentNames = s; }
  public Collection getDisplayFields() { return displayFields; }
  public Collection getVisibleDisplayFields() {
    Collection c=new Vector();
    Iterator i = displayFields.iterator();
    DisplayField f;
    while (i.hasNext()){
      f = (DisplayField) i.next();
      if (f.getVisible()) c.add(f);
    }
    return c;
  }

  public String toXML(){
    String xml="<report name=\""+getName()+"\">\n";
    if (null!=getResults()) {
      Iterator i = getResults().iterator();
      while (i.hasNext()) xml+= ((DocumentInterface)i.next()).toXML();
    }
    xml+="</report>\n";
    return xml;
  }

  public Map getDisplayFieldMap() { return displayFieldMap; }
  public boolean getStreaming() { return streaming; }
  public void setStreaming(boolean b) { streaming=b; }
  public boolean getStreamingEnabled() { return streamingEnabled; }
  public void setStreamingEnabled(boolean b) { streamingEnabled=b; }
  public Collection getResultBuffer() { return (Collection)getResults(); }
  public void setResultBuffer(Collection c) { setResults(c); }

  private String name=null;
  private String originFields="";
  private String memberID = null;
  private String criteria="*";
  private String agentNames = null;
  private Collection displayFields = new Vector();
  private Map displayFieldMap = new HashMap();
  private FunctorVector searchAgents = new FunctorVector();
  private FunctorVector filters = new FunctorVector();
  private FunctorVector criteriaModifiers = new FunctorVector();
  private String displayKey=null;
  private boolean streamingEnabled=false;
  private boolean streaming=false;
}
