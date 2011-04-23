package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 1:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.s;
import zws.data.Metadata;
import zws.exception.InvalidName;

import java.io.Serializable;
import java.util.*;

public class RoutedEventBase implements RoutedEvent, Serializable {
  public RoutedEventBase() { 
    stampTime(); 
    initializeType(getClass().getName()); 
  }

  public void stampTime() {
    setEventTime(TimeUtil.timeStamp());
  }

  protected void initializeType(String className) {
    String s = className;
    if (s.startsWith("zws.")) s = s.substring(4);
    String name = s.substring(s.lastIndexOf('.')+1);
    s = s.substring(0, s.lastIndexOf('.')+1);
    name = StringUtil.javaName2xmlAttribute(name);
    s += name;
    setEventType(s);
  }

	public Namespace getNamespace() {return new Namespace(EVENT+s.dot+getRepositoryRoute()); }

  public String getDomainName() { return (String)get(DOMAIN_NAME); }
  public void setDomainName(String s) { set(DOMAIN_NAME, s); }
  public String getServerName() { return (String)get(SERVER_NODE); }
  public void setServerName(String s) { set(SERVER_NODE, s); }
	public void setDatasourceType(String s) { set(DATASOURCE_TYPE, s); }
  public String getDatasourceType() { return (String)get(DATASOURCE_TYPE); }
	public void setDatasourceName(String s) { set(DATASOURCE_NAME, s); }
  public String getDatasourceName() { return (String)get(DATASOURCE_NAME); }

  public String getEventType() { return (String)get(EVENT_TYPE); }
  public void setEventType(String s) { set(EVENT_TYPE, s); }
  
  public String getEventName() { return getEventType().substring(getEventType().lastIndexOf(s.dot)+1); }
  
  public String getEventTime() { return (String)get(EVENT_TIME); }
  public void setEventTime(String s) { set(EVENT_TIME, s); }
	public void setEventTime() { setEventTime(TimeUtil.now()); }
	
	public String getRoute() { return getDomainName()+s.delim+getServerName()+s.delim+getName(); }
	public static String getRoute(Routing r) { return r.getDomainName()+s.delim+r.getServerName()+s.delim+r.getName(); }
	
	public String getDataRoute() { return getRepositoryRoute()+s.delim+getName(); }
	public String getEventRoute() { return getRepositoryRoute()+s.delim+getEventName(); }
	public String getRepositoryRoute() { return getDomainName()+s.dot+getServerName()+s.dot+getDatasourceName(); }
	
  
  public String getName() { return (String)get(NAME); }
  public void setName(String s) { set(NAME, s); }
	public void setAuthor(String s) { set(AUTHOR, s); }
	public String getAuthor() { return (String)get(AUTHOR); }

  public Object get(String attribute){ if (pairs.containsKey(attribute)){return pairs.get(attribute);} else {return null;}  }
  public String getString(String attribute){ if (pairs.containsKey(attribute)){return (String)pairs.get(attribute);} else {return null;}  }
  public void set(String attribute, Object value) { pairs.put(attribute, value ); }
  public void addProperties(Map attributes) { if (null==attributes) return; else pairs.putAll(attributes); }
  public Map getProperties(){ return pairs; }

  public String toXML() throws InvalidName {
    String s=null;
    if (null!=metadataList && !metadataList.isEmpty()) {
		  s = StringUtil.openBinaryXMLTag(tagName, getProperties());
		  Iterator i = metadataList.iterator();
		  while(i.hasNext())  s += i.next().toString();
	    s += StringUtil.closeBinaryXMLTag(tagName);
    }
    else {
      s = StringUtil.unaryXMLTag(tagName, getProperties());
    }
    return s;
  }

  public long getEventID() {
    String id = (String)get(EVENT_ID);
    if (null==id) return -1;
    long num;
    try { num = Long.valueOf(id).longValue(); }
    catch (NumberFormatException e) { e.printStackTrace(); return -1; }
    return num;
  }

  public Collection getMetadataList() { return metadataList; }
  public void add(Metadata m) {
    if (null==metadataList) metadataList = new Vector();
    metadataList.add(m); 
  }
  public String toString() { 
    try {return toXML(); }
    catch(Exception e) { e.printStackTrace(); return e.getMessage(); }
  }

  public static String tagName="event";
  
  public static String DOMAIN_NAME="domain";
  public static String SERVER_NODE="server";
  public static String DATASOURCE_TYPE="datasource";
  public static String DATASOURCE_NAME="datasource-name";

  public static String EVENT_FQCNamespace="fqc-namespace";
  public static String EVENT_TYPE="event-type";
  public static String EVENT_TIME="time";

  public static String AUTHOR="author";
  public static String NAME="name";

  //event status
  
  private Map pairs = new HashMap();
  private Collection metadataList = null;
  private static String EVENT = "event";

  public static String STATUS_FIRED = "fired";
  public static String EVENT_ID="event-id";
}
