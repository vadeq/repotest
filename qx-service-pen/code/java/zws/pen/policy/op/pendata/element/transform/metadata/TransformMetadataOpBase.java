package zws.pen.policy.op.pendata.element.transform.metadata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.service.pen.PENDataElement;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class TransformMetadataOpBase extends PENDataOpBase {

  public void execute() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    Metadata sourceData = penData.getSourceDataElement().getSourceData();
    Metadata txData = penData.getTxDataElement().getTxData();
    transformSourceMetadata(sourceData, txData);
  }
  
  public abstract void transformSourceMetadata(Metadata sourceData, Metadata txData) throws Exception;

}
