package zws.pen.policy.op.pendata.element.status.item;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.data.Metadata;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.service.pen.StatusElement;

public class DetectNameConflictsOP extends PENDataProcessor {

  public void process() throws Exception {
    String conflict = FALSE;
    StatusElement statusElement= lookupStatusElement(getCurrentItem());
    String syncStatus = statusElement.getItemStatus(STAT_SYNC_STATUS);
    if(OPT_SYNCHRONIZED.equalsIgnoreCase(syncStatus) || OPT_MODIFIED.equalsIgnoreCase(syncStatus)) conflict = FALSE;
    else { //item has not been published, see if there is already one in the target...
      Repository targetRepository = this.getPenPolicy().getTargetRepository();
      RepositoryMetadataSource  target = targetRepository.materializeMetadataSource();
      Metadata transformedData = lookupTxMetadata(getCurrentItem());
      Metadata item = target.findLatest(getQxCtx(), transformedData.getName(), getAuthentication());
      if(null==item) conflict=FALSE;
      else {
        conflict=TRUE;
        statusElement.setItemStatus(DATA_NAME_CONFLICT_ORIGIN, item.getOrigin().toString());
      }
    }
    statusElement.setItemStatus(STAT_ITEM_HAS_NAME_CONFLICT, conflict);
    RecorderUtil.logActivity(getQxCtx(), "name conflict ", conflict+"("+getCurrentItem()+")");
 }
}
