/* DesignState - Design Compression Technology
 * @author: ptoleti 
 * @version: 1.0 Created on Apr 27,
 * 2007 9:58:02 AM Copywrite (c) 2007 Zero Wait-State
 * Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.runtime;

import zws.pen.policy.op.pendata.util.string.StringResult;

public class GetContextValue extends StringResult {
  public void execute() throws Exception {
      String s = getQxCtx().get(getAttributeName());
      setStringResult(s);
  }
  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String attrName) {attributeName = attrName;}
  private String attributeName = null;
}
