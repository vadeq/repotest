package zws.topology;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 7, 2004, 3:01 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.Named;
import zws.util.StringPair;

import java.util.Properties;

public interface Node extends Named {
  public long getNumber();  
  public String getRealm();
  public String getAlias();
  public String getType();  
  public String getIPAddress();
  public String getHostName();
  public long getPort();  
  public String getDomainName();  
  public String getConnectivityType();

  public String getStatus();
  public void setStatus(String s);
  //Geographic Location for Node
  public String getCountry();
  public String getState();
  public String getCity();
  public String getLocation();
  
  public String getDescription();
 
  //Contact info
  public String getContactName();
  public void setContactName(String s);
  public String getContactEmail();
  public String getContactNumber();
  
  public Properties getJNDIProperties();
  public Properties getNodeProperties();
  public String getNodeProperty(String key);
  public void setJNDIProperty(StringPair pair);
  public void setProperty(StringPair pair);
  
  public String toXML();

  public static String ON_LINE="on line";
  public static String OFF_LINE="off line";
  
  public static String WAN="WAN";
  public static String INTERNET="Internet";  
}
