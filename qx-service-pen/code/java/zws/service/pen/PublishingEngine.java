package zws.service.pen; /*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Mar 29, 2007 10:23:29 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.Alert;
import zws.application.Names;
import zws.application.messageconstants.MessageConstants;
import zws.context.Context;
import zws.data.Metadata;
import zws.exception.NameNotFound;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.pen.policy.PENPolicy;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.security.Authentication;
import zws.service.pen.policy.PENPolicySvc;
import zws.util.MapUtil;
import zws.util.StringUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Class PublishingEngine.
 *
 * @author ptoleti
 */
public class PublishingEngine {


  /**
   * @param origin origin
   * @param penPolicyName policy name
   * @param authID authentication
   * @param ctx qxcontext
   * @throws Exception exception
   */
  public static void publish(QxContext ctx, Collection originsToPublish, String penPolicyName, String recipients, Authentication authID) throws Exception {
    publish(ctx, originsToPublish, findPolicy(penPolicyName), recipients, authID);
  }

  // this method will construct the PENData object to share the common Context object
  // for origins grouped by policy name
  public static void publish(QxContext ctx, HashMap originPolicyMap, HashMap priorityMap, String recipients, Authentication authID) throws Exception {
    // originPolicyMap  origin1 --> policy1 origin2 --> policy1 origin3 --> policy3
    Collection originsToPublish = null;
    String policyName = null;
    String o = null;
    String priority = null;
    PENData penData = null;
    Context context = null;
    Map policyMap = new HashMap();  // policy map with policyName -> collection of origins
    Map policyPriorityMap = null;
    Iterator itr = originPolicyMap.keySet().iterator();
    while(itr.hasNext()) {
      o = (String)itr.next();
      policyName = (String) originPolicyMap.get(o);
      originsToPublish = MapUtil.getCollectionFromMap(policyMap, policyName);
      originsToPublish.add(OriginMaker.materialize(o));
    }

    policyPriorityMap = new TreeMap(priorityMap);
    Iterator priorityItr = policyPriorityMap.keySet().iterator();
    while(priorityItr.hasNext()) {
      priority = (String) priorityItr.next();
      policyName = (String) policyPriorityMap.get(priority);
      Collection originList = (Collection) policyMap.get(policyName);
      if(null == context) {
        penData = new PENData(originList);
        context = penData.lookupPublishingContext();
      } else {
        penData = new PENData(originList, context);
      }
      publish(ctx, findPolicy(policyName), penData,recipients, authID);
    }
  }

  public static void publish(QxContext ctx, Collection originsToPublish, PENPolicy penPolicy, String recipients, Authentication id) throws Exception {
    PENData penData = new PENData(originsToPublish);
    publish(ctx, penPolicy, penData,recipients, id);
  }

