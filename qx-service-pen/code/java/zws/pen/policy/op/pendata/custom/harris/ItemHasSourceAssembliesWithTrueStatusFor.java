/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Jul 17, 2008 10:21:30 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.custom.harris;

import java.util.Collection;
import java.util.Iterator;

import zws.data.Metadata;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.StatusElement;

public class ItemHasSourceAssembliesWithTrueStatusFor extends ConditionOPBase {


  private static final long serialVersionUID = -544332000891104752L;

  public Boolean evaluateCondition() throws Exception {        
    boolean status = false;
    boolean found = false;
    String statusName = concatenateDoOps();
    
    Metadata data = lookupSrcMetadata(getCurrentItem());
    Collection subComponents = data.getSubComponents();
    if(null == subComponents) return new Boolean(status);     
    
    Iterator itr = subComponents.iterator();
    while(itr.hasNext() && !found) {
    	Metadata sub = (Metadata) itr.next();
    	if(sub.getName().endsWith(".asm") && (found = hasTrueValueForStatusName(sub, statusName))) {
    		status = true;
    		break;
    	}
    }
    if(status) {
    	RecorderUtil.logActivity(getQxCtx(), getCurrentItem(), "Has atleast one assembly as sub-component and which needs evaluation");
    }
    return new Boolean(status);
  }

  private boolean hasTrueValueForStatusName(Metadata sub, String statusName) {
    boolean result = false;
    
    if (null != sub && (null != statusName && !statusName.equals(""))) {
      StatusElement statusInfo = lookupStatusElement(sub.getName());
      String statusValue = statusInfo.getItemStatus(statusName);
      if ("true".equalsIgnoreCase(statusValue)) result = true;
    }    
    return result;
  }

}
