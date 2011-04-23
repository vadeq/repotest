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
import zws.service.pen.ECOElement;

public class SavePendingECO extends PENDataOpBase {

  public void execute() throws Exception{
    String logicalName = lookupLogicalName();
    if (null==logicalName || "".equals(logicalName.trim())) throw new zws.exception.InvalidName("ECO Logical Name is NULL");
    if(null != getPenData().getECO(logicalName)) return; //eco with this name is already saved
    
    ECOElement ecoElement = lookupECOElement(getCurrentItem());
    ECO eco = ecoElement.lookupFirstPendingECO();
    if (null!=eco) {
      getPenData().addECO(logicalName, eco);
      RecorderUtil.logActivity(getQxCtx(), "Pending ECO added",  eco.getNumber());
    }
  }
}
