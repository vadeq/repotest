package zws.repository.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;

import java.util.Collection;
import java.util.Map;


public interface RepositoryStateTarget extends Repository {
  void setStateAttributes(QxContext runningCtx, Origin origin, Map attribValues, Authentication id) throws Exception;
  void setStateAttribute(QxContext runningCtx, Origin origin, String attribute, String newValue, Authentication id) throws Exception;
  void lock(QxContext runningCtx, Origin origin, Authentication id) throws Exception;
  void unlock(QxContext runningCtx, Origin origin, Authentication id) throws Exception;
  void lock(QxContext runningCtx, Collection origins, Authentication id) throws Exception;
  void unlock(QxContext runningCtx, Collection origins, Authentication id) throws Exception;
  void updateRevision(QxContext runningCtx, Origin origin, String newRevision, Authentication id) throws Exception;
  void promoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception;
  void demoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception;
  //void updateStateAttribute(QxContext runningCtx, Origin origin, String attribute, String newValue, Authentication id) throws Exception;
  //void updateLifeCycleReleaseState(QxContext runningCtx, Origin origin, String newLifeCycleReleaseValue, Authentication id) throws Exception;
}
