/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.agile.sdk;


import zws.data.Metadata;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;

import zws.qx.QxContext;
import zws.repository.agile.sdk.svc.AgileSDKSvcBase;
import zws.repository.source.RepositoryECOSource;
import zws.security.Authentication;

import java.util.Collection;


/**
 * The Class Ilink3RepositoryMetadataSource.
 */
public class AgileSDKRepositoryECOSource extends AgileSDKRepositoryBase
    implements RepositoryECOSource {


  protected AgileSDKRepositoryECOSource(QxContext parent, AgileSDKSvcBase sdkSvc) {
    configureParentContext(parent);
    configureSDKBase(sdkSvc);
  }

  public ECO findECO(QxContext runningCtx, String ecoNumber, Authentication id) throws Exception {
    ECO eco = getEcoSvc().findECO(ecoNumber, id);
    return eco;
  }

  public Collection findPendingECOs(QxContext runningCtx, String itemNumber, Authentication id) throws Exception {
    Collection ecoSet = null;
    Metadata data = getItemSvc().find(itemNumber, id);
    if(null != data) {
      AffectedItem aItem = new AffectedItem();
      aItem.setName(data.getName());
      aItem.setItemNumber(data.getName());
      ecoSet = getEcoSvc().detectPendingChanges(aItem, id);
    } 
    return ecoSet;
  }
 }
