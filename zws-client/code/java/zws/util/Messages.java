/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Sep 24, 2007 2:03:03 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Messages {
  public void recordMessage(String message) { recordMessage(MESSAGE, message); }
  public void recordWarningMessage(String message) { recordMessage(WARNING, message); }
  public void recordErrorMessage(String message) { recordMessage(ERROR, message); }
  public void recordDebugMessage(String message) { recordMessage(DEBUG, message); }
  public Collection getMessages() { return getMessages(MESSAGE); }
  public Collection getWarningMessages() { return getMessages(WARNING); }
  public Collection getErrorMessages() { return getMessages(ERROR); }
  public Collection getDebugMessages() { return getMessages(DEBUG); }
  
  private void recordMessage(String type, String message) {
    ArrayList messageList = null;
    if(null == messageMap.get(type)) {
      messageList = new ArrayList ();
    } else {
      messageList = (ArrayList) messageMap.get(type);
    }
    if(null != message) {
      messageList.add(message);
      messageMap.put(type, messageList);
    }
  }

  private ArrayList getMessages(String type) {
    ArrayList messageList = null;
    if(null != messageMap.get(type)) {
      messageList = (ArrayList) messageMap.get(type);
    }
    return messageList;
  }

  private HashMap messageMap= new HashMap();  // map(msgtype, list of messages) msgtype=message,warning,error
  public final static String MESSAGE = "message";
  public final static String WARNING = "warning";
  public final static String ERROR = "error";
  public final static String DEBUG= "debug";
  
}
