package zws.repository.search; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.data.Metadata;
import zws.repository.Repository;
import zws.search.SearchAgentBase;
import zws.util.FileNameUtil;


/**
 * The Class RepositorySearchAgentBase.
 */
public abstract class RepositorySearchAgentBase extends SearchAgentBase implements RepositorySearchAgent {

  /** execute query.
   * @throws Exception exception
   * @see zws.search.SearchAgentBase#executeQuery()
   */
  public abstract void executeQuery() throws Exception;

  /** addSystemAttributes.
   * @param data Metadata
   * @see zws.search.SearchAgentBase#addSystemAttributes(zws.data.Metadata)
   */
  public void addSystemAttributes(Metadata data) {
    String type, extension;
    extension = FileNameUtil.getFileNameExtension(data.getName());
    type = FileNameUtil.lookupFileType(data.getName());
    if (!"".equals(extension)) {
      data.set(Names.METADATA_FILE_TYPE, extension);
    }
    if (!"".equals(type)) {
      data.set(Names.METADATA_TYPE, type);
    }
    data.set(Names.METADATA_DOMAIN_NAME, repository.getDomainName());
    data.set(Names.METADATA_SERVER_NAME, repository.getServerName());
    data.set(Names.METADATA_REPOSITORY_NAME, repository.getName());
    data.set(Names.METADATA_DATASOURCE_SEARCH_AGENT_NAME, getName());
  }

  /**
   * Sets the repository.
   *
   * @param r the repository
   */
  public void setRepository(Repository r) {
    repository = r;
  }

  /**
   * Gets the repository.
   *
   * @return the repository
   */
  public Repository getRepository() { return repository; }

  /** supports deep copy.
   * @return boolean boolean
   * @see zws.op.ListOpBase#supportsDeepCopy()
   */
  public boolean supportsDeepCopy() { return false; }

  /** The repository. */
  private Repository repository = null;
}
