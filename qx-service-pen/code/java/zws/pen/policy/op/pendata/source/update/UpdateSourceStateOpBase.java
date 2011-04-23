package zws.pen.policy.op.pendata.source.update;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.origin.Origin;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.repository.Repository;
import zws.repository.target.RepositoryStateTarget;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class UpdateSourceStateOpBase extends PENDataOpBase {

  public void execute() throws Exception {
    Repository sourceRepository = getPenPolicy().getSourceRepository();
    RepositoryStateTarget repositoryStateTarget = sourceRepository.materializeStateTarget();
    Origin origin = lookupSrcMetadata(getCurrentItem()).getOrigin();
    process(repositoryStateTarget, origin);
  }
  public abstract void process(RepositoryStateTarget repositoryStateTarget, Origin origin) throws Exception;  
  public String getStateName() {return stateName;}
  public void setStateName(String s) {stateName = s;}
  
  private String stateName= null;
}
