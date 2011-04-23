package com.zws.functor.processor.action.tejas;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 16, 2003, 2:34 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import com.zws.datasource.IntralinkSource;
import com.zws.datasource.SQLServerSource;
import com.zws.functor.Functor;
import com.zws.functor.intralink.Search;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;
import com.zws.xml.TejasSearchCSVHandler;

public class TejasSearch extends Action {
  //do not execute in parallel! same license may be used for each agent
  //todo: add license pool in order to do this in parallel - and inconjunction with multiple intralink search agents
  public void execute() throws Exception {
    getActionLog().log("Searching for " + getCriteria() + " Release level=" + getReleaseLevel());
    {} //System.out.println("Executing Search");
    String crit = getCriteria();
    Search seeker = new Search();
    IntralinkSource dSource= getDatasource();
    if (null==crit || 0==crit.length()) return;
    if (!crit.endsWith("*")) crit += "*";
    seeker.setMemberID("Tejas");
    seeker.setCriteria(crit);
    seeker.setMaxCount(getMaxCount());
    seeker.setReleaseLevel(releaseLevel);
    seeker.setDataSourceName(dSource.getName());
    seeker.setSystemLevelAttributes(getSystemLevelAttributes());
    seeker.setUserDefinedAttributes(getUserDefinedAttributes());
    seeker.setOutputName(getDatasourceName());
//    seeker.setDeleteOutputFile(getDeleteOutputFile());
      seeker.setDeleteOutputFile(false);
    TejasSearchCSVHandler handler = new TejasSearchCSVHandler();
    handler.setSystemAttributesFile(getSystemAttributesFile());
    handler.setUserDefinedAttributesFile(getUserDefinedAttributesFile());
    handler.setSystemAttributes(getSystemAttributesOut());
    seeker.setContentHandler(handler);
//    seeker.setErrorHandler(handler);
    seeker.execute();
//    return seeker.getResults();
    getTargetDatasource().bulkCopy(getSystemAttributesFile(), getUserDefinedAttributesFile());
    getActionLog().log("executing bulk copy");
  }

  public String getCriteria() { return criteria; }
  public void setCriteria(String s) { criteria=s; }
  public long getMaxCount() { return maxCount; }
  public void setMaxCount(long l) { maxCount=l; }
  public void setReleaseLevel(String s) { releaseLevel=s; }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public String getTargetDatasourceName() { return targetDatasourceName; }
  public void setTargetDatasourceName(String s) { targetDatasourceName=s; }
  public IntralinkSource getDatasource() throws Exception { return (IntralinkSource)DataSourceService.find(datasourceName); }
  public SQLServerSource getTargetDatasource() throws Exception { return (SQLServerSource)DataSourceService.find(targetDatasourceName); }
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

  public void setDeleteOutputFile(boolean b) { deleteOutputFile=b; }
  public boolean getDeleteOutputFile() { return deleteOutputFile; }
 
  public String getSystemAttributesOut() { return systemAttributesOut; }
  public void setSystemAttributesOut(String s) { systemAttributesOut = s; }
  public String getSystemAttributesFile() { return systemAttributesFile; }
  public void setSystemAttributesFile(String s) { systemAttributesFile = s; }
  public String getUserDefinedAttributesFile() { return userDefinedAttributesFile; }
  public void setUserDefinedAttributesFile(String s) { userDefinedAttributesFile = s; }
  
  private String criteria = null;
  private long maxCount=0;
  private String datasourceName = null;
  private String targetDatasourceName = null;
  private String releaseLevel=null;
  private String systemLevelAttributes=null;
  private String userDefinedAttributes=null;
  private String systemAttributesOut=null;
  private Functor userDefinedAttributesFunctor=null;
  private boolean deleteOutputFile = true;
  private String systemAttributesFile; 
  private String userDefinedAttributesFile;
}
