/* DesignState - Design Compression Technology 
 * @author: ptoleti @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved */
package zws.pen.policy.op.pendata.util.filename;

import zws.util.FileNameUtil;
import zws.pen.policy.op.pendata.util.string.StringMaker;

public class GetFileNameBase extends StringMaker {
  public String makeString(String s) throws Exception {
    String basename = FileNameUtil.getBaseName(s);
    return basename;
  }
}
