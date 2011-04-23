package zws.pen.policy.op.pendata.element.transform.binary;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.Alert;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.qx.QxContext;
import zws.service.pen.PENDataElement;
import zws.service.pen.StatusElement;
//import zws.util.{}//Logwriter;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
//public abstract class TransformBinaryOpBase extends PENDataProcessor {
public abstract class TransformBinaryOpBase extends PENDataOpBase {
  static final String PROE_FORMAT_VALUE = "ProE";
  static final String IGES_FORMAT_VALUE = "IGES";
  static final String PDF_FORMAT_VALUE = "PDF";
  static final String STEP_FORMAT_VALUE = "STEP";
  
  public void execute() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
//    StatusElement statusElement = lookupStatusElement(getCurrentItem());    
//    boolean updateStatus = TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_FILE_NEEDS_TO_BE_UPDATED));
//    if(!updateStatus) return; 
    transformBinary(penData);
  }
  
  public abstract void transformBinary(PENDataElement penData) throws Exception;
  
  protected void logAndNotify() {
    {}//Logwriter.printOnConsole(notificationMessage);
    {}//Logwriter.printOnConsole("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    //zws.Alert.notify(notificationSubject, notificationMessage);
    String recipients = getQxCtx().get(QxContext.RECIPIENTS);
    if(null != recipients)  Alert.notify(notificationSubject, notificationMessage,  recipients);
    else Alert.notify(notificationSubject, notificationMessage);
  }
  
  protected String notificationSubject="PEN: VIEWABLE GENERATION FAILED!";
  protected String notificationMessage="";
}
