package zws.repository.source;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.data.Metadata;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;


import java.util.Collection;
import java.util.Map;


/**
 * The Interface RepositoryWorkflowTarget.
 */
public interface RepositoryECOTarget extends Repository {
  
  void addAffectedItem(QxContext runningCtx, String ecoNum, Metadata item, Authentication id) throws Exception;
  void addAffectedItem(QxContext runningCtx, String ecoNum, String itemNum, Authentication id) throws Exception;
  void addAffectedItems(QxContext runningCtx, String ecoNum, Collection itemNumbers, Authentication id) throws Exception;
  void update(QxContext runningCtx, String ecoNum, Metadata data, Authentication id) throws Exception;
  String createECO(QxContext runningCtx, String agileClass, Authentication id) throws Exception;
  void setECOAttribute(QxContext runningCtx, String ecoNum, String att, String val, Authentication id) throws Exception;
  void setBOMAttribute(QxContext runningCtx, String ecoNum, String itemNum, String subcompNum, String att, String val, Authentication id) throws Exception;
  String createECO(QxContext runningCtx, Map ecoAtts, Authentication id) throws Exception;
  
}

