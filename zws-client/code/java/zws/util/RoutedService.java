package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 24, 2004, 1:02 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public interface RoutedService extends Routed {
  public String getDomainName();
  public String getServerName();
  public String getServiceName();
  public String getMethodName(); //synonim for geName()

  public String getMethodRoute();
  public String getServiceRoute();
}
