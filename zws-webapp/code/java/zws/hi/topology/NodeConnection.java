package zws.hi.topology; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Oct 1, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

public class NodeConnection {  
  private static String SELF = "self";
  private static String ONLINE = "on-line";
  private static String OFFLINE = "off-line";
  
	public NodeConnection() { }

  public NodeConnection(NodeAdapter sourceNode, NodeAdapter targetNode, boolean online) {
    source = sourceNode;
    target = targetNode;
    if (source.getIPAddress().equals(target.getIPAddress())) status = SELF;
    else if (online) status = ONLINE;
    else status = OFFLINE;
  }
  
  public String getConnectionStatus() { return status; }
  
  public String getSourceIPAddress() { return source.getIPAddress(); }
  public String getTargetIPAddress() { return target.getIPAddress(); }
  public String getSourceAlias() { return source.getAlias(); }
  public String getTargetAlias() { return target.getAlias(); }

  private NodeAdapter source, target;
  private String status = OFFLINE;
  
}
