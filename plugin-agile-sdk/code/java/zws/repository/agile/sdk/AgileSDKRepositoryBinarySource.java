package zws.repository.agile.sdk;
/*
 * DesignState - Design Compression Technology
 * @author: Arbind Thakur
 * @version: 1.0
 * Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */

import zws.exception.UnsupportedOperation;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryBinarySource;
import zws.security.Authentication;
import zws.util.RemotePackage;

import java.io.File;
import java.util.Collection;

public class AgileSDKRepositoryBinarySource extends AgileSDKRepositoryBase
    implements RepositoryBinarySource {

  /**
   * The Constructor.
   *
   * @param parent the parent
   */
  protected AgileSDKRepositoryBinarySource(QxContext parent) { configureParentContext(parent); }

  public RemotePackage fetchDesignFiles(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    return null;
  }

  public RemotePackage fetchNativeFile(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    return null;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryBinarySource#fetchDesignFilesLocally(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection fetchDesignFilesLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    throw new UnsupportedOperation("fetchDesignFilesLocally(QxContext runningCtx, Origin origin, Authentication id)");
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryBinarySource#fetchNativeFileLocally(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public File fetchNativeFileLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    throw new UnsupportedOperation("fetchNativeFileLocally(QxContext runningCtx, Origin origin, Authentication id)");
  }



}
