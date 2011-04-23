package zws.handler.i2a; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.*;
import zws.handler.*;
import zws.origin.Origin;
import zws.data.Metadata;
import zws.data.eco.ECO;
import zws.util.RoutedEventBase;
import zws.security.Authentication;
import zws.exception.CanNotMaterialize;


import java.util.*;
import java.io.Serializable;

public abstract class BaseI2AHandler extends HandlerBase implements Serializable{
 //public void configure(I2APolicy p) { policy=p; }
  
  public abstract Class getEventClass();
  
  public abstract boolean handles(RoutedEventBase event);

  public boolean hasBeenSynchronized(RoutedEventBase event, String name) throws Exception {
	  Origin o = sync.lastSynchronization(event.getDomainName(), event.getServerName(), event.getDatasourceName(), name);
	  return hasBeenSynchronized(o);
  }
  public void handle(RoutedEventBase event) {
    try {
      loadAgileClient(event);
      preHandleEvent(event);
      handleEvent(event);
      postHandleEvent(event);
      reset();
    }
    catch(Exception e) {
      e.printStackTrace(); 
    }
  }

  protected void loadAgileClient(RoutedEventBase ev) throws Exception {
   setAgileClient(AgileAccess.getAccess(ev.getServerName(), ev.getDatasourceName(), getAuthentication()));
  }
  
  protected void setAgileClient(AgileAccess client) { agileClient = client; }

  public abstract void preHandleEvent(RoutedEventBase event) throws Exception;
  public abstract void handleEvent(RoutedEventBase event) throws Exception;
  public abstract void postHandleEvent(RoutedEventBase event) throws Exception;
  public abstract void reset() throws Exception;

  protected Authentication getAuthentication() throws Exception {
    if (id==null) id = new Authentication("designstate", "zero0");
    return id;
  }

  protected Metadata retrieveMetadata(RoutedEventBase ev) {
    Metadata data=null;
    try { data = agile().find(ev.getName()); }
    catch (Exception e) { e.printStackTrace(); }
    return data;
  }

  protected ECO retrieveECO(RoutedEventBase ev) throws CanNotMaterialize {
	  ECO eco=null;
	  try { eco = agile().findECO(ev.getName()); }
	  catch (Exception e) { e.printStackTrace(); }
	  return eco;
  }

  public boolean hasBeenSynchronized(ECO eco) throws Exception {
	  if (null==eco) return false;	  
	  Collection c = eco.getAffectedItems();
	  if (null!=c) return false;
	  if (c.isEmpty()) return false;
	  Iterator i = c.iterator();
	  Metadata data;
	  while (i.hasNext()) {
	    data = (Metadata) i.next();
	    if (hasBeenSynchronized(data.getOrigin())) return true;
	  }
	  return false;
	}

  public boolean hasBeenSynchronized(Origin o) throws Exception {
	  if (null==o) return false;
	  Collection c = sync.findAllSynchronizationRecords(o);
	  if (null!=c) return false;
	  if (c.isEmpty()) return false;
	  return true;
  }

  protected AgileAccess agile() {return agileClient; }

  protected Recorder recorder() { return rec; }
  protected Synchronizer syncronizer() { return sync; }

  private Authentication id=null;
  //private I2APolicy policy=null;
  AgileAccess agileClient = null;
  //Synchronizer sync = Synchronizer.getClient(zws.application.Properties.get(Names.CENTRAL_SERVER));
  Synchronizer sync = Synchronizer.getClient();
  //Recorder rec = Recorder.getClient(zws.application.Properties.get(Names.CENTRAL_SERVER));
  Recorder rec = Recorder.getClient();
}