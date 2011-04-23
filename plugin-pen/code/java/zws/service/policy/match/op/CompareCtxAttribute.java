package zws.service.policy.match.op;
/*
 * DesignState - Design Compression Technology
 * @author:ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.qx.QxContext;

public class CompareCtxAttribute extends PolicyMatchOpBase {

  public void execute() throws Exception {
    boolean result = false;
    try {
      QxContext ctx = getQxCtx();
      if (null != ctx && null != ctx.get(getAttributeName())) {
        if (getAttributeValue().equalsIgnoreCase(ctx.get(getAttributeName()))) {
          result= true;
        }
      }
      setResult(new Boolean(result).toString());
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  private String attributeName = null;
  private String attributeValue = null;

  /**
   * @return the folderName
   */
  public String getAttributeName() {
    return attributeName;
  }

  /**
   * @param strAttributeName the folderName to set
   */
  public void setAttributeName(String s) {
    attributeName = s;
  }

  /**
   * @return the attributeValue
   */
  public String getAttributeValue() {
    return attributeValue;
  }

  /**
   * @param strAttributeValue the attributeValue to set
   */
  public void setAttributeValue(String strAttributeValue) {
    this.attributeValue = strAttributeValue;
  }


}
