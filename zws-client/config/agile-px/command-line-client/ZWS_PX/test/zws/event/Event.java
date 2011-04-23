package zws.event; /*
DesignState - Design Compression Technology.
@author: Guy Robinson
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.*;
import java.io.Serializable;

public class Event implements Serializable {
  public Event() { initializeType(getClass().getName()); }

  protected void initializeType(String className) {
    String s = className;
    if (s.startsWith("zws.")) s = s.substring(4);
    String name = s.substring(s.lastIndexOf('.')+1);
    s = s.substring(0, s.lastIndexOf('.')+1);
    name = StringUtil.javaName2xmlAttribute(name);
    s += name;
    setEventType(s);
  }
  
  public String getEventType() { return (String)get(EVENT_TYPE); }
  public void setEventType(String s) { set(EVENT_TYPE, s); }
  
  public String getName() { return (String)get(NAME); }
  public void setName(String s) { set(NAME, s); }
  public String getTime() { return (String)get(TIME); }
  public void setTime(String s) { set(TIME, s); }
  public String getDomainName() { return (String)get(DOMAIN_NAME); }
  public void setDomainName(String s) { set(DOMAIN_NAME, s); }
  public String getServerNode() { return (String)get(SERVER_NODE); }
  public void setServerNode(String s) { set(SERVER_NODE, s); }

  public Object get(String attribute){ if (pairs.containsKey(attribute)){return pairs.get(attribute);} else {return null;}  }
  public void set(String attribute, Object value) { pairs.put(attribute, value ); }
  public void setAttributes(Map attributes) { if (null==attributes) return; else pairs.putAll(attributes); }
  public Map getProperties(){ return pairs; }
  public Collection getMetadataList() { return metadataList; }
  public void add(Metadata m) {
    if (null==metadataList) metadataList = new Vector();
    metadataList.add(m); 
  }

  public String toString() { 
    try {return toXML(); }
    catch(Exception e) { e.printStackTrace(); return e.getMessage(); }
  }
  
  public String toXML() throws InvalidName {
    String s=null;
    if (null!=metadataList && !metadataList.isEmpty()) {
		  s = StringUtil.openBinaryXMLTag(tagName, pairs);
		  Iterator i = metadataList.iterator();
		  while(i.hasNext())  s += i.next().toString();
	    s += StringUtil.closeBinaryXMLTag(tagName);
    }
    else s = StringUtil.unaryXMLTag(tagName, pairs);
    return s;
  }

  private Map pairs = new HashMap();

  private Collection metadataList = null;
  
  public static String EVENT="event";
  public static String ITEM_LOCKED="event.repository.item-locked";
  public static String ITEM_UNLOCKED="event.repository.item-unlocked";
  public static String ITEM_MOVED="event.repository.item-moved";
  public static String ITEM_ADDED="event.repository.item-created";
  public static String ITEM_RENAMED="event.repository.item-renamed";
  public static String ITEM_MODIFIED="event.repository.item-versioned";
  public static String ITEM_DELETED="event.repository.item-deleted";
  public static String VERSION_DELETED="event.repository.version-deleted";
  public static String ITEM_PROMOTED="event.repository.items-promoted";
  public static String ITEM_DEMOTED="event.repository.items-demoted";
  public static String ITEMS_CHECKED_IN="event.repository.items-checked-in";
  
  public static String tagName="event";
  public static String EVENT_TYPE="event-type";
  public static String NAME="name";
  public static String TIME="time";
  public static String DOMAIN_NAME="domain";
  public static String SERVER_NODE="server-node";
}
