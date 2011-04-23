package zws.repository.ilink3.qx.client.op.xml;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;
import zws.data.Metadata;
import zws.log.failure.Failure;

import org.xml.sax.Attributes;

public class ListEventsHandler extends IntralinkResultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    if ( qName.equalsIgnoreCase("event")) { addEvent(atts); return; }
    else if ( qName.equalsIgnoreCase("metadata")) { addMetadata(atts); return; }
    try {
      if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  private void addEvent(Attributes atts) {
    try {
      ev = unmarshallEvent(atts);
      getStorable().store(ev);
    }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }

  private void addMetadata(Attributes atts) {
	  try {
	    data = unmarshallComponent(atts);
	    ev.add(data);
	  }
	  catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
	}
  
  private RoutedEventBase ev;
  private Metadata data;
}
