/**
 * This class is the Handler for class ChrysalisDesignConverter. 
 * It holds onto the ChrysalisDesignConverter instance that it
 * is a handler for. The use of this Handler class obviates the 
 * need to have class ChrysalisDesignConverter extend class QxOp.
 * This way, the RPC-style of invoking ChrysalisDesignConverter
 * is still intact.   
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
 Created on Aug 22, 2007 5:17:44 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author eankutse
 *
 */
public class ChrysalisSvcDesignConverterQHandler extends QxOp {

  /**
   * 
   */
  private static final long serialVersionUID = 4225616855959598913L;
  private int queueingPriority;  

  /**
   * @param delegate
   */
  public ChrysalisSvcDesignConverterQHandler() {
    super();
    this.queueingPriority = 1;    
  }
  
  private QxQueuePersistent nextQ() throws Exception {
    return ChrysalisSvc.getOutputQueue();    
  }
  

  public final QxXML executeQx( QxContext ctx,
                                QxXML dataInstruction) {
  {} //System.out.println("Enter ChrysalisDesignConverterHandler.executeQx"); 
  QxXML result=null;
  
  try {
    //call the ChrysalisDesignConverter to do the work
    ChrysalisDesignConverter converter = new ChrysalisDesignConverter(ctx, dataInstruction);
    result  = converter.process();
  //queue the request onto the next queue - ChrysalisSvc.outputQueue    
		nextQ().add(queueingPriority, ctx, dataInstruction);
  } catch (Exception e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
    return new QxXML("<DesignConversionFAILED/>");    
  }
  {} //System.out.println("Exit ChrysalisDesignConverterHandler.executeQx");
  return result;
  }
  
}
