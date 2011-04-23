/* DesignState - Design Compression Technology
 * @author: ptoleti, arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.select;

import zws.exception.InvalidConfiguration;
import zws.pen.policy.op.pendata.PENDataOp;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

import java.util.Collection;

public abstract class SelectOpBase extends PENDataOpBase {

  public void execute() throws Exception {
    String activeItem = getCurrentItem();
    String selectedItem = select();
    if (null==selectedItem) throw new zws.exception.InvalidName("null was selected");
    setCurrentItem(selectedItem);
    Collection result = doOps();
    if (null!=result && 1==result.size()) setResult(result.iterator().next());
    else setResult(result);
    setCurrentItem(activeItem);
  }
  
  protected abstract String select() throws Exception;
}
