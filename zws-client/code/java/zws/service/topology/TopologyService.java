package zws.service.topology; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.topology.Node;
import zws.topology.Topology;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface TopologyService extends Serializable {
  public Node ping() throws RemoteException;
  public Topology getTopology() throws RemoteException;
  public void register(Node n) throws RemoteException;
  public void unregister(String ip) throws RemoteException;
  //public void synchronize() throws RemoteException;
  //public void synchronizeToRemoteTopology(Node node) throws RemoteException;

  //for server access only
  //public void remove(String hostName) throws RemoteException;
  //public void updateTopology(Topology top) throws RemoteException;
}
