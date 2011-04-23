package zws.pen.policy.op.pendata.element.message;
/*
* DesignState - Design Compression Technology
* @author: arbind
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.service.pen.PENDataElement;
import zws.pen.policy.op.pendata.util.RecordMessageOpBase;

import java.util.Iterator;

public class RecordElementDebugMessage extends RecordMessageOpBase {

  protected void recordMessage(String s) {
    PENDataElement element= this.lookupPENDataElement(getCurrentItem());
    element.recordDebugMessage(s);    
  }

}
