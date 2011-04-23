package zws.service.policy.match.op;
/*
 * DesignState - Design Compression Technology
 * @author:ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.data.Metadata;
import zws.repository.Repository;
import zws.repository.source.RepositoryStructureSource;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;

import java.util.Collection;

/**
 * The Class CompareAttributeOP.
 *
 * @author ptoleti
 */
public class HasDependencies extends PolicyMatchOpBase {

  /**
   * @throws Exception
   * Exception
   * @see zws.pen.policy.op.PENDataOpBase#execute()
   */
  public void execute() throws Exception {
    boolean finalString = false; 
    Metadata metaData = getMetaData();
    RepositoryService r = RepositoryClient.getClient();
    Repository repositoryObj = r.findRepository(metaData.getOrigin().getRepositoryName());
    RepositoryStructureSource structureSource = repositoryObj.materializeStructureSource();
    Collection c = structureSource.reportDependencies(getQxCtx(), metaData.getOrigin(), getAuthentication());
    if(c!=null && c.size() >0) {
      finalString = true;
    }
    setResult(new Boolean(finalString).toString());
  }
}
