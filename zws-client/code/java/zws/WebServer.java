package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 29, 2005, 8:34 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.application.Properties;
import zws.util.IPUtil;

import java.net.UnknownHostException;

public class WebServer {
  public static boolean debugMode() { return Boolean.valueOf(Properties.get(Names.DEBUG_MODE)).booleanValue(); }

  public String getPrimaryAppServerHostName() { return primaryAppServerHostName; }
  public void setPrimaryAppServerHostName(String s) { primaryAppServerHostName = s; }
  
  public static String getIPAddress() { 
    if (null==ipAddress) {
      try { ipAddress = IPUtil.getIPAddress(); }
      catch (UnknownHostException e) { e.printStackTrace(); }
    }
    return ipAddress;
  }
  
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

  private static String primaryAppServerHostName = "localhost";
  private static String ipAddress=null;
  private static String hostName=null;
  private static String domainName=null;
}
