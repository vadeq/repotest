/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Aug 12, 2008 2:26:20 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.lang.condition.util;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.service.pen.DocumentElement;

import java.util.Iterator;

public class ItemHasDocumentParents extends ConditionOPBase {

  private static final long serialVersionUID = -1492622837187070596L;

  public Boolean evaluateCondition() throws Exception {
    boolean itemHasDocParents = false;
    String name = getCurrentItem();
    DocumentElement data = null;
    String dataName=null;
    Iterator i = this.getPenData().materializeIterator();//referenceTable.keySet().iterator();
    while (false==itemHasDocParents && i.hasNext()) {
      dataName = (String)i.next();
      data = lookupPENDataElement(dataName).getDocumentElement();
      if(name.equalsIgnoreCase(dataName)) continue;
      if (data.containsDocument(name)) itemHasDocParents=true;
    }    
    return new Boolean(itemHasDocParents);
  }

}
