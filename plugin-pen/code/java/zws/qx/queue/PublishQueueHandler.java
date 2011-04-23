package zws.qx.queue;
/**
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 5, 2007 4:29:44 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
import zws.Alert;
import zws.application.Names;
import zws.application.messageconstants.MessageConstants;
import zws.exception.CanNotMaterialize;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.qx.QxContext;
import zws.qx.QxOp;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.security.Authentication;
import zws.service.pen.PublishingEngineClient;
import zws.service.policy.match.PolicyMatchSvc;
//impoer zws.util.Logwriter;
import zws.recorder.util.RecorderUtil;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author ptoleti
 */
public class PublishQueueHandler extends QxOp {

  /**
   * returns Queue object.
   * @param ctx context
   * @param dataInstruction xml instruction
   * @see zws.qx.QxOp#executeQx(zws.qx.QxContext, zws.qx.xml.QxXML)
   * @return Queue object
   * @throws CanNotMaterialize
   */
  public final QxXML executeQx(final QxContext ctx, final QxXML dataInstruction) {
    Origin originObj = null;
    Authentication authID = null;
    QxXML finalResult = null;
    String policyName = null;
    String priority = null;
    try {
          RecorderUtil.logActivity(ctx, "policy mapping", Names.STATUS_STARTED);
          String recipients = ctx.get(QxContext.RECIPIENTS);          
          Collection originsToPublish = getOrigins(dataInstruction);
          HashMap originPolicyMap = new HashMap();
          HashMap priorityPolicyMap = new HashMap();
          Iterator itr = originsToPublish.iterator();
          while(itr.hasNext()) {
            originObj = (Origin) itr.next();
            authID = new Authentication(ctx.get(QxContext.USERNAME), ctx.get(QxContext.PASSWORD));
            policyName = PolicyMatchSvc.matchPolicy(ctx, originObj, authID);
            if (policyName != null) {
              originPolicyMap.put(originObj, policyName);
              priority = PolicyMatchSvc.getPripority(policyName);
              priorityPolicyMap.put(policyName, priority);
              RecorderUtil.logActivity(ctx, originObj.getName(), "Policy " + policyName + " matched. Priority:" + priority);
            } else {
              Map messageConstantParams = new HashMap();
              messageConstantParams.put("name", originObj.getName());
              String subject = MessageConstants.resolve("email.subject.policy-no-match-found", messageConstantParams);
              messageConstantParams.clear();
              messageConstantParams.put("origin", originObj.toString());
              StringBuffer mailMessage = new StringBuffer(); 
              mailMessage.append(MessageConstants.resolve("email.msg.title.policy-no-match-found", messageConstantParams));
              mailMessage.append(Names.NEW_LINE+ Names.NEW_LINE + Names.NEW_LINE);
              mailMessage.append("_________________________________________________" +Names.NEW_LINE);
              messageConstantParams.clear();
              messageConstantParams.put("user", authID.getUsername());
              mailMessage.append(MessageConstants.resolve("email.msg.footer.pen-published-by", messageConstantParams));
              mailMessage.append(Names.NEW_LINE);
              Alert.notify(subject, mailMessage.toString());
              if(null != recipients)  Alert.notify(subject, mailMessage.toString(),  recipients);
              else Alert.notify(subject, mailMessage.toString());              
              System.out.println(subject);
              System.out.println(mailMessage.toString());
              finalResult =  new QxXML("<Policy Names not matched for " + originObj + "/>");
              RecorderUtil.logActivity(ctx, originObj.getName(), " No policy matched.");
            }
          }
          PublishingEngineClient penClient = new PublishingEngineClient(ctx.get(QxContext.SOAP_HOSTNAME));
          RecorderUtil.logActivity(ctx, "policy mapping", Names.STATUS_COMPLETE);
          RecorderUtil.logActivity(ctx, "queue handle", Names.STATUS_STARTED);
          finalResult = penClient.publish(ctx, originPolicyMap,priorityPolicyMap, recipients, authID);
          RecorderUtil.logActivity(ctx, "queue handle", Names.STATUS_COMPLETE);

    } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
          finalResult =  new QxXML("<Exception message='" + e.getMessage() + "'/>");
    } catch (Exception e) {
          e.printStackTrace();
          finalResult =  new QxXML("<Exception message='" + e.getMessage() + "'/>");
    }
    return finalResult;
  }


  private Collection getOrigins(QxXML dataInstruction) throws CanNotMaterialize {
    Collection origins = new ArrayList();
    QxInstruction publishInstr = dataInstruction.toQxProgram();
    Collection originList = publishInstr.getSubInstructions();
    Iterator itr = originList.iterator();
    Origin o = null;
    String strOrigin = null;
    QxInstruction originInstr = null;
    while(itr.hasNext()) {
      originInstr = (QxInstruction) itr.next();
      strOrigin = originInstr.get("value");
      o = OriginMaker.materialize(strOrigin);
      origins.add(o);
    }
    return origins;
  }
}
