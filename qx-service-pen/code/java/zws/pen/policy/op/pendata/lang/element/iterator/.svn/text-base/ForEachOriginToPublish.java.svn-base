/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Jan 4, 2008 4:58:33 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.lang.element.iterator;

import zws.origin.Origin;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * Return a collection of the elements that correspond to the 
 * Origin objects in the PenData. These Origin objects in the 
 * PenData are for elements that are currently intended to be 
 * explicitly published. Therefore, the collection of elements
 * returned are the ones that are currently intended to be 
 * explicitly published.
 * 
 * This collection of elements returned by this op is useful 
 * for demoting the correct set of elements upon publising cancel.
 */
public class ForEachOriginToPublish extends IteratorOpBase {

  private static final long serialVersionUID = 7285910162940115083L;

  protected Collection listElements(String itemName) throws Exception {
    
    String elementName = null;
    Collection results = new Vector();
    Origin origin = null;    
    
    Iterator e = getPenData().getOriginsToPublish().iterator();    
    while(e.hasNext()) {
      origin = (Origin)e.next();
      elementName = (String) origin.getName();
      results.add(elementName);
    }   
    return results;
  }

}
