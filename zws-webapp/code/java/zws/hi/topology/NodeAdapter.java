package zws.hi.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 30, 2005, 12:45 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.TopologyClient;
import zws.topology.Node;
import zws.topology.Topology;
import zws.util.AdapterBase;
import zws.util.StringPair;

import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;



public class NodeAdapter extends AdapterBase implements Node {
  NodeAdapter(NetworkNodes net, Node n) { 
    network = net;
    adapt(n); 
  }
  
  public void adapt(Object node) { adapt((Node)node); }
  public void adapt(Node n) { node = n; }

  public TopologyAdapter getTopology () throws Exception {
    Topology t = getClient().getTopology();
    return new TopologyAdapter(network, t);
  }
  public long getPingSpeed() { return pingSpeed; }
  public void setPingSpeed(long speedINms) { pingSpeed = speedINms; }
  //public void synchronize() throws Exception { getClient().synchronize(); }
  //public void register(Node node) throws Exception { getClient().register(node); }
  //public void unregister(String hostName) throws Exception { getClient().unregister(hostName); }
  
  /*
  public int getConnectionCount () {
    return getConnections().size();
  }
  */
  public Collection getNodeConnections () {
    Collection connections = new Vector();
    NodeConnection con;
    if (getStatus().equals(OFF_LINE)) return connections;
    try {
      NodeAdapter n;
      Topology t = getClient().getTopology();
      Iterator i = network.getNodeList().iterator();
      while (i.hasNext()) {
        n = (NodeAdapter)i.next();
        if (t.containsIP(n.getIPAddress())) con = new NodeConnection(this, n, true);
        else con = new NodeConnection(this, n, false);
        connections.add(con);
      }
    }
    catch (Exception e) { e.printStackTrace(); }
    return connections;
  }
  
  public String getName(){ return node.getName(); }
  public String getRealm(){ return node.getRealm(); }  
  public long getNumber(){ return node.getNumber(); }
  public String getType(){ return node.getType(); }  
  public String getAlias(){ return node.getAlias(); }
  public String getHostName(){ return node.getHostName(); }
  public String getDomainName(){ return node.getHostName(); }
  public String getIPAddress(){ return node.getIPAddress(); }
  public long getPort() { return node.getPort(); }
  public String getConnectivityType(){ return node.getConnectivityType(); }

  public void setJNDIProperty(StringPair pair){ node.setJNDIProperty(pair); }
  public void setProperty(StringPair pair){ node.setProperty(pair); }
  public String getStatus(){ return node.getStatus(); }
  public void setStatus(String s){ node.setStatus(s); }
  //Geographic Location for Node
  public String getCountry(){ return node.getCountry(); }
  public String getState(){ return node.getState(); }
  public String getCity(){ return node.getCity(); }
  public String getLocation(){ return node.getLocation(); }

  public String getDescription(){ return node.getDescription(); }
 
  //Contact info
  public String getContactName(){ return node.getContactName(); }
  public void setContactName(String s){ node.setContactName(s); }
  public String getContactEmail(){ return node.getContactEmail(); }
  public String getContactNumber(){ return node.getContactNumber(); }

  public Properties getJNDIProperties(){ return node.getJNDIProperties(); }
  public Properties getNodeProperties(){ return node.getNodeProperties(); }
  public String getNodeProperty(String key){ return node.getNodeProperty(key); }
  
  public String toString() { return node.toString(); }
  public String toXML() { return node.toXML(); }

  private TopologyClient getClient() throws Exception {
    if (null==client) {
      client = TopologyClient.getClient(node);
    }
    return client;
  }
  
  public Node getNode() { return node; }
  
  private Node node=null;
  NetworkNodes network = null;
  private transient TopologyClient client = null;
  private long pingSpeed=0;
}
