package zws.repository.agile.plm.api;


/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */

import zws.data.Metadata;
import zws.exception.UnsupportedOperation;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.target.RepositoryMetadataTarget;
import zws.security.Authentication;

import java.io.File;
import java.util.Map;
import com.agile.sdo.cif.AgileWSXItemSvc;



/**
 * The Class AgileSDKRepositoryMetadataTarget.
 *
 * @author ptoleti
 */
public class AgilePLMAPIRepositoryMetadataTarget extends AgilePLMAPIRepositoryBase
    implements RepositoryMetadataTarget {



  /**
   * The Constructor.
   * @param parent the parent
   * @param sdkSvc the sdkSvc
   */
  protected AgilePLMAPIRepositoryMetadataTarget(QxContext parent) {
    configureParentContext(parent);
  }


  /**
   * Create.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param data metadata
   * @return origin origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#create(zws.qx.QxContext,
   * zws.data.Metadata, zws.security.Authentication)
   */
  public Origin create(QxContext runningCtx, Metadata data, Authentication id) throws Exception {
    return update(runningCtx,data,new File("c:/test.txt"),id).getOrigin();
  }

  /* (non-Javadoc)
   * @see zws.repository.target.RepositoryMetadataTarget#create(zws.qx.QxContext, zws.data.Metadata, java.io.File, zws.security.Authentication)
   */
  public Origin create(QxContext runningCtx, Metadata data, File file, Authentication id) throws Exception {
    return update(runningCtx,data,file,id).getOrigin();
  }


  /**
   * Update.
   *
   * @param id Authentication
   * @param file binaryFile
   * @param runningCtx runningCtx
   * @param data metadata
   * @return metadata metadata
   * @throws Exception exception
   * @see zws.repository.target.RepositoryMetadataTarget#update(zws.qx.QxContext,
   * zws.data.Metadata, java.io.File, zws.security.Authentication)
   */
  public Metadata update(QxContext runningCtx, Metadata data, File file, Authentication id) throws Exception {
    Map optionsMap = null;
    String url=getProtocol() + "://" + getHostName() + "/" + getServicesPath();
    {} //System.out.println("URL is ---> " + url);
    //String url="http://pwebdev.cisco.com/pls/services";
    // check in projectMapping_zwstc.xml in Agile
    //runningCtx.set(AgilePLMAPIConstants.CREATED_BY_TOOL,AgilePLMAPIConstants.TC10_TOOL);
    //runningCtx.set(AgilePLMAPIConstants.CREATED_FOR_PROJECT,AgilePLMAPIConstants.TC10_PROJECT_NAME);
    boolean mappedflag = false;
    boolean dirtyflag = true;
    boolean asyncFlag = true;
    String source = null;
    if(null != runningCtx.get(AgilePLMAPIConstants.DIRTY_FLAG)) {
      dirtyflag = new Boolean(runningCtx.get(AgilePLMAPIConstants.DIRTY_FLAG)).booleanValue();
    }
    if(null != runningCtx.get(AgilePLMAPIConstants.MAPPED_FLAG)) {
      mappedflag = new Boolean(runningCtx.get(AgilePLMAPIConstants.MAPPED_FLAG)).booleanValue();
    }
    if(null != runningCtx.get(AgilePLMAPIConstants.ASYNC_FLAG)) {
      asyncFlag = new Boolean(runningCtx.get(AgilePLMAPIConstants.ASYNC_FLAG)).booleanValue();
    }
    if(null != runningCtx.get(AgilePLMAPIConstants.AGILE_WSX_SOURCE)) {
      source = runningCtx.get(AgilePLMAPIConstants.AGILE_WSX_SOURCE);
    } else {
      source = AgilePLMAPIConstants.DEFAULT_AGILE_WSX_SOURCE;
    }
    AgileWSXItemSvc itemSvc = new AgileWSXItemSvc(url,id, this, runningCtx);
    /*if(null == data.get("attachment")) {
      File f = new File("C:\\test.txt");
      data.set("attachment", f.getAbsolutePath());
    }*/
    if(null == data.get("attachment")) {
      throw new Exception("Attachment was not set for " + data.getName());
    }
    Metadata item = itemSvc.createAgileObject(data,source,optionsMap,mappedflag,dirtyflag,asyncFlag, runningCtx);
    {} //System.out.println(item);
    return item;
  }

  /**
   * Move.
   *
   * @param id Authentication
   * @param newFolderPath newFolderPath
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @return metadata metadata
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#move(zws.qx.QxContext,
   * zws.origin.Origin, java.lang.String, zws.security.Authentication)
   */
  public Origin move(QxContext runningCtx, Origin o, String newFolderPath, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "move(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Rename.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   * @param newName newName
   *
   * @return metadata metadata
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#rename(zws.qx.QxContext,
   * zws.origin.Origin, java.lang.String, zws.security.Authentication)
   */
  public Origin rename(QxContext runningCtx, Origin o, String newName, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "rename(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Renumber.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   * @param newNumber newNumber
   *
   * @return metadata metadata
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#renumber(zws.qx.QxContext,
   * zws.origin.Origin, java.lang.String, zws.security.Authentication)
   */
  public Origin renumber(QxContext runningCtx, Origin o, String newNumber, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "renumber(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Delete minor version.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param o origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#deleteMinorVersion(zws.qx.QxContext,
   * zws.origin.Origin, zws.security.Authentication)
   */
  public void deleteMinorVersion(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "deleteMinorVersion(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Delete entire history.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param name name
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#deleteEntireHistory(zws.qx.QxContext,
   * java.lang.String, zws.security.Authentication)
   */
  public void deleteEntireHistory(QxContext runningCtx, String name, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "deleteEntireHistory(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Link.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param source origin
   * @param target origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#link(zws.qx.QxContext,
   * zws.origin.Origin, zws.origin.Origin, zws.security.Authentication)
   */
  public void link(QxContext runningCtx, Origin source, Origin target, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "link(Origin o, String newFolderPath, Authentication id)");
  }

  /**
   * Unlink.
   *
   * @param id Authentication
   * @param runningCtx runningCtx
   * @param source origin
   * @param target origin
   *
   * @throws Exception exception
   *
   * @see zws.repository.target.RepositoryMetadataTarget#unlink(zws.qx.QxContext,
   * zws.origin.Origin, zws.origin.Origin, zws.security.Authentication)
   */
  public void unlink(QxContext runningCtx, Origin source, Origin target, Authentication id) throws Exception {
    throw new UnsupportedOperation(
        "unlink(Origin o, String newFolderPath, Authentication id)");
  }
}
