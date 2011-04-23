/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.pen.policy.op.pendata.util.string;

public class Prefix extends StringMaker {

  public String makeString(String s) {
    String x = getPrefix() + s;
    return x;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String s) {
    prefix = s;
  }

  private String prefix = null;
}
