/* DesignState - Design Compression Technology
 * @author: ptoleti, arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.select;

import zws.service.pen.TxDataElement;

import java.util.Collection;

public class SelectFirstDocumentParent extends SelectOpBase {

  public String select() throws Exception {
    Collection c = getPenData().reportWhereUsedAsDocumentation(getCurrentItem());
    if (null==c || c.isEmpty()) return null;
    String firstParent = (String)c.iterator().next();
    return firstParent;
  }
  
}
  
