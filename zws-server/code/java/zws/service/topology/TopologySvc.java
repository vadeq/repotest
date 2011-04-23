package zws.service.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 7, 2004, 3:10 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.TopologyClient;
import zws.application.Names;
import zws.exception.Duplicate;
import zws.exception.NameNotFound;
import zws.topology.*;
//import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.naming.*;

public class TopologySvc {
  public static Node ping() {
    {}//Logwriter.printOnConsole("ping: "+ getTopology());
    return Server.getServerNode();
  }

  public static Topology getTopology() { return topology(); }

  public static void register(Node node) throws Exception {
    Node n=node;
    /*
    TopologyClient c20 = TopologyClient.getClient("192.168.0.79");
    TopologyClient c13 = TopologyClient.getClient("192.168.0.125");
    Node n20 = c20.ping();
    Node n13 = c13.ping();
    */
    TopologyClient client = TopologyClient.getClient(node);
    n = client.ping();
    {}//Logwriter.printOnConsole("Existing Topology:----------");
    {}//Logwriter.printOnConsole(getTopology().toXML());
    topology().add(n); // get the real node from the source server + make sure it is alive
    {}//Logwriter.printOnConsole("New Topology:----------");
    {}//Logwriter.printOnConsole(""+getTopology().toXML());
    //synchronize();
  }

  public static void unregister(String ip) throws Exception {
    topology().removeIP(ip); // get the real node from the source server + make sure it is alive
    //synchronize();
  }

  public static TopologyBase topology()  {
    initTopology();
    return top;
  }
  private static void initTopology() {
    if (null!=top) return;
    top = new TopologyBase();
    top.add(Server.getServerNode());
  }


  private static TopologyBase top = null;
}



/*
 *
 *
  public static void remove(String hostName) throws Exception {
    {}//Logwriter.printOnConsole("Removing: " + hostName);
    if (hostName.trim().equalsIgnoreCase(Server.getServerNode().getHostName())){
      topology=null; //removed from network
      Server.getServerNode().setStatus(Node.OFF_LINE);
      initTopology(); //make sure topology is initialized!
      {}//Logwriter.printOnConsole("Disconnected! ");
    }
    else {
      initTopology(); //make sure topology is initialized!
      Node n = topology().removeHost(hostName);
      {}//Logwriter.printOnConsole("Removed: " + n);
    }
  }



public static void unregister(String hostName) throws Exception {
  {}//Logwriter.printOnConsole("Unregistering: " + hostName);
  TopologyClient client = TopologyClient.getClient(node);
  Iterator i = getTopology().getNodes().iterator();
  Node n=null;
  while (i.hasNext()) {
    n = (Node)i.next();
    if (n.getHostName().equalsIgnoreCase(Server.getServerNode().getHostName())) continue;
    //if (n.ON_LINE.equals(n.getStatus())) {
    if (n.getStatus().equals(n.ON_LINE)) {
      try {
        service = findTopologyService(n);
        service.remove(hostName);
      }
      catch(Exception e) { n.setStatus(n.OFF_LINE); }
    }
    //}
  }
  remove(hostName); //remove self from topology
  {}//Logwriter.printOnConsole("Unregistered: " + n);

}
public static void synchronizeToRemoteTopology(Node node) throws Exception {
  Node me = Server.getServerNode();
  if (me.getHostName().equalsIgnoreCase(node.getHostName())) return ; //don't reload from self
  TopologyServiceRemote service;
  {}//Logwriter.printOnConsole("Synchronizing To Remote Topology: " );
  try {
    service = findTopologyService(node);
    topology = merge(getTopology(), service.getTopology());
    synchronize();
  }
  catch(CommunicationException e) { node.setStatus(node.OFF_LINE); }
  catch(Exception e) { node.setStatus(node.OFF_LINE); }
}

public static void synchronize() throws Exception {
  TopologyServiceRemote service;
  initTopology(); //make sure topology is initialized
  {}//Logwriter.printOnConsole("Synchronizing All Nodes In Topology: " );
  {}//Logwriter.printOnConsole("Old Topology: " );
  {}//Logwriter.printOnConsole(getTopology());

  Iterator i = getTopology().getNodes().iterator();
  Node n;
  while (i.hasNext()) { //merge all topologies on the network
    n = (Node)i.next();
    if (n.getHostName().equalsIgnoreCase(Server.getServerNode().getHostName())) continue;
    if (n.getStatus().equals(n.ON_LINE)) {
      try { //update as many nodes as can be reached
        service = findTopologyService(n);
        topology = merge(getTopology(), service.getTopology());
      }
      catch(Exception e) { n.setStatus(n.OFF_LINE); }
    }
  }
  {}//Logwriter.printOnConsole("Broadcasting new Topology: " );
  {}//Logwriter.printOnConsole(getTopology());
  broadcastTopology();  //update all topologies on the network
}

private static void broadcastTopology() throws Exception {
  Node n;
  TopologyServiceRemote service;

  //initialize on-line state for all nodes by pininging them
  Iterator i = getTopology().getNodes().iterator();
  while (i.hasNext()) {
    n = (Node)i.next();
    if (n.getHostName().equalsIgnoreCase(Server.getServerNode().getHostName())) continue;
    try {
      service = findTopologyService(n);
      service.ping();
      n.setStatus(n.ON_LINE);
    }
    catch(Exception e) { n.setStatus(n.OFF_LINE); }
  }

  i = getTopology().getNodes().iterator();
  while (i.hasNext()) {
    n = (Node)i.next();
    if (n.getHostName().equalsIgnoreCase(Server.getServerNode().getHostName())) continue;
    if (n.getStatus().equals(n.ON_LINE)) {
      try { //update as many nodes as can be reached
        service = findTopologyService(n);
        service.updateTopology(getTopology());
      }
      catch(Exception e) { n.setStatus(n.OFF_LINE); }
    }
  }
}

public static void updateTopology(Topology top) {
  {}//Logwriter.printOnConsole("Updating Topology: " );
  {}//Logwriter.printOnConsole("Old Topology: " );
  {}//Logwriter.printOnConsole(getTopology());
  topology = (TopologyBase)top;
  Node me = Server.getServerNode();
  try { me = topology.findIP(me.getIPAddress()); }
  catch(NameNotFound e) {
    me = Server.getServerNode();
    {}//Logwriter.printOnConsole("Added my Node to new Topology: " );
    try { topology.add(me); } catch(Exception ex) {}
  }
  {}//Logwriter.printOnConsole("New Topology: " );
  {}//Logwriter.printOnConsole(getTopology());
}

private static TopologyBase merge(Topology t0, Topology t1){
  TopologyBase t = new TopologyBase();
  Collection c;
  Iterator i;
  c = t0.getNodes();
  Node n;
  if (null!=c) {
    i = c.iterator();
    while (i.hasNext()) {
      n = (Node)i.next();
      try { t.add(n); } catch (Duplicate e) {}
    }
  }

  c = t1.getNodes();
  if (null!=c) {
    i = c.iterator();
    while (i.hasNext()) {
      n = (Node)i.next();
      try { t.add(n); } catch (Duplicate   e) {}
    }
  }
  return t;
}
*/
