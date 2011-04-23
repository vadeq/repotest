/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.service.pen.TxDataElement;

import java.util.Collection;
import java.util.Iterator;

public class HasParent extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {
    String name = getCurrentItem();
    boolean r = false;

    Collection subComponents= null;
    TxDataElement txDataElement = null;
    Iterator itr = getPenData().materializeIterator();
    while (itr.hasNext()) {
      String itemName = (String) itr.next();
      txDataElement = lookupTxDataElement(itemName);
      subComponents = txDataElement.getSubComponents();
      if(subComponents.contains(name)) {
        r = true;
        break;
      }
    }
    return new Boolean(r);
  }
 }
