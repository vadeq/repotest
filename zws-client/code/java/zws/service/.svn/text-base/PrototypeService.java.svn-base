package zws.service; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Map;

public interface PrototypeService extends Serializable {
  public void addServices(String namespace, Map m ) throws RemoteException;
  public void addServices(Map namespacedName) throws RemoteException;
  
  public void load() throws RemoteException;
  public void reload() throws RemoteException;
  public void unload(String namespace) throws RemoteException;
  public void unloadAll() throws RemoteException;
}
