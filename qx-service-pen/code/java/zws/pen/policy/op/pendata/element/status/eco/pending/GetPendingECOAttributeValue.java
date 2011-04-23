package zws.pen.policy.op.pendata.element.status.eco.pending;
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
////import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Iterator;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class GetPendingECOAttributeValue extends PENDataOpBase {

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
      RecorderUtil.logActivity(getQxCtx(), "ECO status",  " ECO No." +ecoNumber + " Status:" + ecoStatus);
      //if("Pending".equals(ecoStatus) || "Unassigned".equals(ecoStatus)) {
        {}//Logwriter.printOnConsole("getting value from ECO: " + eco);
        if("status".equalsIgnoreCase(getAttributeName())) {
          setResult(eco.getStatus());
        } else if ("type".equalsIgnoreCase(getAttributeName())) {
          setResult(eco.getType());
        } else if ("number".equalsIgnoreCase(getAttributeName())) {
          setResult(eco.getNumber());
        } else if ("nextstatus".equalsIgnoreCase(getAttributeName())) {
          setResult(eco.getNextStatus());
        } else if ("workflow".equalsIgnoreCase(getAttributeName())) {
          setResult(eco.getWorkflow());
        } else if ("description".equalsIgnoreCase(getAttributeName())) {
          setResult(eco.getDescription());
        } else  {
          String value = eco.get(getAttributeName());
          if (null==value) value ="NULL";
          setResult(value);
        }
        break;
      //}
      }
   }
  }
  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String name) {attributeName = name;  }

  private String attributeName = null;
}