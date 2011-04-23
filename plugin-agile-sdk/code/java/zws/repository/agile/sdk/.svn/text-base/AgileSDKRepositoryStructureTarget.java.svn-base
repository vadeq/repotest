/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.agile.sdk;

import zws.bill.intralink.BillOfMaterials;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.repository.agile.sdk.svc.AgileSDKSvcBase;
import zws.repository.target.RepositoryStructureTarget;


/**
 * The Class Ilink3RepositoryMetadataSource.
 */
public class AgileSDKRepositoryStructureTarget extends AgileSDKRepositoryBase
    implements RepositoryStructureTarget {

  /**
   * The Constructor.
   */
  public AgileSDKRepositoryStructureTarget() {
  }

  /**
   * The Constructor.
   * @param sdkBase sdkBase
   * @param parent parentContext
   */
  public AgileSDKRepositoryStructureTarget(QxContext parent, AgileSDKSvcBase sdkBase) {
    configureParentContext(parent);
    configureSDKBase(sdkBase);
  }

  /**
   * contains.
   *
   * @param id authentication
   * @param runningCtx the running ctx
   * @param origin origin
   *
   * @return boolean
   *
   * @see zws.repository.source.RepositoryMetadataSource#contains(zws.origin.Origin,
   * zws.security.Authentication)
   */
  public final boolean contains(QxContext runningCtx, Origin origin, Authentication id) {
    return false;
  }

  /**
   * createBill.
   *
   * @param id authentication
   * @param runningCtx the running ctx
   * @param bill bill
   *
   * @return Metadata result data
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#findLatest(java.lang.String,
   * zws.security.Authentication)
   */

  public final void structureBill(QxContext runningCtx, BillOfMaterials bill, Authentication id) throws Exception {
   getBomSvc().create(bill.getMetadata(), id);
   getBomSvc().storeBill(bill, id);
  }

  public void createAndStructureBill(QxContext runningCtx, BillOfMaterials bill, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return ;
  }
}
