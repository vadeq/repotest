package zws.qx.event.processor;
/**
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 5, 2007 4:29:44 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
import zws.qx.QxContext;
import zws.qx.QxOp;
import zws.qx.xml.QxXML;
import zws.security.Authentication;
import zws.service.event.processor.EventProcessorClient;
//import zws.util.{}//Logwriter;


import java.security.NoSuchAlgorithmException;

public class EventProcessQueueHandler extends QxOp {
  public final QxXML executeQx(final QxContext ctx, final QxXML dataInstruction) {
    Authentication authID = null;
    QxXML finalResult = null;
    try {
          {} //System.out.println("EventProcessQueueHandler  executeQx " + dataInstruction);
          authID = new Authentication(ctx.get(QxContext.USERNAME), ctx.get(QxContext.PASSWORD));
          EventProcessorClient epClient = new EventProcessorClient();
          finalResult = epClient.process(dataInstruction, authID);
    } catch (NoSuchAlgorithmException e) {
          e.printStackTrace();
          {}//Logwriter.printOnConsole("NoSuchAlgorithmException - EventProcessQueueHandler");
          finalResult =  new QxXML("<Exception message='" + e.getMessage() + "'/>");
    } catch (Exception e) {
          e.printStackTrace();
          {}//Logwriter.printOnConsole("Exception - EventProcessQueueHandler");
          finalResult =  new QxXML("<Exception message='" + e.getMessage() + "'/>");
    }
    return finalResult;
  }
}
