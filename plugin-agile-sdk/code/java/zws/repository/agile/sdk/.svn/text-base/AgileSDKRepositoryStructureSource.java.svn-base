/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.agile.sdk;


import zws.data.Metadata;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.repository.agile.sdk.svc.AgileSDKSvcBase;
import zws.repository.source.RepositoryStructureSource;

import java.util.Collection;


public class AgileSDKRepositoryStructureSource extends AgileSDKRepositoryBase
    implements RepositoryStructureSource {

  public AgileSDKRepositoryStructureSource() {}

  public AgileSDKRepositoryStructureSource(QxContext parent, AgileSDKSvcBase sdkBase) {
    configureParentContext(parent);
    configureSDKBase(sdkBase);
  }

  public Metadata reportBOM(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    Metadata bom = null;
    try {
      bom = getBomSvc().retrieveBOM(origin.getName(), id);
    } catch (Exception exp) {
      exp.printStackTrace();
      throw exp;
    }
    return bom;
  }

  public Collection reportDependencies(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    return null;
  }

  public Collection reportFirstLevelDependencies(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    Collection items = null;
    try {
      items = getBomSvc().listFirstLevelItems(origin.getName(), id);
    } catch (Exception exp) {
      exp.printStackTrace();
      throw exp;
    }
    return items;
  }

  public Collection reportFirstLevelLatestDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }

  public Metadata reportLatestBOM(QxContext runningCtx, String name, Authentication id) throws Exception {
    return null;
  }

  public Collection reportLatestDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }

  public Metadata reportWhereUsed(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }

  public Metadata reportBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }

  public Metadata reportLatestBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }  
  

}
