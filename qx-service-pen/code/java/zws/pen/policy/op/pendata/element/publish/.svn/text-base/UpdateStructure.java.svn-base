package zws.pen.policy.op.pendata.element.publish;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.data.MetadataSubComponent;
import zws.data.eco.ECO;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.target.RepositoryECOTarget;
import zws.service.pen.PENDataElement;
import zws.service.pen.RedlineElement;
import zws.service.pen.StatusElement;
import zws.service.pen.TxDataElement;
//import zws.util.{}//Logwriter;

import java.util.Iterator;

public class UpdateStructure extends PENDataProcessor {
  public void process() throws Exception {
    ECO eco = getPenData().lookupTargetECO(getCurrentItem());
    Metadata transformedData = lookupTxMetadata(getCurrentItem());
    StatusElement statusElement = lookupStatusElement(getCurrentItem());
    boolean updateStatus = TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_METADATA_NEEDS_TO_BE_UPDATED));
    if(!updateStatus) return;
    if(null !=eco)redline(eco); 
    else redline(); 
  }
    
  private void redline() {
    //+++ todo
  }

  private void redline(ECO eco) throws Exception {
    Repository targetRepository = this.getPenPolicy().getTargetRepository();
    String currentItem = getCurrentItem();
    TxDataElement txDataElement = this.lookupTxDataElement(currentItem);
    RepositoryECOTarget ecoTarget = targetRepository.materializeECOTarget();
    Metadata txData = getPenData().lookupTxMetaData(currentItem);
    TxDataElement txElement = getPenData().lookupPENDataElement(currentItem).getTxDataElement();
    RedlineElement reds = lookupRedlineElement(currentItem);
    MetadataSubComponent subItem = null;

    ecoTarget.undoPriorRedlines(getQxCtx(), eco.getNumber(), txData.getName(), getAuthentication());

    ecoTarget.addAffectedItem(getQxCtx(), eco.getNumber(), txData, getAuthentication());
    Iterator itr;

    itr = reds.getRedlineAddList().iterator();
    String srcSubcomponent;
    String targetSubcomponent;
    while(itr.hasNext()) {
      targetSubcomponent = (String)itr.next();
      PENDataElement penElement = this.getPenData().lookupPENDataElementByTargetName(targetSubcomponent);
      if(null == penElement) throw new zws.exception.NameNotFound("PENDataElement not found for target " + targetSubcomponent);
      srcSubcomponent = penElement.getItemName();
      subItem = txDataElement.getSubComponent(srcSubcomponent);
      if (null==subItem) throw new zws.exception.NameNotFound("redline add "+ targetSubcomponent+ " not found as subcomponent of " + txData.getName());
      ecoTarget.redlineAdd(getQxCtx(), eco.getNumber(), txData.getName(), targetSubcomponent, subItem.getQuantity(),txElement.getBOMAttributes(srcSubcomponent), getAuthentication());
      RecorderUtil.logActivity(getQxCtx(), "Redline add",  "ECO:" + eco.getNumber() + " Item No."+ txData.getName());
      {}//Logwriter.printOnConsole(targetSubcomponent + " redline add under " + txData.getName());
    }      

    itr = reds.getRedlineDeleteList().iterator();
    while(itr.hasNext()) {
      targetSubcomponent  = (String)itr.next();
      ecoTarget.redlineDelete(getQxCtx(), eco.getNumber(), txData.getName(), targetSubcomponent, getAuthentication());
      RecorderUtil.logActivity(getQxCtx(), "Redline delete",  "ECO:" + eco.getNumber() + " Item No."+ txData.getName());
      {}//Logwriter.printOnConsole( targetSubcomponent + " redline deleted under " + txData.getName());
    }      

    QxContext bomAttributes;
    itr = reds.getRedlineModifyList().iterator();
    while(itr.hasNext()) {
      targetSubcomponent  = (String)itr.next();
      PENDataElement penElement = this.getPenData().lookupPENDataElementByTargetName(targetSubcomponent);
      if(null == penElement) throw new zws.exception.NameNotFound("PENDataElement not found for " + targetSubcomponent);
      srcSubcomponent = penElement.getItemName();      
      subItem = txDataElement.getSubComponent(srcSubcomponent );
      if (null==subItem) throw new zws.exception.NameNotFound("redline modify "+ targetSubcomponent+ " not found as subcomponent of " + txData.getName());
      bomAttributes = txDataElement.getBOMAttributes(srcSubcomponent );
      ecoTarget.redlineModify(getQxCtx(), eco.getNumber(), txData.getName(), targetSubcomponent , bomAttributes, getAuthentication());
      RecorderUtil.logActivity(getQxCtx(), "Redline Modify",  "ECO:" + eco.getNumber() + " Item No."+ txData.getName());
      {}//Logwriter.printOnConsole(targetSubcomponent + " redline modify under " + txData.getName());
    }      
  }
  
  /*private String lookupSrcBinding(String targetSubcomponent) { 
   String srcSubcomponent = lookupPENDataElement(getCurrentItem()).lookupSrcBinding(targetSubcomponent);
   return srcSubcomponent;
  }*/

}

