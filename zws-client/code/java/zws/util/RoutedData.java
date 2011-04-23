package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 1:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//Routable items are identifiable by domain, server, datsource, and name
//Routable items can be tranported (are serializable)
public interface RoutedData extends Routed {
  String getDomainName();
  String getServerName();
  String getDatasourceName();
  String getName();

  String getDataRoute();
  String getRepositoryRoute();
}
