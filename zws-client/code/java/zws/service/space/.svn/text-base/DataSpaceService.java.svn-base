package zws.service.space; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.pkg.DataPackage;
import zws.replication.report.ConflictReport;
import zws.security.Authentication;
import zws.space.DataSpace;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;

public interface DataSpaceService extends Serializable {
  public void add(DataSpace space) throws RemoteException;
  public DataSpace find(String name) throws RemoteException;
  public Collection findAll() throws RemoteException;
  public DataPackage createDesignPackage(DataSpace sourceSpace, String packageName, Collection updates, String tarballName, Authentication id) throws RemoteException;
  public DataPackage createPackage(DataSpace sourceSpace, String packageName, Collection updates, String tarballName, Authentication id) throws RemoteException;
  public Collection importDesignPackage(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws RemoteException;
  public Collection importPackage(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws RemoteException;
  public Collection synchronizeFromPackage(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws RemoteException;
  public ConflictReport reportConflicts(DataSpace targetSpace, DataPackage dPkg, Authentication id) throws RemoteException;
  public Collection getAvailableUpdates(DataSpace sourceSpace, DataSpace targetSpace) throws RemoteException;  
}
