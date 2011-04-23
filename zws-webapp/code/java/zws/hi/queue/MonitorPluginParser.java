/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Mar 5, 2008 1:20:10 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */
package zws.hi.queue;

import javax.xml.parsers.*;
import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import java.io.*;

 public class MonitorPluginParser {

  QueueHandler queueHandler = new QueueHandler();

  public void parseQueues(String xml) {

    try {

      // get a parser
      SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();

      // get a reader
      XMLReader reader = saxParser.getXMLReader();

      // set the handler to parse the xml and a mechanism to provide results

      reader.setContentHandler(queueHandler);

      // process the input
      reader.parse(new InputSource( new StringReader(xml) ));

    } catch (Exception e) {
      System.out.println("Error parsing response: " + e.getMessage());
    }
  }

  public MonitoredQueue[] getQueues() {
    return queueHandler.getQueues();
  }
}