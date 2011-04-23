/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved*/
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.service.pen.StatusElement;

public class CompareItemStatus extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {
    StatusElement statusElement = lookupStatusElement(getCurrentItem());
    String sValue = statusElement.getItemStatus(getStatusName());
    String compareTo = getCheckFor();
    if (null==sValue || "".equals(sValue.trim())) sValue="";
    if (null==compareTo || "".equals(compareTo.trim())) compareTo="";
    return new Boolean(sValue.equalsIgnoreCase(compareTo));
  }

  public String getStatusName() {return statusName;  }
  public void setStatusName(String s) {statusName = s;  }
  public String getCheckFor() {return checkFor;}
  public void setCheckFor(String s) {checkFor = s;  }
  private String statusName = null;
  private String checkFor = null;
 }
