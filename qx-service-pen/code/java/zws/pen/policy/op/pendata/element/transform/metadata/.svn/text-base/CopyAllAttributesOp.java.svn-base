package zws.pen.policy.op.pendata.element.transform.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.application.Names;
import zws.data.Metadata;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;

import java.util.Iterator;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class CopyAllAttributesOp extends AttributeSetterOPBase{

  public void transformSourceMetadata(Metadata sourceData, Metadata txData) throws Exception{
    RecorderUtil.logActivity(getQxCtx(), "copy attributes",  sourceData.getName());
    QxContext tempCtx = RecorderUtil.startSubProcess(getQxCtx(), sourceData.getName(), "Copy attributes");
    String attName=null;
    String attValue=null;
    Iterator i = sourceData.getAttributes().keySet().iterator();
    setAttribute(txData, "name", sourceData.getName());
    RecorderUtil.logActivity(tempCtx, "attribute copied ",  "name="+ sourceData.getName());
    while (i.hasNext()) {
      attName = i.next().toString();
      attValue = sourceData.get(attName);
      setAttribute(txData, attName, attValue);
      RecorderUtil.logActivity(tempCtx, "attribute copied ",  attName + "="+ attValue);
    }
    RecorderUtil.endRecProcess(tempCtx, Names.STATUS_COMPLETE);
  }
}
