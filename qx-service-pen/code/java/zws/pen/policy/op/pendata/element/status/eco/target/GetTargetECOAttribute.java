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

public class GetTargetECOAttribute extends PENDataOpBase {

  public void execute() throws Exception {
    ECOElement ecoElement = this.getPenData().lookupECOElement(getCurrentItem());
    String ecoName = ecoElement.getTargetECO();
    ECO eco = getPenData().getECO(ecoName);
    if (null==eco) return;
    String value = eco.get(getAttributeName());
    setResult(value);
    RecorderUtil.logActivity(getQxCtx(), "get ECO attribute",   getAttributeName()+ "=" + value);
  }
 
  public String getAttributeName() { return attributeName; }
  public void setAttributeName(String s) { attributeName = s; }
  private String attributeName = null;
}
