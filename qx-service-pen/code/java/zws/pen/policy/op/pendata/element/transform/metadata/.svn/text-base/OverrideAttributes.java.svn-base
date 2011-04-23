package zws.pen.policy.op.pendata.element.transform.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.util.StringUtil;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class OverrideAttributes extends AttributeSetterOPBase {

  public void transformSourceMetadata(Metadata sourceData, Metadata txData) throws Exception{
    //context will have partName="attr1=newValue1,attr2=newValue2, attr3=newValue3";
    //String ctxAttValue=getQxCtx().get(QxContext.NAME + "-" + sourceData.getName());
    // convert to XML attribute if name contains spaces
    String ctxAttValue=getQxCtx().get(StringUtil.name2xmlAttribute(QxContext.NAME + "-" + sourceData.getName()));
    if (null == ctxAttValue) return;

    String attrKeyValuePair = null;
    String attrName = null;
    String newValue = null;
    int idx =0;
    String[] newAttValuePairs = ctxAttValue.split(",");
    while(idx<newAttValuePairs.length) {
        attrKeyValuePair = newAttValuePairs[idx++];
        attrName = attrKeyValuePair.split("=")[0];
        newValue = attrKeyValuePair.split("=")[1];
        setAttribute(txData,attrName.trim(), newValue.trim());
        RecorderUtil.logActivity(getQxCtx(), "override attribute " + txData.getName(),  attrName.trim() +"="+ newValue.trim());
    }
  }
}
