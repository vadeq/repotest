package zws.repository.source;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;



/**
 * The Interface RepositoryTemplateSource.
 */
public interface RepositoryTemplateSource extends Repository {

  /**
   * Report template design.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Metadata reportTemplateDesign(QxContext runningCtx, Origin o, Authentication id) throws Exception;
}
