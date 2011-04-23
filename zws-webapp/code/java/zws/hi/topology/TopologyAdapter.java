package zws.hi.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 30, 2005, 1:06 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.NameNotFound;
import zws.topology.Node;
import zws.topology.Topology;
import zws.util.AdapterBase;

import java.util.*;

public class TopologyAdapter extends AdapterBase implements Topology {
  TopologyAdapter(NetworkNodes net, Topology t) { network=net; adapt(t); }
  public void adapt(Object topology) { adapt((Topology)topology); }
  public void adapt(Topology t) { topology = t; }
  public Collection getNodes() {
    Collection nodes = new Vector();
    Iterator i = topology.getNodes().iterator();
    while (i.hasNext()) nodes.add(new NodeAdapter(network, (Node)i.next()));
    return nodes;
  }

  public boolean contains(Node n) { return topology.contains(n); }
  public boolean containsIP(String ip) { return topology.containsIP(ip); }
  public boolean containsHost(String hostName) { return topology.containsHost(hostName); }
  public Node find(Node n) throws NameNotFound { return topology.find(n); }
  public Node findIP(String ip) throws NameNotFound { return topology.findIP(ip); }
  public Node findHost(String hostName) throws NameNotFound { return topology.findHost(hostName); }

  public String toString() { return topology.toString(); }
  public String toXML() { return topology.toXML(); }
  private Topology topology=null;
  private NetworkNodes network = null;
}
