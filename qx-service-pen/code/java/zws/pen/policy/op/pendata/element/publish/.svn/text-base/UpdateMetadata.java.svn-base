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
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.target.RepositoryECOTarget;
import zws.repository.target.RepositoryMetadataTarget;
import zws.service.pen.StatusElement;

public class UpdateMetadata extends PENDataProcessor {
  public void process() throws Exception {    
    ECO eco = getPenData().lookupTargetECO(getCurrentItem());
    Repository targetRepository = this.getPenPolicy().getTargetRepository();
    Metadata transformedData = lookupTxMetadata(getCurrentItem());
    StatusElement statusElement = lookupStatusElement(getCurrentItem());
    boolean updateStatus = TRUE.equalsIgnoreCase(statusElement.getItemStatus(ACTION_METADATA_NEEDS_TO_BE_UPDATED));
    if(!updateStatus) return;
    if(null != eco)update(eco);
    else update();
    RepositoryMetadataSource source = targetRepository.materializeMetadataSource();
    Metadata data = source.findLatest(getQxCtx(), transformedData.getName(), getAuthentication());
    statusElement.setItemStatus(DATA_TARGET_ORIGIN, data.getOrigin().toString());
  }
  
  private void update() throws Exception {
    Repository targetRepository = this.getPenPolicy().getTargetRepository();
    RepositoryMetadataTarget metadataTarget = targetRepository.materializeMetadataTarget();
    Metadata transformedData = lookupTxMetadata(getCurrentItem());
    metadataTarget.update(getQxCtx(),transformedData, null, getAuthentication());
    RecorderUtil.logActivity(getQxCtx(), "Metadata Update",  transformedData.getName());
  }
  
  private void update(ECO eco) throws Exception {
    Repository targetRepository = this.getPenPolicy().getTargetRepository();
    RepositoryECOTarget ecoTarget = targetRepository.materializeECOTarget();
    Metadata txData = lookupTxMetadata(getCurrentItem());
    //ECOElement ecoElement = getPenData().lookupECOElement(getCurrentItem());

    //String targetECORefName = ecoElement.getTargetECO();
    //eco = getPenData().getECO(targetECORefName);
    //Metadata txData = getPenData().lookupTxMetaData(getCurrentItem());
    ecoTarget.addAffectedItem(getQxCtx(), eco.getNumber(), txData, getAuthentication());    
    ecoTarget.update(getQxCtx(), eco.getNumber(), txData, getAuthentication());    
    RecorderUtil.logActivity(getQxCtx(), "Metadata Update",  "ECO:" + eco.getNumber() + " Item No."+ txData.getName());
  }
  
}
