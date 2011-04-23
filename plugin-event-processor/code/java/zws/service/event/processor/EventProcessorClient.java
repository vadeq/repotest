package zws.service.event.processor;


import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.service.QxServiceFinder;
import zws.qx.xml.QxXML;
import zws.security.Authentication;
//import zws.util.{}//Logwriter;

/**
 * The Class PublishingEngineClient.
 */
public class EventProcessorClient {

  /**
   * The Constructor.
   * @param hostName hostname
   */
  public EventProcessorClient() {
    //host = hostName;
  }

  /**
   * Publish.
   * @param ctx qxcontext
   * @param originObj origin
   * @param penPolicyName policy
   * @param authID auth the name
   * @return QxXML QxXML
   */
  public final QxXML process(final QxXML eventXML, final Authentication authID) {
    QxXML result = null;
    try {
      {}//Logwriter.printOnConsole("----------EventProcessorClient process--------------------");
        /*ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
        ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "EventProcessorQxService");
        ctx.set(QxContext.SOAP_HOSTNAME, host);
        ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
        ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
        ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
        ctx.set(QxContext.USERNAME, authID.getUsername());
        ctx.set(QxContext.PASSWORD, authID.getPassword());
        {}//Logwriter.printOnConsole("Context set.... ");
        // create XML data instruction and call execteQX
        QxWebClient webClient = QxWebClient.materializeClient();*/

      QxServiceFinder finder = QxServiceFinder.materializeFinder("event-service");
      QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","EventProcessorQxService");
      ctx.set(QxContext.USERNAME, authID.getUsername());
      ctx.set(QxContext.PASSWORD, authID.getPassword());      
      QxWebClient webClient = (QxWebClient) finder.materializeClient();    
      
      {}//Logwriter.printOnConsole("instruction from test" + eventXML);
      result = webClient.executeQx(ctx, eventXML);
    } catch (Exception e) {
        e.printStackTrace();
        {}//Logwriter.printOnConsole("Error in PublishingEngineClient.publish");
    }
    return result;
  }
  /** */
  //private String host = null;
}
