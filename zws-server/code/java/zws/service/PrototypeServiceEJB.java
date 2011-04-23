package zws.service; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
import zws.util.Named;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class PrototypeServiceEJB implements SessionBean {

  public void addServices(String namespace, Map services ) throws RemoteException {
    try {
      Iterator i = services.keySet().iterator();
      while (i.hasNext()) PrototypeSvc.add(namespace, (Named)services.get((String)i.next()));
    }
    catch(Throwable e ) { throw new RemoteException("Could Add services.", e); }
  }

  public void addServices(Map namespacedServices) throws RemoteException{
    try {
      Iterator i = namespacedServices.keySet().iterator();
      while (i.hasNext()) {
        String namespace = (String) i.next();
        addServices(namespace, (Map)namespacedServices.get(namespace));
      }
    }
    catch(Throwable e ) { throw new RemoteException("Could Add services.", e); }
  }

  public void load() throws RemoteException {
    try { Configurator.load(); } 
    catch(Throwable e ) { throw new RemoteException("Could not load configurations.", e); }
  }

  public void reload() throws RemoteException {
    try { PrototypeSvc.reload(); }
    catch(Throwable e ) { throw new RemoteException("Could not reload configurations.", e); }
  }
 
  public void unload(String namespace) throws RemoteException {
    try { PrototypeSvc.unload(namespace); }
    catch(Throwable e ) { throw new RemoteException("Could not unload configurations for "+namespace+".", e); }
  }
 
  public void unloadAll() throws RemoteException {
    try { PrototypeSvc.unloadAll(); }
    catch(Throwable e) { throw new RemoteException("Could not unload configurations.", e); }
  }
  
  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
