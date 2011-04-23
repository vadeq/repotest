package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 29, 2004, 6:15 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.application.Properties;
import zws.topology.Node;
import zws.topology.NodeBase;
import zws.util.IPUtil;
import zws.util.comparator.AlphaNumericComparator;

import java.net.UnknownHostException;
import java.util.*;

public class Server {
    

//  public static boolean isClustered() { return getServerNode().getNumber()>-1; }
  public static Node getServerNode() {
    if (null==serverNode) {
      serverNode = new NodeBase();
      serverNode.initialize();
    }
    return serverNode;
  }

  public static String getDomainName() { //make obsolete
    if (null==domain) domain = Properties.get(Names.DOMAIN_NAME);
    return domain;
  }
  public static String getNode() { //make obsolete
    return "DesignState-node-" + getNodeNumber();
    //if (null==node) node = Properties.get(Names.NODE);
    //return node;
  }
  public static String getName() { //make obsolete
    if (null==name) name = Properties.get(Names.SERVER_NAME);
    return name;
  }

  public static String getRealm() {
    if (null==realm) realm = Properties.get(Names.REALM);
    return realm;
  }

  public static String getNodeType() {
    if (null==nodeType) nodeType= Properties.get(Names.NODE_TYPE);
    return nodeType;
  }

  public static int getNodeNumber() {
    try {
      if (-1==nodeNumber) nodeNumber= Integer.valueOf(Properties.get(Names.NODE_NUMBER)).intValue();
    }
    catch (java.lang.NumberFormatException e) {}; 
    return nodeNumber;
  }
  public static String getAlias() {
    if (null==alias) alias= Properties.get(Names.NODE_ALIAS);
    return alias;
  }
  //public static boolean debugMode() { return Boolean.valueOf(Properties.get(Names.DEBUG_MODE)).booleanValue(); }
  public static boolean debugMode() { return true;}
  public static boolean productionMode() { return !debugMode(); }

  public synchronized static String generateUniqueID() {
    StringBuffer b = new StringBuffer();
    return b.append(name).append(System.currentTimeMillis()).append(autoDecrement()).toString();
  }
  private static long autoDec=Long.MAX_VALUE;
  private static long autoDecrement() { return autoDec--; }

  public static String getIPAddress() { 
    if (null==ipAddress) {
      try { ipAddress = IPUtil.getIPAddress(); }
      catch (UnknownHostException e) { e.printStackTrace(); }
    }
    return ipAddress;
  }

  public static String getJndiPort() {
    if (null==jndiPort) jndiPort= Properties.get(Names.JNDI_PORT);
    if (null==jndiPort) jndiPort = "1099";
    return jndiPort; 
  }
  
  public static void setJndiPort(String s) { jndiPort=s; }
  
  public static String getHostName() { 
    if (null==hostName) {
      try { hostName = IPUtil.getHostName(getIPAddress()); }
      catch (UnknownHostException e) { e.printStackTrace(); }
    }
    return hostName;
  }

  public static String getFQDN() { 
    if (null==domainName) {
      try { domainName = IPUtil.getDomain(getHostName()); }
      catch (UnknownHostException e) { e.printStackTrace(); }
    }
    return domainName;
  }

  //Geographic Location for Node
  public static String getCountry() {
    if (null==country) country= Properties.get(Names.NODE_COUNTRY);
    return country; 
  }
  
  public static String getState() {
    if (null==state) state= Properties.get(Names.NODE_STATE);
    return state; 
  }
  public static String getCity() {
    if (null==city) city = Properties.get(Names.NODE_CITY);
    return city; 
  }
  public static String getLocation() {
    if (null==location) location= Properties.get(Names.NODE_LOCATION);
    return location; 
  }
  
  public static String getDescription() { 
    if (null==description) description= Properties.get(Names.NODE_DESCRIPTION);
    return description; 
  }
 
  //Contact info
  public static String getContactName() { 
    if (null==contactName) contactName = Properties.get(Names.NODE_CONTACT_NAME);
    return contactName; 
  }
  public static String getContactEmail() { 
    if (null==contactEmail) contactEmail = Properties.get(Names.NODE_CONTACT_EMAIL);
    return contactEmail; 
  }
  public static String getContactNumber() { 
    if (null==contactNumber) contactNumber = Properties.get(Names.NODE_CONTACT_NUMBER);
    return contactNumber; 
  }
  
  
  public static long getHTTPPort() { 
  	String port = Properties.get(Names.HTTP_PORT);
  	if (null==port || "".equals(port.trim())) return 80;
  	try { return Long.valueOf(port).longValue(); }
  	catch (NumberFormatException e) { return 80; }
  }
  
  public static SortedSet getServerList() { //make obsolete
    //+++ initialize servers using the topology service;
	  AlphaNumericComparator comparator = new AlphaNumericComparator();
	  SortedSet servers = new TreeSet(comparator);
	  //Map serverMap = new HashMap();
   
	  String serverList = Properties.get(Names.SERVER_LIST);
	  StringTokenizer tokens = new StringTokenizer(serverList, Names.DELIMITER);
	  try {
	    if (tokens.hasMoreTokens()) servers.add(tokens.nextToken().trim());
	    else servers.add(serverList.trim());
	  }
	  catch(Exception e) { e.printStackTrace(); }
	  while (tokens.hasMoreTokens()) { 
	    try { servers.add(tokens.nextToken().trim()); }
	    catch(Exception e) { e.printStackTrace(); }
	  }
	  return servers;
  }  
  
  private static String name=null;
  
  private static int nodeNumber=-1;
  private static String nodeType=null;
  private static String alias=null;
  private static String realm=null;
  private static String domain=null;
  private static NodeBase serverNode=null;
   
  //private static String node=null;
  private static String ipAddress=null;
  private static String hostName=null;
  private static String jndiPort=null;
  private static String domainName=null;
  
  private static String city=null;
  private static String state=null;
  private static String country=null;
  private static String location=null;
  private static String description=null;
  private static String contactName=null;
  private static String contactEmail=null;
  private static String contactNumber=null;
}
