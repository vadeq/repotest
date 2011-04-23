package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.*;
import zws.application.Names;
import zws.application.s;
import zws.space.DataSpace;
import zws.synchronization.SynchronizationRecord;
import zws.handler.*;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.event.*;
import zws.event.DesignEvent;
import zws.util.RoutedEventBase;

import zws.recorder.ExecutionRecord;
import zws.replication.policy.ReplicationPolicy;
import zws.replication.policy.BroadcastPolicy;
import zws.security.Authentication;
import zws.util.RoutedEvent;
import zws.exception.CanNotMaterialize;


import java.util.*;
import java.io.Serializable;

public abstract class ReplicationHandlerBase extends HandlerBase implements Serializable{
  public void configure(BroadcastPolicy p) { policy=p; }
  public void configure(ReplicationPolicy p) { policy=(BroadcastPolicy)p; }

  public abstract Class getEventClass();

  public boolean wasFiredFromSource(RoutedEvent event) {
    if(!getSourceDomainName().equals(event.getDomainName())) return false;
    if(!getSourceServerNode().equals(event.getServerName())) return false;
    if(!getSourceRepositoryName().equals(event.getDatasourceName())) return false;
    return true;
  }

  protected String getSourceDomainName() {
    return policy.getSourceSpace().getDomainName();
  }
  
  protected String getSourceServerNode() {
    return policy.getSourceSpace().getServerName();
  }

  protected String getSourceRepositoryName() {
    return policy.getSourceSpace().getDatasourceName();
  }

  public boolean binaryNameHasBeenSynchronized(DesignEvent event) throws Exception {
    String n = (String)event.get(FIELD_BINARY_NAME);
    if (null==n || "".equals(n.trim())) n = event.getName();
  	return nameHasBeenSynchronized(event.getDomainName(), event.getServerName(), event.getDatasourceName(), n);
  }

  public boolean binaryNameHasBeenSynchronized(DesignEvent event, Metadata data) throws Exception {
    String n = data.get(FIELD_BINARY_NAME);
    if (null==n || "".equals(n.trim())) n = data.getName();
  	return nameHasBeenSynchronized(event.getDomainName(), event.getServerName(), event.getDatasourceName(), n);
  }

  public boolean nameHasBeenSynchronized(String domain, String server, String repository, String name) throws Exception {
    Collection records = sync.findAllSynchronizationRecords(name);
    if (null==records || records.isEmpty()) return false;
    Map routes = new HashMap();
    SynchronizationRecord r;
    Origin o;
    Iterator i = records.iterator();
    while(i.hasNext()) {
      r = (SynchronizationRecord) i.next();
      o = r.getOriginA();
      routes.put(o.getRepositoryRoute(), o);  
      {} //System.out.println("Stored route: " + o.getRepositoryRoute());
      o = r.getOrigin0();
      routes.put(o.getRepositoryRoute(), o);
      {} //System.out.println("Stored route: " + o.getRepositoryRoute());
    }
    DataSpace space = getPolicy().getSourceSpace();
    {} //System.out.println("Looking for Source:: " + space.getRepositoryRoute());
    if (!routes.containsKey(space.getRepositoryRoute())) return false;
    String checkinRepositoryRoute = domain + server + repository;
    {} //System.out.println("Looking for Event: " + checkinRepositoryRoute);
    if (!routes.containsKey(checkinRepositoryRoute )) return false;
    i = getPolicy().getTargetSpaces().iterator();
    while (i.hasNext()) {
      space = (DataSpace)i.next();
      {} //System.out.println("Looking for Target: " + space.getRepositoryRoute());
      if (!routes.containsKey(space.getRepositoryRoute())) return false;
    }
    {} //System.out.println("Policy " + getPolicy().getName()+ " Matched to " + name);
    return true;
  }

  protected Origin targetOrigin(Origin source, DataSpace target) throws Exception {
    Origin o = sync.findSynchronization(source, target.getDomainName(), target.getServerName(), target.getDatasourceName());
    return o;
  }

  public boolean versionHasBeenSynchronized(VersionedDesignEvent event) throws Exception {
	  Origin o = sync.lastSynchronization(event.getDomainName(), event.getServerName(), event.getDatasourceName(), event.getName());
	  return originHasBeenSynchronized(o);
  }

  public boolean originHasBeenSynchronized(Origin o) throws Exception {
	  if (null==o) return false;
	  Collection c = sync.findAllSynchronizationRecords(o);
	  Map m = new HashMap();
	  Iterator i =c.iterator();
	  Origin x;
	  while (i.hasNext()) {
	    x = (Origin)i.next();
	    m.put(x.getRepositoryRoute(), x);
	  }
	  c = policy.getTargetSpaces();
	  i = c.iterator();
	  int count = 0;
	  DataSpace target;
	  while (i.hasNext()) {
	   x=null;
	   target = (DataSpace)i.next();
	   x = (Origin) m.get(target.getRepositoryRoute());
	   if (null!=x) count++;
	  }
	  if (0==count) return false;
	  if (policy.getTargetSpaces().size()==count) return true;
	  return false; //partial synchronization.
  }

