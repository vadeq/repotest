/**
 * Implementation of the Chrysalis Document Translation Service
 */
package zws.service.chrysalis;

import zws.qx.QxContext;
import zws.qx.queue.QxQueuePersistent;
import zws.qx.queue.QxQueueService;
import zws.qx.xml.QxXML;
//import zws.util.{}//Logwriter;
import java.io.Serializable;




/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 9, 2007 11:42:12 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author eankutse
 *
 */

public class ChrysalisSvc implements Serializable {
  private static final long serialVersionUID = 3889201319773958570L;
  
  private static final ChrysalisRequestStatusRegistry requestStatusRegistry = new ChrysalisRequestStatusRegistry();
  
 /**
  * Communication Queues:
  * Input Queue - takes ChrysalisSvcInputQHandler. Handler should 
  * add() onto Converter Queue.
  *  
  * Converter Queue - takes ChrysalisSvcDesignConverterQHandler. Handler should 
  * add() onto Output Queue.
  *  
  * Output Queue - takes ChrysalisSvcOutputQHandler. Handler should 
  * effect a return from ChrysalisSvc.translate call. Currently
  * effect the return by updating status of request to 'Done'
  * 
  */

  private static final String INPUT_Q_NAME = "chrysalis-input-queue";
  private static final String CONVERTER_Q_NAME = "chrysalis-convert-queue";
  private static final String OUTPUT_Q_NAME = "chrysalis-output-queue";
  public static final int QUEUEING_DEFAULT_PRIORITY = 1;  
 
  public static ChrysalisRequestStatusRegistry getStatusRegistry() {
    return ChrysalisSvc.requestStatusRegistry;
  }
    
  private static String getRequestId(QxXML instr) {
    return ChrysalisRequestProcessorResource.getUniqueDirName(instr);         
  }
  
  public static void translate(QxContext ctx, QxXML instr) throws Exception {    
    {}//Logwriter.printOnConsole("ChrysalisSvc.translate(QxContext ctx, QxXML instr) called");

    //add a status info object for the request into the status registry
    String id = getRequestId(instr);
    requestStatusRegistry.addStatusInfo(id);
    requestStatusRegistry.updateStatusToOnInputQueue(id);
    
    //queue request onto the Input Queue
    getInputQueue().add(QUEUEING_DEFAULT_PRIORITY, ctx, instr);
  }
    
  public static QxXML requestStatus(QxContext ctx, QxXML instr) throws Exception {  
    return ChrysalisStatusRequestProcessor.requestStatus(ctx, instr);
  }
  
  public static QxXML removeStatus(QxContext ctx, QxXML instr) throws Exception {  
    return ChrysalisStatusRequestProcessor.removeStatus(ctx, instr);
  }

  public static QxQueuePersistent getInputQueue() throws Exception {return QxQueueService.lookupQueue(INPUT_Q_NAME);}  
  public static QxQueuePersistent getConverterQueue() throws Exception {return QxQueueService.lookupQueue(CONVERTER_Q_NAME);}
  public static QxQueuePersistent getOutputQueue() throws Exception {return QxQueueService.lookupQueue(OUTPUT_Q_NAME);}   
}
