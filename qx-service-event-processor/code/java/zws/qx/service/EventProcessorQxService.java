/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Oct 23, 2007 10:02:35 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.qx.service;

import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.event.handler.QxHandler;
import zws.qx.event.processor.EventProcessor;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
//import zws.util.{}//Logwriter;
import zws.util.RoutedEventBase;

import java.io.StringReader;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class EventProcessorQxService implements Qx {
  
  public final QxXML executeQx(final QxContext ctx, final QxXML dataInstruction) {
    QxHandler handler = null;
    RoutedEventBase event;
    try {
      QxInstruction qxInstr = dataInstruction.toQxProgram();
            {} //System.out.println("----------------EventProcessorQxService executeQx---------------------------");
      {}//Logwriter.printOnConsole("strEvent " + dataInstruction);
      EventParser eventParser = new EventParser();
      XMLReader rdr = XMLReaderFactory
          .createXMLReader("org.apache.xerces.parsers.SAXParser");
      rdr.setFeature("http://xml.org/sax/features/validation", false);
      rdr.setContentHandler(eventParser);
      rdr.parse(new InputSource(new StringReader(dataInstruction.toString())));
      //event = EventMaker.materializeXML(dataInstruction.toString());
      event = eventParser.getEvent();
     // EventProcessor.registerHandlers();
      EventProcessor.processEvent(ctx, event);
      return new QxXML("<status message='event-processed'/>");
    } catch (Exception e) {
      e.printStackTrace();
      return new QxXML("<exception message='" + e.getMessage() + "'/>");
    }
  }

}
