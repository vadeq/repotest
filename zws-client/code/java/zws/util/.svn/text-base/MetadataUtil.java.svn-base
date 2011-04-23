package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 21, 2004, 7:43 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.util.comparator.metadata.ChronologicalOrder;

import java.util.*;

public class MetadataUtil {

  public static SortedMap organizeChronologically(Collection components) {
    SortedMap map = new TreeMap();
    if (null==components || 0==components.size()) return map;
    Metadata m;
    Long timeOfCreation;
    Iterator i = components.iterator();
    while(i.hasNext()) {
      m = (Metadata)i.next();
      timeOfCreation = new Long(m.getOrigin().getTimeOfCreationInMillis());
      MapUtil.getListFromMap(map,timeOfCreation).add(m);
    }
    return map;
  }

  //Sorts a flat list of components.
  //"assemblies" have subcomponent references (1-level deep) to components in the list
  //handles missing sub components
  //does not restructure parent assemblies to 
  //resulting list is sorted by time, and by dependency (with independent objects comming first)
  public static Collection sortByDependency(Collection components) {
    if (null==components || 0==components.size()) return components;
    ChronologicalOrder c = new ChronologicalOrder();
    c.setAscendingOrder(true);
    TreeSet timed = new TreeSet(c);
    timed.addAll(components);
    Collection orderedComponents = new Vector(timed);
    Map index = new HashMap();
    Map parents = new HashMap();
    Iterator i = orderedComponents.iterator();
    Metadata data;
    while (i.hasNext()) { //indexes all components and those that are parents
      data = (Metadata)i.next();
      index.put(data.getOrigin().getUniqueID().toLowerCase(), data);
      if (data.hasSubComponents()) parents.put(data.getOrigin().getUniqueID().toLowerCase(), data);
    }
    Stack parentStack = new Stack();
    Iterator sub;
    i = parents.values().iterator();
    while (i.hasNext()) {
      data = (Metadata)i.next();
      sortDependencies(data, orderedComponents, index, parentStack);
    }
    //orderedComponents.addAll(parentStack);
    timed.clear();
    timed.addAll(orderedComponents);
    timed.addAll(parentStack);
    return timed;
  }

  private static void sortDependencies(Metadata parent, Collection components, Map index, Stack parentStack) {
    if (null==parent) return;
    if (parentStack.contains(parent)) return; //already processed
    components.remove(parent);
    Metadata sub;
    Iterator i = parent.getSubComponents().iterator();
    while (i.hasNext()) { //push subcomponents that are also assemblies
      sub = (Metadata)i.next();
      sub = (Metadata)index.get(sub.getOrigin().getUniqueID());
      if (null!=sub && sub.hasSubComponents()) sortDependencies(sub, components, index, parentStack);
    }
    parentStack.push(parent);
  }

  //naming grammar="[basename] | '_' | meta[Rev] | '_' | meta[Ver] | '.pdf'"
  //[type] is also a valid identifier for the naming convention grammar
  public static String parseNamingGrammar(Metadata data, String grammar) throws Exception {
    if (null==grammar) return "";
    StringTokenizer tokens = new StringTokenizer(grammar, Names.DELIMITER);
    if (1>tokens.countTokens()) return extractIdentifier(data, grammar);
    if (1==tokens.countTokens()) return extractIdentifier(data, tokens.nextToken().trim());
    String s="";
    String id = extractIdentifier(data, tokens.nextToken().trim());
    if (null==id) id="";
    if (tokens.hasMoreTokens()) s += id;
    while (tokens.hasMoreTokens()) {
      id = extractIdentifier(data, tokens.nextToken().trim());
      if (null==id) id="";
      s += id;
    }
    return s;
  }

  private static String extractIdentifier(Metadata data, String identifier) throws Exception {
  String s=null;
    if (identifier.startsWith("'") && identifier.endsWith("'")){
      s = identifier.substring(1,identifier.length()-1);
      if (null==s) throw new Exception ("Invalid identifer: " + identifier);
      return s.trim();
    }
    else if ((identifier.startsWith("[") && identifier.endsWith("]")) || (identifier.startsWith("{") && identifier.endsWith("}"))){
      s = identifier.substring(1,identifier.length()-1);
      if (null==s) throw new Exception ("Invalid identifer: " + identifier);
      return analyzeIdentifier(data, s.trim()); 
    }
    else if (identifier.startsWith("meta") && identifier.endsWith("]") && 3 < identifier.indexOf("[")) {
      s = identifier.substring(identifier.indexOf("[")+1,identifier.length()-1);
      if (null==s) throw new Exception ("Invalid identifer: " + identifier);
      return data.get(s.trim());
    }
    else if (identifier.startsWith("meta") && identifier.endsWith("}") && 3 < identifier.indexOf("{")) {
      s = identifier.substring(identifier.indexOf("{")+1,identifier.length()-1);
      if (null==s) throw new Exception ("Invalid identifer: " + identifier);
      return data.get(s.trim());
    }
    else return identifier;
  }

  private static String analyzeIdentifier(Metadata data, String identifier) throws Exception {
    if (null==identifier || "".equals(identifier.trim())) throw new Exception ("Invalid identifer: " + identifier);
    String s = identifier.toLowerCase().trim();
    if ("basename".equals(s)) return FileNameUtil.getBaseName(data.getName());
    if ("type".equals(s)) return FileNameUtil.getFileNameExtension(data.getName());
    else  throw new Exception ("Unknown identifer: " + identifier);
  }
}
