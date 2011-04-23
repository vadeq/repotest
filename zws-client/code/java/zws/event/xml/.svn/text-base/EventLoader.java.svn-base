package zws.event.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 29, 2004, 12:42 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;
import zws.event.EventMaker;
import zws.exception.CanNotMaterialize;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class EventLoader extends DefaultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    try { if ( qName.equalsIgnoreCase("event")) { createEvent(atts); } }
    catch (Exception e) { e.printStackTrace(); return; }
  }
  
  private void createEvent(Attributes atts) throws CanNotMaterialize { 
    String eventType= atts.getValue(RoutedEventBase.EVENT_TYPE);
    Map props = mapAttributes(atts);
    RoutedEventBase ev = EventMaker.materialize(eventType, props);
    getResults().add(ev);
  }
  
  public Map mapAttributes(Attributes atts) {
   Map m = new HashMap();
   for (int idx=0; idx<atts.getLength(); idx++) m.put(atts.getQName(idx), atts.getValue(idx));
   return m;
  }
  public Collection getResults() { return results; }
  
  private Collection results=new Vector();
}
