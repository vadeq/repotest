package zws.service.synchronization; /*
DesignState - Design Compression Technology.
@author: athakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
import zws.datasource.Datasource;
import zws.origin.Origin;
import zws.space.DataSpace;
import zws.synchronization.SynchronizationRecord;

import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class SynchronizationServiceEJB implements SessionBean, SynchronizationService {
  public Collection record(Collection origins) throws RemoteException {
    try{ return SynchronizationSvc.record(origins); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public SynchronizationRecord record(Origin a, Origin b) throws RemoteException {
    try{ return SynchronizationSvc.record(a, b); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public Collection record(Origin a, Collection origins) throws RemoteException {
    try{ return SynchronizationSvc.record(a, origins); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  
  public void record(SynchronizationRecord sync) throws RemoteException {
    try{ SynchronizationSvc.record(sync); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public void rename(String domainName, String serverName, String source, String name, String newName) throws RemoteException {
	  try{ SynchronizationSvc.rename(domainName, serverName, source, name, newName); }
	  catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
	}
  public Collection findMatches(String domain, String serverName, String source, String name) throws RemoteException {
	  try{ return SynchronizationSvc.findMatches(domain, serverName, source, name); }
	  catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
	}
  public Origin lastSynchronization(String domain, String serverName, String source, String name) throws RemoteException {
	  try{ return SynchronizationSvc.lastSynchronization(domain, serverName, source, name); }
	  catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
	}
  
  public void remove(SynchronizationRecord sync) throws RemoteException {
    try{ SynchronizationSvc.remove(sync); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
	public void purgeMatches(Origin origin) throws RemoteException {
    try{ SynchronizationSvc.purgeMatches(origin); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }      
  
  public void purgeByUID(String domainName, String serverName, String datasourceName, String uid) throws RemoteException {
    try{ SynchronizationSvc.purgeByUID(domainName, serverName, datasourceName, uid); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
    
  public void purgeByName(String domainName, String serverName, String datasourceName, String name) throws RemoteException {
    try{ SynchronizationSvc.purgeByName(domainName, serverName,datasourceName, name); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public void purgeDatasourceRecords(String domainName, String serverName, String datasourceName) throws RemoteException {
    try{ SynchronizationSvc.purgeDatasourceRecords(domainName, serverName,datasourceName); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public void purgeServerRecords(String domainName, String serverName) throws RemoteException {
    try{ SynchronizationSvc.purgeServerRecords(domainName, serverName); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public boolean isSynchronized(Collection origins) throws RemoteException {
    try{ return SynchronizationSvc.isSynchronized(origins); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public boolean isSynchronized(Origin a, Origin b) throws RemoteException {
    try{ return SynchronizationSvc.isSynchronized(a,b); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public boolean isSynchronizedToDatasource(Origin o, Collection targetDatasources) throws RemoteException {
    try{ return SynchronizationSvc.isSynchronizedToDatasource(o,targetDatasources); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public boolean isIndirectlySynchronizedToDatasource(Origin o, Datasource target) throws RemoteException {
    try{ return SynchronizationSvc.isIndirectlySynchronizedToDatasource(o, target); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public boolean isSynchronizedToDatasource(Origin o, DataSpace target) throws RemoteException {
    try{ return SynchronizationSvc.isSynchronizedToDatasource(o, target); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public boolean isSynchronizedToDatasource(Origin o, Datasource target) throws RemoteException {
    try{ return SynchronizationSvc.isSynchronizedToDatasource(o, target); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public boolean isSynchronizedToDatasource(Origin o, String domainName, String serverName, String datasourceName) throws RemoteException {
    try{ return SynchronizationSvc.isSynchronizedToDatasource(o, domainName, serverName, datasourceName); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public Origin findSynchronization(Origin o, String domainName, String serverName, String datasourceName) throws RemoteException {  
	  try{ return SynchronizationSvc.findSynchronization(o, domainName, serverName, datasourceName); }
	  catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
	}  
  
  public Collection findAllSynchronizationRecords(Origin a) throws RemoteException {
    try{ return SynchronizationSvc.findAllSynchronizationRecords(a); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }  

  public Collection findAllSynchronizationRecords(String name) throws RemoteException {
    try{ return SynchronizationSvc.findAllSynchronizationRecords(name); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public Collection findNameSynchronization(String domain, String serverName, String datasourceName, String name) throws RemoteException {
    try{ return SynchronizationSvc.findNameSynchronization(domain, serverName,datasourceName, name); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public Collection findDatasourceSynchronization(String domain, String serverName, String datasourceName) throws RemoteException {
    try{ return SynchronizationSvc.findDatasourceSynchronization(domain, serverName,datasourceName); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public Collection findServerSynchronization(String domain, String serverName) throws RemoteException {
    try{ return SynchronizationSvc.findServerSynchronization(domain, serverName); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public Calendar lastNameSynchronization(String name) throws RemoteException {
    try{ return SynchronizationSvc.lastNameSynchronization(name); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public Calendar lastDatasourceSynchronization(String domain, String serverName, String datasourceName) throws RemoteException {
    try{ return SynchronizationSvc.lastDatasourceSynchronization(domain, serverName, datasourceName); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public Calendar lastServerSynchronization(String domain, String serverName) throws RemoteException {
    try{ return SynchronizationSvc.lastServerSynchronization(domain, serverName); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
  
}
