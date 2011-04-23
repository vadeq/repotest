package zws.repository.source;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;
import zws.util.RemotePackage;

import java.io.File;
import java.util.Collection;


/**
 * The Interface RepositoryBinarySource.
 */
public interface RepositoryBinarySource extends Repository {


  /**
   * Download.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param location the location
   * @param id the id
   *
   * @return the file
   *
   * @throws Exception the exception
   */
 // File download(QxContext runningCtx, Origin o, String location, Authentication id) throws Exception;

  //returns remote file to native binary
  RemotePackage fetchNativeFile(QxContext runningCtx, Origin origin, Authentication id) throws Exception;
  //returns remote file that expands into native binaries required for regenerating items design
  RemotePackage fetchDesignFiles(QxContext runningCtx, Origin origin, Authentication id) throws Exception;
  File fetchNativeFileLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception;
  Collection fetchDesignFilesLocally(QxContext runningCtx, Origin origin, Authentication id) throws Exception;
}
