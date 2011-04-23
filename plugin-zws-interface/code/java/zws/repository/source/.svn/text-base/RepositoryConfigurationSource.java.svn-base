package zws.repository.source;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.Namespace;
import zws.exception.InvalidConfiguration;
import zws.folder.Folder;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;
import java.util.Collection;



/**
 * The Interface RepositoryConfigurationSource.
 */
public interface RepositoryConfigurationSource extends Repository {

  /**
   * Verify configuration.
   *
   * @param runningCtx the running ctx
   *
   * @throws InvalidConfiguration the invalid configuration
   */
  void verifyConfiguration(QxContext runningCtx) throws InvalidConfiguration;

  /**
   * List namespaces.
   *
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  Collection listNamespaces(QxContext runningCtx, Authentication id) throws Exception;

  /**
   * List attributes.
   *
   * @param ns the ns
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  Collection listAttributes(QxContext runningCtx, Namespace ns, Authentication id) throws Exception;

  /**
   * List life cycle release levels.
   *
   * @param ns the ns
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  Collection listLifeCycleReleaseLevels(QxContext runningCtx, Namespace ns, Authentication id) throws Exception;

  /**
   * List revisions.
   *
   * @param ns the ns
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  Collection listRevisions(QxContext runningCtx, Namespace ns, Authentication id) throws Exception;

  /**
   * List all folders.
   *
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  Collection listAllFolders(QxContext runningCtx, Authentication id) throws Exception;

  /**
   * Find folder.
   *
   * @param runningCtx the running ctx
   * @param folderPath the folder path
   * @param id the id
   *
   * @return the string
   *
   * @throws Exception the exception
   */
  String findFolder(QxContext runningCtx, Namespace folderPath, Authentication id) throws Exception;

  /**
   * Report folder tree.
   *
   * @param rootFolderPath the root folder path
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the folder
   *
   * @throws Exception the exception
   */
  Folder reportFolderTree(QxContext runningCtx, Namespace rootFolderPath, Authentication id) throws Exception;

  /**
   * List sub folders.
   *
   * @param recursive the recursive
   * @param rootFolderPath the root folder path
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  Collection listSubFolders(QxContext runningCtx, Namespace rootFolderPath, boolean recursive, Authentication id) throws Exception;

}
