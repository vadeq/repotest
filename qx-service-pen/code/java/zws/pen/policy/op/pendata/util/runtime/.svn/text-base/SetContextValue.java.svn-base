/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0 Created on Apr 27,
 * 2007 9:58:02 AM Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.runtime;

import zws.pen.policy.op.pendata.util.Setter;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;

public class SetContextValue extends Setter {

  protected void set(String fieldName, String value) throws Exception {
    QxContext ctx = getQxCtx();
    while (null!=ctx.parent()) ctx = ctx.parent();
    ctx.set(fieldName, value);
    RecorderUtil.logActivity(getQxCtx(), "context value set", fieldName + "="+ value);
  }
  
}
