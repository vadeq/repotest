package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 9, 2004, 11:02 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.datasource.Datasource;
import zws.origin.Origin;
import zws.security.Authentication;

import java.util.Collection;
import java.util.Map;

public abstract class DatasourceAccess {
  public abstract Metadata find(Origin origin, Authentication id) throws Exception;
  public abstract void deleteFromRepository(Origin o, Authentication id) throws Exception;
  public abstract void deleteVersionFromRepository(Origin o, Authentication id) throws Exception;
  public abstract void checkout(Origin origin, String workspace, Authentication id) throws Exception;
  public abstract void checkin(Origin origin, String workspace, Authentication id) throws Exception;
  public abstract void checkin(String serverName, String datasourceName, String workspace, Authentication id) throws Exception;
  public abstract void promote(Origin origin, String promotionLevel, Authentication id) throws Exception;
  public abstract void promote(Origin origin, String promotionLevel, String dependencies, String configuration, Authentication id) throws Exception;
  public abstract void setAttribute(Origin origin, String workspace, String attribute, String value, Authentication id) throws Exception;
  public abstract void setAttribute(String serverName, String datasourceName, String workspace, String name, String attribute, String value, Authentication id) throws Exception;
  public abstract void setAttributes(Origin origin, String workspace, Map attributes, Authentication id) throws Exception;
  public abstract void setAttributes(String serverName, String datasourceName, String workspace, String name, Map attributes, Authentication id) throws Exception;
  public abstract Collection getQueuedSnapshots(String serverName, String datasourceName) throws Exception;
  public abstract void snapshotImage(Origin o, String outputType, Authentication id) throws Exception;
  public abstract void snapshotImage(Origin origin, String outputType, String targetDatasource, Authentication id) throws Exception;

  //public abstract Metadata snapshotImage(Origin o, String imageNamingConvention, String stampNamingConvention, boolean deleteOldImage, Authentication id) throws Exception;
  /*
  public abstract void setAttributeForAll(String serverName, String datasourceName, String workspace, String attribute, String value, Authentication id) throws Exception;
  public abstract void setAttributesForAll(String serverName, String datasourceName, String workspace, Map attributes, Authentication id) throws Exception;
   **/
  public abstract void link(String serverName, String datasourceName, String workspace, String parent, String child, Authentication id) throws Exception;
  public abstract Collection export(Origin origin, String workspace, String location, Authentication id) throws Exception;
  public abstract Collection export(String serverName, String datasourceName, String workspace, String location, String name, Authentication id) throws Exception;
  public abstract void importToWorkspace(String serverName, String datasourceName, String workspace, String location, String filename, Authentication id) throws Exception;
  public abstract void createWorkspace(String serverName, String datasourceName, String workspace, Authentication id) throws Exception;
  public abstract void destroyWorkspace(Origin o, String workspace, Authentication id) throws Exception;
  public abstract void destroyWorkspace(String serverName, String datasourceName, String workspace, Authentication id) throws Exception;

  public static DatasourceAccess getDatasourceAccess(Origin o) {
    if (o.FROM_ILINK.equals(o.getDatasourceType())) return IntralinkAccess.getAccess();
    else return null;
  }
  
  private Datasource datasource;
  private String nodeName=null;
  private String datasourceName=null;
}
