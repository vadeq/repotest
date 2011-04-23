package zws.pen.policy.op.pendata.element.transform.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.pen.policy.op.pendata.PENDataOp;
import zws.recorder.util.RecorderUtil;

import java.util.Collection;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class SetAttributeOp extends AttributeSetterOPBase {

  public void transformSourceMetadata(Metadata sourceData, Metadata txData) throws Exception{
    {}//Logwriter.printOnConsole(this,"transformSourceMetadata","start");
    String attValue=null;
    PENDataOp op = null;
    Collection opList = getOps();
    attValue = getAttributeValue();
    if (null==attValue) attValue="";

    if(null!= opList && !opList.isEmpty()) {
        op = (PENDataOp) opList.iterator().next();
        passConfiguration(op);
        op.execute();
        attValue += (String) op.getResult();
     }
    if(null == attValue) attValue = this.getDefaultValue();
    {}//Logwriter.printOnConsole("setting " + getAttributeName() + " with " + attValue);
    setAttribute(txData,getAttributeName(), attValue);
    //RecorderUtil.logActivity(getQxCtx(), "setattribute" ,  getAttributeName() + "="+ attValue);
  }

  public String getDefaultValue() {   return defaultValue;  }
  public void setDefaultValue(String value) {defaultValue = value; }

  private String defaultValue = null;
}
