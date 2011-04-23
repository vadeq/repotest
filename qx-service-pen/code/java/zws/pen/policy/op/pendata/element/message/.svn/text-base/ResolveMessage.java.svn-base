/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Mar 7, 2008 8:38:18 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.element.message;

import zws.application.messageconstants.MessageConstant;
import zws.application.messageconstants.MessageConstants;
import zws.pen.policy.op.pendata.PENDataOpBase;

import java.util.Map;

public class ResolveMessage extends PENDataOpBase {

  private static final long serialVersionUID = 3596350414181479428L;
  private static final String EMPTY_MESSAGE = "";
  
  public void execute() throws Exception {
    String resolvedMsg=null;
    if (null==getOps() || getOps().isEmpty()) { setResult(EMPTY_MESSAGE); return;}
      
    messageParams = doOpsAsAttributes();
    MessageConstant msgConst = MessageConstants.lookup(messageId);
    if (null!=msgConst) resolvedMsg = msgConst.resolve(messageParams);
    else resolvedMsg = messageId + " {undefined message constant}";
    
    setResult(resolvedMsg);
  }

  public String getMessageId() {return messageId;}
  public void setMessageId(String key) {messageId = key;}
  public Map getMessageParams() {return messageParams;}
  public void setMessageParams(Map msgParams) {messageParams = msgParams;}
  

  private String messageId=null;
  private Map messageParams=null;
}
