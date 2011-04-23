/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

public class Substring extends StringMaker {
  
  protected String makeString(String s) {
    if (null==s) return "";
    if (s.length()<= count) return s;

    String x="";
    x= s.substring(0,count);
    return x;
  }

  public int getCount() { return count; }
  public void setCount(int i) { count=i; }
  
  private int count = 1;
}
