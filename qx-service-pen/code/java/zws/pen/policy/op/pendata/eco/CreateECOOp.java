package zws.pen.policy.op.pendata.eco;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.data.eco.ECO;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryECOSource;
import zws.repository.target.RepositoryECOTarget;

import java.util.Map;

public class CreateECOOp extends PENDataOpBase {

  public void execute() throws Exception{
    String name = lookupLogicalName();
    if (null==name || "".equals(name.trim())) throw new zws.exception.InvalidName("ECO Logical Name is NULL");
    if(null != getPenData().getECO(name)) return;
    
    Map ecoAttributes = doOpsAsAttributes();
    
    //create eco in target
    Repository targetRepository = getPenPolicy().getTargetRepository();
    RepositoryECOTarget ecoTarget = targetRepository.materializeECOTarget();
    String number = ecoTarget.createECO(getQxCtx(), ecoAttributes, getAuthentication());

    //retrieve created eco from target and place into PENData eco reference table
    RepositoryECOSource ecoSource = targetRepository.materializeECOSource();
    ECO eco = ecoSource.findECO(getQxCtx(), number, getAuthentication());
    getPenData().addECO(name, eco);
    RecorderUtil.logActivity(getQxCtx(), "ECO added",  number);
  }
}
