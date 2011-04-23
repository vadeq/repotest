/**
 * 
 */
package zws.service.chrysalis;

import zws.qx.QxContext;
import zws.qx.QxOp;
import zws.qx.queue.QxQueuePersistent;
import zws.qx.xml.QxXML;

/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 22, 2007 4:40:26 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author eankutse
 *
 */


public class ChrysalisSvcInputQHandler extends QxOp {

  //private QxQueuePersistent nextQ; //the queue this handler adds to
  private int queueingPriority;
  
  /**
   * 
   */
  private static final long serialVersionUID = -5435489656230824809L;


  
  /**
   * @param delegate the ChrysalisInputManager object that this object
   * is a handler for.
   * @param nextQ the next queue onto which this handler queues the request
   * @param queueingPriority the priority with which the request is queued
   */
  public ChrysalisSvcInputQHandler() {
    super();
    this.queueingPriority = 1;
  }
  
  private QxQueuePersistent nextQ() throws Exception {
    return ChrysalisSvc.getConverterQueue();    
  }
  
  
  public final QxXML executeQx( QxContext ctx,
                                QxXML dataInstruction) {
    {} //System.out.println("Enter ChrysalisSvcInputQHandler.executeQx");
    QxXML result=null;
    
    //create an instance of ChrysalisInputManager to do the work
    try {
      ChrysalisInputManager inputManager = new ChrysalisInputManager(ctx, dataInstruction);
      result = inputManager.process();
			//add onto the next queue - ChrysalisSvc.ConverterQueue
			nextQ().add(this.queueingPriority, ctx, dataInstruction);
    } catch (Exception e) {
      e.printStackTrace();      
      return new QxXML("<InputProcessingFAILED/>");
    }
     
    {} //System.out.println("Exit ChrysalisSvcInputQHandler.executeQx");
    return result;
    }



}
