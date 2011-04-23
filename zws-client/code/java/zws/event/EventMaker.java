package zws.event;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 29, 2004, 9:24 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.event.xml.EventLoader;
import zws.util.StringUtil;
import zws.util.RoutedEventBase;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.*;

public class EventMaker {
  
 public static RoutedEventBase materializeXML(String xml) throws Exception {
   EventLoader handler = new EventLoader();
   SAXParserFactory factory = SAXParserFactory.newInstance();
   factory.setValidating(false);
   XMLReader xr = factory.newSAXParser().getXMLReader();
   xr.setContentHandler(handler);
   xr.setErrorHandler(handler);
   xr.parse(new InputSource(new ByteArrayInputStream(xml.getBytes())));
   //zws.util.PrintUtil.print(handler.getResults());
   Iterator i = handler.getResults().iterator();
   RoutedEventBase ev=null;
   if (i.hasNext()) ev = (RoutedEventBase)i.next();
   return ev;
 }
    
  public static RoutedEventBase materialize(String eventName) {
    RoutedEventBase ev = createNewEvent(createEventClassName(eventName));
    return ev;
  }
  
  public static RoutedEventBase materialize(Map attributes)  {
    RoutedEventBase ev = createNewEvent(createEventClassName((String)attributes.get(RoutedEventBase.EVENT_TYPE)));
    ev.addProperties(attributes);
    return ev;
  }

  public static RoutedEventBase materialize(String eventType, Map attributes) {
    RoutedEventBase ev = createNewEvent(createEventClassName(eventType));
    ev.addProperties(attributes);
    if (null==ev.getDomainName()) ev.setDomainName(zws.Server.getDomainName());
    if (null==ev.getServerName()) ev.setServerName(zws.Server.getName());
    return ev;
  }
  
  private static String createEventClassName (String eventType) {
    if (null==eventType) return null;
    int idx = eventType.lastIndexOf(".")+1;
    String name = eventType.substring(idx);
    String fqcn = eventType.substring(0,idx);
    if (fqcn.startsWith("event.")) fqcn= "zws."+fqcn;
    fqcn = fqcn + StringUtil.capitalize(StringUtil.xmlAttribute2JavaProperty(name));
    return fqcn;
  }
  
  private static RoutedEventBase createNewEvent(String className) {
    if (null==className) return new RoutedEventBase();
    try {
      Class c = Class.forName(className);
      return (RoutedEventBase)c.newInstance();
    }
    catch (Exception e) { 
      {} //System.out.println("Can not materialize event from name "+className+" ["+e.getClass().getName()+"-"+e.getMessage()+"]. Creating a generic event..");       
      return new RoutedEventBase();      
    }
  }
}
