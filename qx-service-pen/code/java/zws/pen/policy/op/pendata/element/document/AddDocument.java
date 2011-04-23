/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved*/
package zws.pen.policy.op.pendata.element.document;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.DocumentElement;
import zws.service.pen.PENDataElement;

import java.util.Map;

public class AddDocument extends PENDataOpBase {

  public void execute() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    DocumentElement docElement = penData.getDocumentElement();
    Map bomAttributes = doOpsAsAttributes();
    String logicalName = lookupLogicalName();
    docElement.addDocument(logicalName, 1, bomAttributes);
    RecorderUtil.logActivity(getQxCtx(), "Document added",  logicalName);
  }
  
  
}


