package zws.repository.agile.plm.api;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.data.eco.ECO;
import zws.qx.QxContext;
import zws.repository.source.RepositoryECOSource;
import zws.security.Authentication;


import java.util.Collection;


/**
 * The Interface RepositoryWorkflowTarget.
 */
public class AgilePLMAPIRepositoryECOSource extends AgilePLMAPIRepositoryBase 
                      implements RepositoryECOSource{

  public ECO findECO(QxContext runningCtx, String ecoNumber, Authentication id) throws Exception {
    return null;
  }
  public Collection findPendingECOs(QxContext runningCtx, String number, Authentication id) throws Exception {
    return null;
  }
}

