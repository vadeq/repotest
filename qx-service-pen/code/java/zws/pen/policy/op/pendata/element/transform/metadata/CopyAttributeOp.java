package zws.pen.policy.op.pendata.element.transform.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.recorder.util.RecorderUtil;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class CopyAttributeOp extends AttributeSetterOPBase {
  
  public void transformSourceMetadata(Metadata sourceData, Metadata txData) throws Exception{
    String attValue=sourceData.get(this.getAttributeName());
     this.setAttribute(txData,attributeName, attValue);
     RecorderUtil.logActivity(getQxCtx(), "attribute copied " + txData.getName(),  attributeName + "="+ attValue);
  }
  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String name) { attributeName = name;}
  private String attributeName = null;
}
