package zws.pen.policy.op.pendata.element.status.item;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/


import zws.Server;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.service.pen.StatusElement;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;
import zws.synchronization.SynchronizationRecord;
//import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Iterator;

public class DetectSynchronizationConflictsOP extends PENDataProcessor {

  public void process() throws Exception {
    {}//Logwriter.printOnConsole(this,"analyzeItemStatus","start");
    String synchronizationConflict = FALSE;
    StatusElement statusElement= lookupStatusElement(getCurrentItem());
    String nameConflict = statusElement.getItemStatus(STAT_ITEM_HAS_NAME_CONFLICT);
    if(TRUE.equalsIgnoreCase(nameConflict)) {
      //item has not been published
      //but the item is already in agile
      //see if the agile-item is synchronized to something else..
      boolean hasSyncConflict = hasSynchronizationConflict();
      if (hasSyncConflict) synchronizationConflict = TRUE;
    }
    statusElement.setItemStatus(STAT_ITEM_HAS_SYNCHRONIZATION_CONFLICT, synchronizationConflict);
    RecorderUtil.logActivity(getQxCtx(), "sync conflict " , synchronizationConflict+ "(" + getCurrentItem() + ")");
  }

  private boolean hasSynchronizationConflict() throws Exception {
    StatusElement statusElement= lookupStatusElement(getCurrentItem());
    String originOftargetNameConflict=statusElement.getItemStatus(DATA_NAME_CONFLICT_ORIGIN);
    if (null==originOftargetNameConflict) throw new zws.exception.zwsException("target origin for conflict is unknown");
    Origin o = OriginMaker.materialize(originOftargetNameConflict);
    boolean synkConflict = hasSynkConflict(o);
    return synkConflict;
  }

  private boolean hasSynkConflict(Origin targetOrigin) throws Exception {
    String trgDomain, trgServer, trgRepository;
    trgDomain = targetOrigin.getDomainName();
    trgServer = targetOrigin.getServerName();
    trgRepository = targetOrigin.getRepositoryName();

    Repository srcRep = getPenPolicy().getSourceRepository();
    String repDomain = srcRep.getDomainName();
    String repServer = srcRep.getServerName();
    String repRepository= srcRep.getRepositoryName();

    //Get the synch records for the target name
    SynchronizationService r = SynchronizationClient.getClient();
    Collection c = r.findNameSynchronization(trgDomain, trgServer, trgRepository, targetOrigin.getName());
    if (null==c || c.isEmpty()) return false;
    SynchronizationRecord rec = null;
    Iterator i = c.iterator();
    Origin o1, oa;
    Origin srcOrigin = null;
    String srcDomain, srcServer, srcRepository;
    boolean conflictFound = false;
    while(i.hasNext() && !conflictFound) {
      rec = (SynchronizationRecord)i.next();
      o1 = rec.getOrigin0();
      oa = rec.getOriginA();
      //identify which origin is the target
      if (targetOrigin.isFromSameDatasource(o1)) srcOrigin = oa;
      else if (targetOrigin.isFromSameDatasource(oa)) srcOrigin = o1;
      else continue; //does not come from same source as sourceOrigin
      //see if the target origin for this name matches the target repository
      srcDomain = srcOrigin.getDomainName();
      srcServer = srcOrigin.getServerName();
      srcRepository = srcOrigin.getDatasourceName();
      if (srcDomain.equalsIgnoreCase(repDomain) &&
          srcServer.equalsIgnoreCase(repServer) &&
          srcRepository.equalsIgnoreCase(repRepository)) {
          conflictFound = true;
          StatusElement statusElement= lookupStatusElement(getCurrentItem());
          statusElement.setItemStatus(DATA_SYNCHRONIZATION_CONFLICT_UID, srcOrigin.getUniqueIDDisplay());
      }
    }
    return conflictFound;
  }

}
