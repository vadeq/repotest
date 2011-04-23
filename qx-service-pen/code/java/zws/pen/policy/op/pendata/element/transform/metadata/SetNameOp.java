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
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
//impoer zws.util.{}//Logwriter;

import java.util.Collection;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class SetNameOp extends AttributeSetterOPBase {

  public void transformSourceMetadata(Metadata sourceData, Metadata txData) throws Exception{
    {}//Logwriter.printOnConsole("SetNameOp execute....");
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
    //if(null == attValue) attValue = this.getDefaultValue();
    setAttribute(txData,QxContext.NAME, attValue);
    RecorderUtil.logActivity(getQxCtx(), "set name" ,  getAttributeName() + "="+ attValue);
    {}//Logwriter.printOnConsole("setting " + QxContext.NAME + " with " + attValue);
    {}//Logwriter.printOnConsole("txData " + txData);
  }
  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String name) { attributeName = name;}
  public String getAttributeValue() {return attributeValue;}
  public void setAttributeValue(String value) {attributeValue = value;}

  private String attributeName = null;
  private String attributeValue = null;
}
