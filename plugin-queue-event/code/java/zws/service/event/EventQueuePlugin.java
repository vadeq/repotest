package zws.service.event;

import zws.application.Names;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.service.QxServiceFinder;
import zws.qx.xml.QxXML;
import zws.security.Authentication;
//import zws.util.{}//Logwriter;
import zws.util.RoutedEventBase;


// TODO: Auto-generated Javadoc
/*
 DesignState - Design Compression Technology
 @author: HP USER
 @version: 1.0
 Created on Mar 31, 2007 11:44:27 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * The Class EventQueuePlugin.
 */
public class EventQueuePlugin {

  /**
   * The Constructor.
   *
   * @param hostname the hostname
   */
  public EventQueuePlugin() { 
    //host = hostname; 
  }

  /**
   * Fire.
   *
   * @param event the event
   *
   * @return the qx XML
   *
   * @throws Exception the exception
   */
  public QxXML  fire(Authentication id, RoutedEventBase event) throws Exception {
    /*QxContext ctx = materializeQxContext();
    //Set Queue Name to "event-queue"
    ctx.set(QxContext.QUEUE_NAME, "event-queue");
    ctx.set(QxContext.USERNAME, id.getUsername());
    ctx.set(QxContext.PASSWORD, id.getPassword());
    //Convert Origin implementation object to QxXML
    //Invoke the QxService
    QxWebClient webClient = QxWebClient.materializeClient();*/
    
    QxServiceFinder finder = QxServiceFinder.materializeFinder("event-queue");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    ctx.set(QxContext.QUEUE_NAME, "event-queue");
    ctx.set(QxContext.USERNAME, id.getUsername());
    ctx.set(QxContext.PASSWORD, id.getPassword());    
    QxWebClient webClient = (QxWebClient) finder.materializeClient();    
    
    QxXML  xmlDataInstr = new QxXML(event.toString());    
    {} //System.out.println("executeQx start---------------" + xmlDataInstr);
    QxXML  responseXML = webClient.executeQx(ctx, xmlDataInstr);
    String summary="Fired from Event Queue Plug-in. ID-" + event.getEventID() + 
                                                  " Name-" + event.getEventName() + 
                                                  " Time-" + event.getEventTime();
    ctx.set(Names.SUMMARY, summary);
    {} //System.out.println("executeQx end---------------------------");
    return responseXML;
  }
  
  public QxXML  fire(Authentication id, QxXML eventXML) throws Exception {
  /*  QxContext ctx = materializeQxContext();
    //Set Queue Name to "event-queue"
    ctx.set(QxContext.QUEUE_NAME, "event-queue");
    ctx.set(QxContext.USERNAME, id.getUsername());
    ctx.set(QxContext.PASSWORD, id.getPassword());
    //Convert Origin implementation object to QxXML
    QxWebClient webClient = QxWebClient.materializeClient();
*/
    QxServiceFinder finder = QxServiceFinder.materializeFinder("event-queue");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    ctx.set(QxContext.QUEUE_NAME, "event-queue");
    ctx.set(QxContext.USERNAME, id.getUsername());
    ctx.set(QxContext.PASSWORD, id.getPassword());    
    QxWebClient webClient = (QxWebClient) finder.materializeClient();
    
    {} //System.out.println("----------------executeQx start---------------------------");
    QxXML  responseXML = webClient.executeQx(ctx, eventXML);
    {} //System.out.println("----------------executeQx end---------------------------" + responseXML);
    return responseXML;
  }  

  /**
   * Execute.
   *
   * @param strInstruction the str instruction
   *
   * @return the qx XML
   * @throws Exception
   */
  public QxXML execute(String strInstruction) throws Exception {
    QxXML instruction = null;
    QxXML  responseXML = null;
    if ("start".equalsIgnoreCase(strInstruction)) {
      instruction = new QxXML("<start/>");
    } else if ("stop".equalsIgnoreCase(strInstruction)) {
      instruction = new QxXML("<stop/>");
    } else if ("cancel".equalsIgnoreCase(strInstruction)) {
      instruction = new QxXML("<cancel/>");
    } else {
      {}//Logwriter.printOnConsole(" Invalid instruction. " + strInstruction);
    }
    if (instruction != null) {
      /*QxContext ctx = materializeQxContext();
      ctx.set(QxContext.QUEUE_NAME, "event-queue");
      QxWebClient webClient = QxWebClient.materializeClient();
      */
      QxServiceFinder finder = QxServiceFinder.materializeFinder("event-queue");
      QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","QxQueueService");
      ctx.set(QxContext.QUEUE_NAME, "event-queue");
      QxWebClient webClient = (QxWebClient) finder.materializeClient();
      
      responseXML = webClient.executeQx(ctx, instruction);
    }
    return responseXML;
  }

  /**
   * Materialize qx context.
   *
   * @return the qx context
   * @throws Exception
   */
  /*private QxContext materializeQxContext() throws Exception {
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, host);
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    //ctx.set(QxContext.SOAP_SERVICE_OPERATION, "executeQx");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
    return ctx;
  }*/

  //private String host = null;
}
