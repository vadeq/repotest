/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Nov 21, 2007 9:27:07 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.element.transform.binary;

import zws.data.Metadata;
import zws.data.MetadataSubComponentBase;
import zws.qx.QxContext;
import zws.service.pen.PENDataElement;
import zws.service.pen.SourceDataElement;
import zws.service.pen.TxDataElement;
import zws.util.RemotePackage;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class AdoptBinariesFromDeps extends TransformBinaryOpBase {

  private static final long serialVersionUID = 1L;

  /**
   * Move the binaries from the subcomponents into the parent
   */
  public void transformBinary(PENDataElement penElement) throws Exception {
    //the dependency structure between a drawing and dependents is 
    //reversed in the Tx so one CANNOT determine the children of the
    //drawing from Tx by calling getSubComponents on the drawing. 
    //Therefore, go to the sourceData
    SourceDataElement srcDataElement = lookupSourceDataElement(getCurrentItem());
    Metadata srcMetadata = srcDataElement.getSourceData();
    Collection c = srcMetadata.getSubComponents();
    if(null == c || c.isEmpty()) return;

    String subCompName = null;
    TxDataElement childTxData =null;
    TxDataElement parentTxData = penElement.getTxDataElement();   
    Iterator subcomps = c.iterator();
    while (subcomps.hasNext()) {
      childTxData =null;
      subCompName = ((MetadataSubComponentBase)subcomps.next()).getName().toLowerCase();      
      
      //before calling lookupTxDataElement for a subCompName, verify that the 
      //subCompName exists in the Tx. This eliminates source subcomponents 
      //of the drawing (collection obtained above) but that are filtered out in Tx
      if(null == getPenData().lookupPENDataElement(subCompName)) continue;
      childTxData = lookupTxDataElement(subCompName);
      if (null == childTxData) continue;
      
      ArrayList subCompBinaries = childTxData.getBinaryFiles();
      for (int i=0; i<subCompBinaries.size(); i++) {
        RemotePackage rf = (RemotePackage)subCompBinaries.get(i);
        QxContext rfCtx = childTxData.getBinaryFileContext(rf);        
        parentTxData.addBinaryFile(rf);
        parentTxData.addBinaryFileContext(rf, rfCtx);
      }
      
      childTxData.setBinaryFilesAdoptionCount(1+childTxData.getBinaryFilesAdoptionCount());
      //if all (DOC) parents of the subcomponent have done adopting  
      //from the subcomponent then remove the adopted binaries. 
      if (childTxData.getPENDataElement().getDocumentElement().getDocumentRefNames().size()==childTxData.getBinaryFilesAdoptionCount()) {
        childTxData.removeBinaryFilesAndContexts();          
      }      
    }//while
  }
  
  
}
