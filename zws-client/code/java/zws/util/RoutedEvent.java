package zws.util;
import zws.data.Metadata;
import zws.exception.InvalidName;

import java.util.Collection;
import java.util.Map;

/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 1:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//Routable items are identifiable by domain, server, datsource, and name
//Routable items can be tranported (are serializable)
public interface RoutedEvent extends Routed, Namespaced {
  String getDomainName();
  String getServerName();
  String getDatasourceName();
  String getEventType();
  String getEventName();
  String getEventTime();
  String getName();
  long getEventID();
  
  String getRepositoryRoute();
  String getEventRoute();

  public Object get(String attribute);
  public void set(String attribute, Object value);
  public Map getProperties();
  public void addProperties(Map attributes);

  Collection getMetadataList();
  void add(Metadata m);
  String toXML() throws InvalidName;
  
  public static String EVENT="event";
  public static String ITEM_LOCKED="event.design.locked";
  public static String ITEM_UNLOCKED="event.design.unlocked";
  public static String ITEM_MOVED="event.design.moved";
  public static String ITEM_ADDED="event.design.created";
  public static String ITEM_RENAMED="event.design.renamed";
  public static String ITEM_MODIFIED="event.design.versioned";
  public static String ITEM_DELETED="event.design.deleted";
  public static String VERSION_DELETED="event.design.version-deleted";
  public static String ITEMS_CHECKED_IN="event.design.checki-n";
  public static String ITEM_PROMOTED="event.release.promotion";
  public static String ITEM_DEMOTED="event.release.demotioon";

  public static String tagName="event";
}
