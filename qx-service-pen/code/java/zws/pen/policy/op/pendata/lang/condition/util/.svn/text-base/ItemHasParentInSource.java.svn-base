/* 
 * DesignState - Design Compression Technology
 * @author: Rodney McCabe
 * @version: 1.0
 * Created on 07-14-2008
 * Copyright (c) 2007 Zero Wait-State Inc. All rights reserved 
 * 
 * Find out if name of one of the parents of the current item exists
 * 
 * */
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.service.pen.SourceDataElement;
import java.util.Collection;
import java.util.Iterator;
import zws.data.Metadata;

public class ItemHasParentInSource extends ConditionOPBase {

	private PENDataOpBase searchOp;
	  
	public Boolean evaluateCondition() throws Exception {
		
		String current = getCurrentItem();
		String parent = concatenateDoOps();

	    if (parent == null) 
	    	throw new Exception("A String is required for comparison.");

		SourceDataElement element = null;
		Iterator iterator = getPenData().materializeIterator();
		
		// loop variables
		Metadata data;
		String item;
		boolean found = false;
		boolean isParent = false;
		
		/*
		 * 1.  Loop through all elements
		 * 2.  Get the meta-data for the current item
		 * 3.  If the following are true, return true:
		 * 		a. this element is the parent we are looking for
		 * 		b. this element's subcomponent list contains the current item
		 * 4.  Else, return false
		 */
		while (iterator.hasNext()) {
			
			// 2.  Get the meta-data for the current item
			item = (String) iterator.next();
			
			// 3a. this element is the parent we are looking for
			if (isIgnoreCase() && item.equalsIgnoreCase(parent)) {
				isParent = true;
			} else if (!isIgnoreCase() && item.equals(parent)) {
				isParent = true;
			} else {
				isParent = false;
			}
			
			if (isParent) {
        element = lookupSourceDataElement(item);
				data = element.getSourceData();
				
				// 3b. this element's subcomponent list contains the current item
				if (data.hasSubComponent(current)) {
					found = true;
					break;
				}				
			}
		}
		return new Boolean(found);
	}
	
	private boolean ignoreCase = false;
	public boolean isIgnoreCase() { return ignoreCase; }
	public void setIgnoreCase(boolean b) { ignoreCase = b; } 	
 }
