package zws.pen.policy.op.pendata.element.retrieve.binary;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.service.pen.PENDataElement;

/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class RetrieveNativeFileOp extends RetrieveBinaryOpBase {

  protected void downloadBinary(Metadata sourceData) {
    //+++ todo - migrate code from RetrieveBinaryOp
  }
  public void process() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    Metadata sourceData = penData.getSourceDataElement().getSourceData();
    downloadBinary(sourceData);
  }
}
