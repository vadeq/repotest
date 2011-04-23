package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 1:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.s;
import zws.security.Authentication;

import java.io.Serializable;

public class RoutedUserBase extends Routing implements RoutedUser, Serializable {
  public Authentication getAuthentication() { return id; }


  public String getUserRoute() { return getDomainName()+s.delim+getServerName()+s.delim + id.getUsername(); }
  
  public String toString() { return getUserRoute(); }
  
  private String datasourceName=null;
  private Authentication id = null;  
}
