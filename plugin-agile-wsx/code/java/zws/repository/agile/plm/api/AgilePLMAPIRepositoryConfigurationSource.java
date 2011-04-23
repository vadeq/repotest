package zws.repository.agile.plm.api;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.Namespace;
import zws.exception.InvalidConfiguration;
import zws.folder.Folder;
import zws.qx.QxContext;
import zws.repository.source.RepositoryConfigurationSource;
import zws.security.Authentication;
import java.util.Collection;



public class AgilePLMAPIRepositoryConfigurationSource extends AgilePLMAPIRepositoryBase 
                    implements RepositoryConfigurationSource {

  public void verifyConfiguration(QxContext runningCtx) throws InvalidConfiguration {}
  public Collection listNamespaces(QxContext runningCtx, Authentication id) throws Exception{
    return null;
  }
  public Collection listAttributes(QxContext runningCtx, Namespace ns, Authentication id) throws Exception {
    return null;
  }
 
  public Collection listLifeCycleReleaseLevels(QxContext runningCtx, Namespace ns, Authentication id) throws Exception {
    return null;
  }
  public Collection listRevisions(QxContext runningCtx, Namespace ns, Authentication id) throws Exception {
    return null;
  }

  public Collection listAllFolders(QxContext runningCtx, Authentication id) throws Exception{
    return null;
  }

  public String findFolder(QxContext runningCtx, Namespace folderPath, Authentication id) throws Exception{
    return null;
  }

  public Folder reportFolderTree(QxContext runningCtx, Namespace rootFolderPath, Authentication id) throws Exception{
    return null;
  }

  public Collection listSubFolders(QxContext runningCtx, Namespace rootFolderPath, boolean recursive, Authentication id) throws Exception{
    return null;
  }

}
