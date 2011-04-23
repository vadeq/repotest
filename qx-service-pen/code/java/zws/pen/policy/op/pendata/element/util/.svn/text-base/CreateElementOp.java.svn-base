package zws.pen.policy.op.pendata.element.util;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.PENDataElement;
import zws.exception.DuplicateName;
//import zws.util.{}//Logwriter;

public class CreateElementOp extends PENDataOpBase{

  public void execute() throws Exception{
    {}//Logwriter.printOnConsole(this,"process","start");
    
    if(null != getPenData().lookupPENDataElement(lookupLogicalName())) {
       throw new DuplicateName(lookupLogicalName());}
    String logicalName = lookupLogicalName();
    createElement(logicalName);
    {}//Logwriter.printOnConsole(logicalName + " added to penData");
    RecorderUtil.logActivity(getQxCtx(), "element created",   logicalName);
    }
  
  private void createElement(String logicalName) {
   {}//Logwriter.printOnConsole(logicalName);
   PENDataElement penDataElement = new PENDataElement();
   penDataElement.setItemName(logicalName);
   getPenData().add(penDataElement);
  }
}
