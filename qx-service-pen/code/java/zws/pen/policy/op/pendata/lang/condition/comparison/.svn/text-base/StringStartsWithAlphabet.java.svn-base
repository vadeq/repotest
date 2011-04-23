 /* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.comparison;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;



public class StringStartsWithAlphabet extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception{
    boolean isOK= false;
    String str = getString();
    if (null==str) str = "";
    if(str.length()!=0) {
      char c[] = str.toCharArray();
      char firstChar = c[0];
      if((firstChar >= 'A' && firstChar <= 'Z') || 
          (firstChar >= 'a' && firstChar <= 'z')) {
        isOK = true;
      }
    }
    return new Boolean(isOK);
  }

  public String getString() { return value; }
  public void setString(String s) { value = s; }
 
  private String value = null;
}

