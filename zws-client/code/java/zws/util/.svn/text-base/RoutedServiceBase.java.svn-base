package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 24, 2004, 4:29 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.s;

import java.io.Serializable;

public class RoutedServiceBase extends Routing implements RoutedService, Serializable {
  public String getServiceName() { return serviceName; }
  public void setServiceName(String s) { serviceName=s; }
  public String getMethodName() { return getName(); }
  public void setMethodName(String s) { setName(s); }

  public String getMethodRoute() { return getServiceRoute()+s.delim+getMethodName(); }
  public String getServiceRoute() { return getDomainName()+s.delim+getServerName()+s.delim+getServiceName(); }

  public String toString() { return getMethodRoute(); }

  private String serviceName=null;}
