package zws.repository.teamcenter.proxy;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.exception.UnsupportedOperation;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.qx.program.TC10Poet;
import zws.security.Authentication;
import zws.security.CryptoUtil;
import zws.service.file.depot.FileDepotClient;
import zws.util.RemotePackage;

import java.io.File;
import java.util.Collection;

public class TC10ProxyRepositoryBinarySource extends TC10ProxyRepositoryBase
implements RepositoryBinarySource {
	protected TC10ProxyRepositoryBinarySource (QxContext parent) { configureParentContext(parent); 	}

	public RemotePackage fetchNativeFile(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
      QxInstruction root = new QxInstruction(TC10Constants.TAG_QX);
      QxInstruction open = new QxInstruction(TC10Constants.TAG_OPEN_REPOSITORY);
      QxInstruction fetch= new QxInstruction(TC10Constants.TAG_FETCH_NATIVE_FILE);

      open.set(TC10Constants.ATT_REPOSITORY_NAME, getRepositoryName());
      fetch.set(TC10Constants.ATT_ORIGIN, origin.toString());
      open.set(TC10Constants.ATT_USER, id.getUsername());
      //open.set(TC10Constants.ATT_PASSWORD, id.getPassword());
      CryptoUtil encryptutil = new CryptoUtil();
      String encryptedPassword = encryptutil.encrypt(id.getPassword());
      open.set(TC10Constants.ATT_ENCRYPTED_PASSWORD, encryptedPassword);      
      //download.set(TC10Constants.ATT_TO_DIR, location);

      root.add(open);
      open.add(fetch);
      QxXML dataInstruction = new QxXML(root.toString());

      TC10Poet poet = TC10Poet.materializePoet(this);
      QxXML result = poet.executeQx(runningCtx, dataInstruction);
      RemotePackage r = FileDepotClient.materializeRemotePackage(result.toString());
      return r;
	}

  public RemotePackage fetchDesignFiles(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    QxInstruction root = new QxInstruction(TC10Constants.TAG_QX);
    QxInstruction open = new QxInstruction(TC10Constants.TAG_OPEN_REPOSITORY);
    QxInstruction fetch= new QxInstruction(TC10Constants.TAG_FETCH_DESIGN_FILES);

    open.set(TC10Constants.ATT_REPOSITORY_NAME, getRepositoryName());
    fetch.set(TC10Constants.ATT_ORIGIN, origin.toString());    
    open.set(TC10Constants.ATT_USER, id.getUsername());
    //open.set(TC10Constants.ATT_PASSWORD, id.getPassword());
    CryptoUtil encryptutil = new CryptoUtil();
    String encryptedPassword = encryptutil.encrypt(id.getPassword());
    open.set(TC10Constants.ATT_ENCRYPTED_PASSWORD, encryptedPassword);    
    //download.set(TC10Constants.ATT_TO_DIR, location);

    root.add(open);
    open.add(fetch);
    QxXML dataInstruction = new QxXML(root.toString());

    TC10Poet poet = TC10Poet.materializePoet(this);
    QxXML result = poet.executeQx(runningCtx, dataInstruction);
    RemotePackage r = FileDepotClient.materializeRemotePackage(result.toString());
    return r;
  }

  public RemotePackage fetchBOMFiles(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    throw new UnsupportedOperation("fetchBOMFiles(QxContext runningCtx, Origin origin, Authentication id)");
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