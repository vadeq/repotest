/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.runtime;

import zws.pen.policy.op.pendata.util.string.StringResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Now extends StringResult {

  public void execute() throws Exception {
    //String datetime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    String datetime = new SimpleDateFormat(this.getDateFormat()).format(new Date());
    setStringResult(datetime);
  } 

  public String getDateFormat() {
    return dateFormat;
  }

  public void setDateFormat(String strDateFormat) {
    dateFormat = strDateFormat;
  }

  private String dateFormat = "yyyy.MM.dd.HH.mm.ss";
}
