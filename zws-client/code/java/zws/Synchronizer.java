package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 13, 2004, 11:00 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.Datasource;
import zws.origin.Origin;
import zws.service.synchronization.EJBLocator;
import zws.service.synchronization.SynchronizationService;
import zws.space.DataSpace;
import zws.synchronization.SynchronizationRecord;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;

public class Synchronizer {
  public static Synchronizer getClient() {      
    Synchronizer s = new Synchronizer();
    s.setServerName(Server.getNode());
    return s;
  }
  public static Synchronizer getClient(String serverName) {
    Synchronizer s = new Synchronizer();
    s.setServerName(serverName);
    return s;
  }
  
  public Collection record(Collection origins) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.record(origins);
  }
  public SynchronizationRecord record(Origin a, Origin b) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.record(a, b);
  }
  public Collection record(Origin a, Collection origins) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.record(a, origins);
  }
  public void record(SynchronizationRecord sync) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    service.record(sync);
  }
  public void rename(String domainName, String serverName, String source, String name, String newName) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    service.rename(domainName, serverName, source, name, newName);
  }
  public Collection findMatches(String domain, String serverName, String source, String name) throws Exception, RemoteException {
	  SynchronizationService service = EJBLocator.findService(getServerName());
	  return service.findMatches(domain, serverName, source, name); 
	}
  public Origin lastSynchronization(String domain, String serverName, String source, String name) throws Exception, RemoteException {
	  SynchronizationService service = EJBLocator.findService(getServerName());
	  return service.lastSynchronization(domain, serverName, source, name); 
	}
  
  public void remove(SynchronizationRecord sync) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    service.remove(sync);
  }  
  public void purgeMatches(Origin o) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    service.purgeMatches(o);
  }
  public void purgeByUID(String domainName, String serverName, String datasourceName, String uid) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    service.purgeByUID(domainName, serverName, datasourceName, uid);
  }
  public void purgeByName(String domainName, String serverName, String datasourceName, String name) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    service.purgeByName(domainName, serverName, datasourceName, name);
  }
  public void purgeDatasourceRecords(String domainName, String serverName, String datasourceName) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    service.purgeDatasourceRecords(domainName, serverName, datasourceName);
  }
  public void purgeServerRecords(String domainName, String serverName) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    service.purgeServerRecords(domainName, serverName);
  }
  public boolean isSynchronized(Collection origins) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.isSynchronized(origins);
  }
  public boolean isSynchronized(Origin a, Origin b) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.isSynchronized(a, b);
  }
  public boolean isSynchronizedToDatasource(Origin o, Collection targetDatasources) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.isSynchronizedToDatasource(o, targetDatasources);
  }
  public boolean isIndirectlySynchronizedToDatasource(Origin o, Datasource target) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.isIndirectlySynchronizedToDatasource(o, target);
  }
  public boolean isSynchronizedToDatasource(Origin o, DataSpace target) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.isSynchronizedToDatasource(o, target);
  }
  public boolean isSynchronizedToDatasource(Origin o, Datasource target) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.isSynchronizedToDatasource(o, target);
  }
  public boolean isSynchronizedToDatasource(Origin o, String domainName, String serverName, String datasourceName) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.isSynchronizedToDatasource(o, domainName, serverName, datasourceName);
  }
  public Origin findSynchronization(Origin o, String domainName, String serverName, String datasourceName) throws Exception, RemoteException {  
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.findSynchronization(o, domainName, serverName, datasourceName);
  }
  public Collection findAllSynchronizationRecords(Origin a) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.findAllSynchronizationRecords(a);
  }
  public Collection findAllSynchronizationRecords(String name) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.findAllSynchronizationRecords(name);
  }
  public Collection findNameSynchronization(String domain, String serverName, String datasourceName, String name) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.findNameSynchronization(domain, serverName, datasourceName, name);
  }
  public Collection findDatasourceSynchronization(String domain, String serverName, String datasourceName) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.findDatasourceSynchronization(domain, serverName, datasourceName);
  }
  public Collection findServerSynchronization(String domain, String serverName) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.findServerSynchronization(domain, serverName);
  }
  public Calendar lastNameSynchronization(String name) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.lastNameSynchronization(name);
  }
  public Calendar lastDatasourceSynchronization(String domain, String serverName, String datasourceName) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.lastDatasourceSynchronization(domain, serverName, datasourceName);
  }
  public Calendar lastServerSynchronization(String domain, String serverName) throws Exception, RemoteException {
    SynchronizationService service = EJBLocator.findService(getServerName());
    return service.lastServerSynchronization(domain, serverName);
  }

  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }

  public String serverName = null;
}
