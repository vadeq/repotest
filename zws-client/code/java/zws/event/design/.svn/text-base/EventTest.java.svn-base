package zws.event.design;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 28, 2004, 3:05 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.event.xml.EventLoader;

import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class EventTest {
  public EventTest() { }
  public static void main(String[] args) {
    try {
      //DesignPromoted ev = (DesignPromoted)EventMaker.materialize("event.repository.design-promoted");
      //ev.setNewReleaseLevel("released");
      //ev.setOLDReleaseLevel("wip");
      //System.out.print(ev);
      //System.out.print(ev);
    EventLoader handler = new EventLoader();
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(false);
    XMLReader xr = factory.newSAXParser().getXMLReader();
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);
    File xml = new File("C:\\events.xml");
    FileReader r = new FileReader(xml);
    xr.parse(new InputSource(r));
    zws.util.PrintUtil.print(handler.getResults());
    Iterator i = handler.getResults().iterator();
    while (i.hasNext())  { }{} //System.out.println(i.next().getClass().getName());
    }
    catch (Exception e) { e.printStackTrace(); }
  }
}
