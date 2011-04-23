/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.util;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.util.FileNameUtil;

public class CompareItemTypeOP extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception {
    String itemType = FileNameUtil.getFileNameExtension(getCurrentItem());
    String compareTo = getCheckFor();
    if (null==itemType || "".equals(itemType.trim())) itemType="";
    if (null==compareTo || "".equals(compareTo.trim())) compareTo="";
    return new Boolean(itemType.equalsIgnoreCase(compareTo));
  }

  public String getCheckFor() {return checkFor;}
  public void setCheckFor(String s) {checkFor = s;  }
  private String checkFor = null;
 }
