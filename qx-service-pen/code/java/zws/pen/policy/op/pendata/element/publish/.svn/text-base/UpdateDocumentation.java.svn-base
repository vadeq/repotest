package zws.pen.policy.op.pendata.element.publish;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.data.eco.ECO;
import zws.exception.NameNotFound;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.target.RepositoryECOTarget;
import zws.service.pen.DocumentElement;
import zws.service.pen.StatusElement;
import java.util.Collection;
import java.util.Iterator;

public class UpdateDocumentation extends PENDataProcessor {
  public void process() throws Exception {
    ECO eco = getPenData().lookupTargetECO(getCurrentItem());
    DocumentElement documentElement = lookupDocumentElement(getCurrentItem());
    StatusElement statusElement = lookupStatusElement(getCurrentItem());
    boolean updateStatus = TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_DOC_NEEDS_TO_BE_ADDED));
    if(!updateStatus) return;
    if(null !=eco)addDocument(eco); 
    else addDocument(); 
  }

  private void addDocument() {
    //+++ todo
  }

  private void addDocument(ECO eco) throws Exception {
    String currentItem = getCurrentItem();
    String currentItemTargetName = lookupTxMetadata(currentItem).getName();
    DocumentElement docElement = lookupDocumentElement(currentItem);
    Repository targetRepository = getPenPolicy().getTargetRepository();
    RepositoryECOTarget ecoTarget = targetRepository.materializeECOTarget();
    Collection docRefNames = docElement.getDocumentRefNames();
    if (null==docRefNames) return;
    Iterator itr;
    itr = docRefNames.iterator();
    String docRefName;
    String docTargetName;
    Metadata targetDoc;
    QxContext bomAttributes = null;
    Metadata txData = lookupTxMetadata(getCurrentItem());
    ecoTarget.addAffectedItem(getQxCtx(), eco.getNumber(), txData, getAuthentication());    
    while(itr.hasNext()) {
      docRefName =(String) itr.next();
      targetDoc = lookupTargetMetadata(docRefName);
      if (null==targetDoc) throw new NameNotFound("Target data was not loaded for " +docRefName+ "! (was it deleted from "+targetRepository.getName()+"?)");
      docTargetName = targetDoc.getName();
      bomAttributes = docElement.getBOMAttributes(docRefName);      
      ecoTarget.redlineAdd(getQxCtx(), eco.getNumber(), currentItemTargetName, docTargetName, 1, bomAttributes, getAuthentication());
      RecorderUtil.logActivity(getQxCtx(), "Redline add",  "ECO:" + eco.getNumber());
      RecorderUtil.logActivity(getQxCtx(), "Redline add",  "currentItemTargetName:" + currentItemTargetName);
      RecorderUtil.logActivity(getQxCtx(), "Redline add",  "docTargetName:" + docTargetName);
      //RecorderUtil.logActivity(getQxCtx(), "Redline add",  "bomAttributes:" + bomAttributes);
    }      
  }
}

