/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Sep 24, 2007 3:04:18 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.util;

import zws.pen.policy.op.pendata.PENDataOpBase;

public abstract class Setter extends PENDataOpBase{
  protected abstract void set(String field, String value) throws Exception;
  
  public void execute() throws Exception {
    String field = getAttributeName();
    String value = makeValue();
    set(field, value);
  }
  protected String makeValue() throws Exception {
    String x= getAttributeValue();
    if (null==x) x = "";
    x += concatenateDoOps();
    return x;
  }

  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String attrName) {attributeName = attrName;}
  public String getAttributeValue() {return attributeValue;}
  public void setAttributeValue(String s) { attributeValue = s;}
  private String attributeName = null;
  private String attributeValue = null;
}
