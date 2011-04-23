package zws.repository.source;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.data.eco.ECO;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;


import java.util.Collection;


/**
 * The Interface RepositoryWorkflowTarget.
 */
public interface RepositoryECOSource extends Repository {

  ECO findECO(QxContext runningCtx, String ecoNumber, Authentication id) throws Exception;
  Collection findPendingECOs(QxContext runningCtx, String number, Authentication id) throws Exception;
}

