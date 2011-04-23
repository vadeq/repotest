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
import zws.util.RemotePackage;

import java.io.*;


/**
 * The Interface RepositoryBinaryTarget.
 */
public interface RepositoryBinaryTarget extends Repository  {

  //Files
  /**
   * Add.
   *
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   * @return the origin
   * @throws Exception the exception
   */
   Origin add(QxContext runningCtx, File file, Authentication id) throws Exception;

  /**
   * Update.
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   * @return the origin
   * @throws Exception the exception
   */
  Origin update(QxContext runningCtx, File file, Authentication id) throws Exception;

  /**
   * Store.
   *
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin store(QxContext runningCtx, File file, Authentication id) throws Exception;

  /**
   * Adds the attachment.
   *
   * @param o the o
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
  void addAttachment(QxContext runningCtx, Origin o, File file, Authentication id) throws Exception;

  /**
   * Update attachment.
   *
   * @param o the o
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
  void updateAttachment(QxContext runningCtx, Origin o, File file, Authentication id) throws Exception;

  /**
   * Store attachment.
   *
   * @param o the o
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
  void storeAttachment(QxContext runningCtx, String itemName, RemotePackage remoteFile, Authentication id) throws Exception;
  
  /**
   * Store attachment.
   *
   * @param o the o
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
  void storeAttachmentForECOItem(QxContext runningCtx, String itemName, String ecoNumber, RemotePackage remoteFile, Authentication id) throws Exception;

  //InputStreams
  /**
   * Add.
   *
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin add(QxContext runningCtx, InputStream iStream, Authentication id) throws Exception;

  /**
   * Update.
   *
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin update(QxContext runningCtx, InputStream iStream, Authentication id) throws Exception;

  /**
   * Store.
   *
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin store(QxContext runningCtx, InputStream iStream, Authentication id) throws Exception;

  /**
   * Adds the attachment.
   *
   * @param o the o
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
  void addAttachment(QxContext runningCtx, Origin o, InputStream  iStream, Authentication id) throws Exception;

  /**
   * Update attachment.
   *
   * @param o the o
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
  void updateAttachment(QxContext runningCtx, Origin o, InputStream  iStream, Authentication id) throws Exception;

  /**
   * Store attachment.
   *
   * @param o the o
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
  void storeAttachment(QxContext runningCtx, Origin o, InputStream  iStream, Authentication id) throws Exception;
}
