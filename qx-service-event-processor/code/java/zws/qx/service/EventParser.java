package zws.qx.service;

import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.util.RoutedEventBase;

import java.util.Stack;

import org.xml.sax.Attributes; 
import org.xml.sax.helpers.DefaultHandler;

/**
 * The Class InstructionParser.
 */
public class EventParser extends DefaultHandler {

  private RoutedEventBase finalEvent = null;
  private Stack stack;

  public EventParser() {
    stack = new Stack();
  }
  public void startElement(String uri, String localName, String qName, Attributes attribs) {
    try {
      if ("event".equals(localName)) {
        stack.push(prepareEvent(attribs));
      } else if ("metadata".equals(localName)) {
        Metadata m = prepareMetadata(attribs);
        RoutedEventBase event = (RoutedEventBase)stack.peek();
        event.add(m);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  
  public void endElement(String uri, String localName, String qName) {
    if ("event".equals(localName)) {
      finalEvent = (RoutedEventBase)stack.pop();
    } 
  }

  private RoutedEventBase prepareEvent(Attributes attribs) throws Exception {
    String key, value;
    RoutedEventBase event = new RoutedEventBase();
    for (int idx = 0; idx < attribs.getLength(); idx++) {
      key = attribs.getQName(idx);
      value = attribs.getValue(idx);
      event.set(key, value);
    }
    return event;
  }

  private Metadata prepareMetadata(Attributes attribs) throws Exception {
    String key, value;
    MetadataBase metadata = new MetadataBase();
    for (int idx = 0; idx < attribs.getLength(); idx++) {
      key = attribs.getQName(idx);
      value = attribs.getValue(idx);
      metadata.set(key, value);
    }
    Origin  originObj = OriginMaker.materialize(metadata.get("origin"));
    metadata.setOrigin(originObj);
    return metadata;
  }

  public RoutedEventBase getEvent() {
    return finalEvent;
  }
 
  public static void main(String[] args) {
   /* InputSource src;
    try { //
    //  src = new InputSource(new FileInputStream(file));
      StringReader strRdr = new StringReader("<event to='pavan.toleti@gmail.com' time='2007.10.24.12.34.53' domain='zws-domain' server='designstate-0' name='Event-1' event-type='zws.qx.event.handler.ilink.Intralink8CheckinHandler' author='pavan'><metadata name='testin-123' lib_x='888.88' number='testin-123' description='Code Drop 3' attachment='C:\test.txt' author='Matt Mohr' version='B.8' dirtyFlag='true'/></event>");
      //InputSource wrapping a StringReader wrapping your String,
      src = new InputSource(strRdr);
      EventParser insParser = new EventParser();
      XMLReader rdr = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
      rdr.setContentHandler(insParser);
      rdr.setFeature("http://xml.org/sax/features/validation", false);
      rdr.parse(src); 
      RoutedEventBase e = insParser.getEvent();
      {} //System.out.println(e.toString());
      {} //System.out.println(e.getAuthor());
    } catch (Exception e) {
        e.printStackTrace();
    }*/
  }
}
