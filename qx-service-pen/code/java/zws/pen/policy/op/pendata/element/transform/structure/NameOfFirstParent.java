/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Sep 27, 2007 3:35:19 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.element.transform.structure;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.util.PrintUtil;

import java.util.Collection;

public class NameOfFirstParent extends PENDataOpBase {
  
  public void execute() {
    String parent=null;
    Collection c = getPenData().reportWhereUsedInSource(getCurrentItem());
    if (null!=c && !c.isEmpty()) parent = (String)c.iterator().next();
    setResult(parent);
    try {
      RecorderUtil.logActivity(getQxCtx(), "Name of first parent for" + getCurrentItem(),  parent);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
