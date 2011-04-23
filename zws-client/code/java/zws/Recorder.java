package zws;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
Created on Feb 16, 2006
@version: 1.0
Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.recorder.*;
import zws.service.recorder.EJBLocator;
import zws.service.recorder.RecorderService;
import zws.util.Namespace;
import zws.util.RoutedEventBase;

import java.util.Date;
import java.util.SortedSet;


public class Recorder {
  private Recorder(String server) { serverName=server; }
  public static Recorder getClient() { return new Recorder(Server.getNode()); }
  //public static Recorder getClient() { return new Recorder(Properties.get(Names.CENTRAL_SERVER)); }
  public static Recorder getClient(String server) { return new Recorder(server); }

  
  public  void recordFiredEvent(RoutedEventBase ev) throws Exception {
    long id = stampStartTime(ev.getNamespace().asString(), ev.getEventName(), ev.STATUS_FIRED, ev.getEventTime());
    ev.set(ev.EVENT_ID, ""+id);
  }
  
  public long stampStartTime(Namespace namespace, String name, String dotedDate) throws Exception {
    return stampStartTime(namespace.asString(), name, dotedDate);
  }
  
  public long stampStartTime(Namespace namespace, String name, String status, String dotedDate) throws Exception {
    return stampStartTime(namespace.asString(), name, status, dotedDate);
  }

  public long stampStartTime(Namespace namespace, String name, String dotedDate, String status, String description) throws Exception {
    return stampStartTime(namespace.asString(), name, dotedDate, description);
  }
  
  public long stampStartTime(String namespace, String name, String dotedDate) throws Exception {
	  RecorderService service = EJBLocator.findService(getServerName());
	  return service.stampStartTime(namespace, name, dotedDate) ;      
  }

  public long stampStartTime(String namespace, String name, String status, String dotedDate) throws Exception {
	  RecorderService service = EJBLocator.findService(getServerName());
	  return service.stampStartTime(namespace, name, status, dotedDate);      
	}


  public long stampStartTime(String namespace, String name, String status, String dotedDate, String description) throws Exception {
    RecorderService service = EJBLocator.findService(getServerName());
    return service.stampStartTime(namespace, name, status, dotedDate, description);      
  }

  public long recordStartTime(Namespace namespace, String name) throws Exception {
    return recordStartTime(namespace.asString(), name);
  }
  
  public long recordStartTime(Namespace namespace, String name, String status, String description) throws Exception {
    return recordStartTime(namespace.asString(), name, status, description);
  }

  public long recordStartTime(Namespace namespace, String name, String status) throws Exception {
    return recordStartTime(namespace.asString(), name, status);
  }

  public long recordStartTime(String namespace, String name) throws Exception {
	  RecorderService service = EJBLocator.findService(getServerName());
	  return service.recordStartTime(namespace, name) ;      
  }

  public long recordStartTime(String namespace, String name, String status) throws Exception {
	  RecorderService service = EJBLocator.findService(getServerName());
	  return service.recordStartTime(namespace, name, status);      
	}

  public long recordStartTime(String namespace, String name, String status, String description) throws Exception {
    RecorderService service = EJBLocator.findService(getServerName());
    return service.recordStartTime(namespace, name, status, description);      
  }

  public long recordChildStartTime(long parentId, String namespace, String name) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    return service.recordChildStartTime(parentId, namespace, name);      
  }

  public long recordChildStartTime(long parentId, String namespace, String name, String status, String description) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    return service.recordChildStartTime(parentId, namespace, name, status, description);      
  }
  
  public void recordEndTime(long id, String status) throws Exception{
	  RecorderService service = EJBLocator.findService(getServerName());
	  service.recordEndTime(id, status) ;      
	}

  public void recordEndTime(long id) throws Exception{
	  RecorderService service = EJBLocator.findService(getServerName());
	  service.recordEndTime(id) ;      
	}

  public void recordStatus(long id, String status) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    service.recordStatus(id, status);      
  }

  public void recordActivity(long id, String domain, String node, String msgType, String msg) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    service.recordActivity(id, domain, node, msgType, msg);      
  }

  public void deleteRecord(long id) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    service.deleteRecord(id);      
  }
  public void purgeRecords(Date cutOffTime) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    service.purgeRecords(cutOffTime);      
  }
  public ExecutionRecord getRecording(long id) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    return service.getRecording(id) ;      
  }

  public ExecutionRecord getLastRecording(String namespace) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    return service.getLastRecording(namespace) ;      
  }
  public ExecutionRecord getLastRecording(String namespace, String name) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    return service.getLastRecording(namespace, name) ;      
  }
  public ExecutionRecord getParentRecording(long child) throws Exception {
    RecorderService service = EJBLocator.findService(getServerName());
    return service.getParentRecording(child) ;      
  }
  public SortedSet getChildRecordings(long id) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    return service.getChildRecordings(id) ;      
  }
  public SortedSet getRecordings(String namespace, String name) throws Exception{
    RecorderService service = EJBLocator.findService(getServerName());
    return service.getRecordings(namespace, name);      
  }
  
  public SortedSet getActivity(long id) throws Exception{
	  RecorderService service = EJBLocator.findService(getServerName());
	  return service.getActivity(id);      
  }
  
  
  public SortedSet getNamespaces() throws Exception{
	  //RecorderService service = EJBLocator.findService(getServerName());
	  //return service.getUniqueNamespace();  
	  /*SortedSet tempset=new TreeSet();
	  tempset.add(new String("queue.publishing.0"));
	  tempset.add(new String("queue.publishing.1"));
	  return tempset;*/
      
      RecorderService service = EJBLocator.findService(getServerName());
  	  return service.getNamespaces();
      
	}
    
 
  // method to get names for namspace 
  // method added by rahul 
  public SortedSet getNames(String namespace) throws Exception {
      RecorderService service = EJBLocator.findService(getServerName());
      return service.getNames(namespace);
  }
  
  
  public String getServerName() { return serverName; }
  private String serverName=null;
  
}
