package zws.qx.event.custom.harris.handlers;

/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Apr 9, 2007 3:17:33 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.origin.IntralinkOrigin;
import zws.application.Names;
import zws.data.Metadata;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
import zws.exception.CanNotMaterialize;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.event.handler.EventHandlerBase;
import zws.qx.event.handler.QxHandler;
import zws.util.RoutedEventBase;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryECOSource;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.target.RepositoryStateTarget;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;




public class UpdateIntralinkOnAgileECOUpdate extends EventHandlerBase implements QxHandler{
  public void execute(QxContext eventContext, RoutedEventBase event) throws Exception {
    /*
      <event user="admin" type="3" time="2007.11.15.14.27.18" status="Pending" event-type="eco-updated" number="C01604"/>
    */

    String ecoNumber = (String)event.get("number");
    String user = (String)event.get("user");
    String agileState = (String)event.get("status");
    if (null!=agileState) agileState=agileState.toLowerCase();
    String repositoryName = (String)event.get("repository-name");

    setEventType((String)event.get("event-type"));
    {} //System.out.println(eventType + " triggered for " + agileState);
     // get repository name
     // get eco
     // get all affected items from eco
     // for each affected item
     //     lookup sync record
     //     if no sync record continue
     //     if sync status is synchronized
     //        call corresponding function for given status

    RecorderUtil.logActivity(eventContext, "processing ECO", ecoNumber);
    SynchronizationService r = null;
    Origin latestAgileOrigin = null;
    Origin latestIlinkOrigin = null;
    AffectedItem affectedItem = null;
    Origin affectedItemOrigin = null;
    String srcRepositoryName = null;
    ECO eco = null;
    Collection affectedItems = null;
    Iterator affectedItemsItr = null;
    Authentication id = null;
    RepositoryService rc = RepositoryClient.getClient();
    Repository targetRepository = rc.findRepository(repositoryName);
    RepositoryECOSource ecoSource;
    try {
      ecoSource = targetRepository.materializeECOSource();
    } catch (CanNotMaterialize e1) {
      throw new Exception(e1);
    }
    id = ecoSource.getSystemAuthentication();
    eco = ecoSource.findECO(eventContext, ecoNumber, id);
    affectedItems = eco.getAffectedItems();

    affectedItemsItr = affectedItems.iterator();
    while(affectedItemsItr.hasNext()) {
      affectedItem = (AffectedItem) affectedItemsItr.next();
      affectedItemOrigin = affectedItem.getOrigin();
      r = SynchronizationClient.getClient();
      try {
        latestAgileOrigin = r.lastSynchronization(affectedItemOrigin.getDomainName(), affectedItemOrigin.getServerName(),
                                               affectedItemOrigin.getRepositoryName(), affectedItemOrigin.getName());
      } catch (Exception e) {
        {} //System.out.println("******* IT IS SAFE TO IGNORE PREVOUS zws.exception.CanNotMaterialize: Origin[Not enough tokens delimited by |] *****");
        continue;
      }

      {} //System.out.println("latestOrigin -----> " + latestAgileOrigin);
      if(null==latestAgileOrigin) continue;
      Collection allSyncOrigins = r.findAllSynchronizationOrigins(latestAgileOrigin);
      if(null==allSyncOrigins) continue;
      Iterator allSyncItr = allSyncOrigins.iterator();
      while(allSyncItr.hasNext()) {
        Origin syncOrigin = (Origin) allSyncItr.next();
        srcRepositoryName = syncOrigin.getRepositoryName();
        Repository srcRepository = rc.findRepository(srcRepositoryName);
        RepositoryStateTarget srcStateTarget = srcRepository.materializeStateTarget();
        id = srcStateTarget.getSystemAuthentication();
        {} //System.out.println("adding  -----> " + syncOrigin);
        if(agileState.equalsIgnoreCase("pending")) {
          srcStateTarget.unlock(eventContext, syncOrigin, id);
        } else if(agileState.equals("submitted")) {
          srcStateTarget.lock(eventContext, syncOrigin, id);
        } else if(agileState.startsWith(("cancel"))){
          srcStateTarget.unlock(eventContext, syncOrigin, id);
          srcStateTarget.demoteLifeCycleReleaseState(eventContext, syncOrigin, "WIP", id);
        } else if(agileState.equals("released")) {
          srcStateTarget.unlock(eventContext, syncOrigin, id);
          latestIlinkOrigin = r.lastSynchronization(syncOrigin.getDomainName(),syncOrigin.getServerName(),
              syncOrigin.getRepositoryName(), syncOrigin.getName());
          // if latestAgileOrigin is syncOrigin
          if(latestIlinkOrigin.isExactlyTheSame(syncOrigin)) {
            if(isRevisonSynchronized((IntralinkOrigin)syncOrigin,user,eventContext,id)) {
             srcStateTarget.promoteLifeCycleReleaseState(eventContext, syncOrigin, "Released", id);
            }
          }
      }
    }

    }
  }

  public boolean handles(QxContext ctx, RoutedEventBase event) {
    if(eventType.equals(event.getEventType()))
      return true;
    else
      return false;
  }

  private boolean isRevisonSynchronized(IntralinkOrigin syncOrigin, String originator, QxContext ctx, Authentication id) throws Exception {
    boolean result = false;
    RepositoryService rc = RepositoryClient.getClient();
    Repository srcRepository = rc.findRepository(syncOrigin.getDatasourceName());
    RepositoryMetadataSource metadataSource = srcRepository.materializeMetadataSource();
    Metadata data = metadataSource.findLatest(ctx, syncOrigin.getName(), id);
    IntralinkOrigin ilinkLatestOrigin = (IntralinkOrigin) data.getOrigin();
    if(ilinkLatestOrigin.getRevision().equalsIgnoreCase(syncOrigin.getRevision()) &&
       ilinkLatestOrigin.getVersion() == syncOrigin.getVersion()) result = true;
    else sendMessage(syncOrigin, ilinkLatestOrigin, originator);
    return result;
  }

  private void sendMessage(IntralinkOrigin syncOrigin,IntralinkOrigin ilinkOrigin, String originator) {
    //String recipients = null;
    //recipients= Properties.get(Names.EMAIL_RECIPIENTS);
    //if(null!=recipients && recipients.length() >0) recipients += "|";
    //recipients = originator + "@harris.com";
    String subject ="Intralink version and Agile version are not matched for " + syncOrigin.getName();
    String message ="Synchronized item: " + syncOrigin.toString();
    message += Names.NEW_LINE;
    message += "Intralink latest item: " + ilinkOrigin.toString();
    Collection harrisUser = new ArrayList();
    harrisUser.add(originator + "@harris.com");
    zws.Alert.notify(subject, message);
    zws.Alert.notify(subject, message,harrisUser);
  }
}
