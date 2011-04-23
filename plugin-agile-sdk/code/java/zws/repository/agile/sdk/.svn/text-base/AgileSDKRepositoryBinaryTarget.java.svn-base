package zws.repository.agile.sdk;
/*
 * DesignState - Design Compression Technology
 * @author: Arbind Thakur @version: 1.0 Copywrite
 * (c) 2003 Zero Wait-State Inc. All rights reserved
 */


import zws.qx.QxContext;
import zws.repository.agile.sdk.svc.AgileSDKSvcBase;
import zws.repository.target.RepositoryBinaryTarget;
import zws.exception.UnsupportedOperation;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.util.RemotePackage;

import java.io.File;
import java.io.InputStream;

/**
 * The Class AgileSDKRepositoryBinaryTarget.
 */
public class AgileSDKRepositoryBinaryTarget extends AgileSDKRepositoryBase
    implements RepositoryBinaryTarget {


  /**
   * The Constructor.
   */
  public AgileSDKRepositoryBinaryTarget() {

  }

  /**
   * The Constructor.
   *
   * @param runningCtx the running ctx
   * @param parent the parent
   */
  public AgileSDKRepositoryBinaryTarget(QxContext runningCtx, QxContext parent) {
    configureParentContext(parent);
  }

  /**
   * The Constructor.
   * @param parent the parent
   * @param sdkSvc the sdkSvc   *
   */
  protected AgileSDKRepositoryBinaryTarget(QxContext parent, AgileSDKSvcBase sdkSvc) {
    configureParentContext(parent);
    configureSDKBase(sdkSvc);
  }
  

  /**
   * Update attachment.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryBinaryTarget#updateAttachment(zws.qx.QxContext, zws.origin.Origin, java.io.File, zws.security.Authentication)
   */
  public void updateAttachment(QxContext runningCtx, Origin o, File binary, Authentication id) throws Exception {
    throw new UnsupportedOperation("updateAttachment(QxContext runningCtx, Origin o, File binary, Authentication id)");
  }

  /**
   * Add.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   *
   * @return origin origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryBinaryTarget#add(zws.qx.QxContext, java.io.File, zws.security.Authentication)
   */
  public Origin add(QxContext runningCtx, File binary, Authentication id) throws Exception {
    throw new UnsupportedOperation("add(QxContext runningCtx, File binary, Authentication id)");
  }

  /**
   * Update.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   *
   * @return origin origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryBinaryTarget#update(zws.qx.QxContext, java.io.File, zws.security.Authentication)
   */
  public Origin update(QxContext runningCtx, File binary, Authentication id)
      throws Exception {
    throw new UnsupportedOperation("create(Metadata data, Authentication id)");
  }

  /**
   * Store.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   *
   * @return origin origin   *
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryBinaryTarget#store(zws.qx.QxContext, java.io.File, zws.security.Authentication)
   */
  public Origin store(QxContext runningCtx, File binary, Authentication id)
      throws Exception {
    throw new UnsupportedOperation("store(QxContext runningCtx, File binary, Authentication id)");
  }

  /**
   * Adds the attachment.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryBinaryTarget#addAttachment(zws.qx.QxContext, zws.origin.Origin, java.io.File, zws.security.Authentication)
   */
  public void addAttachment(QxContext runningCtx, Origin o, File binary, Authentication id) throws Exception {
    throw new UnsupportedOperation("create(Metadata data, Authentication id)");
  }

  /**
   * Store attachment.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @throws Exception exception
   *
   */
  public void storeAttachment(QxContext runningCtx, String itemName, RemotePackage remotePackage, Authentication id) throws Exception {
    getItemSvc().attachFileToItem(runningCtx, itemName, remotePackage, id);
  }
  
  public void storeAttachmentForECOItem(QxContext runningCtx, String itemName, String ecoNumber, RemotePackage remotePackage, Authentication id) throws Exception {
    getItemSvc().attachFileToECOItem(runningCtx, itemName, ecoNumber, remotePackage, id);
  }

  /**
   * Add.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   *
   * @return origin origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryBinaryTarget#add(zws.qx.QxContext, java.io.InputStream, zws.security.Authentication)
   */
  public Origin add(QxContext runningCtx, InputStream binary, Authentication id) throws Exception {
    throw new UnsupportedOperation("create(Metadata data, Authentication id)");
  }

  /**
   * Update.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   *
   * @return origin origin
   *
   * @throws Exception exception   * @see zws.repository.target.RepositoryBinaryTarget#update(zws.qx.QxContext, java.io.InputStream, zws.security.Authentication)
   */
  public Origin update(QxContext runningCtx, InputStream binary, Authentication id) throws Exception {
    throw new UnsupportedOperation("create(Metadata data, Authentication id)");
  }

  /**
   * Store.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   *
   * @return origin origin
   *
   * @throws Exception exception
   * * @see zws.repository.target.RepositoryBinaryTarget#store(zws.qx.QxContext, java.io.InputStream, zws.security.Authentication)
   */
  public Origin store(QxContext runningCtx, InputStream binary, Authentication id) throws Exception {
    throw new UnsupportedOperation("create(Metadata data, Authentication id)");
  }

  /**
   * Adds the attachment.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryBinaryTarget#addAttachment(zws.qx.QxContext, zws.origin.Origin, java.io.InputStream, zws.security.Authentication)
   */
  public void addAttachment(QxContext runningCtx, Origin o, InputStream binary, Authentication id) throws Exception {
    throw new UnsupportedOperation("create(Metadata data, Authentication id)");
  }

  /**
   * Store attachment.
   *
   * @param id the id
   * @param binary binaryFile
   * * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryBinaryTarget#storeAttachment(zws.qx.QxContext, zws.origin.Origin, java.io.InputStream, zws.security.Authentication)
   */
  public void storeAttachment(QxContext runningCtx, Origin o, InputStream binary, Authentication id) throws Exception {
    throw new UnsupportedOperation("create(Metadata data, Authentication id)");
  }

  /**
   * Update attachment.
   *
   * @param id Authentication
   * @param binary binaryFile
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryBinaryTarget#updateAttachment(zws.qx.QxContext, zws.origin.Origin, java.io.InputStream, zws.security.Authentication)
   */
  public void updateAttachment(QxContext runningCtx, Origin o, InputStream binary, Authentication id) throws Exception {
    throw new UnsupportedOperation("create(Metadata data, Authentication id)");
  }
  
  /***** ATTACHMENT TEST SUPPORT - EKA 12/18/07 *****/
    public void TestStoreAttachment(QxContext runningCtx, String itemName, File attachment, Authentication id) throws Exception {
      getItemSvc().TestAttachFileToItem(runningCtx, itemName, attachment, id);
    }
  /***** END ATTACHMENT TEST SUPPORT - EKA 12/18/07 *****/
}
