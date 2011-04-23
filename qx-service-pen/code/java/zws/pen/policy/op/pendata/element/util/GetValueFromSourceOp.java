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

public class GetValueFromSourceOp extends PENDataOpBase {
  public void execute() throws Exception {
      Metadata m  = getPenData().lookupSrcMetaData(getCurrentItem());
      if(null == m) {
        setResult(null);
        {}//Logwriter.printOnConsole("Source data is not loaded for  " + getCurrentItem());
        return;
      }
      String s = m.get(getAttributeName());
      {}//Logwriter.printOnConsole("Source Attribute " + getAttributeName() + " value " + s);
      setResult(s);
  }
  private String attributeName = null;
  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String attrName) {this.attributeName = attrName;}
}
