package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 19, 2004, 1:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.s;
import java.io.Serializable;

public class RoutedDataBase extends Routing implements RoutedData, Serializable {
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }

  public String getDataRoute() { return getRepositoryRoute()+s.delim+getName(); }
  public String getRepositoryRoute() { return getDomainName()+s.delim+getServerName()+s.delim+getDatasourceName(); }

  public String toString() { return getDataRoute(); }
  
  public void inactivate() {}
  
  private String datasourceName=null;
}
