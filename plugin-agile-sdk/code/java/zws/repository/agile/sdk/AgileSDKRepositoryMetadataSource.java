package zws.repository.agile.sdk;
/*
 * DesignState - Design Compression Technology
 * @author: Arbind Thakur
 * @version: 1.0
 * Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */

import zws.data.Metadata;

import zws.repository.agile.sdk.svc.AgileSDKSvcBase;
import zws.repository.source.RepositoryMetadataSource;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.qx.QxContext;

import java.util.Collection;


/**
 * The Class AgileSDKRepositoryMetadataSource.
 */
public class AgileSDKRepositoryMetadataSource extends AgileSDKRepositoryBase
    implements RepositoryMetadataSource {

  /**
   * The Constructor.
   * @param parent the parent
   * @param sdkSvc the sdkSvc   *
   */
  protected AgileSDKRepositoryMetadataSource(QxContext parent, AgileSDKSvcBase sdkSvc) {
    configureParentContext(parent);
    configureSDKBase(sdkSvc);
  }

  /**
   * Contains.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @return boolean contains
   *
   * @see zws.repository.source.RepositoryMetadataSource#contains(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public boolean contains(QxContext runningCtx, Origin o, Authentication id) {
    return false;
  }

  /**
   * Find.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @return boolean contains
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#find(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Metadata find(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    Metadata data = null;
    data = getItemSvc().find(o.getName(), id);
    return data;
  }

  /**
   * Find latest.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param name name
   *
   * @return boolean contains
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#findLatest(zws.qx.QxContext, java.lang.String, zws.security.Authentication)
   */
  public Metadata findLatest(QxContext runningCtx, String name, Authentication id) throws Exception {
    Metadata data = null;
    data = getItemSvc().find(name, id);
    return data;
  }

  /**
   * Search latest.
   *
   * @param id Authentication
   * @param name name
   * @param runtimeCtx runtimeCtx
   *
   * @return boolean contains
   *
   * @throws Exception exception
   *
   * @see zws.repository.source.RepositoryMetadataSource#searchLatest(zws.qx.QxContext, java.lang.String, zws.security.Authentication)
   */
  public Collection searchLatest(QxContext runtimeCtx, String name, Authentication id) throws Exception {
    Collection items = null;
    try {
      items = getItemSvc().findItems(name, id);
    } catch (Exception exp) {
      exp.printStackTrace();
      throw exp;
    }
    return items;
  }

  // public Origin findOrigin(String uid, Authentication id) throws Exception;
  // public Collection reportAllNames(Authentication id) throws Exception;
  // public Collection reportLatest(String path, Authentication id) throws
  // PathDoesNotExist, Exception;
}
