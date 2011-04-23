/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.lang.condition.comparison;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;

public class CompareStringLength extends ConditionOPBase {

  public Boolean evaluateCondition() throws Exception{
    String x = getString();
    if (null==x) x = "";
     
    x = x + concatenateDoOps();
    boolean r;
    long len = x.length();
    if (-1==max) r = len>=min;
    else if (-1==min) r = len <=max;
    else r = (len>=min && len <=max);
    return new Boolean(r);
  }

  public String getString() { return value; }
  public void setString(String s) { value = s; }

  public int getMin() { return min; }
  public void setMin(int i) { min=i; }

  public int getMax() { return max; }
  public void setMax(int i) { max=i; }

  private String value = null;
  private int max = -1;
  private int min = -1;
}

