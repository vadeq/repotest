package zws.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 21, 2004, 2:59 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.util.PrintUtil;
import zws.util.StringPair;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class NodeBase implements Node, Serializable {

  public NodeBase() { }
  	
	public NodeBase(long node, String ip) { 
	  nodeNumber = node;
	  ipAddress = ip.trim();
	}

	public NodeBase(long node, String ip, long namingServicePort) { 
	  nodeNumber = node;
	  ipAddress = ip.trim();
	  port = namingServicePort;
	}

	public NodeBase(String ip, long namingServicePort) {
	  port = namingServicePort;
    ipAddress = ip; 
  }

  public void initialize() {
    nodeNumber = Server.getNodeNumber();
    alias = Server.getAlias();
    realm = Server.getRealm();
    ipAddress = Server.getIPAddress();
    hostName = Server.getHostName();
    domainName = Server.getFQDN();
    city = Server.getCity();
    state = Server.getState();
    country = Server.getCountry();
    location = Server.getLocation();
    description = Server.getDescription();
    contactName=Server.getContactName();
    contactEmail=Server.getContactEmail();
    contactNumber=Server.getContactNumber();
    nodeType = Server.getNodeType();
    port = Long.valueOf(Server.getJndiPort()).longValue();
  }

  public long getNumber() { return nodeNumber; }
  public String getRealm() { return realm; }
  public String getType() { return nodeType; }

  public String getName() { return alias; }
  public String getAlias() { return alias; }
  public String getIPAddress() { return ipAddress; }
  public String getHostName() { return hostName; }
  public String getDomainName() { return domainName; }
  public String getConnectivityType() { return connectivityType; }
  public void setConnectivityType(String s) { 
    String c=null;
    if (WAN.equalsIgnoreCase(s)) c = WAN;
    else if (INTERNET.equalsIgnoreCase(s)) c = INTERNET;
    connectivityType=c;
  }

  public void setJNDIProperties(Properties p) { jndiProperties= new Properties(p); }
  public void setJNDIProperty(StringPair pair) {
    jndiProperties.setProperty(pair.getString0(), pair.getString1());
  }
  public void setProperty(StringPair pair) {
    nodeProperties.setProperty(pair.getString0(), pair.getString1());
  }
  public void set(String property, String value) { nodeProperties.put(property, value); }

  public String getStatus() { return status; }
  public void setStatus(String s) { status=s; }

  //Geographic Location for Node
  public String getCountry() { return country; }
  public String getState() { return state; }
  public String getCity() { return city; }
  public String getLocation() { return location; }
  
  public String getDescription() { return description; }
  public void setDescription(String s) { description=s; }
 
  //Contact info\
  public String getContactName() { return contactName; }
  public void setContactName(String s) { contactName=s; }
  public String getContactEmail() { return contactEmail; }
  public void setContactEmail(String s) { contactEmail=s; }
  public String getContactNumber() { return contactNumber; }
  public void setContactNumber(String s) { contactNumber=s; }
  
  public long getPort() { return port; }
  
  public void setPort(long l) { port=l; }
  public void setNumber(long l) { nodeNumber=l; }
  public void setHostName(String s) { hostName=s; }
  public void setIPAddress(String s) { ipAddress=s; }
  
  public Properties getJNDIProperties() {
    constructJNDIProperties();
    PrintUtil.print(jndiProperties);
    return jndiProperties;
  }
  private void constructJNDIProperties() {
   if (jndiProperties.isEmpty()) { //tomcat uses jndiProperties..
     try {
   		 {} //System.out.println("...........Reading /zws-jndi.properties for node-" + this.getNumber());
   		 InputStream in = getClass().getResourceAsStream("/zws-node-"+getNumber()+"-jndi.properties");
   		 jndiProperties.load(in);
   		 in.close();
   		 {} //System.out.println("...........done reading");
     }
     catch (Exception e) {
			 {} //System.out.println("\nCould not load jndi.properties file from class path");
 			 {} //System.out.println("Setting default (JBOSS) jndi properties");
  	    if (null==jndiProperties.getProperty("java.naming.factory.initial")) jndiProperties.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
   	    if (null==jndiProperties.getProperty("java.naming.factory.url.pkgs")) jndiProperties.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
        if (null==jndiProperties.getProperty("java.naming.provider.url")) jndiProperties.setProperty("java.naming.provider.url", "jnp://"+ipAddress+":"+port);
   			//e.printStackTrace();
   		}
   	}
    //Set default (JBOSS) jndi properties if necessary
    if (null==jndiProperties.getProperty("java.naming.factory.initial")) jndiProperties.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
    if (null==jndiProperties.getProperty("java.naming.factory.url.pkgs")) jndiProperties.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
    if (null==jndiProperties.getProperty("java.naming.provider.url")) jndiProperties.setProperty("java.naming.provider.url", "jnp://"+ipAddress+":"+port);
    
    String url = jndiProperties.getProperty("java.naming.provider.url");
    String tPort=null;
    if (null!=url) 
    if (null!=url) url = url.substring(url.indexOf(':')+1);
    if (null!=url) tPort = url.substring(url.indexOf(':')+1);
    {} //System.out.println("TargetPort: " + tPort);
    if (null!=tPort && !tPort.equals(""+getPort())) {
      {} //System.out.println("Resetting port from " + getPort() + " to " + tPort + "!");
      setPort(Long.valueOf(tPort).longValue());
    }
		{} //System.out.println(jndiProperties);
  }
  
  public Properties getNodeProperties() { return nodeProperties; }
  public String getNodeProperty(String key) { return nodeProperties.getProperty(key); }

  public String toString() { return toXML(); }
  public String toXML() {
    return "<node number=\""+getName()+"\" name=\""+getName()+"\" alias=\""+getAlias()+"\" status=\""+status+"\" host-name=\""+getHostName()+"\" ip=\""+getIPAddress()+"\" port=\""+port+"\" connectivity-type=\""+connectivityType+"\"/>"+zws.application.Names.NEW_LINE;
  }
  
  
  private String connectivityType=WAN; 
  //assume that naming provider is also deployed on same machine as the node
  private long port=1099L; //Naming provider port
  //private long port=1100L; //Naming provider port
  private String status=ON_LINE;
  private Properties jndiProperties = new Properties();
  private Properties nodeProperties = new Properties();
  
  private String alias=null;
  private long nodeNumber=-1;
  private String nodeType=null;
  private String realm=null;
   
  private String ipAddress=null;
  private String hostName=null;
  private String domainName=null;
  
  private String city=null;
  private String state=null;
  private String country=null;
  private String location=null;
  private String description=null;
  private String contactName=null;
  private String contactEmail=null;
  private String contactNumber=null;
}
