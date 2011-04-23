/*
 * DesignState - Design Compression Technology
 * @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.ilink3;


import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.repository.source.RepositoryStateSource;

/**
 * The Class Ilink3RepositoryMetadataSource.
 */
public class Ilink3RepositoryStateSource extends Ilink3RepositoryBase
    implements RepositoryStateSource {

  public Ilink3RepositoryStateSource(QxContext parent) {
    configureParentContext(parent);
  }

  public void readLifeCycleReleaseState(QxContext runningCtx, Origin origin, Authentication id) throws Exception {}

  public void readLockState(QxContext runningCtx, Origin origin, Authentication id) throws Exception {}

  public void readRevision(QxContext runningCtx, Origin origin, String newRevision, Authentication id) throws Exception {}

  }
