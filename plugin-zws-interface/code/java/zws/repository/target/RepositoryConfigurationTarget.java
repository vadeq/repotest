package zws.repository.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.Namespace;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.security.Authentication;
import zws.exception.*;

/**
 * The Interface RepositoryConfigurationTarget.
 */
public interface RepositoryConfigurationTarget extends Repository {

  /**
   * Configure.
   *
   * @throws InvalidConfiguration the invalid configuration
   */
  void configure() throws InvalidConfiguration;

  /**
   * Creates the folder.
   *
   * @param folderPath the folder path
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   * @throws InvalidName the invalid name
   */
  void createFolder(QxContext runningCtx, Namespace folderPath, Authentication id) throws InvalidName, Exception;

  /**
   * Delete folder.
   *
   * @param folderPath the folder path
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   * @throws PathDoesNotExist the path does not exist
   * @throws NotEmpty the not empty
   */
  void deleteFolder(QxContext runningCtx, Namespace folderPath, Authentication id) throws PathDoesNotExist, NotEmpty, Exception;

  /**
   * Creates the life cycle release.
   *
   * @param releaseLevelName the release level name
   * @param runningCtx the running ctx
   * @param id the id
   * @param releaseCategory the release category
   *
   * @throws Exception the exception
   * @throws InvalidName the invalid name
   */
  void createLifeCycleRelease(QxContext runningCtx, Namespace releaseCategory, String releaseLevelName, Authentication id) throws InvalidName, Exception;

  /**
   * Delete life cycle release.
   *
   * @param releaseLevelName the release level name
   * @param runningCtx the running ctx
   * @param id the id
   * @param releaseCategory the release category
   *
   * @throws Exception the exception
   * @throws InvalidName the invalid name
   */
  void deleteLifeCycleRelease(QxContext runningCtx, Namespace releaseCategory, String releaseLevelName, Authentication id) throws InvalidName, Exception;

  /**
   * Creates the attribute.
   *
   * @param attributeName the attribute name
   * @param attributeCategory the attribute category
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   * @throws InvalidName the invalid name
   */
  void createAttribute(QxContext runningCtx, Namespace attributeCategory, String attributeName, Authentication id) throws InvalidName, Exception;

  /**
   * Delete attribute.
   *
   * @param attributeName the attribute name
   * @param runningCtx the running ctx
   * @param id the id
   * @param releaseCategory the release category
   *
   * @throws Exception the exception
   * @throws InvalidName the invalid name
   */
  void deleteAttribute(QxContext runningCtx, Namespace releaseCategory, String attributeName, Authentication id) throws InvalidName, Exception;

  /**
   * Creates the state variable.
   *
   * @param stateVariableCategory the state variable category
   * @param runningCtx the running ctx
   * @param id the id
   * @param stateVariableName the state variable name
   *
   * @throws Exception the exception
   * @throws InvalidName the invalid name
   */
  void createStateVariable(QxContext runningCtx, Namespace stateVariableCategory, String stateVariableName, Authentication id) throws InvalidName, Exception;

  /**
   * Delete state variable.
   *
   * @param stateVariableCategory the state variable category
   * @param runningCtx the running ctx
   * @param id the id
   * @param stateVariableName the state variable name
   *
   * @throws Exception the exception
   * @throws InvalidName the invalid name
   */
  void deleteStateVariable(QxContext runningCtx, Namespace stateVariableCategory, String stateVariableName, Authentication id) throws InvalidName, Exception;

  /**
   * Creates the revision.
   *
   * @param RevisionCategory the Revision category
   * @param RevisionName the Revision name
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   * @throws InvalidName the invalid name
   */
  void createRevision(QxContext runningCtx, Namespace RevisionCategory, String RevisionName, Authentication id) throws InvalidName, Exception;

  /**
   * Delete revision.
   *
   * @param RevisionCategory the Revision category
   * @param RevisionName the Revision name
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   * @throws InvalidName the invalid name
   */
  void deleteRevision(QxContext runningCtx, Namespace RevisionCategory, String RevisionName, Authentication id) throws InvalidName, Exception;

}
