package zws.repository.teamcenter.proxy.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.origin.TC10Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.util.RemotePackage;
import zws.repository.target.RepositoryBinaryTarget;
import zws.repository.teamcenter.proxy.TC10ProxyRepositoryBase;

import java.io.*;


/**
 * The  TC10RepositoryBinaryTarget.
 */
public class TC10ProxyRepositoryBinaryTarget extends TC10ProxyRepositoryBase
implements RepositoryBinaryTarget  {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public TC10ProxyRepositoryBinaryTarget(QxContext parent) 
  {
    configureParentContext(parent);
  }
  
  //Files
  /**
   * Add.
   *
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   * @return the origin
   * @throws Exception the exception
   */
   public Origin add(QxContext runningCtx, File file, Authentication id) throws Exception
   {
     TC10Origin o = new TC10Origin();
     return o;
   }

  /**
   * Update.
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   * @return the origin
   * @throws Exception the exception
   */
   public Origin update(QxContext runningCtx, File file, Authentication id)
   {
     TC10Origin o = new TC10Origin();
     return o;
   }

  /**
   * Store.
   *
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
   public Origin store(QxContext runningCtx, File file, Authentication id)
   {
     TC10Origin o = new TC10Origin();
     return o;
   }

  

 

  //InputStreams
  /**
   * Add.
   *
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
   public Origin add(QxContext runningCtx, InputStream iStream, Authentication id)
   {TC10Origin o = new TC10Origin();
   return o;}

  /**
   * Update.
   *
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
   public Origin update(QxContext runningCtx, InputStream iStream, Authentication id)
   {TC10Origin o = new TC10Origin();
   return o;}

  /**
   * Store.
   *
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
   public Origin store(QxContext runningCtx, InputStream iStream, Authentication id)
   {TC10Origin o = new TC10Origin();
   return o;}

  public void addAttachment(QxContext runningCtx, Origin o, File file, Authentication id) throws Exception {}

  public void addAttachment(QxContext runningCtx, Origin o, InputStream stream, Authentication id) throws Exception {}

  public void storeAttachment(QxContext runningCtx, String itemName, RemotePackage remotePackage, Authentication id) throws Exception {}

  public void storeAttachment(QxContext runningCtx, Origin o, InputStream stream, Authentication id) throws Exception {}

  public void storeAttachmentForECOItem(QxContext runningCtx, String itemName, String ecoNumber, RemotePackage remotePackage, Authentication id) throws Exception {}

  public void updateAttachment(QxContext runningCtx, Origin o, File file, Authentication id) throws Exception {}

  public void updateAttachment(QxContext runningCtx, Origin o, InputStream stream, Authentication id) throws Exception {}

  
   
  
}
