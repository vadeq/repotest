/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

import zws.util.FileUtil;

public class StringWriter extends StringMaker {
  
  protected String makeString(String s) throws Exception {
    if(null != getFileName()) {
      FileUtil.appendInNewLine(s, fileName, true);
    }    
    return s;
  }
  protected String makeOnEachString(String s) throws Exception {
    return s;
  }

  public String getFileName() { return fileName; }
  public void setFileName(String s) { fileName = s; }

  private String fileName = null;
}
