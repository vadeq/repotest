/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

import zws.util.StringUtil;

public class FirstWord extends StringMaker {
  
  protected String makeString(String s) {
    if (null==s) return "";
    
    StringBuffer buf = new StringBuffer();
    char c;
    String ss = s.trim();
    for (int i=0; i < ss.length(); i++) {
      c = s.charAt(i);
      if (StringUtil.isWhiteSpace(c)) break;
      buf.append(c);
    }
    ss = buf.toString();
    return ss;
  }
}
