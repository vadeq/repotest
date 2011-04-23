package zws.hi.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 30, 2005, 12:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.TopologyClient;
import zws.topology.*;
import zws.topology.Node;
import zws.topology.NodeBase;
import zws.util.comparator.AlphaNumericComparator;
import zws.util.comparator.TopologyNodeComparator;

import java.util.*;
import java.util.Map;
import java.util.TreeMap;

public class NetworkNodes {

  NetworkNodes() {
    AlphaNumericComparator c = new AlphaNumericComparator(); 
    nodes = new TreeMap(c);
    NodeBase n = new NodeBase();
    n.initialize();
    //n.setHostName("localhost");
    //add(n);
  }
    
  public NodeAdapter getSelectedNode() {
    if (0==nodes.size()) return null;
    if (1==nodes.size() || null==selectedHostName) {
      NodeAdapter n = (NodeAdapter)nodes.values().iterator().next();
      selectedHostName = n.getHostName();
    }
    return (NodeAdapter)nodes.get(getKey(selectedHostName));
  }
  
  public String getSelection() { return selectedHostName; }
  public void setSelection(String hostName) { selectedHostName=hostName; }

  public Map getNodes() { return nodes; }
  
  public NodeAdapter find(String ip) {  return (NodeAdapter)nodes.get(ip.trim()); }
  public void add(Node node) throws Exception { add(new NodeAdapter(this, node));  }
  public void add(NodeAdapter node) throws Exception { pingStatus(node); }

  public void remove(String ip) { nodes.remove(ip.trim()); }

  public void pingStatus(String ip) throws Exception { pingStatus(find(ip.trim())); }

  public void pingStatus(Node node) throws Exception { //sets status of node, automatically updates node list
    if (null==node) return ;
    Calendar before = new GregorianCalendar();
    Node n = getClient(node).ping();
    Calendar after = new GregorianCalendar();
    NodeAdapter a = new NodeAdapter(this, n);
    a.setPingSpeed(after.getTimeInMillis() - before.getTimeInMillis());
    nodes.remove(a.getIPAddress());
    nodes.put(a.getIPAddress(), a);
    Topology t = a.getTopology();
    Collection top = t.getNodes();
    Iterator i = top.iterator();
    NodeAdapter aa;
    while (i.hasNext()) {
      aa = (NodeAdapter)i.next();
      if (!nodes.containsKey(aa.getIPAddress())) add(aa.getNode());
    }
  }

  private String getKey(String hostName) { return hostName.trim().toLowerCase(); }

  private TopologyClient getClient(Node node) throws Exception { return TopologyClient.getClient(node); }

  public Collection getNodeList() {
    TopologyNodeComparator c = new TopologyNodeComparator();
    TreeSet list = new TreeSet(c);
    list.addAll(nodes.values());
    return list;
  }

  /*
  public Collection getGridStatus() {
    Collection nodeList = getNodeList();
    if (null==nodeList || nodeList.isEmpty() || 1==nodeList.size()) return null;
    Collection gridStatus = new Vector();
    Iterator rows = nodeList.iterator();
    NodeAdapter r,c;
    while(rows.hasNext()) {
     r = (NodeAdapter)rows.next();
     Topology t=null;
     try { t = r.getTopology(); }
     catch (Exception e) {}
     Iterator cols = nodeList.iterator();
     Collection status = new Vector();
     status.add(r.getAlias());
     gridStatus.add(status);
     while (cols.hasNext()) {
       c = (NodeAdapter)cols.next();
       if (c.getIPAddress().equals(r.getIPAddress())) status.add("x");
       else{
         try {
           t.find(c.getHostName());
           status.add("on-line");
         }
         catch(Exception e) { status.add("off-line"); }       
       }
     }
    }
    return gridStatus;
	}
*/
  String selectedHostName=null;
  Map nodes=null;
}