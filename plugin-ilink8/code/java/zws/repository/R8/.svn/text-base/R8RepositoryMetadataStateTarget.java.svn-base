package zws.repository.R8;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.repository.target.RepositoryStateTarget;

import java.util.Collection;
import java.util.Map;

/**
 * The Class TC10RepositoryMetadataSource.
 */
public class R8RepositoryMetadataStateTarget extends R8RepositoryBase
    implements RepositoryStateTarget {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public R8RepositoryMetadataStateTarget(QxContext parent) {
    configureParentContext(parent);
  }

  public void promoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception {}
  public void demoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception {}

  public void lock(QxContext runningCtx, Origin origin, Authentication id) throws Exception {}
  public void lock(QxContext runningCtx, Collection origins, Authentication id) throws Exception {}

  public void unlock(QxContext runningCtx, Origin origin, Authentication id) throws Exception {}
  public void unlock(QxContext runningCtx, Collection origins, Authentication id) throws Exception {}

  public void updateLifeCycleReleaseState(QxContext runningCtx, Origin origin, String newLifeCycleReleaseValue, Authentication id) throws Exception {}

  public void updateRevision(QxContext runningCtx, Origin origin, String newRevision, Authentication id) throws Exception {}

  public void setStateAttribute(QxContext runningCtx, Origin origin, String attribute, String newValue, Authentication id) throws Exception {}

  public void setStateAttributes(QxContext runningCtx, Origin origin, Map attribValues, Authentication id) throws Exception {}




}
