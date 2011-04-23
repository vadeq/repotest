package zws.pen.policy.op.pendata.element.status.eco.pending;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.data.Metadata;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryECOSource;
import zws.service.pen.PENDataElement;

import java.util.Collection;
import java.util.Iterator;


/**
 * The Class UpdateStateAttributeOp.
 *
 * @author ptoleti
 */
public class DetectPendingECOOp extends PendingECOStatusOpBase {

  public void analyzeECOStatus(PENDataElement penData) throws Exception{
    Metadata txData = this.getPenData().lookupTxMetaData(getCurrentItem());
    Repository targetRepository = this.getPenPolicy().getTargetRepository();
    RepositoryECOSource ecoSource = targetRepository.materializeECOSource();
    Collection c = ecoSource.findPendingECOs(getQxCtx(), txData.getName(), getAuthentication());
    lookupStatusElement(getCurrentItem()).setItemStatus(STAT_ITEM_HAS_PENDING_ECO, FALSE);
    if(null != c) {
      Iterator itr = c.iterator();
      String ecoNumber= null;
      while(itr.hasNext()) {
        ecoNumber = (String) itr.next();
        addPendingECO(ecoNumber);
        lookupStatusElement(getCurrentItem()).setItemStatus(STAT_ITEM_HAS_PENDING_ECO, TRUE);
        lookupStatusElement(getCurrentItem()).setItemStatus(DATA_PENDING_ECO_NUMBER, ecoNumber);
        RecorderUtil.logActivity(getQxCtx(), "Set Pending ECO",  "Item No." + getCurrentItem() + " ECO No." +ecoNumber);
      }
    }
  }
}
