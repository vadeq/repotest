package zws.util;
import zws.security.Authentication;

/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 1:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//Routable items are identifiable by domain, server, datsource, and name
//Routable items can be tranported (are serializable)
public interface RoutedUserData extends Routed {
  String getDomainName();
  String getServerName();
  String getDatasourceName();
  Authentication getAuthentication();

  String getName();

  String getDataRoute();
  String getUserRoute();
  String getRepositoryRoute();
}
