package zws.pen.policy.op.pendata.element.status.eco.target;
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
//impoer zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Iterator;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class SetPendingECOAsTargetECOOP extends PENDataOpBase {

  public void execute() throws Exception{
    ECOElement ecoElement = this.getPenData().lookupECOElement(getCurrentItem());
    Collection ecoList = ecoElement.getPendingECOList();
    String ecoNumber = null;
    String ecoStatus = null;
    ECO eco = null;
    if(null != ecoList && ecoList.size() >0) {
      Iterator itr = ecoList.iterator();
      while(itr.hasNext()) {
      ecoNumber = (String) itr.next();
      eco = getPenData().getECO(ecoNumber);
      ecoStatus = eco.getStatus();
      {}//Logwriter.printOnConsole("eco " + eco + " status " + ecoStatus);
      if("Pending".equals(ecoStatus) || "Unassigned".equals(ecoStatus)) {
        ecoElement.setTargetECO(eco.getNumber());
        {}//Logwriter.printOnConsole("set pending ECO as TargetECO  " + eco.getNumber());
        RecorderUtil.logActivity(getQxCtx(), "set pending eco as target ECO:",   eco.getNumber());
        break;
      }
      }
   }
  }
}