package zws.repository.agile.plm.api;


import zws.exception.UnsupportedOperation;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryBinarySource;
import zws.security.Authentication;
import zws.util.RemotePackage;

import java.io.File;
import java.util.Collection;


/**
 * The Class AgileSDKRepositoryBinarySource.
 */
public class AgilePLMAPIRepositoryBinarySource extends AgilePLMAPIRepositoryBase
    implements RepositoryBinarySource {

  /**
   * The Constructor.
   *
   * @param parent the parent
   */
  protected AgilePLMAPIRepositoryBinarySource(QxContext parent) {
    configureParentContext(parent);
  }

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
