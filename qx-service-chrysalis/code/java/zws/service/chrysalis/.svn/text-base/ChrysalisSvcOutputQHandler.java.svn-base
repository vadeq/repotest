/**
 * 
 */
package zws.service.chrysalis;


import zws.qx.QxContext;
import zws.qx.QxOp;
import zws.qx.xml.QxXML;


/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 22, 2007 5:18:17 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author eankutse
 *
 */
public class ChrysalisSvcOutputQHandler extends QxOp {
  
  /**
   * 
   */
  private static final long serialVersionUID = 5135151621249959796L;

  /**
   * @param delegate
   */
  public ChrysalisSvcOutputQHandler() {
    super();
  }
  
  
  public final QxXML executeQx(QxContext ctx,
                                 QxXML dataInstruction) {
  {} //System.out.println("Enter ChrysalisOutputManagerHandler.executeQx"); 

  QxXML result=null;
  //call ChrysalisOutputManager to do the work
  try {
    ChrysalisOutputManager outputMgr = new ChrysalisOutputManager(ctx, dataInstruction);
    result = outputMgr.process();
  } catch (ChrysalisTranslationResultsFileNotFoundException tre) {
    tre.printStackTrace();
    return new QxXML("<OutputProcessingFAILED: "+tre.toString()+" />");    
  } catch (Exception e) {
    e.printStackTrace();
    return new QxXML("<OutputProcessingFAILED/>");    
  }
  
  {} //System.out.println("   dataInstruction on return from deliverResults: " + dataInstruction.toString()); 
 
  {} //System.out.println("Exit ChrysalisOutputManagerHandler.executeQx");  
  return result;
  }
}
