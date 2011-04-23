/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Jul 15, 2008 9:28:53 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.custom.harris;

import java.util.Collection;
import java.util.Iterator;

import zws.data.Metadata;
import zws.origin.Origin;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.recorder.util.RecorderUtil;

public class ItemIsSoleDrwParentOfSourceAssemblySubcomponents extends ConditionOPBase {
/*
 * Current item should be drawing.
 * get the sub-components for current item
 * For each sub-component, get where used items(parents)
 * if it has one parent return true.
 * Else check each drawing parent and return false if it has more that one drawing parent.
 */
	public Boolean evaluateCondition() throws Exception {
		boolean status = true;
		Metadata data = lookupSrcMetadata(getCurrentItem());
		Collection subComponents = data.getSubComponents();
		if (null == subComponents)
			return new Boolean(status);
		if (subComponents.isEmpty())
			return new Boolean(status);

		Collection origins = getOriginsToPublish();
		Iterator itemItr = subComponents.iterator();
    
    Metadata sub = null;
    String name =  null;
    Collection parents =  null;    
		while (itemItr.hasNext() && true==status) {
			
			sub = (Metadata) itemItr.next();
			name = sub.getName();
			parents = getPenData().reportWhereUsedInSource(name); //this includes other assemblies as well as drawings
			if (parents.size() <= 1) {
				status = true;
			} else {
        status = false;
				// check item has multiple parents, those should not be matched with origins to publish.
				if(!checkForMultipleParents(parents, origins)) {
          status = true;    
        }
			}
		}
		if (!status) {
			RecorderUtil.logActivity(getQxCtx(), getCurrentItem(),"sub-component has multiple drawing parents");
		}
		return new Boolean(status);
	}

	private boolean checkForMultipleParents(Collection parents, Collection origins) {
		boolean result = false;
		int parentCount = 0;
		String parent = null;
		Iterator parentsItr = parents.iterator();
		Iterator originItr = null;
    Origin o =null;
		// if more than one parent exists in origins to publish, item has multiple publishing parents. 
		while (parentsItr.hasNext() && !result) {
			parent = (String) parentsItr.next();
			if(!parent.endsWith(".drw")) {
				continue;
			}
			originItr = origins.iterator();
			while (originItr.hasNext()) {
				o = (Origin) originItr.next();
				if (parent.equalsIgnoreCase(o.getName())) {
					parentCount++;
					if (parentCount >= 2) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}
}
