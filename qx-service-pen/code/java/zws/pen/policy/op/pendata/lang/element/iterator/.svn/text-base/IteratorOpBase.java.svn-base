/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.element.iterator;

import java.util.Iterator;
import java.util.Collection;
import zws.pen.policy.op.pendata.PENDataOp;
import zws.pen.policy.op.pendata.PENDataOpBase;

public abstract class IteratorOpBase extends PENDataOpBase {
  protected abstract Collection listElements(String name) throws Exception;
  public void execute() throws Exception {
    String activeItem = getCurrentItem();
    Collection elements= listElements(activeItem);
    if(null == elements) return;
    Iterator e= elements.iterator();
    String elementName;
    Collection results = null;
    while(e.hasNext()) {
      elementName = (String) e.next();
      setCurrentItem(elementName);
      results = doOps();
    }
    setCurrentItem(activeItem);
    setResult(results);
  }
}
