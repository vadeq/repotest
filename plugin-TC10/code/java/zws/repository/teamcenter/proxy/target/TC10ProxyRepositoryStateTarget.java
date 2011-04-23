package zws.repository.teamcenter.proxy.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.target.RepositoryStateTarget;
import zws.repository.teamcenter.proxy.TC10ProxyRepositoryBase;
import zws.security.Authentication;

import java.util.Collection;
import java.util.Map;


/**
 * The Interface RepositoryStateTarget.
 */
public class TC10ProxyRepositoryStateTarget extends TC10ProxyRepositoryBase
implements RepositoryStateTarget {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public TC10ProxyRepositoryStateTarget(QxContext parent) 
  {
    configureParentContext(parent);
  }
  
  public void setStateAttributes(QxContext runningCtx, Origin origin, Map attribValues, Authentication id) throws Exception{}
  
  public void setStateAttribute(QxContext runningCtx, Origin origin, String attribute, String newValue, Authentication id) throws Exception{}
  
  public void lock(QxContext runningCtx, Origin origin, Authentication id) throws Exception{}
  
  public void unlock(QxContext runningCtx, Origin origin, Authentication id) throws Exception{}
  
  public void lock(QxContext runningCtx, Collection origins, Authentication id) throws Exception{}
  
  public void unlock(QxContext runningCtx, Collection origins, Authentication id) throws Exception{}
  
  public void updateRevision(QxContext runningCtx, Origin origin, String newRevision, Authentication id) throws Exception{}
  
  public void promoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception{}
  
  public void demoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception{}
  
}
