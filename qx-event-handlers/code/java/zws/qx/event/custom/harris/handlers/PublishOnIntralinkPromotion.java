package zws.qx.event.custom.harris.handlers;

/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Apr 9, 2007 3:17:33 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.Alert;
import zws.data.Metadata;
import zws.qx.QxContext;
import zws.qx.event.handler.EventHandlerBase;
import zws.qx.event.handler.QxHandler;
import zws.repository.Repository;
import zws.service.pen.PenQueuePlugin;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.util.RoutedEventBase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import zws.application.Names;
import zws.application.Properties;
import zws.application.messageconstants.MessageConstants;
import zws.origin.Origin;
import zws.repository.target.RepositoryStateTarget;

public class PublishOnIntralinkPromotion extends EventHandlerBase implements QxHandler{

  private static Collection untouchables = Properties.getCollection("untouchable-folders");
  private static Collection nonpublish = Properties.getCollection("non-publishing-folders");
  
  public void execute(QxContext eventContext, RoutedEventBase event) {

    int count = 0;
    int pub = 0;
    int no_pub = 0;
    int untouchable = 0;
    
    try {
      String user = (String)event.get("author");
      String repositoryName = (String)event.get("repository-name");
      String userMailID = user + "@harris.com";
      Collection originList = new ArrayList();
      Collection metadataList = event.getMetadataList();
      //metadataList = eliminateDuplicates(metadataList);
      if(null == metadataList) return;
      Iterator itr = metadataList.iterator();
      
      while(itr.hasNext()) {
        Metadata data = (Metadata) itr.next();
        if("pending".equalsIgnoreCase(data.get("new-release-level"))) {
          originList.add(data.getOrigin());
        }
        
        // increment the items that are available for publish
        count++;
        
        if(isSpecialFolder(nonpublish, data.get("folder")) ) {
          no_pub++;
        } else if(isSpecialFolder(untouchables, data.get("folder")) ) {
          untouchable++;
        } else {
          pub++;
        }
       
        repositoryName = data.getOrigin().getDatasourceName();
      }
 
      RepositoryService r = RepositoryClient.getClient();
      Repository sourceRepository = r.findRepository(repositoryName);
      RepositoryStateTarget stateTarget = sourceRepository.materializeStateTarget();
      
      /*  Truth table:
            Case 1 -- All items are pub:  let it through (default behavior)
            Case 2 -- Combination of pub and something else: cancel, notify, demote
            Case 3 -- All Untouchable: cancel, notify, demote
            Case 4 -- ALL is Untouchable and No-Pub: ignore 
            Case 5 -- All is No-Pub: ignore */
      
      //  if ( case 2 ) or ( case 3 ) then cancel
      if ((pub>0 && pub != count) || (untouchable==count))  {   
        cancelAndNotify(eventContext, stateTarget, originList,userMailID );   
        return;
      } 
      
      // if ( case 4 ) or ( case 5 ) then ignore
      else if ((no_pub+untouchable==count) || (no_pub==count)){
        return;        
      }
      
      PenQueuePlugin penQ = new PenQueuePlugin();      

      eventContext.set(QxContext.ORIGINATING_USER, user);
      eventContext.set(QxContext.ORIGINATING_EVENT, "promote");
      
      if(null == originList || originList.isEmpty()) return;
      eventContext.set(Names.SUMMARY, "Published from Ilink promotion.");
      String recipients = Properties.get("email-recipients");
      recipients = recipients + "|" + userMailID;
      penQ.publish(originList,"publish","1", sourceRepository.getName(), recipients, 
          eventContext, sourceRepository.getSystemAuthentication());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void cancelAndNotify(QxContext context, RepositoryStateTarget target, Collection origins, String userMailID) throws Exception {
    
    // demote and prepare origin list for email
    StringBuffer items = new StringBuffer();
    Iterator iterator = origins.iterator();
    Origin origin;
    
    while (iterator.hasNext()) {
      origin = (Origin) iterator.next();
      target.demoteLifeCycleReleaseState(context, origin, "WIP", target.getSystemAuthentication());
      items.append(origin.getName()).append(" ");
    }

    // send email notification
    Map messageConstantParams = new HashMap();
    messageConstantParams.put("published-items", items.toString());
    
    String subject = MessageConstants.resolve("email.msg.title.handler-canceled");
    String body = MessageConstants.resolve("email.subject.handler-canceled", messageConstantParams);
    String recipients = Properties.get("email-recipients");
    recipients = recipients + "|" + userMailID;    
    Alert.notify(subject, body, recipients);   
  }
  
  private boolean isSpecialFolder(Collection folderNames, String folder) {
    
    Iterator iterator = folderNames.iterator();
    boolean found = false;
    Object item;
    String value;
    boolean hasWildcard = false;
    
    while(iterator.hasNext()) {        
      item = iterator.next();
      if (item == null) continue;
      value = item.toString();
 
      if (value.endsWith("*")) {
        value = value.substring(0,value.indexOf("*")-1);
        hasWildcard = true;
      } else {
        hasWildcard = false;
      }
      
      // add an ending slash if the folder name has one
      if (value.endsWith("/") && !folder.endsWith("/"))
        folder = folder + "/";
      
      if (hasWildcard && folder.startsWith(value)) {
        found = true;
        break;
      } else if (value.equals(folder)) {
        found = true;
        break;
      }
    }
    
    return found;
  }
  
  public boolean handles(QxContext ctx, RoutedEventBase event) {
    if(eventType.equals(event.getEventType()))
      return true;
    else
      return false;
  }

  private Collection eliminateDuplicates(Collection srcMetadaDataList) {
    HashMap metadtaList = new HashMap();
    Iterator itr = srcMetadaDataList.iterator();
    while(itr.hasNext()) {
      Metadata data = (Metadata) itr.next();
      metadtaList.put(data.getName(), data);
    }
    return metadtaList.values();
  }
}

