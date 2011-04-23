package com.zws.functor.search;

import com.zws.datasource.IntralinkSource;
import com.zws.domo.document.Document;
import com.zws.functor.Functor;
import com.zws.functor.finder.Finder;
import com.zws.functor.finder.IntralinkFinder;
import com.zws.functor.intralink.Search;

import java.util.*;

public class IntralinkAgent extends SearchAgent {

  public void setReleaseLevel(String s) { releaseLevel=s; }
  public String getReleaseLevel() { return releaseLevel; }
  public String getSystemLevelAttributes() { return systemLevelAttributes; }
  public void setSystemLevelAttributes(String s) { systemLevelAttributes=s; }
  public String getUserDefinedAttributes() throws Exception {
    if (null!=userDefinedAttributesFunctor) {
      userDefinedAttributesFunctor.execute();
      userDefinedAttributes = (String) userDefinedAttributesFunctor.getResult();
    }
    return userDefinedAttributes;
  }
  public void setUserDefinedAttributes(String s) { userDefinedAttributes = s; }
  public Functor getUserDefinedAttributesFunctor() { return userDefinedAttributesFunctor; }
  public void setUserDefinedAttributesFunctor(Functor f) { userDefinedAttributesFunctor = f; }

  //do not execute in parallel! same license may be used for each agent
  //todo: add license pool in order to do this in parallel - and inconjunction with multiple intralink search agents
  public void execute() throws Exception {
    Document doc;
    StringTokenizer tok = new StringTokenizer(getCriteria(), " ");
    while (tok.hasMoreTokens()) {
      Collection docs = searchIntralink(tok.nextToken());
      Iterator i = docs.iterator();
      while (i.hasNext()){
        doc = (Document)i.next();
        doc = map(doc);
        doc.setFinder(createFinder(doc));
        add(doc);
      }
    }
  }

  public Collection searchIntralink (String criteria) throws Exception {
    Search seeker = new Search();
    IntralinkSource dSource= (IntralinkSource)getDataSource();
    if (null==criteria || 0==criteria.length()) return new Vector();
    if (!criteria.endsWith("*")) criteria += "*";
    seeker.setMemberID(getMemberID());
    seeker.setCriteria(criteria);
    seeker.setMaxCount(getMaxCount().longValue());
    seeker.setReleaseLevel(releaseLevel);
    seeker.setDataSourceName(dSource.getName());
    seeker.setSystemLevelAttributes(getSystemLevelAttributes());
    seeker.setUserDefinedAttributes(getUserDefinedAttributes());
    seeker.setOutputName(getDataSourceName()+"."+getName());
    seeker.setDeleteOutputFile(getDeleteOutputFile());
    seeker.execute();
    return seeker.getResults();
  }

  public Finder createFinder(Object document){
    IntralinkFinder finder = new IntralinkFinder();
    Document doc = (Document) document;
    finder.setBinary(doc.getName());
    finder.setComponentName(doc.getName());
    finder.setWorkspaceName(doc.getName());
    finder.setDataSource((IntralinkSource)getDataSource());
    return finder;
  }

  public void setDeleteOutputFile(boolean b) { deleteOutputFile=b; }
  public boolean getDeleteOutputFile() { return deleteOutputFile; }

  private String releaseLevel=null;
  private String systemLevelAttributes=null;
  private String userDefinedAttributes=null;
  private Functor userDefinedAttributesFunctor=null;
  private boolean deleteOutputFile = true;
}
