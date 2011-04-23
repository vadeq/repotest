/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

public class Last extends StringMaker {
  
  protected String makeString(String s) {
    if (null==s) return "";
    int b = begin-1;
    int e = begin+count;
    int len = s.length();
    
    if (len<=b) return "";
    
    if (b<0) b = 0;
    if (count<0) e = len-1;
    if (e>=len) e = len-1;

    String x = s.substring(b,e);
    return x;
  }

  public int getBegin() { return begin; }
  public void setBegin(int i) { begin=i; }
  public int getCount() { return count; }
  public void setCount(int i) { count=i; }
  
  private int begin = -1;
  private int count= -1;
}
