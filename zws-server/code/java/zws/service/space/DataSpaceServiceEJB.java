package zws.service.space; /*
DesignState - Design Compression Technology.
@author: athakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
import zws.pkg.DataPackage;
import zws.replication.report.ConflictReport;
import zws.security.Authentication;
import zws.space.DataSpace;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class DataSpaceServiceEJB implements SessionBean, DataSpaceService {

  public void add(DataSpace space) throws RemoteException  {
    try{ DataSpaceSvc.add(space); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
    
  public DataSpace find(String name) throws RemoteException  {
    try{ return DataSpaceSvc.find(name); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public Collection findAll() throws RemoteException  {
    try{ return DataSpaceSvc.findAll(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public Collection getAvailableUpdates(DataSpace sourceSpace, DataSpace targetSpace) throws RemoteException  {
    try{ return DataSpaceSvc.getAvailableUpdates(sourceSpace, targetSpace); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public DataPackage createDesignPackage(DataSpace sourceSpace, String packageName, Collection updates, String tarballName, Authentication id) throws RemoteException {
    try{ return DataSpaceSvc.createDesignPackage(sourceSpace, packageName, updates, tarballName, id); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public DataPackage createPackage(DataSpace sourceSpace, String packageName, Collection updates, String tarballName, Authentication id) throws RemoteException {
    try{ return DataSpaceSvc.createPackage(sourceSpace, packageName, updates, tarballName, id); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public Collection importDesignPackage(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws RemoteException {
    try{ return DataSpaceSvc.importDesignPackage(targetSpace, dPkg, id); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public Collection importPackage(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws RemoteException {
    try{ return DataSpaceSvc.importPackage(targetSpace, dPkg, id); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
  
  public Collection synchronizeFromPackage(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws RemoteException {
    try{ return DataSpaceSvc.synchronizeFromPackage(targetSpace, dPkg, id); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public ConflictReport reportConflicts(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws RemoteException {
    try{ return DataSpaceSvc.reportConflicts(targetSpace, dPkg, id); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
