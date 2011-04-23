package zws.repository.source;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;



public interface RepositoryStateSource extends Repository {
  void readLockState(QxContext runningCtx, Origin origin, Authentication id) throws Exception;
  void readRevision(QxContext runningCtx, Origin origin, String newRevision, Authentication id) throws Exception;
  void readLifeCycleReleaseState(QxContext runningCtx, Origin origin, Authentication id) throws Exception;
}