  public static void publish(QxContext ctx, PENPolicy penPolicy, PENData penData,String recipients, Authentication id) throws Exception {
    String processStatus = null;
    QxContext publishCtx = null;
    String subject = null;
    String stackTrace = null;
    StringBuffer mailMessage = new StringBuffer();
    boolean notify = true;
    
    
    Collection originsToPublish = penData.getOriginsToPublish();
    String originUIDs = PENPolicy.displayOrigins(originsToPublish);
    System.out.println("");
    System.out.println("_____________________________________________________");
    System.out.println("Publishing " + StringUtil.truncateWithIndicator(originUIDs, 400));
    System.out.println("_____________________________________________________");
    
    Map messageConstantParams = new HashMap();
    messageConstantParams.put("published-items", originUIDs);
    messageConstantParams.put("policy-name", penPolicy.getName());
     
    try {
      // start the child process for publishData
      // if originUIDs are more then don't display all origins
      String processDesc = originUIDs;
      if(processDesc.length() > 40 ) {
        processDesc = processDesc.substring(0, 40) + " ...";
      } 
      
      publishCtx = RecorderUtil.startSubProcess(ctx, Names.PEN_PUBLISH_DATA, processDesc);
      RecorderUtil.logActivity(publishCtx, "Using",  penPolicy.getName());
      RecorderUtil.logActivity(publishCtx, "Publishing", originUIDs);
      RecorderUtil.setStatus(publishCtx, zws.application.Names.STATUS_PUBLISHING);
      penPolicy.open(id);
      penPolicy.getData(publishCtx, penData, penPolicy, originsToPublish, id);
      penPolicy.transformSource(publishCtx, penData, penPolicy, originsToPublish, id);
      penPolicy.analyzeTxData(publishCtx, penData, penPolicy, originsToPublish, id);
      logTxDataToRecorder(publishCtx, penData);
      
      //get notification flag setting after analyze section in policy is executed  
      //but before destroyPulishingContext() is called
      notify = penData.doNotify();      
      
      if (penData.isCancelled()) {
        penPolicy.cancelPublish(publishCtx, penData, penPolicy, originsToPublish, id);
        processStatus = Names.STATUS_CANCELED;
        subject = MessageConstants.resolve("email.subject.pen-canceled", messageConstantParams);
        mailMessage.append(MessageConstants.resolve("email.msg.title.pen-canceled"));
        mailMessage.append(Names.NEW_LINE);
        penData.destroyPulishingContext();
      }
      else if (penData.isMarkedForRepublishing()) {
        QueueOriginsForRepublish(publishCtx, penPolicy,  penData, id);         
        
        processStatus = Names.STATUS_REPUBLISHING;
        subject = MessageConstants.resolve("email.subject.pen-republished", messageConstantParams);
        mailMessage.append(MessageConstants.resolve("email.msg.title.pen-republished"));        
        mailMessage.append(Names.NEW_LINE);
        PenQueuePlugin penQ = new PenQueuePlugin();
        //penQ.publish(originsToPublish,"republish","2",penPolicy.getTargetRepositoryName(), recipients, id);
        QxContext republishCtx = new QxContext();
        republishCtx.set("originator", publishCtx.get("originator"));
        republishCtx.set("originating-event", publishCtx.get("originating-event"));
        republishCtx.set(Names.SUMMARY, "Published from PEN republish.");
        penQ.publish(originsToPublish, "republish","2",penPolicy.getTargetRepositoryName(), recipients, republishCtx, id);
        penData.unsetRepublishing();
      }
      else {
        penPolicy.publishTargetData(publishCtx, penData, penPolicy, originsToPublish, id);
        penPolicy.updateSource(publishCtx, penData, penPolicy, originsToPublish, id);
        processStatus = Names.STATUS_COMPLETE;
        subject = MessageConstants.resolve("email.subject.pen-published-ok", messageConstantParams);
        mailMessage.append(MessageConstants.resolve("email.msg.title.pen-published-ok"));
        mailMessage.append(Names.NEW_LINE);
        penData.destroyPulishingContext();
      }
    } catch (Exception e) {         
      subject = MessageConstants.resolve("email.subject.pen-failed", messageConstantParams);
      mailMessage.append(MessageConstants.resolve("email.msg.title.pen-failed") + Names.NEW_LINE);
      mailMessage.append("Exception: " + e.getClass().getName() + Names.NEW_LINE);
      mailMessage.append(e.getMessage());
      mailMessage.append(Names.NEW_LINE + "---------------" + Names.NEW_LINE);
      e.printStackTrace();
      // collect the stacktace;
      StringWriter sWriter = new StringWriter();
      PrintWriter pWriter = new PrintWriter(sWriter);
      e.printStackTrace(pWriter);
      stackTrace = sWriter.toString();      
      processStatus = zws.application.Names.STATUS_ERROR;
      RecorderUtil.logActivity(publishCtx, Names.ACTIVITY_ERROR, e.getMessage());
      // cancel the publish if an error occurs.
      penData.setCancelStatus(true);
      penPolicy.cancelPublish(publishCtx, penData, penPolicy, originsToPublish, id);
      
      penData.destroyPulishingContext();
      throw e;
    } finally {
      
      // publishing context may not exist any longer, if publishing was cancelled
      // calls to get status variables may not be available
      penPolicy.close(id);

      System.out.println(subject);
      if (Names.STATUS_CANCELED.equals(processStatus)) 
        System.out.println(mailMessage);
      System.out.println("=====================================================");

      RecorderUtil.setStatus(publishCtx, processStatus);
      RecorderUtil.endRecProcess(publishCtx, processStatus);
      QxContext parentCtx = publishCtx.parent();
      while(null != parentCtx) {
        RecorderUtil.endRecProcess(parentCtx, processStatus);
        parentCtx = parentCtx.parent();
      }
      publishCtx.configureParent(null);
      
      if (!notify) return;
      
      messageConstantParams.clear();
      messageConstantParams.put("user", id.getUsername());
      mailMessage.append(processMessages(penData.getGlobalMessageMap(),publishCtx));
      mailMessage.append(processMessages(penData.getElementMessageMap(),publishCtx));
      mailMessage.append(Names.NEW_LINE+ Names.NEW_LINE + Names.NEW_LINE);
      mailMessage.append("_________________________________________________" +Names.NEW_LINE);
      mailMessage.append(MessageConstants.resolve("email.msg.footer.pen-published-by", messageConstantParams));
      mailMessage.append(Names.NEW_LINE);
      if(null != stackTrace && stackTrace.length() >0) {
        mailMessage.append("StackTrace: " + Names.NEW_LINE + stackTrace + Names.NEW_LINE);
      }
      
      if(null != recipients)  Alert.notify(subject, mailMessage.toString(),  recipients);
      else Alert.notify(subject, mailMessage.toString());
      //System.out.println("To: " + recipients);
    }
  }
  
  
  private static void QueueOriginsForRepublish(QxContext repubCtx, PENPolicy penPolicy, PENData penData, Authentication id) throws Exception {
    PenQueuePlugin penQ = new PenQueuePlugin();      

    Collection originsToREpub = penData.getOriginsToRepublish();
    Iterator i = originsToREpub.iterator();
    Origin o = null;
    while(i.hasNext()) {
      o = (Origin)i.next();
      RecorderUtil.logActivity(repubCtx, "republishing ",   o.getName());
    }
    repubCtx.set(Names.SUMMARY, "Republishing.");
    penQ.publish(originsToREpub,repubCtx.get(QxContext.INTENT),"1",penPolicy.getTargetRepositoryName(), repubCtx, id);
  }
  
