package zws.pen.policy.op.pendata.element.util;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.pen.policy.op.pendata.PENDataOpBase;
////import zws.util.{}//Logwriter;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class GetTxAttributeOp extends PENDataOpBase {

  public void execute() throws Exception{
    String attValue=null;
    Metadata txData = getPenData().lookupTxMetaData(this.getCurrentItem());
    {}//Logwriter.printOnConsole(this,"transformSourceMetadata ", txData);
    attValue = txData.get(this.getAttributeName());
    if(null == attValue) {
      attValue = getDefaultValue();
    }
    {}//Logwriter.printOnConsole("attValue :",attValue);
    this.setResult(attValue);
  }
  public String getAttributeName() {return attributeName;}
  public void setAttributeName(String name) { attributeName = name;}
  public String getDefaultValue() {   return defaultValue;  }
  public void setDefaultValue(String value) {defaultValue = value; }

  private String attributeName = null;
  private String defaultValue = null;
}
