package zws.pen.policy.op.pendata.element.status.item;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/



import zws.application.Names;
import zws.data.Metadata;
import zws.origin.Origin;
import zws.repository.Repository;
import zws.service.pen.StatusElement;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;
import zws.synchronization.SynchronizationRecord;

import java.util.Collection;
import java.util.Iterator;



public class LoadSyncStatusOp extends ItemStatusOpBase {

  /**
   * @throws Exception Exception
   * @see zws.pen.policy.old.op.PENDataOpBase#execute()
   */
  public void setItemStatus(StatusElement statusElement) throws Exception {
    boolean isSynchronized = false;
    boolean isModified= false;
    boolean isRenumbered= false;
    Metadata sourceData = this.getPenData().lookupSrcMetaData(getCurrentItem());
    Origin sourceOrigin = sourceData.getOrigin();
    SynchronizationService r = SynchronizationClient.getClient();
    Collection c = r.findAllSynchronizationOrigins(sourceOrigin );
    /*if(null == c || c.isEmpty()) isModifed = hasBeenModified(sourceOrigin);
    else isSynchronized = hasBeenSynchronized(c);
*/
    if(null != c && !c.isEmpty()) isSynchronized = hasBeenSynchronized(c);
    if(!isSynchronized ) isModified = hasBeenModified(sourceOrigin);
    if(isModified) isRenumbered= hasBeenRenumbered(sourceOrigin);

    //See if this origin is already synchronized to the target repository
    //if not see if an earlier version was previously published
    if(isSynchronized) statusElement.setItemStatus(STAT_SYNC_STATUS, OPT_SYNCHRONIZED);    
    else if (isRenumbered) statusElement.setItemStatus(STAT_SYNC_STATUS, OPT_RENUMBERED);
    else if (isModified) statusElement.setItemStatus(STAT_SYNC_STATUS, OPT_MODIFIED);
    else  statusElement.setItemStatus(STAT_SYNC_STATUS, OPT_NEW_ITEM);
  }

  private boolean hasBeenSynchronized(Collection targetSynchronizations) {
    //See if this origin is already synchronized to the target repository
    boolean hasBeenSynchronized = false;
    Repository trgRep = getPenPolicy().getTargetRepository();
    String repDomain, repServer, repName;
    repDomain = trgRep.getDomainName();
    repServer = trgRep.getServerName();
    repName = trgRep.getName();
    Iterator i = targetSynchronizations.iterator();

    Origin trgOrigin = null;
    String trgDomain, trgServer, trgRepository;
    while (i.hasNext()&& !hasBeenSynchronized) {
      trgOrigin = (Origin) i.next();
      trgDomain = trgOrigin.getDomainName();
      trgServer = trgOrigin.getServerName();
      trgRepository = trgOrigin.getDatasourceName();
      if ( trgDomain.equalsIgnoreCase(repDomain) &&
          trgServer.equalsIgnoreCase(repServer) &&
          trgRepository.equalsIgnoreCase(repName)) {
        hasBeenSynchronized = true;
      }
    }
    return hasBeenSynchronized;
  }

  private boolean hasBeenModified(Origin sourceOrigin) throws Exception {
    //if an earlier version was previously published
    boolean hasBeenModified=false;
    String name = sourceOrigin.getName();
    String srcDomain = sourceOrigin.getDomainName();
    String srcServer = sourceOrigin.getServerName();
    String srcRepository= sourceOrigin.getRepositoryName();
    Repository trgRep = getPenPolicy().getTargetRepository();
    String repDomain, repServer, repRepository;
    repDomain = trgRep.getDomainName();
    repServer = trgRep.getServerName();
    repRepository = trgRep.getName();
    //Get the synch records for this name
    SynchronizationService r = SynchronizationClient.getClient();
    Collection c = r.findNameSynchronization(srcDomain, srcServer, srcRepository, name);
    if (null==c || c.isEmpty()) return false;
    SynchronizationRecord rec = null;
    Iterator i = c.iterator();
    Origin o1, oa;
    Origin trgOrigin = null;
    String trgDomain, trgServer, trgRepository;
    while(i.hasNext() && !hasBeenModified) {
      rec = (SynchronizationRecord)i.next();
      o1 = rec.getOrigin0();
      oa = rec.getOriginA();
      //identify which origin is the target
      if (sourceOrigin.isFromSameDatasource(o1)) trgOrigin = oa;
      else if (sourceOrigin.isFromSameDatasource(oa)) trgOrigin = o1;
      else continue; //does not come from same source as sourceOrigin
      //see if the target origin for this name matches the target repository
      trgDomain = trgOrigin.getDomainName();
      trgServer = trgOrigin.getServerName();
      trgRepository = trgOrigin.getDatasourceName();
      if (trgDomain.equalsIgnoreCase(repDomain) &&
          trgServer.equalsIgnoreCase(repServer) &&
          trgRepository.equalsIgnoreCase(repRepository)) {
          hasBeenModified = true;
        }
    }
    return hasBeenModified;
  }

  public boolean  hasBeenRenumbered(Origin sourceOrigin) {
    try {
      SynchronizationService s = SynchronizationClient.getClient();
      Origin lastSynchronization = s.lastSynchronization(sourceOrigin.getDomainName(), sourceOrigin
          .getServerName(), sourceOrigin.getRepositoryName(), sourceOrigin.getName());
      if (null == lastSynchronization)  return false;
      Collection c = s.findAllSynchronizationOrigins(lastSynchronization);
      Iterator i = c.iterator();
      Origin t;
      Repository targetRepository = getPenPolicy().getTargetRepository();
      while (i.hasNext()) {
        t = (Origin) i.next();
        if (!t.getDomainName().equals(targetRepository.getDomainName())) continue;
        else if (!t.getServerName().equals(targetRepository.getServerName())) continue;
        else if (!t.getRepositoryName().equals(
          targetRepository.getRepositoryName())) continue;
        else {
          Metadata txData = lookupTxMetadata(getCurrentItem());
          if(t.getName().equalsIgnoreCase(txData.getName())) return false;
          else return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return false;
  }
  

  //convenience function for synchronization
  //originIsSynchronized-to-trg (domainname, servername, repositorytype, repositoryname)
  //nameIsSynchronized-to-trg (domainname, servername, repositorytype, repositoryname)
  //getpreviouslysynchronizedorigin
  //public Collection getSyncchronizedNameRecord() { return syncdNameRecords; }
  //public Collection getSyncronizedOrigins() { return syncdOrigins ; }
  //private Collection syncdOrigins = new ArrayList();
  //private Collection syncdNameRecords= new ArrayList();
}
