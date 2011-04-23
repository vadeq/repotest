package com.zws.service.config;

import com.zws.functor.report.DataReport;
import com.zws.functor.report.MetaDataMapping;
import com.zws.functor.security.Guard;

import java.util.*;

public class DataReportService {
  private static String RESOURCE_TYPE = "Data Report";
  private static String MAPPING_RESOURCE_TYPE = "Report-Agent Mapping";
  private static DataReportService service = null;

//todo: make constructor private when xml processor is finished
//  private DataReportService(){}
  public static DataReportService getInstance() {
    if (null==service) service = new DataReportService();
    return service;
  }

  public static Collection getDataReportNames(){ return dataReportPrototypes.keySet(); }
  public static Collection getDisplayReportNames(){
    Collection reports = new Vector();
    reports.addAll(displayReportNames);
    Iterator i = secureReports.values().iterator();
    Guard guard=null;
    while (i.hasNext() ) {
      guard = (Guard) i.next();
      try { guard.execute(); }
      catch (Exception e) { e.printStackTrace(); }
      if (null!= guard.getResult()) reports.add(((DataReport)guard.getResult()).getDisplayKey());
    }
    return reports;
  }

  public static DataReport find(String name) throws ConfigurationNotFound {
    DataReport report = (DataReport)dataReportPrototypes.get(name);
    if (null==report) throw new ConfigurationNotFound(RESOURCE_TYPE, name);
    return (DataReport) report.copy();
  }

  public static DataReport findDisplayReport(String name) throws ConfigurationNotFound {
    DataReport report = (DataReport)displayReportPrototypes.get(name);
    if (null==report) throw new ConfigurationNotFound(RESOURCE_TYPE, name);
    return (DataReport) report.copy();
  }

  public static Collection findAll() {
    Collection c = new Vector();
    Iterator i = dataReportPrototypes.values().iterator();
    while (i.hasNext()) c.add(((DataReport)i.next()).copy());
    return c;
  }

/*
  public static void add(DataReport report) throws DuplicateConfigurationName{
    if (null!=dataReportPrototypes.get(report.getName()))
      throw new DuplicateConfigurationName(RESOURCE_TYPE, report.getName());
    dataReportPrototypes.put(report.getName(), report);
    if (null!=report.getDisplayKey() && !"".equals(report.getDisplayKey())){
      if (null!=displayReportPrototypes.get(report.getDisplayKey()))
        throw new DuplicateConfigurationName(RESOURCE_TYPE, report.getName()+" [displayKey="+report.getDisplayKey()+"]");
      displayReportNames.add(report.getDisplayKey());
      displayReportPrototypes.put(report.getDisplayKey(), report);
    }
  }
*/
  public static void add(DataReport report) throws DuplicateConfigurationName{
    if (null!=dataReportPrototypes.get(report.getName()))
      throw new DuplicateConfigurationName(RESOURCE_TYPE, report.getName());
    dataReportPrototypes.put(report.getName(), report);
  }
  public static void add(Guard guard) throws DuplicateConfigurationName, Exception { //can only guard datareports with display keys
    DataReport report = (DataReport)guard.getBinding();
    if (null==report.getDisplayKey() && !"".equals(report.getDisplayKey())) throw new Exception("Guarded reports must have display keys");
    secureReports.put(report.getDisplayKey(), guard);
    if (null!=dataReportPrototypes.get(report.getName()))
      throw new DuplicateConfigurationName(RESOURCE_TYPE, report.getName());
    dataReportPrototypes.put(report.getName(), report);
    if (null!=displayReportPrototypes.get(report.getDisplayKey()))
      throw new DuplicateConfigurationName(RESOURCE_TYPE, report.getName()+" [displayKey="+report.getDisplayKey()+"]");
    displayReportPrototypes.put(report.getDisplayKey(), report);
  }

  public static DataReport remove(DataReport report) { return remove(report.getName()); }

  public static DataReport remove(String reportName) {
    DataReport report = (DataReport) dataReportPrototypes.remove(reportName);
    displayReportPrototypes.remove(report);
    mappings.remove(reportName);
    Iterator i = displayReportNames.iterator();
    while (i.hasNext()) {
      if (i.next().toString().equals(reportName)) i.remove();
    }
    return report;
  }

  public static void update(DataReport report) throws Exception {
    remove(report);
    add(report);
  }

  public static MetaDataMapping findMetaDataMapping(String report, String agent) throws ConfigurationNotFound {
    Map reportMappings = (Map) mappings.get(report);
    if (null==reportMappings) throw new ConfigurationNotFound(MAPPING_RESOURCE_TYPE, "["+report+", "+agent+"]");
    MetaDataMapping mapping = (MetaDataMapping)reportMappings.get(agent);
    if (null==mapping) throw new ConfigurationNotFound(MAPPING_RESOURCE_TYPE, "["+report+", "+agent+"]");
    return mapping;
  }

  public static Map findAllMetaDataMappings(String report) throws ConfigurationNotFound  {
    Map reportMappings = (Map) mappings.get(report);
    if (null==reportMappings) throw new ConfigurationNotFound(MAPPING_RESOURCE_TYPE, report);
    return reportMappings;
  }

  public static void add(MetaDataMapping metaDataMap) throws DuplicateConfigurationName, ConfigurationNotFound {
    String report = metaDataMap.getDataReport();
    String agent = metaDataMap.getSearchAgent();
    // parser should validate that these attributes exist
    if (null==report) throw new NullPointerException("'report' not specified for " + MAPPING_RESOURCE_TYPE);
    if (null==agent) throw new NullPointerException("'agent' not specified for " + MAPPING_RESOURCE_TYPE);
    DataReport r = find(report);
    if (null==r) throw new ConfigurationNotFound("specified report [" + report + "] not found for " + MAPPING_RESOURCE_TYPE);
    String fieldName=null;
    Iterator i = metaDataMap.getMetaDataMappings().keySet().iterator();
    while (i.hasNext()) {
      fieldName = i.next().toString();
      if (!r.getDisplayFieldMap().containsKey(fieldName))
        throw new ConfigurationNotFound("metadata field ("+fieldName+") specified in "+MAPPING_RESOURCE_TYPE+ " ["+report+", "+agent+"] not declared as a display field in the report");
    }
    Map reportMappings = (Map)mappings.get(report);
    if (null==reportMappings) {
      reportMappings = new HashMap();
      mappings.put(report, reportMappings);
    }
    if (null==reportMappings.get(agent)) reportMappings.put(agent, metaDataMap);
    else throw new DuplicateConfigurationName(MAPPING_RESOURCE_TYPE, "["+report+", "+agent+"]");
  }

  public static MetaDataMapping removeMapping(String report, String agent) {
    Map reportMappings = (Map)mappings.get(report);
    if (null==reportMappings) return null;
    return (MetaDataMapping)reportMappings.remove(agent);
  }

  public static void updateMapping(MetaDataMapping metaDataMap) {
    removeMapping(metaDataMap.getDataReport(), metaDataMap.getSearchAgent());
    try { add(metaDataMap); }
    catch(Exception ignore) { ignore.printStackTrace(); }
  }

  public static void unload() {
    displayReportNames.clear();
    displayReportPrototypes.clear();
    dataReportPrototypes.clear();
    mappings.clear();
  }

  private static Collection displayReportNames = new Vector();
  private static Map displayReportPrototypes = new HashMap();
  private static Map dataReportPrototypes = new HashMap();
  private static Map secureReports = new HashMap();
  private static Map mappings = new HashMap();
}
