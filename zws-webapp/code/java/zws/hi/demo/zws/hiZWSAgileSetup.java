package zws.hi.demo.zws; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.util.comparator.AlphaNumericComparator;

import java.util.*;
import zws.util.StringPair;
import zws.util.MapUtil;

public class hiZWSAgileSetup extends hiZWSReport {
  public String getSelectedReportName() { return Properties.get("demo-report-harris"); }

  //+fix refresh logic
  public String refreshAgileConfiguration() {
    Collection agileAttributes = MapUtil.getSortedSetFromMap(agileAttributeMap, agileClassName, attComparator);
    refreshAgileConfiguration(agileAttributes);
    return ctrlOK;
  }

  public Collection getAgileAttributes() {
    Collection agileAttributes = MapUtil.getSortedSetFromMap(agileAttributeMap, agileClassName, attComparator);
    if(agileAttributes.size()==0) refreshAgileConfiguration(agileAttributes);

    Map agileMappings = MapUtil.getMapFromMap(hiZWSDemo.agileMappings, agileClassName);
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
    if(null==intralinkAttributes || intralinkAttributes.size()==0) refreshIntralinkConfiguration();
    
    Map ilinkMappings = MapUtil.getMapFromMap(hiZWSDemo.ilinkMappings, agileClassName);
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

  public Collection getAgileCADClasses() {
    if (null!=agileCADClasses) return agileCADClasses;
    agileCADClasses = new Vector();
    try { 
      Collection classes = agile().listClasses();
      if (null==classes) return agileCADClasses;
      if (classes.isEmpty()) return agileCADClasses;
      Iterator i = classes.iterator();
      String classname;
      while (i.hasNext()) {
        classname = (String) i.next();
        if (classname.length() > CAD.length() && CAD.equalsIgnoreCase(classname.substring(0,CAD.length()))) agileCADClasses.add(classname);
        else if (classname.length()> ILINK.length() && ILINK.equalsIgnoreCase(classname.substring(0,ILINK.length()))) agileCADClasses.add(classname);
        else if (classname.length() > INTRALINK.length() && INTRALINK.equalsIgnoreCase(classname.substring(0,INTRALINK.length()))) agileCADClasses.add(classname);
      }
      Iterator j;
      i = agileCADClasses.iterator();
      String att;
      while (i.hasNext()) {
        agileClassName = (String)i.next();
        Collection agileAttributes = MapUtil.getSortedSetFromMap(agileAttributeMap, agileClassName, attComparator);
        refreshAgileConfiguration(agileAttributes);
        j = agileAttributes.iterator();
        while (j.hasNext()) {
          att = (String)j.next();
          addDefaultMapping(att);
        }
      }
    }
    catch (Exception e) { e.printStackTrace(); }
    return agileCADClasses;
  }

  private boolean isIlinkAttribute(String att) {
    if (null==intralinkAttributes) getIntralinkAttributes(); //force refresh
    Iterator i = intralinkAttributes.iterator();
    String s;
    while(i.hasNext()) {
      s = (String)i.next();
      if (s.equalsIgnoreCase(att)) return true;
    }
    return false;
  }
 
  private void addDefaultMapping(String agileAtt) {
    if (agileAtt.length() > ILINK.length() && ILINK.equalsIgnoreCase(agileAtt.substring(0,ILINK.length()))) {
	    agileAttribute = agileAtt;
      ilinkAttribute = lookupDefaultIlinkAttributeMapping(ILINK, agileAttribute);
      if (isIlinkAttribute(ilinkAttribute)) addMapping();
    }
    else if (agileAtt.length() > MCAD.length() && MCAD.equalsIgnoreCase(agileAtt.substring(0,MCAD.length()))) {
      agileAttribute = agileAtt;
	    ilinkAttribute = lookupDefaultIlinkAttributeMapping(MCAD, agileAttribute);
      if (isIlinkAttribute(ilinkAttribute)) addMapping();
	  }
    else if (agileAtt.length() > CAD.length() && CAD.equalsIgnoreCase(agileAtt.substring(0,CAD.length()))) {
      agileAttribute = agileAtt;
	    ilinkAttribute = lookupDefaultIlinkAttributeMapping(CAD, agileAttribute);
      if (isIlinkAttribute(ilinkAttribute)) addMapping();
    }
    
    else if (isIlinkAttribute(agileAtt)) {
      agileAttribute = agileAtt;
	    ilinkAttribute = agileAtt;
      addMapping();
    }
    agileAttribute = null;
    ilinkAttribute = null;
  }   

  private String lookupDefaultIlinkAttributeMapping(String prefix, String agileAttribute) {
    String ilinkAtt=null;
    ilinkAtt = agileAttribute.substring((prefix.length()));
    if ("release".equals(ilinkAtt)) ilinkAtt = "release";
    return ilinkAtt;
  }

  public void refreshAgileConfiguration(Collection agileAttributes) {
    agileAttributes.clear();
    try {
      agile().refreshConfiguration();
      Collection c = agile().refreshAttributes(agileClassName);
      if (null==c) return;
      Iterator i = c.iterator();
      String att;
      while (i.hasNext()) {
        att=i.next().toString();
        if(isReservedAgileAttribute(att)) continue;
        agileAttributes.add(att);
      }
    }
    catch(Exception e) {e.printStackTrace(); }
  }
  
  
  private boolean isReservedAgileAttribute(String att) {
	  if ("rev".equalsIgnoreCase(att)) return true;
	  if ("number".equalsIgnoreCase(att)) return true;
	  if ("document type".equalsIgnoreCase(att)) return true;
	  if ("commodity".equalsIgnoreCase(att)) return true;
	  if ("effectivity date".equalsIgnoreCase(att)) return true;
	  if ("lifecycle phase".equalsIgnoreCase(att)) return true;
	  if ("intralink system attributes".equalsIgnoreCase(att)) return true;
	  if ("object attributes".equalsIgnoreCase(att)) return true;
	  if ("category".equalsIgnoreCase(att)) return true;
	  if ("part category".equalsIgnoreCase(att)) return true;
	  if ("part type".equalsIgnoreCase(att)) return true;
	  if ("rev incorp date".equalsIgnoreCase(att)) return true;
	  if ("rev release date".equalsIgnoreCase(att)) return true;
	  if ("overall compliance".equalsIgnoreCase(att)) return true;
	  if ("compliance calculated date".equalsIgnoreCase(att)) return true;
	  if ("security".equalsIgnoreCase(att)) return true;
	  if ("create user".equalsIgnoreCase(att)) return true;
	  if ("publish internal".equalsIgnoreCase(att)) return true;
	  if ("product line".equalsIgnoreCase(att)) return true;
	  if ("external access".equalsIgnoreCase(att)) return true;
	  if ("approved partners".equalsIgnoreCase(att)) return true;
	  if ("business unit".equalsIgnoreCase(att)) return true;
	  if ("target".equalsIgnoreCase(att)) return true;
	  if ("value".equalsIgnoreCase(att)) return true;
	  if ("division".equalsIgnoreCase(att)) return true;
	  return false;
  }

  public String refreshIntralinkConfiguration() {
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

  public Collection getMappings() { return MapUtil.getCollectionFromMap(hiZWSDemo.mappings, agileClassName); }

  private void addMapping(StringPair mapping) {
    String key;
    Map ilinkMappings = MapUtil.getMapFromMap(hiZWSDemo.ilinkMappings, agileClassName);
    key = mapping.getString0().toLowerCase();
    if (ilinkMappings.containsKey(key)) ilinkMappings.remove(key);
    ilinkMappings.put(mapping.getString0().toLowerCase(), mapping);

    Map agileMappings = MapUtil.getMapFromMap(hiZWSDemo.agileMappings, agileClassName);
    key = mapping.getString1().toLowerCase();
    if (agileMappings.containsKey(key)) agileMappings.remove(key);
    agileMappings.put(mapping.getString1().toLowerCase(), mapping);

    Collection mappings = MapUtil.getCollectionFromMap(hiZWSDemo.mappings, agileClassName);
    Iterator i = mappings.iterator();
    StringPair p, dup = null;
    while(i.hasNext()) {
      p = (StringPair)i.next();
      if (p.getString0().equals(mapping.getString0())) dup = p;
    }
    if (null!=dup) mappings.remove(dup);
    mappings.add(mapping);
  }

  public String deleteMapping() {
    Map ilinkMappings = MapUtil.getMapFromMap(hiZWSDemo.ilinkMappings, agileClassName);
    Map agileMappings = MapUtil.getMapFromMap(hiZWSDemo.agileMappings, agileClassName);
    Collection mappings = MapUtil.getCollectionFromMap(hiZWSDemo.mappings, agileClassName);
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
  public void setAgileClassName(String s) { agileClassName=s; agileAttribute=null;}

  private AgileAccess agile() throws Exception {
    if (null==agileAccess) agileAccess = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
    return agileAccess;
  }
  private String agileClassName="CAD Document";
  
  private String ilinkAttribute=null;
  private String agileAttribute=null;
  private Collection intralinkAttributes = new TreeSet(new AlphaNumericComparator());
  private Map agileAttributeMap = new HashMap();
  private Collection agileCADClasses=null;
  
  private static Comparator attComparator = new AlphaNumericComparator();
  AgileAccess agileAccess = null;
  
  private static String CAD = "CAD ";
  private static String MCAD = "MCAD ";
  private static String ILINK = "ILINK ";  
  private static String INTRALINK= "Intralink ";  
}
