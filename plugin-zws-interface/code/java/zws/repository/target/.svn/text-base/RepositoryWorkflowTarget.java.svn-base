package zws.repository.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.data.Metadata;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;

import java.io.File;


/**
 * The Interface RepositoryWorkflowTarget.
 */
public interface RepositoryWorkflowTarget extends Repository {

  //create eco
  //add affected item: origin, eco
  /**
   * Update.
   *
   * @param data the data
   * @param eco the eco
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin update(QxContext runningCtx, Metadata data, zws.data.eco.ECO eco, Authentication id) throws Exception;
  Origin update(QxContext runningCtx, Metadata data, File file, Authentication id) throws Exception;
}
