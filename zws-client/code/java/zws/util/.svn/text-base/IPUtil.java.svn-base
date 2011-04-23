package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 6, 2004, 3:24 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPUtil {
  public static String getIPAddress() throws UnknownHostException {
    String localHostName = InetAddress.getLocalHost().getHostName();
    return getIPAddress(localHostName);
  }
  
  public static String getIPAddress(String hostName) throws UnknownHostException {
    InetAddress addr = InetAddress.getByName(hostName);
    return addr.getHostAddress();
  }
  
  public static String getHostName(String ip) throws UnknownHostException {
    InetAddress addr = InetAddress.getByName(ip);
    return addr.getCanonicalHostName();
  }
  
  public static String getDomain(String hostName) throws UnknownHostException {
   int idx = hostName.indexOf(".");
   if (idx==-1) return null;
   return hostName.substring(idx+1);
 }

}
