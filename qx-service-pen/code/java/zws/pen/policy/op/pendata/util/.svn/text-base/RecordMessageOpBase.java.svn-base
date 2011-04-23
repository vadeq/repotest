/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.pen.policy.op.pendata.util;
import zws.pen.policy.op.pendata.PENDataOpBase;



public abstract class RecordMessageOpBase extends PENDataOpBase {

  public void execute() throws Exception {
    String x = getMessage();
    if (null==getMessage()) x="";
    x = x + concatenateDoOps();

    recordMessage(x);
  } 

  protected abstract void recordMessage(String s) throws Exception;
  
  public String getMessage() { return message; }
  public void setMessage(String s) { message = s; }

  
  public String getMessageType() {return MESSAGE;}
  
  private static String MESSAGE = "message";
  private String message= null;
 }
