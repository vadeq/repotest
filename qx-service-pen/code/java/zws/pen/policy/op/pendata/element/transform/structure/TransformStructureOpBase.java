package zws.pen.policy.op.pendata.element.transform.structure;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.service.pen.PENDataElement;
import zws.service.pen.TxDataElement;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public abstract class TransformStructureOpBase extends PENDataProcessor {

  public void process() throws Exception {
    PENDataElement penData = lookupPENDataElement(getCurrentItem());
    Metadata sourceData = penData.getSourceDataElement().getSourceData();
    transformStructure(sourceData, penData.getTxDataElement());
  }
  
  public abstract void transformStructure(Metadata sourceData, TxDataElement txDataElement) throws Exception;

}
