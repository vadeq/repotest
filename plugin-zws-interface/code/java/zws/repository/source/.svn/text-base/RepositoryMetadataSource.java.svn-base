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

import java.util.Collection;


/**
 * The Interface RepositoryMetadataSource.
 */
public interface RepositoryMetadataSource extends Repository {

  /**
   * Contains.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return true, if contains
   */
  boolean contains(QxContext runningCtx, Origin o, Authentication id);

  /**
   * Find.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Metadata find(QxContext runningCtx, Origin o, Authentication id) throws Exception;

  /**
   * Find latest.
   *
   * @param runningCtx the running ctx
   * @param name the name
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Metadata findLatest(QxContext runningCtx, String name, Authentication id) throws Exception;
  //public Metadata reportBill(Origin origin, Authentication id)  throws Exception;
  //public Origin findOrigin(String uid, Authentication id) throws Exception;
  //public Collection reportAllNames(Authentication id) throws Exception;
  //public Collection reportLatest(String path, Authentication id) throws PathDoesNotExist, Exception;
  Collection searchLatest(QxContext runtimeCtx, String name, Authentication id) throws Exception;
}
