package zws.pen.policy.op.pendata.element.util;
/*
* DesignState - Design Compression Technology
* @author: ptoleti @version: 1.0 Created on Apr 27,
* 2007 9:58:02 AM Copywrite (c) 2007 Zero Wait-State
* Inc. All rights reserved
*/

import zws.data.Metadata;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
//impoer zws.util.{}//Logwriter;

public class SetValueInTargetOp extends PENDataOpBase {

  public void execute() throws Exception {
    String attrValue = null;
    Metadata targetData = this.getPenData().lookupTargetMetaData(getCurrentItem());
    {}//Logwriter.printOnConsole("Set Attribute name " + getAttributeName());
    if (null != getAttributeValue()) {
      attrValue = getAttributeValue();
    } else if(null != getOperand()){
      passConfiguration(operand);
      operand.execute();
      attrValue = (String) operand.getResult();
    }

    {}//Logwriter.printOnConsole("Set Attribute value " + attrValue);
    if(null != attrValue) {
      targetData.set(getAttributeName(), attrValue);
      RecorderUtil.logActivity(getQxCtx(), "set in target data", getAttributeName() + "=" + attrValue);
    }
  }
  public void add(PENDataOpBase op) throws Exception {
    if (null==getOperand()) setOperand(op);
    else throw new Exception("To Many operands! operand is already set");
  }

  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String attrName) {attributeName = attrName;}
  public String getAttributeValue() {return attributeValue;}
  public void setAttributeValue(String attrValue) {attributeValue = attrValue;}
  public PENDataOpBase getOperand() {return operand;}
  public void setOperand(PENDataOpBase o) {operand = o;}
  private String attributeName = null;
  private String attributeValue = null;
  private PENDataOpBase operand = null;
}
