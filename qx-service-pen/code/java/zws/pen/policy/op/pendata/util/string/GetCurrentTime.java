/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

import zws.pen.policy.op.pendata.PENDataOpBase;

import java.text.SimpleDateFormat;
import java.util.Calendar;



public class GetCurrentTime extends PENDataOpBase {

  public void execute() throws Exception {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    setResult(sdf.format(cal.getTime()));
  }

  private String format = "yyyy:MM:dd HH:mm:ss";

  public String getFormat() {
    return format;
  }

  public void setFormat(String s) {
    format = s;
  }
}
