package zws.qx.service;
/*
* DesignState - Design Compression Technology
* @author: arbind @version: 1.0
* Created on Mar 30, 2007 11:23:20 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.application.Names;
import zws.exception.CanNotMaterialize;
import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.recorder.util.RecorderUtil;
import zws.security.Authentication;
import zws.service.pen.PublishingEngine;
//impoer zws.util.Logwriter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The Class PenQxService.
 * @author ptoleti
 */
public class PenQxService implements Qx {

  /**
   * Webservice for PEN Policy.
   * @param dataInstruction QxXML
   * @param ctx QX context
   * @return QX MQL
   * @see zws.qx.Qx#executeQx(zws.qx.QxContext, zws.qx.xml.QxXML)
   */
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    QxXML result = null;
    String status= null;
    try {
          {}//Logwriter.printOnConsole("PenQxService invoked with dataInstruction :");
          {}//Logwriter.printOnConsole("" + dataInstruction);
          Authentication authID = new Authentication(ctx.get(QxContext.USERNAME), ctx.get(QxContext.PASSWORD));
          {}//Logwriter.printOnConsole("authID " + authID.toString());
          String recipients = ctx.get(QxContext.RECIPIENTS);
          HashMap originPolicyMap = null;
          HashMap policyPriorityMap = null;
          originPolicyMap = getOriginPolicyMap(dataInstruction);
          policyPriorityMap = this.getPolicyPriorityMap(dataInstruction);
          RecorderUtil.logActivity(ctx, "PEN web-service", Names.STATUS_STARTED);
          PublishingEngine.publish(ctx, originPolicyMap, policyPriorityMap, recipients, authID);
          {}//Logwriter.printOnConsole("PenQxService completed");
          result = new QxXML("<pen message=\"published successfully.\"/>");
          status = Names.STATUS_COMPLETE;
    } catch (Exception e) {
          e.printStackTrace();
          {}//Logwriter.printOnConsole("Exception ");
          result = new QxXML("<pen message=\"publish failed.\"/>");
          status = Names.STATUS_ERROR;
    } finally {
      try {
        RecorderUtil.logActivity(ctx, "PEN web-service", status);
      } catch (Exception e) {
        {}//Logwriter.printOnConsole("Error in recorder activity");
        e.printStackTrace();
      }
    }
    return result;
  }

  /*
  <publish>
  <pair origin= "" policy-name ="policy1">
  <pair origin= "" policy-name="policy1"/>
  <pair origin= "" policy-name="policy2"/>
</publish>*/

  private HashMap getOriginPolicyMap(QxXML dataInstruction) throws CanNotMaterialize {
    HashMap originPolicyMap = new HashMap();
    QxInstruction publishInstr = dataInstruction.toQxProgram();
    String policyName = null;
    Collection originList = publishInstr.getSubInstructions();
    Iterator itr = originList.iterator();
    String strOrigin = null;
    QxInstruction pairInstr = null;
    while(itr.hasNext()) {
      pairInstr = (QxInstruction) itr.next();
      strOrigin = pairInstr.get(QxContext.ORIGIN);
      policyName = pairInstr.get(QxContext.POLICY_NAME);
      originPolicyMap.put(strOrigin, policyName);
    }
    return originPolicyMap;
  }

  private HashMap getPolicyPriorityMap(QxXML dataInstruction) throws CanNotMaterialize {
    HashMap policyPriorityMap = new HashMap();
    QxInstruction publishInstr = dataInstruction.toQxProgram();
    String policyName = null;
    Collection originList = publishInstr.getSubInstructions();
    Iterator itr = originList.iterator();
    String priority  = null;
    QxInstruction pairInstr = null;
    while(itr.hasNext()) {
      pairInstr = (QxInstruction) itr.next();
      policyName = pairInstr.get(QxContext.POLICY_NAME);
      priority = pairInstr.get(QxContext.PRIORITY);
      policyPriorityMap.put(priority, policyName);
    }
    return policyPriorityMap;
  }
}
