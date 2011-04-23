package zws.repository.agile.plm.api;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.data.Metadata;
import zws.qx.QxContext;
import zws.repository.source.RepositoryECOTarget;
import zws.security.Authentication;


import java.util.Collection;
import java.util.Map;


/**
 * The Interface RepositoryWorkflowTarget.
 */
public class AgilePLMAPIRepositoryECOTarget extends AgilePLMAPIRepositoryBase 
                      implements RepositoryECOTarget{

  public void addAffectedItem(QxContext runningCtx, String ecoNum, Metadata item, Authentication id) throws Exception {}

  public void addAffectedItem(QxContext runningCtx, String ecoNum, String itemNum, Authentication id) throws Exception {}

  public void addAffectedItems(QxContext runningCtx, String ecoNum, Collection itemNumbers, Authentication id) throws Exception {}

  public String createECO(QxContext runningCtx, String agileClass, Authentication id) throws Exception {
    return null;
  }

  public void update(QxContext runningCtx, String ecoNum, Metadata data, Authentication id) throws Exception {}

  public String createECO(QxContext runningCtx, Map ecoAtts, Authentication id) throws Exception {
    return null;
  }

  public void setBOMAttribute(QxContext runningCtx, String ecoNum, String itemNum, String subcompNum, String att, String val, Authentication id) throws Exception {}

  public void setECOAttribute(QxContext runningCtx, String ecoNum, String att, String val, Authentication id) throws Exception {}

  
}

