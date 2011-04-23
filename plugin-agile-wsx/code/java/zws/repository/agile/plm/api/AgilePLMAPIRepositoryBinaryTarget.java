package zws.repository.agile.plm.api;



import zws.qx.QxContext;
import zws.repository.target.RepositoryBinaryTarget;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.util.RemotePackage;

import java.io.File;
import java.io.InputStream;

/**
 * The Class AgileSDKRepositoryBinaryTarget.
 */
public class AgilePLMAPIRepositoryBinaryTarget extends AgilePLMAPIRepositoryBase
    implements RepositoryBinaryTarget {


  /**
   * The Constructor.
   */
  public AgilePLMAPIRepositoryBinaryTarget() {

  }

  /**
   * The Constructor.
   *
   * @param runningCtx the running ctx
   * @param parent the parent
   */
  public AgilePLMAPIRepositoryBinaryTarget(QxContext runningCtx, QxContext parent) {
    configureParentContext(parent);
  }

  public Origin add(QxContext runningCtx, File file, Authentication id) throws Exception {
    return null;
  }

  public Origin add(QxContext runningCtx, InputStream stream, Authentication id) throws Exception {
    return null;
  }

  public void addAttachment(QxContext runningCtx, Origin o, File file, Authentication id) throws Exception {}

  public void addAttachment(QxContext runningCtx, Origin o, InputStream stream, Authentication id) throws Exception {}

  public Origin store(QxContext runningCtx, File file, Authentication id) throws Exception {
    return null;
  }

  public Origin store(QxContext runningCtx, InputStream stream, Authentication id) throws Exception {
    return null;
  }

  public void storeAttachment(QxContext runningCtx, String itemName, RemotePackage remoteFile, Authentication id) throws Exception {



  }

  public void storeAttachment(QxContext runningCtx, Origin o, InputStream stream, Authentication id) throws Exception {}

  public void storeAttachmentForECOItem(QxContext runningCtx, String itemName, String ecoNumber, RemotePackage remoteFile, Authentication id) throws Exception {}

  public Origin update(QxContext runningCtx, File file, Authentication id) throws Exception {
    return null;
  }

  public Origin update(QxContext runningCtx, InputStream stream, Authentication id) throws Exception {
    return null;
  }

  public void updateAttachment(QxContext runningCtx, Origin o, File file, Authentication id) throws Exception {}

  public void updateAttachment(QxContext runningCtx, Origin o, InputStream stream, Authentication id) throws Exception {}

}
