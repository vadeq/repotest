package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 1:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.s;
import zws.security.Authentication;

import java.io.Serializable;

public class RoutedUserDataBase extends Routing implements RoutedUserData, Serializable {
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public Authentication getAuthentication() { return id; }
  public void setAuthentication(Authentication auth) { id=auth; }

  public String getRepositoryRoute() { return getDomainName() + s.delim + getServerName() + s.delim+getDatasourceName(); }
  public String getUserRoute() { return getRepositoryRoute()+s.delim+id.getUsername(); }
  public String getDataRoute() { return getUserRoute()+s.delim+getName(); }
  
  public String toString() { return getDataRoute(); }
  
  private String datasourceName=null;
  private Authentication id = null;  
}
