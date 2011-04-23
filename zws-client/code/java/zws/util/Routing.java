package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 24, 2004, 3:34 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.s;

import java.io.Serializable;

public class Routing implements Routed, Serializable {
  public String getName() { return name; }
  public String getDomainName() { return domainName; }
  public String getServerName() { return serverName; }
  public void setName(String s) { name=s; }
  public void setDomainName(String s) { domainName=s; }
  public void setServerName(String s) { serverName=s; }

  public String getRoute() { return getDomainName()+s.delim+getServerName()+s.delim+getName(); }
  public static String getRoute(Routing r) { return r.getDomainName()+s.delim+r.getServerName()+s.delim+r.getName(); }

  public String toString() { return getRoute(); }
  
  private String name=null;
  private String domainName=null;
  private String serverName=null;
}
