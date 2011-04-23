package zws.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 21, 2004, 3:30 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.TopologyClient;
import zws.exception.NameNotFound;
import zws.service.EJBLocatorBase;

import java.io.Serializable;
import java.util.*;

public class TopologyBase implements Topology, Serializable {

	public boolean contains(Node n) {
	  try { return null!=find(n);}
	  catch (NameNotFound e) { return false; }
	}

  public boolean containsHost(String hostName) {
	  try { return null!=findHost(hostName);}
 	  catch (NameNotFound e) { return false; }
  }

  public boolean containsIP(String ip) {
	  try { return null!=findIP(ip);}
	  catch (NameNotFound e) { return false; }
  }

	public Node find(Node n) throws NameNotFound {
    if (null!=n.getIPAddress()) return findIP(n.getIPAddress());
    return findHost(n.getHostName());
	}

  public Node findHost(String hostName) throws NameNotFound {
    Node n = (Node)nodeHostNames.get(getKey(hostName)); 
    if (null==n) throw new NameNotFound(hostName);
    return n;
  }

  public Node findIP(String ip) throws NameNotFound {
    Node n = (Node)nodes.get(ip); 
    if (null==n) throw new NameNotFound(ip);
    return n;
  }

  public void add(Node n) {
    try {
      Node old = find(n);
      remove(old);
      EJBLocatorBase.clear(old);
  	  TopologyClient c = TopologyClient.getClient(n);
      c.ping();
    }
    catch (NameNotFound e) {} //don't want it to be found
    catch (Exception e) {e.printStackTrace(); } //probably could not ping the node
    
    nodes.put(n.getIPAddress(), n);
    nodeHostNames.put(getKey(n.getHostName()), n);
  }

  public Node remove(Node n) throws NameNotFound {
    Node old = find(n);
    nodes.remove(old);
    nodeHostNames.remove(old);
    return old;
  }

  public Node removeHost(String hostName) throws NameNotFound{
    Node old = findHost(hostName);
    nodes.remove(old);
    return old;
  }

  public Node removeIP(String ip) throws NameNotFound{
    Node old = findIP(ip);
    nodes.remove(old);
    nodeHostNames.remove(old);
    return old;
  }


  public Collection getNodes() { return nodes.values(); }
 
  /*
  public void setNodes(Collection c) throws Duplicate {
    nodes.clear();
    Iterator i = c.iterator();
    while (i.hasNext()) add((Node)i.next());
  }
  */

 private String getKey(String hostName) { return hostName.trim().toLowerCase(); }
  
  public String toString() { return toXML(); }
  public String toXML() {
    String s = "<topology>" + NEW_LINE;
    Iterator i = nodes.values().iterator();
    while (i.hasNext()) { s += i.next().toString(); }
    s += "</topology>" + NEW_LINE;
    return s;
  }
  private Map nodes = new HashMap();
  private Map nodeHostNames = new HashMap();
  
  private static String NEW_LINE = zws.application.Names.NEW_LINE;
}
