package zws.repository.agile.sdk;

/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */

import zws.data.Metadata;
import zws.exception.UnsupportedOperation;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.agile.sdk.svc.AgileSDKSvcBase;
import zws.repository.target.RepositoryMetadataTarget;
import zws.security.Authentication;

import java.io.File;


/**
 * The Class AgileSDKRepositoryMetadataTarget.
 *
 * @author ptoleti
 */
public class AgileSDKRepositoryMetadataTarget extends AgileSDKRepositoryBase
    implements RepositoryMetadataTarget {


  /**
   * The Constructor.
   * @param parent the parent
   * @param sdkSvc the sdkSvc
   */
  protected AgileSDKRepositoryMetadataTarget(QxContext parent, AgileSDKSvcBase sdkSvc) {
    configureParentContext(parent);
    configureSDKBase(sdkSvc);
  }


  /**
   * Update.
   *
   * @param id Authentication
   * @param file binaryFile
   * @param runningCtx runningCtx
   * @param data metadata
   * @return metadata metadata
   * @throws Exception exception
   * @see zws.repository.target.RepositoryMetadataTarget#update(zws.qx.QxContext,
   * zws.data.Metadata, java.io.File, zws.security.Authentication)
   */
  public Metadata update(QxContext runningCtx, Metadata data, File file, Authentication id) throws Exception {
    Metadata metadata = getItemSvc().createItem(data, id);
    return metadata;
  }

  /**
   * Create.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param data metadata
   * @return origin origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#create(zws.qx.QxContext,
   * zws.data.Metadata, zws.security.Authentication)
   */
  public Origin create(QxContext runningCtx, Metadata data, Authentication id) throws Exception {
    return getItemSvc().createItem(data, id).getOrigin();
  }

  /**
   * Move.
   *
   * @param id Authentication
   * @param newFolderPath newFolderPath
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @return metadata metadata
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#move(zws.qx.QxContext,
   * zws.origin.Origin, java.lang.String, zws.security.Authentication)
   */
  public Origin move(QxContext runningCtx, Origin o, String newFolderPath, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "move(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Rename.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   * @param newName newName
   *
   * @return metadata metadata
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#rename(zws.qx.QxContext,
   * zws.origin.Origin, java.lang.String, zws.security.Authentication)
   */
  public Origin rename(QxContext runningCtx, Origin o, String newName, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "rename(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Renumber.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   * @param newNumber newNumber
   *
   * @return metadata metadata
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#renumber(zws.qx.QxContext,
   * zws.origin.Origin, java.lang.String, zws.security.Authentication)
   */
  public Origin renumber(QxContext runningCtx, Origin o, String newNumber, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "renumber(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Delete minor version.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#deleteMinorVersion(zws.qx.QxContext,
   * zws.origin.Origin, zws.security.Authentication)
   */
  public void deleteMinorVersion(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "deleteMinorVersion(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Delete entire history.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param name name
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#deleteEntireHistory(zws.qx.QxContext,
   * java.lang.String, zws.security.Authentication)
   */
  public void deleteEntireHistory(QxContext runningCtx, String name, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "deleteEntireHistory(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Link.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param source origin
   * @param target origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#link(zws.qx.QxContext,
   * zws.origin.Origin, zws.origin.Origin, zws.security.Authentication)
   */
  public void link(QxContext runningCtx, Origin source, Origin target, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "link(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Unlink.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param source origin
   * @param target origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#unlink(zws.qx.QxContext,
   * zws.origin.Origin, zws.origin.Origin, zws.security.Authentication)
   */
  public void unlink(QxContext runningCtx, Origin source, Origin target, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "unlink(Origin o, String newFolderPath, Authentication id)");
  }


  /* (non-Javadoc)
   * @see zws.repository.target.RepositoryMetadataTarget#create(zws.qx.QxContext, zws.data.Metadata, java.io.File, zws.security.Authentication)
   */
  public Origin create(QxContext runningCtx, Metadata data, File file, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

}
