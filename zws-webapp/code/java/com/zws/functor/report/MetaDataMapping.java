package com.zws.functor.report;

import com.zws.util.KeyValue;

import java.util.HashMap;
import java.util.Map;

public class MetaDataMapping {

  public void addHiddenAttribute(KeyValue pair) {
    allMappings.put(pair.getKey(), pair.getValue());
  }

  public void add(KeyValue pair) {
    metaDataMappings.put(pair.getKey(), pair.getValue());
    allMappings.put(pair.getKey(), pair.getValue());
  }

  public Map getMetaDataMappings() { return metaDataMappings; }
  public Map getAllMappings() { return allMappings; }

  public String getFromAllMappings(String name){
    String value = (String)allMappings.get(name);
    if (null==value) value="";
    return value;
  }

  public String getFromMetaDataMappings(String name){
    String value = (String)metaDataMappings.get(name);
    if (null==value) value="";
    return value;
  }

  public String getDataReport() { return dataReport; }
  public void setDataReport(String s) { dataReport=s; }
  public String getSearchAgent() { return searchAgent; }
  public void setSearchAgent(String s) { searchAgent=s; }

  private Map metaDataMappings = new HashMap();
  private Map allMappings = new HashMap();
  private String dataReport=null, searchAgent=null;
}
