/**
 * 
 */
package zws.service.uniqueid;

/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 17, 2007 9:00:51 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.program.QxInstruction;
import zws.qx.service.QxServiceFinder;
import zws.qx.service.ServiceRecord;
import zws.qx.xml.QxXML;
//import zws.util.{}//Logwriter;

/**
 * @author eankutse
 *
 */
public class UniqueIdClient {
  
  public static UniqueIdClient materializeClient() throws Exception {
    UniqueIdClient c = new UniqueIdClient();
    return c;
  }
  
  private void init() throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("uniqueid");
    serviceContext = finder.prepareQxWebClientContext("zws.qx.service", "UniqueIdQxService");

    ServiceRecord r = finder.getServiceRecord();
    qxWebClient = (QxWebClient)r.materializeServiceClient();    
  }
  
  /**
   * The Constructor.
   * @param hostName hostname
   */
  private UniqueIdClient() throws Exception { init(); }

  /**
   * Ping.
   */
  public final String getId(final QxContext ctx) {
    String id="000.000.000";
    QxXML result = null;
    try {
        QxXML dataInstruction = new QxXML("<getid/>");        
        {}//Logwriter.printOnConsole("instruction from test" + dataInstruction);

        serviceContext.configureParent(ctx);
        result = qxWebClient.executeQx(serviceContext, dataInstruction);
        
        QxInstruction r = result.toQxProgram();
        id = r.get("id");
    } catch (Exception e) {
        e.printStackTrace();
        {}//Logwriter.printOnConsole("Error in GetIdClient.publish");
    }
    return id;
  }

  private QxContext serviceContext= null;
  private QxWebClient qxWebClient = null;  
}
