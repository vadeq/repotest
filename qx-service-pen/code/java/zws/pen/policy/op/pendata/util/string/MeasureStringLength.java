/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

public class MeasureStringLength extends StringMaker {

  protected String makeString(String s) {
    long length = 0;
    if (null != s) length = s.length();
    return "" + length;
  }

}
