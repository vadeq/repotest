package zws.hi.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 29, 2005, 9:33 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.TopologyClient;
import zws.topology.Node;

import com.zws.hi.hiList;

import java.util.Collection;
import java.util.Vector;

public class hiTopology extends hiList {
  public void bind() {}
  
  public String addNewNode(){
	  if (null==getNewIPAddress()) return ctrlOK;
	  try {
  	  long number = Long.valueOf(getNewNodeNumber()).longValue(); 
  	  long prt = Long.valueOf(getNewPort()).longValue();
  	  TopologyClient c = TopologyClient.getClient(number, getNewIPAddress(), prt);
      Node n = c.ping();
      getNetworkNodes().add(n);
	  }
	  catch(Exception e) {
	   logFormError("err.could.not.ping", getNewNodeNumber(), getNewIPAddress(), getNewPort());
	   e.printStackTrace();
	  }
	  return ctrlOK;
	}

  public String ping(){
	  if (null==getID()) return ctrlOK; 
	  try { getNetworkNodes().pingStatus(getID()); }
	  catch(Exception e) {
     logFormError("err.could.not.ping", getNewNodeNumber(), getNewIPAddress(), getNewPort());
	 	 e.printStackTrace();
	 	}
	  return ctrlOK;
	}

  public String registerConnection(){
    /*
      TopologyClient c20 = TopologyClient.getClient("192.168.0.79");
      TopologyClient c13 = TopologyClient.getClient("192.168.0.125");
      Node n20 = c20.ping();
      Node n13 = c13.ping();
    */
    if (null==getID()) return ctrlERROR;
    if (null==getEventKey()) return ctrlERROR; 
    NodeAdapter n0=null, n1=null;
    n0 = getNetworkNodes().find(getID());
    n1 = getNetworkNodes().find(getEventKey());
    if (null==n0) return ctrlERROR;
    if (null==n1) return ctrlERROR;
    {} //System.out.println("Node 0: " + n0.toXML());
    {} //System.out.println("Node 1: " + n1.toXML());
    try {
      TopologyClient c0 = TopologyClient.getClient(n0);
      TopologyClient c1 = TopologyClient.getClient(n1);
      c0.register(n1.getNode());
      c1.register(n0.getNode());
      return ctrlOK;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }
  
  public String unregisterConnection(){
	  if (null==getID()) return ctrlERROR;
	  if (null==getEventKey()) return ctrlERROR; 
	  NodeAdapter n0=null, n1=null;
	  n0 = getNetworkNodes().find(getID());
	  n1 = getNetworkNodes().find(getEventKey());
	  if (null==n0) return ctrlERROR;
	  if (null==n1) return ctrlERROR;
	  try {
  	  //TopologyClient c0 = TopologyClient.getClient(n0);
	    //TopologyClient c1 = TopologyClient.getClient(n1);
	    //c0.unregister(n1);
	    //c1.unregister(n0);
	    return ctrlOK;      
	  }
	  catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
	}

  public Collection getNodeList() {
    if (null==getNetworkNodes().getNodes()) return null;
    if (0==getNetworkNodes().getNodes().size()) return null;
    return getNetworkNodes().getNodeList();
  }
  
  public Collection getNodeNumbers() { 
    Collection c= new Vector();
    c.add("0");
    c.add("1");
    c.add("2");
    c.add("3");
    c.add("4");
    c.add("5");
    c.add("6");
    return c;
  }
  
  public String getNewNodeNumber() { return newNodeNumber; }
  public void setNewNodeNumber(String s) { newNodeNumber=s; }
  
  public String getNewIPAddress() { return newIPAddress; }
  public void setNewIPAddress(String s) { newIPAddress=s; }
  
  public String getNewPort() { return ""+newPort; }
  public void setNewPort(String s) { newPort=Long.valueOf(s).longValue(); }
  
  private static NetworkNodes getNetworkNodes() {
    if (null==net) net = new NetworkNodes();
    return net;
  }
 
  private static NetworkNodes net=null;
  private String newNodeNumber=null;
  private String newIPAddress=null;
  private long newPort=1099; 
}



/*

public Collection getSelectedNodeConnections() {
  if(null==network.getSelectedNode()) return new Vector();
  try { return network.getSelectedNode().getConnections(); }
  catch (Exception e) { return new Vector(); }
}

public NodeAdapter getSelectedNode() {
  return network.getSelectedNode();
}

public String select() {
  network.setSelection(getID());
  return ctrlOK;
}


public String addNode(){
  if (null==getNodeHostName()) return ctrlOK; 
  NodeBase n = new NodeBase();
  //n.setHostName(getNodeHostName());
  network.add(n);
  setNodeHostName("");
  return ctrlOK;
}

public String removeNode(){
  if (null==getID()) return ctrlOK; 
  network.remove(getID());
  return ctrlOK;
}

public String pingStatus(){
  if (null==getID()) return ctrlOK; 
  network.pingStatus(getID());
  return ctrlOK;
}

//setSelectedNodeAsPrimaryAppServer();

  
  public String registerHost(){
    if (null==getSelectedNode()) return ctrlOK;
    if (null==getTopologyHostName()) return ctrlOK; 
    NodeBase n = new NodeBase();
    //n.setHostName(getTopologyHostName());
    try {
      getSelectedNode().register(n);
      setTopologyHostName("");
      return ctrlOK;      
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }
  
  public String unregisterHost(){
    if (null==getSelectedNode()) return ctrlOK;
    if (null==getTopologyHostName()) return ctrlOK; 
    try {
      getSelectedNode().unregister(getTopologyHostName());
      return ctrlOK;      
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }

  
  public String synchronize() {
    if (null==getID()) return ctrlOK; 
    if (null==getSelectedNode()) return ctrlOK;
    try {
      getSelectedNode().synchronize();
      return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      return ctrlERROR;
    }
  }
  public String getNodeHostName() { return nodeHostName; }
  public void setNodeHostName(String s) { nodeHostName = s; }
  public String getTopologyHostName() { return topologyHostName; }
  public void setTopologyHostName(String s) { topologyHostName = s; }

  public String getSelection() { return getID(); }
  public void setSelection(String s) { setID(s); network.setSelection(s); }

  private String nodeHostName=null;
  private String topologyHostName=null;
  
*/