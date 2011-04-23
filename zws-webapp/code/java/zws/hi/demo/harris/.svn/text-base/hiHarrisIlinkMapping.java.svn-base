package zws.hi.demo.harris; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.data.*;
import zws.origin.*;
import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.util.comparator.metadata.PartNumberOrder;

import java.util.*;
import zws.util.StringPair;
import zws.util.MapUtil;

public class hiHarrisIlinkMapping extends hiHarrisReport {
  public String getSelectedReportName() { return Properties.get("demo-report-harris"); }
   
  public String refreshAgileAttributes() {
    Collection agileAttributes = MapUtil.getCollectionFromMap(agileAttributeMap, agileClassName);
    refreshAgileAttributes(agileAttributes);
    return ctrlOK;
  }
  
  public Collection getAgileAttributes() {
    Collection agileAttributes = MapUtil.getCollectionFromMap(agileAttributeMap, agileClassName);
    if(agileAttributes.size()==0) refreshAgileAttributes(agileAttributes);

    Map agileMappings = MapUtil.getMapFromMap(hiHarrisDemo.agileMappings, agileClassName);
    Collection c = new Vector();
    Iterator i = agileAttributes.iterator();
    String att;
    while(i.hasNext()) {
      att = i.next().toString().toLowerCase();
      if (agileMappings.containsKey(att)) continue;
      c.add(att);
    }
    return c;
  }

  public Collection getIntralinkAttributes() {
    if(null==intralinkAttributes || intralinkAttributes.size()==0) refreshIntralinkAttributes();
    
    Map ilinkMappings = MapUtil.getMapFromMap(hiHarrisDemo.ilinkMappings, agileClassName);
    Collection c = new Vector();
    Iterator i = intralinkAttributes.iterator();
    String att;
    while(i.hasNext()) {
      att = i.next().toString().toLowerCase();
      if (ilinkMappings.containsKey(att)) continue;
      c.add(att);
    }
    return c;
  }

  public void refreshAgileAttributes(Collection agileAttributes) {
    agileAttributes.clear();
    try {
      AgileAccess x = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
      Collection c = x.refreshAttributes(agileClassName);
      if (null!=c) agileAttributes.addAll(c);
    }
    catch(Exception e) {e.printStackTrace(); }
  }
  
  public String refreshIntralinkAttributes() {
    intralinkAttributes.clear();
    try {
      IntralinkAccess y = IntralinkAccess.getAccess();
      Collection c = y.getAttributes("DesignState-node-0", Properties.get(Names.DEFAULT_DATASOURCE_NAME), getAuthentication());
      if (null!=c) intralinkAttributes.addAll(c);
    }
    catch(Exception e) {e.printStackTrace(); }
    return ctrlOK;
  }    
  
  public String getIntralinkAttribute() { return ilinkAttribute; }
  public String getAgileAttribute() { return agileAttribute; }
  
  public String chooseIntralinkAttribute() {
      ilinkAttribute = getID();
      addMapping();
      return ctrlOK;
  }
  public String chooseAgileAttribute() {
      agileAttribute = getID();
      addMapping();
      return ctrlOK;
  }
  public String clearIntralinkAttribute() {
      ilinkAttribute = null;
      return ctrlOK;
  }
  public String clearAgileAttribute() {
      agileAttribute = null;
      return ctrlOK;
  }

  public void addMapping() {
    if (null==ilinkAttribute || null==agileAttribute) return;
    StringPair mapping = new StringPair(ilinkAttribute, agileAttribute);
    addMapping(mapping);
    ilinkAttribute=null;
    agileAttribute=null;
  }

  public Collection getMappings() { return MapUtil.getCollectionFromMap(hiHarrisDemo.mappings, agileClassName); }

  private void addMapping(StringPair mapping) {
    Map ilinkMappings = MapUtil.getMapFromMap(hiHarrisDemo.ilinkMappings, agileClassName);
    ilinkMappings.put(mapping.getString0().toLowerCase(), mapping);

    Map agileMappings = MapUtil.getMapFromMap(hiHarrisDemo.agileMappings, agileClassName);
    agileMappings.put(mapping.getString1().toLowerCase(), mapping);

    Collection mappings = MapUtil.getCollectionFromMap(hiHarrisDemo.mappings, agileClassName);
    mappings.add(mapping);
  }

  public String deleteMapping() {
    Map ilinkMappings = MapUtil.getMapFromMap(hiHarrisDemo.ilinkMappings, agileClassName);
    Map agileMappings = MapUtil.getMapFromMap(hiHarrisDemo.agileMappings, agileClassName);
    Collection mappings = MapUtil.getCollectionFromMap(hiHarrisDemo.mappings, agileClassName);
    StringPair mapping = (StringPair) ilinkMappings.get(getID().toLowerCase());
    if (null==mapping) return ctrlOK;
    ilinkMappings.remove(getID().toLowerCase());
    agileMappings.remove(mapping.getString1().toLowerCase());
    Iterator i = mappings.iterator();
    while(i.hasNext()) {
      if (((StringPair)i.next()).getString0().equalsIgnoreCase(getID())) {
        i.remove();
        break;
      }
    }
    return ctrlOK;
  }

  public String getAgileClassName() { return agileClassName; }
  public void setAgileClassName(String s) { agileClassName=s; }

  private String agileClassName="CAD Document";
  
  private String ilinkAttribute=null;
  private String agileAttribute=null;
  private Collection intralinkAttributes = new Vector();
  private Map agileAttributeMap = new HashMap();
}
