package zws.pen.policy.op.pendata.source.update;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.origin.Origin;
import zws.recorder.util.RecorderUtil;
import zws.repository.target.RepositoryStateTarget;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class DemoteSourceData extends UpdateSourceStateOpBase {
  
  public void process(RepositoryStateTarget repositoryStateTarget, Origin origin) throws Exception{
    repositoryStateTarget.demoteLifeCycleReleaseState(getQxCtx(), origin, getStateName(), getAuthentication());
    RecorderUtil.logActivity(getQxCtx(), "Demote source data", origin.getName() + " to "+ getStateName());
  }

}
