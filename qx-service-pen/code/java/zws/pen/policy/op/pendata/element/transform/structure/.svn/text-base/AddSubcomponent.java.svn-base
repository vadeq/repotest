/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved*/
package zws.pen.policy.op.pendata.element.transform.structure;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.service.pen.PENDataElement;
import zws.service.pen.TxDataElement;

import java.util.Map;

public class AddSubcomponent extends PENDataOpBase {

  public void execute() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    TxDataElement xferElement = penData.getTxDataElement();
    Map bomAttributes = doOpsAsAttributes();
    xferElement.addSubcomponent(lookupLogicalName(), 1, bomAttributes);
  }
  
}


