package zws.pen.policy.op.pendata.element.util;
/*
* DesignState - Design Compression Technology
* @author: ptoleti @version: 1.0 Created on Apr 27,
* 2007 9:58:02 AM Copywrite (c) 2007 Zero Wait-State
* Inc. All rights reserved
*/

import zws.data.Metadata;
import zws.pen.policy.op.pendata.PENDataOpBase;
////import zws.util.{}//Logwriter;

public class GetValueFromTargetOp extends PENDataOpBase {
  public void execute() throws Exception {
    Metadata targetData  = getPenData().lookupTargetMetaData(getCurrentItem());
    if(null == targetData) {
      setResult(null);
      {}//Logwriter.printOnConsole("Target data is not loaded for  " + getCurrentItem());
      return;
    }
    String s = targetData.get(getAttributeName());
    if (null==s) s = targetData.get(getAttributeName().toLowerCase());
    {}//Logwriter.printOnConsole("Target Attribute " + getAttributeName() + " value " + s);
    setResult(s);
  }
  private String attributeName = null;
  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String attrName) {attributeName = attrName;}
}
