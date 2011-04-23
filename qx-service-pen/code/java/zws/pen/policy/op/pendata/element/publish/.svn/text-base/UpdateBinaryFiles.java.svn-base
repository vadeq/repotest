package zws.pen.policy.op.pendata.element.publish;


/*
* DesignState - Design Compression Technology
* @author: Emmanuel Ankutse
* @version: 1.0
* Created on Nov 19, 2007
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.data.Metadata;
import zws.data.eco.ECO;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.target.RepositoryBinaryTarget;
import zws.repository.target.RepositoryECOTarget;
import zws.security.Authentication;
import zws.service.pen.PENDataElement;
import zws.service.pen.StatusElement;
import zws.service.pen.TxDataElement;
//import zws.util.{}//Logwriter;
import zws.util.RemotePackage;



public class UpdateBinaryFiles extends PENDataProcessor {
  
  
  private static final long serialVersionUID = 1L;

  public void process() throws Exception {    
    try {
      ECO eco = getPenData().lookupTargetECO(getCurrentItem());
      StatusElement statusElement = lookupStatusElement(getCurrentItem());
      boolean updateStatus = TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_FILE_NEEDS_TO_BE_UPDATED));
      if(!updateStatus) return;
      if(null != eco)update(eco);
      else update();
    } catch (Exception e) {
      String notificationSubject = "PEN: BINARY UPDATE FAILED! "+getCurrentItem();
      String notificationMessage="WARNING: "+"Attaching binary to item " + getCurrentItem() + " failed! "+e.toString();
      {}//Logwriter.printOnConsole(notificationMessage);
      {}//Logwriter.printOnConsole("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
      zws.Alert.notify(notificationSubject, notificationMessage);
    }
  }
  
  private void update() throws Exception {           
    String name = getCurrentItem();
    QxContext ctx = getQxCtx();   
    Authentication authentication = getAuthentication();    
    Repository targetRepository = getPenPolicy().getTargetRepository();
    RepositoryBinaryTarget binTarget = targetRepository.materializeBinaryTarget();    
    PENDataElement penElement = getPenData().lookupPENDataElement(name);
    TxDataElement txDataElement = penElement.getTxDataElement();
    Metadata txData = lookupTxMetadata(name);
   
    if (txDataElement.getBinaryFiles().size() == 0) {
      {}//Logwriter.printOnConsole("WARNING: No Binary to Attach for " + name);
      {}//Logwriter.printOnConsole("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");      
      return;
    }
    
    for (int i=0; i<txDataElement.getBinaryFiles().size(); i++) {
      RemotePackage binaryDesign  = (RemotePackage)txDataElement.getBinaryFiles().get(i);
      ctx.merge(txDataElement.getBinaryFileContext(binaryDesign), true);     
      binTarget.storeAttachment(ctx, txData.getName(), binaryDesign, authentication);
      RecorderUtil.logActivity(getQxCtx(), "Binary stored",  txData.getName() + " " +binaryDesign.getName());
    }             
    {}//Logwriter.printOnConsole("Done Attaching Binary for " + name);   
  }
  
  
  private void update(ECO eco) throws Exception {            
    String name = getCurrentItem();
    QxContext ctx = getQxCtx();      
    Authentication authentication = getAuthentication();    
    Repository targetRepository = getPenPolicy().getTargetRepository();
    RepositoryBinaryTarget binTarget = targetRepository.materializeBinaryTarget();    
    RepositoryECOTarget ecoTarget = targetRepository.materializeECOTarget();    
    Metadata txData = lookupTxMetadata(name);
    PENDataElement penElement = getPenData().lookupPENDataElement(name);
    TxDataElement txDataElement = penElement.getTxDataElement();   
    
    ecoTarget.addAffectedItem(getQxCtx(), eco.getNumber(), txData, getAuthentication());
    
    if (txDataElement.getBinaryFiles().size() == 0) {
      {}//Logwriter.printOnConsole("WARNING: No Binary to Attach for " + name);
      {}//Logwriter.printOnConsole("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");       
      return;
    }
    
    for (int i=0; i<txDataElement.getBinaryFiles().size(); i++) {
      RemotePackage binaryDesign  = (RemotePackage)txDataElement.getBinaryFiles().get(i);
      ctx.merge(txDataElement.getBinaryFileContext(binaryDesign), true);
      binTarget.storeAttachmentForECOItem(ctx, txData.getName(), eco.getNumber(), binaryDesign, authentication);
      RecorderUtil.logActivity(getQxCtx(), "Binary stored",  txData.getName() + " " +binaryDesign.getName());
    }             
    {}//Logwriter.printOnConsole("Done Attaching Binary for " + name);           
  } 
}