  public void handle(RoutedEventBase event) {
    try {
	    handleForSource(event);
	    handleForTargets(event);
	    finishHandling(event);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void handleForTargets(RoutedEventBase event) throws Exception {
	  Collection targets = policy.getTargetSpaces();
	  Iterator i = targets.iterator();
	  int count = 0;
	  DataSpace target;
	  while (i.hasNext()) {
	   target = (DataSpace)i.next();
	   if (hasBeenHandlededForTarget(event, target)) {
	      {} //System.out.println (event.getEventName() + getEventKeys(event) + " has been handeled for " + target.getDataRoute());
	      continue;
	   }
     {} //System.out.println (event.getEventName() + getEventKeys(event) + " notyet handeled for " + target.getDataRoute());
     try {  handleForTarget(event, target); }
     catch (Exception e) {
       e.printStackTrace();
       String msg = "Event Type: " +getEventType() + Names.NEW_LINE; 
       msg += "Handler Class: " + getClass().getName() + Names.NEW_LINE; 
       msg += "Exception Message: " + e.getMessage() + Names.NEW_LINE;
       alert("Error Handling Event!", msg);
       //log error and continue on other targets
     }
	  }
  }

  public abstract void handleForSource(RoutedEventBase event) throws Exception;
  public abstract void finishHandling(RoutedEventBase event) throws Exception;
  public abstract void handleForTarget(RoutedEventBase event, DataSpace target) throws Exception;
  protected abstract Collection getEventKeys(RoutedEventBase event);
  
  protected Authentication getAuthentication() throws Exception {
    if (id==null) id = new Authentication(DESIGNSTATE_USER, DESIGNSTATE_PASSWORD);
    return id;
  }

  private static String NA = Names.NA;
  private static String delim = Names.ORIGIN_DELIMITER;
  
  protected MetadataBase materializeNamedMetadata(RoutedEventBase ev) throws CanNotMaterialize {
    MetadataBase data = new MetadataBase();
    data.setName(ev.getName());
    Origin o = OriginMaker.materialize(ev.getDomainName(), ev.getServerName(),ev.getDatasourceType(),ev.getDatasourceName(), 0, NA+delim+NA+delim+NA+delim+NA+delim+NA+delim+NA+delim, ev.getName(),"","");
    data.setOrigin(o);
    data.set(data.CREATED_BY, ev.getAuthor());
    data.set(data.CREATED_ON, ev.getEventTime());
    return data;
  }

  protected Collection filterMetadataOriginsMatchingPolicy(RoutedEvent ev) throws Exception {  
		Collection matches  = new Vector();
	  Collection c = ev.getMetadataList();
	  Iterator i = c.iterator();
	  Metadata data;
	  while (i.hasNext()) {
	    data = (Metadata) i.next();
	    if (itemIsBinary(data)) {
	      if (metadataOriginMatchesPolicy(data)) matches.add(data);
	    }
	    else {
	      if (metadataBinaryOriginMatchesPolicy(data)) matches.add(data);
	    }
	  }
	  return matches;
  }

  public boolean itemIsBinary(Metadata data) {
    String s = data.get(FIELD_BINARY_NAME);
    if (null==s || "".equals(s.trim())) return true;
    return false;
  }

  protected Collection filterMetadataNamesMatchingPolicy(RoutedEvent ev) throws Exception {  
		Collection matches  = new Vector();
	  Collection c = ev.getMetadataList();
	  Iterator i = c.iterator();
	  Metadata data;
	  while (i.hasNext()) {
	    data = (Metadata) i.next();
	    if (metadataNameMatchesPolicy(data)) matches.add(data);
	  }
	  return matches;
  }

  protected Collection filterMetadataBinaryNamesMatchingPolicy(RoutedEvent ev) throws Exception {  
		Collection matches  = new Vector();
	  Collection c = ev.getMetadataList();
	  Iterator i = c.iterator();
	  Metadata data;
	  while (i.hasNext()) {
	    data = (Metadata) i.next();
	    if (metadataBinaryNameMatchesPolicy(data)) matches.add(data);
	  }
	  return matches;
  }
  
  protected boolean metadataOriginMatchesPolicy(Metadata data) throws Exception {
    return originHasBeenSynchronized(data.getOrigin());    
  }

  public String getBinaryUniqueID(Metadata data) {
    return data.get(FIELD_BINARY_BRANCH) + delim + data.get(FIELD_BINARY_REV) + delim + data.get(FIELD_BINARY_VER) + delim + data.get(FIELD_BINARY_NAME); 
  }


  protected Origin metadataBinaryOrigin(Metadata data) throws Exception {
    Origin o = data.getOrigin();
    String uid = getBinaryUniqueID(data);
    Origin b = OriginMaker.materialize(o.getDomainName(), o.getServerName(), o.getDatasourceType(), o.getDatasourceName(), 0, uid, "", "");
    return b;
  }

  protected boolean metadataBinaryOriginMatchesPolicy(Metadata data) throws Exception {
    Origin b = metadataBinaryOrigin(data);
    return originHasBeenSynchronized(b);    
  }

  protected boolean metadataBinaryNameMatchesPolicy(Metadata data) throws Exception {
    Origin o = data.getOrigin();
    String n = data.get(FIELD_BINARY_NAME);
    if (null==n || "".equals(n.trim())) n = data.getName();
    return nameHasBeenSynchronized(o.getDomainName(), o.getServerName(), o.getDatasourceName(), n);    
  }

  protected boolean metadataNameMatchesPolicy(Metadata data) throws Exception {
    Origin o = data.getOrigin();
    String n = data.getName();
    return nameHasBeenSynchronized(o.getDomainName(), o.getServerName(), o.getDatasourceName(), n);    
  }

  public BroadcastPolicy getPolicy() { return policy; }
  protected static String dot = s.dot;
  private static String HANDLER="handler";

  protected String handlingNamespace(RoutedEventBase event) {
    StringBuffer namespace=new StringBuffer();
    namespace.append(HANDLER).append(dot).append(event.getDomainName()).append(dot).append(event.getServerName()).append(dot).append(event.getDatasourceName());
    return namespace.toString();    
  }
  
  protected String handlingNamespace(DataSpace target) {
    StringBuffer namespace=new StringBuffer();
    namespace.append(HANDLER).append(dot).append(target.getDomainName()).append(dot).append(target.getServerName()).append(dot).append(target.getDatasourceName());
    return namespace.toString();    
  }
  
  protected String handlingName(RoutedEventBase event) {
    StringBuffer name=new StringBuffer();
    name.append(event.getEventName());
    Iterator i = getEventKeys(event).iterator();
    String key;
    while (i.hasNext()) {
      key = (String) i.next();
      name.append(dot).append(key);
    }
    return name.toString();    
  }

  public void recordTargetHandling(RoutedEventBase event, DataSpace target) throws Exception {
    String namespace = handlingNamespace(target);
    String name = handlingName(event);
    recordHandling(event.getEventID(), namespace, name, STATUS_HANDLING);
  }

  public long recordHandling(long id, String namespace, String name, String status) throws Exception {
    long x = recorder().recordChildStartTime(id, namespace, name);
    recorder().recordStatus(id, status);
    return x;
  }  

  protected boolean hasBeenHandlededForTarget(RoutedEventBase event, DataSpace target) {
    ExecutionRecord r = findLastHandling(event, target);
    if (null==r) return false;
    try {  
      ExecutionRecord p = recorder().getParentRecording(r.getID());
      if (p==null) return false;
      return (event.getEventID()==p.getID());
    }
    catch (Exception e) { 
      e.printStackTrace();
      return false; 
    }
  }

  public ExecutionRecord findLastHandling(RoutedEventBase event, DataSpace target) {
    try {
      String namespace = handlingNamespace(target);
      String name = handlingName(event);
      ExecutionRecord r = findLastHandling(namespace, name);
      return r;
    }
    catch (Exception e) {  {} //System.out.println(e.getMessage()); 
      return null; 
    }
  }
  
  public ExecutionRecord findLastHandling(String namespace, String name) throws Exception {
    Recorder r = Recorder.getClient();
    ExecutionRecord ex =  r.getLastRecording(namespace, name);
    return ex;
  }
    
  public void recordStatus(long id, String status) throws Exception {
    Recorder r = Recorder.getClient();
    r.recordStatus(id, status);
  }

  protected Recorder recorder() { return rec; }
  protected Synchronizer syncronizer() { return sync; }
  protected Replicator replicator() { return Replicator.getClient(getSourceServerNode()); }
  
  private Authentication id=null;
  private BroadcastPolicy policy=null;
  //Synchronizer sync = Synchronizer.getClient(zws.application.Properties.get(Names.CENTRAL_SERVER));
  Synchronizer sync = Synchronizer.getClient();
  //Recorder rec = Recorder.getClient(zws.application.Properties.get(Names.CENTRAL_SERVER));
  Recorder rec = Recorder.getClient();

  public static String STATUS_HANDLING = "handling-event";
  public static String STATUS_VERIFIED = "verified";
  
  public static String FIELD_BINARY_NAME="binary";
  public static String FIELD_BINARY_BRANCH="binary-branch";
  public static String FIELD_BINARY_REV="binary-rev";
  public static String FIELD_BINARY_VER="binary-ver";  
  protected static String DESIGNSTATE_USER = "designstate";
  protected static String DESIGNSTATE_PASSWORD = "zero0";

}