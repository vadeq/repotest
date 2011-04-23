package zws.service.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 7, 2004, 3:22 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Configurator;
import zws.topology.Node;
import zws.topology.Topology;

import java.rmi.RemoteException;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class TopologyServiceEJB implements SessionBean, TopologyService  { 
  public Node ping() throws RemoteException {
    try { return TopologySvc.ping(); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Topology getTopology() throws RemoteException {
    try { return TopologySvc.getTopology(); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void register(Node n) throws RemoteException {
    try { TopologySvc.register(n); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void unregister(String ip) throws RemoteException {
    try { TopologySvc.unregister(ip); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}


/*

public void synchronize() throws RemoteException {
  try { TopologySvc.synchronize(); }
  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
}

public void synchronizeToRemoteTopology(Node node) throws RemoteException {
  try { TopologySvc.synchronizeToRemoteTopology(node); }
  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
}

//for server access only
public void remove(String hostName) throws RemoteException {
  try { TopologySvc.remove(hostName); }
  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
}

public void updateTopology(Topology top) throws RemoteException {
  try { TopologySvc.updateTopology(top); }
  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
}
*/