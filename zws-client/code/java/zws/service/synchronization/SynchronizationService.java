package zws.service.synchronization; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.Datasource;
import zws.origin.Origin;
import zws.space.DataSpace;
import zws.synchronization.SynchronizationRecord;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;

public interface SynchronizationService extends Serializable{
  public Collection record(Collection origins) throws RemoteException;
  public SynchronizationRecord record(Origin a, Origin b) throws RemoteException;
  public Collection record(Origin a, Collection origins) throws RemoteException;
  public void record(SynchronizationRecord sync) throws RemoteException;
  public void rename(String domainName, String serverName, String source, String name, String newName) throws RemoteException;
  public Collection findMatches(String domain, String serverName, String source, String name) throws RemoteException;
  public Origin lastSynchronization(String domainName, String serverName, String source, String name) throws RemoteException;
  public void remove(SynchronizationRecord sync) throws RemoteException;
  
	public void purgeMatches(Origin origin) throws RemoteException;
  public void purgeByUID(String domainName, String serverName, String datasourceName, String uid) throws RemoteException;
  public void purgeByName(String domainName, String serverName, String datasourceName, String name) throws RemoteException;
  public void purgeDatasourceRecords(String domainName, String serverName, String datasourceName) throws RemoteException;
  public void purgeServerRecords(String domainName, String serverName) throws RemoteException;
  public boolean isSynchronized(Collection origins) throws RemoteException;
  public boolean isSynchronized(Origin a, Origin b) throws RemoteException;
  public boolean isSynchronizedToDatasource(Origin o, Collection targetDatasources) throws RemoteException;
  public boolean isIndirectlySynchronizedToDatasource(Origin o, Datasource target) throws RemoteException;
  public boolean isSynchronizedToDatasource(Origin o, DataSpace target) throws RemoteException;
  public boolean isSynchronizedToDatasource(Origin o, Datasource target) throws RemoteException;
  public boolean isSynchronizedToDatasource(Origin o, String domainName, String serverName, String datasourceName) throws RemoteException;
  public Origin findSynchronization(Origin o, String domainName, String serverName, String datasourceName) throws RemoteException;  
  public Collection findAllSynchronizationRecords(Origin a) throws RemoteException;
  public Collection findAllSynchronizationRecords(String name) throws RemoteException;
  public Collection findNameSynchronization(String domain, String serverName, String datasourceName, String name) throws RemoteException;
  public Collection findDatasourceSynchronization(String domain, String serverName, String datasourceName) throws RemoteException;
  public Collection findServerSynchronization(String domain, String serverName) throws RemoteException;
  public Calendar lastNameSynchronization(String name) throws RemoteException;
  public Calendar lastDatasourceSynchronization(String domain, String serverName, String datasourceName) throws RemoteException;
  public Calendar lastServerSynchronization(String domain, String serverName) throws RemoteException;
}
