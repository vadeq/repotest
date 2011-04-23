/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.data.Metadata;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

import java.util.Collection;

public class TargetHasSubcomponent extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {
    boolean r = false;
    Metadata m = this.lookupTargetMetadata(getCurrentItem());
    if (null==m) r = m.hasSubComponents();
    return new Boolean(r);
  }
 }
