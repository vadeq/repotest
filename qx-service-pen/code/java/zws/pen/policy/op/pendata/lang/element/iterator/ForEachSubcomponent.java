/* DesignState - Design Compression Technology
 * @author: PavanKumar
 * @version: 1.0
 * Created on Sep 1, 2007 11:39:29 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.element.iterator;

import zws.service.pen.TxDataElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ForEachSubcomponent extends IteratorOpBase{
  protected Collection listElements(String itemName) throws Exception{
    TxDataElement txDataElement = lookupTxDataElement(itemName);
    Collection subComponents = txDataElement.getSubComponentRefNames();
    return subComponents ;
  }
}
