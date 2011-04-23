package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 21, 2004, 6:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.service.topology.*;
import zws.topology.*;
import zws.xml.op.create.CreateException;

import javax.naming.*;

public class TopologyClient {
  private TopologyClient(Node target) throws Exception {
    targetNode = target;
    try {
      service = findTopologyService(target);
      targetNode.setStatus(Node.ON_LINE);
    }
    catch(Exception e) {
      e.printStackTrace();
      targetNode.setStatus(Node.OFF_LINE);
      throw e;
    }
  }

  public static TopologyClient getClient(long nodeNumber, String ip) throws Exception {
    return new TopologyClient(new NodeBase(nodeNumber, ip));
  }

  public static TopologyClient getClient(long nodeNumber, String ip, long port) throws Exception {
    return new TopologyClient(new NodeBase(nodeNumber, ip, port));
  }
  
  public static TopologyClient getClient(Node node) throws Exception {
    return new TopologyClient(node);
  }
  
  public Node ping() throws Exception {
    try {
      targetNode = service.ping();
      targetNode.setStatus(Node.ON_LINE);
    }
    catch (Exception e) { 
      e.printStackTrace();
      throw e; 
    }
      return targetNode;
  }
  
  public Topology getTopology() throws Exception {
    if (null==service) {}{} //System.out.println("!!!Service is null");
    return service.getTopology();
  }
  
  public void register(Node n) throws Exception {
    service.register(n);
  }
  
  public void unregister(String ip) throws Exception {
    service.unregister(ip);
  }
  private static TopologyServiceRemote findTopologyService(Node node) 
  throws Exception, NamingException, CreateException {
    
    return EJBLocator.findService(node);
    //InitialContext ctx = new InitialContext(node.getJNDIProperties());
    //EJBHome serviceHome = (EJBHome)ctx.lookup("topology-service");
    //return ((TopologyServiceRemoteHome)serviceHome).create();
  }
  
  private Node targetNode=null;
  private TopologyServiceRemote service=null;
}


/*

public void synchronize() throws Exception {
  service.synchronize();
}

public void synchronizeWithRemoteNode(Node remoteNode) throws Exception {
  service.synchronizeToRemoteTopology(remoteNode);
}

*/