/*
 * DesignState - Design Compression Technology
 * @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.agile.sdk;


import zws.exception.UnsupportedOperation;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.repository.target.RepositoryStateTarget;

import java.util.Collection;
import java.util.Map;

public class AgileSDKRepositoryStateTarget extends AgileSDKRepositoryBase
    implements RepositoryStateTarget {
  public AgileSDKRepositoryStateTarget(QxContext parent) {
    configureParentContext(parent);
  }

  public void demoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception {
    throw new UnsupportedOperation("demoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id)");
  }

  public void lock(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    throw new UnsupportedOperation("lock(QxContext runningCtx, Origin origin, Authentication id)");
  }

  public void lock(QxContext runningCtx, Collection origins, Authentication id) throws Exception {
    throw new UnsupportedOperation("lock(QxContext runningCtx, Collection origins, Authentication id)");
  }

  public void promoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception {
    throw new UnsupportedOperation("promoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id)");
  }

  public void setStateAttribute(QxContext runningCtx, Origin origin, String attribute, String newValue, Authentication id) throws Exception {
    throw new UnsupportedOperation("setStateAttribute(QxContext runningCtx, Origin origin, String attribute, String newValue, Authentication id)");
  }

  public void setStateAttributes(QxContext runningCtx, Origin origin, Map attribValues, Authentication id) throws Exception {
    throw new UnsupportedOperation("setStateAttributes(QxContext runningCtx, Origin origin, Map attribValues, Authentication id)");
  }

  public void unlock(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    throw new UnsupportedOperation("unlock(QxContext runningCtx, Origin origin, Authentication id)");
  }

  public void unlock(QxContext runningCtx, Collection origins, Authentication id) throws Exception {
    throw new UnsupportedOperation("unlock(QxContext runningCtx, Collection origins, Authentication id)");
  }

  public void updateRevision(QxContext runningCtx, Origin origin, String newRevision, Authentication id) throws Exception {
    throw new UnsupportedOperation("updateRevision(QxContext runningCtx, Origin origin, String newRevision, Authentication id)");
  }
}
