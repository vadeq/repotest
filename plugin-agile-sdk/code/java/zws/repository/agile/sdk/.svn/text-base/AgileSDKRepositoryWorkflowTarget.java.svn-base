/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.agile.sdk;

import zws.data.Metadata;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.repository.agile.sdk.svc.AgileSDKSvcBase;
import zws.repository.target.RepositoryWorkflowTarget;
import java.io.File;





/**
 * The Class Ilink3RepositoryMetadataSource.
 */
public class AgileSDKRepositoryWorkflowTarget extends AgileSDKRepositoryBase
    implements RepositoryWorkflowTarget {

  /**
   * The Constructor.
   */
  public AgileSDKRepositoryWorkflowTarget() {
  }

  /**
   * The Constructor.
   * @param sdkBase sdkBase
   * @param parent parentContext
   */
  public AgileSDKRepositoryWorkflowTarget(QxContext parent, AgileSDKSvcBase sdkBase) {
    configureParentContext(parent);
    configureSDKBase(sdkBase);
  }

  /** update ECO.
   * @param runningCtx context
   * @param data  Metadata
   * @param eco AgileECO
   * @param id Authentication
   * @return origin origin
   * @throws Exception exception
   * @see zws.repository.target.RepositoryWorkflowTarget#update(zws.qx.QxContext, zws.data.Metadata, zws.data.eco.ECO, zws.security.Authentication)
   */
  public Origin update(QxContext runningCtx, Metadata data, ECO eco, Authentication id) throws Exception {
    // create ECO
    // add afected Items
    this.getAffectedItemSvc().update(data, id);
    return null;
  }

  /** update ECO.
   * @param runningCtx context
   * @param data  Metadata
   * @param file File
   * @param id Authentication
   * @return origin origin
   * @throws Exception exception
   */
  public Origin update(QxContext runningCtx, Metadata data, File file, Authentication id) throws Exception {
/*  1) find new items x
    2) create new items x
    3) find updated items
    4) create eco
    5) mark new and updated items as affected items on eco
    6) publish new and updated items (updates attributes)
    7) remove latest sync for updated items.
    8) synchronize updated items with cad part,doc and part number
    9) redline structure on the eco*/
    if (null == getItemSvc().find(data.get("number"), id)) {
      getItemSvc().createItem(data, id);
    }
    ECO eco = getEcoSvc().createECO(id);
    AffectedItem aItem = new AffectedItem();
    aItem.setName(data.getName());
    aItem.setItemNumber(data.getName());
    eco.add(aItem);
    getEcoSvc().addChange(eco.getNumber(), aItem, id);
    getAffectedItemSvc().update(data, id);
    return null;
  }
}