  private static PENPolicy findPolicy(String penPolicyName) throws NameNotFound {
    PENPolicy policyObj = PENPolicySvc.find(penPolicyName);
    return policyObj;
  }

  private static String processMessages(HashMap messageMap, QxContext publishCtx) throws Exception{
    StringBuffer mailMessage = new StringBuffer();
    String msgType = null;
    Collection c = null;
    Iterator itr =  messageMap.keySet().iterator();
    while(itr.hasNext()) {
      msgType = (String)itr.next();
      c = (ArrayList)messageMap.get(msgType);
      // set the messages to email body
      mailMessage.append(listAsString(c));
      // log the messages to recorder service
      logMessages(publishCtx,msgType, c);
    }
    return mailMessage.toString();
  }

  private static void logMessages(QxContext publishCtx, String msgType, Collection c) throws Exception {
    if(null == c) return;
    Iterator itr = c.iterator();
    String value = null;
    while(itr.hasNext()) {
      value = (String) itr.next();
      RecorderUtil.logActivity(publishCtx, msgType.toUpperCase(), value);
    }
  }
  private static String listAsString(Collection c) {
    if(null == c) return "";
    StringBuffer buffer = new StringBuffer();
    Iterator itr = c.iterator();
    while(itr.hasNext()) {
      buffer.append((String) itr.next());
      buffer.append(Names.NEW_LINE);
    }
     return buffer.toString();
  }

  private static void logTxDataToRecorder(QxContext publishCtx, PENData penData) throws Exception {
  QxContext tempCtx = RecorderUtil.startSubProcess(publishCtx, "Transformed Data", "transformedData");
  Iterator itr = penData.materializeIterator();
  Metadata txData = null;
  String name  = null;
  while(itr.hasNext()) {
    name = (String) itr.next();
    txData = (Metadata) penData.lookupTxMetaData(name);
    if(null != txData && null != txData.getName()) {
      RecorderUtil.logActivity(tempCtx, txData.getName(), "TxData", txData.toString());
    }
  }
  RecorderUtil.endRecProcess(tempCtx, Names.STATUS_COMPLETE);
  }
 }
