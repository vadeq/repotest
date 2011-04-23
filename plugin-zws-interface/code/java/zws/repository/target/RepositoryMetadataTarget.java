package zws.repository.target;
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

import java.io.File;


/**
 * The Interface RepositoryMetadataTarget.
 */
public interface RepositoryMetadataTarget extends Repository {


  /**
   * Create.
   *
   * @param data the data
   * @param runningCtx the running ctx
   * @param id the id
   * @param file file
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin create(QxContext runningCtx, Metadata data, File file, Authentication id) throws Exception;


  /**
   * Create.
   *
   * @param data the data
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin create(QxContext runningCtx, Metadata data, Authentication id) throws Exception;

  /**
   * Update.
   *
   * @param data the data
   * @param binary the binary
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  Metadata update(QxContext runningCtx, Metadata data, File binary, Authentication id) throws Exception;

  /**
   * Move.
   *
   * @param o the o
   * @param newFolderPath the new folder path
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin move(QxContext runningCtx, Origin o, String newFolderPath, Authentication id) throws Exception;

  /**
   * Rename.
   *
   * @param o the o
   * @param newName the new name
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin rename(QxContext runningCtx, Origin o, String newName, Authentication id) throws Exception;

  /**
   * Renumber.
   *
   * @param o the o
   * @param newNumber the new number
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin renumber(QxContext runningCtx, Origin o, String newNumber, Authentication id) throws Exception;

  /**
   * Delete minor version.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
  void deleteMinorVersion(QxContext runningCtx, Origin o, Authentication id) throws Exception;

  /**
   * Delete entire history.
   *
   * @param runningCtx the running ctx
   * @param name the name
   * @param id the id
   *
   * @throws Exception the exception
   */
  void deleteEntireHistory(QxContext runningCtx, String name, Authentication id) throws Exception;

  /**
   * Link.
   *
   * @param target the target
   * @param runningCtx the running ctx
   * @param source the source
   * @param id the id
   *
   * @throws Exception the exception
   */
  void link(QxContext runningCtx, Origin source, Origin target, Authentication id) throws Exception;

  /**
   * Unlink.
   *
   * @param target the target
   * @param runningCtx the running ctx
   * @param source the source
   * @param id the id
   *
   * @throws Exception the exception
   */
  void unlink(QxContext runningCtx, Origin source, Origin target, Authentication id) throws Exception;

}
