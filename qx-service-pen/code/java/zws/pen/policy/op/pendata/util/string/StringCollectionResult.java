/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Feb 15, 2008 11:09:32 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

import zws.pen.policy.op.pendata.PENDataOpBase;

import java.util.Collection;

public abstract class StringCollectionResult extends PENDataOpBase {

  public void setStringCollectionResult(Collection s) { setResult(s); }
  public Collection getStringCollectionResult() { return (Collection)getResult(); }
}
