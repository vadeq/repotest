package zws.service.pen; /*
                           * DesignState - Design Compression Technology
                           * @author: arbind @version: 1.0 Created on Mar 30,
                           * 2007 11:37:38 AM Copywrite (c) 2007 Zero Wait-State
                           * Inc. All rights reserved
                           */

import zws.application.Names;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.recorder.util.RecorderUtil;
import zws.security.Authentication;
//impoer zws.util.Logwriter;

import java.util.HashMap;
import java.util.Iterator;

/**
 * The Class PublishingEngineClient.
 */
public class PublishingEngineClient {

  /**
   * The Constructor.
   * @param hostName hostname
   */
  public PublishingEngineClient(final String hostName) {
    host = hostName;
  }
    public final QxXML publish(final QxContext ctx, final HashMap originPolicyMap, final HashMap priorityPolicyMap, final String recipients, final Authentication authID) {
    QxXML result = null;
    try {
        ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
        ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "PenQxService");
        ctx.set(QxContext.SOAP_HOSTNAME, host);
        ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
        ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
        ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
        ctx.set(QxContext.USERNAME, authID.getUsername());
        ctx.set(QxContext.PASSWORD, authID.getPassword());
        // create XML data instruction and call execteQX
        QxWebClient webClient = QxWebClient.materializeClient();
        QxXML dataInstruction = constructInstruction(originPolicyMap,priorityPolicyMap );
        RecorderUtil.logActivity(ctx, "Invoke PEN web-service", Names.STATUS_STARTED);
        result = webClient.executeQx(ctx, dataInstruction);
        RecorderUtil.logActivity(ctx, "Invoke PEN web-service", Names.STATUS_COMPLETE);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
  }

  /*
  <publish>
  <pair origin= "" policy-name ="policy1">
  <pair origin= "" policy-name="policy1"/>
  <pair origin= "" policy-name="policy2"/>
</publish>*/

  private QxXML constructInstruction(HashMap originPolicyMap, HashMap priorityPolicyMap) {
    QxInstruction  publishInstr = new QxInstruction("publish");
    QxInstruction  pairInstr = null;
    Iterator itr = originPolicyMap.keySet().iterator();
    Origin o = null;
    String policyName = null;
    String priority = null;
    while(itr.hasNext()) {
      o = (Origin) itr.next();
      policyName = (String) originPolicyMap.get(o);
      priority = (String) priorityPolicyMap.get(policyName);
      pairInstr = new QxInstruction("pair");
      pairInstr.set(QxContext.ORIGIN, o.toString());
      pairInstr.set(QxContext.POLICY_NAME, policyName);
      pairInstr.set(QxContext.PRIORITY, priority);
      publishInstr.add(pairInstr);
    }
    return new QxXML(publishInstr.toString());
  }
    private String host = null;
}
