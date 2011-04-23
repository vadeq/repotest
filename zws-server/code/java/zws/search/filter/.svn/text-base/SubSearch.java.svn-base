package zws.search.filter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 7, 2003, 6:07 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.filter.ListFilterBase;
import zws.exception.NameNotFound;
import zws.search.SearchAgent;
import zws.service.search.SearchAgentSvc;

import java.util.*;

public class SubSearch extends ListFilterBase {
  
  public void filter(Collection list) throws Exception {
    resetStorage();
    initializeStorage(new Vector());
    Iterator i,j;
    Metadata data;
    MetadataBase newData;
    String criteria;
    i = list.iterator();
    while (i.hasNext()){
      data = (Metadata)i.next();
      criteria = defineCriteria(data);
      if (null!=criteria) {
        agent.setCriteria(criteria);
        agent.search();
        if (0==agent.getResults().size()) store(data);
        else {
          j = agent.getResults().iterator();
          while (j.hasNext()) {
            newData = new MetadataBase();
            newData.setOrigin(data.getOrigin());
            newData.setName(data.getName());
            newData.merge(agent.getName(), (Metadata)j.next());
            newData.merge(data);
            store(newData);
          }
        }
      }
    }
  }

  private String defineCriteria(Metadata data) {
    StringTokenizer tokens = new StringTokenizer(criteriaSpec, Names.VALUE_REF_START);
    StringBuffer c = new StringBuffer();
    String fieldRef, token, value=null;
    int idx;
    if (tokens.hasMoreTokens()) c.append(tokens.nextToken());
    while (tokens.hasMoreTokens() && null!=value) { //parameterize rule for what to do if the value is null
      token=tokens.nextToken();
      idx = token.indexOf(Names.VALUE_REF_END);
      fieldRef = (token.substring(0, idx));
      value = data.get(fieldRef);
      if ("".equals(value)) value=null;
      c.append(value);
      c.append(token.substring(idx+1));
    }
    if (null==value) return null;
    return c.toString();
  }
  
  public void setAgent(String s) throws NameNotFound { agent =SearchAgentSvc.find(s); }
  public String getCriteriaSpec() { return criteriaSpec; }
  public void setCriteriaSpec(String s) { criteriaSpec=s; }
  public boolean keep(Object o) throws Exception { return true;}
  private String criteriaSpec;
  private SearchAgent agent;
}
