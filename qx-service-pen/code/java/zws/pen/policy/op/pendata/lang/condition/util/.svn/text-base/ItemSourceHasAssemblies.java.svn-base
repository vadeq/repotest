/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Jul 15, 2008 9:28:53 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.lang.condition.util;

import java.util.Collection;
import java.util.Iterator;

import zws.data.Metadata;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.recorder.util.RecorderUtil;

public class ItemSourceHasAssemblies extends ConditionOPBase {


  public Boolean evaluateCondition() throws Exception {
    boolean status = false;  
    Metadata data = lookupSrcMetadata(getCurrentItem());
    Collection subComponents = data.getSubComponents();
    if(null == subComponents) return new Boolean(status);     
    
    Iterator itr = subComponents.iterator();
    while(itr.hasNext()) {
    	Metadata sub = (Metadata) itr.next();
    	if(sub.getName().endsWith(".asm")) {
    		status = true;
    		break;
    	}
    }
    if(status) {
    	RecorderUtil.logActivity(getQxCtx(), getCurrentItem(), "Has atleast one assembly as sub-component");
    }
    return new Boolean(status);
  }

}
