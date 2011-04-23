/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.pen.policy.op.pendata.util.string;

public class Suffix extends StringMaker {

  public String makeString(String s) {
    String x = s + getSuffix();
    return x;
  }

  public String getSuffix() {
    return suffix ;
  }

  public void setSuffix(String s) {
    suffix = s;
  }

  private String suffix = null;
}
