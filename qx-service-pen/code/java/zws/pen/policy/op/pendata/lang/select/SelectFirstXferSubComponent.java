/* DesignState - Design Compression Technology
 * @author: ptoleti, arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.select;

import zws.service.pen.TxDataElement;

import java.util.Collection;

public class SelectFirstXferSubComponent extends SelectOpBase {

  public String select() throws Exception {
    TxDataElement tx = this.lookupTxDataElement(getCurrentItem());
    Collection names = tx.getSubComponentRefNames();
    if (null==names || names.isEmpty()) return null;
    String selected = (String)names.iterator().next();
    return selected;
  }
  
}
  
