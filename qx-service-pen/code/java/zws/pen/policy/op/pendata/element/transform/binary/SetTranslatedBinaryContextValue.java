/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0 Created on Apr 27,
 * 2007 9:58:02 AM Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.element.transform.binary;

import zws.pen.policy.op.pendata.util.Setter;
import zws.qx.QxContext;
import zws.service.pen.PENDataElement;
//impoer zws.util.{}//Logwriter;
import zws.util.RemotePackage;

public class SetTranslatedBinaryContextValue extends Setter {

  private static final long serialVersionUID = -3565628749733094279L;

  protected void set(String fieldName, String value) {
    QxContext attachmentCtx = null;   
    RemotePackage rf = null;
    PENDataElement penData = lookupPENDataElement(getCurrentItem());

    if (penData.getTxDataElement().getBinaryFiles().size() > 0)
      rf = (RemotePackage)penData.getTxDataElement().getBinaryFiles().get(0);
 
    if (null==rf) {
      {}//Logwriter.printOnConsole("WARNING: SetTranslatedBinaryContextValue could not find the translated binary associated with item "+getCurrentItem()+" to set context on!");
      {}//Logwriter.printOnConsole("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");      
      return;
    }

    attachmentCtx = penData.getTxDataElement().getBinaryFileContext(rf);
    if (null==attachmentCtx) attachmentCtx = new QxContext();
    
    if (fieldName.equalsIgnoreCase("File Description"))
      attachmentCtx.set(QxContext.ATTACHMENT_FILE_DESCRIPTION, value);
    else if (fieldName.equalsIgnoreCase("Folder Description"))    
      attachmentCtx.set(QxContext.ATTACHMENT_FOLDER_DESCRIPTION, value);
 
    penData.getTxDataElement().addBinaryFileContext(rf, attachmentCtx);    
  }
  
}
