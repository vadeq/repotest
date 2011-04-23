package zws.pen.policy.op.pendata.element.transform.structure;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.TxDataElement;
//impoer zws.util.Logwriter;

public class RemoveSubComponentsOp extends PENDataOpBase{

  public void execute() throws Exception{
    TxDataElement txDataElement = lookupTxDataElement(getCurrentItem());
    txDataElement.removeAllSubcomponents();
    {}//Logwriter.printOnConsole("All sub components for the item " + getCurrentItem() + " removed.");
    RecorderUtil.logActivity(getQxCtx(), "remove all sub-components",   getCurrentItem());
  }
}
