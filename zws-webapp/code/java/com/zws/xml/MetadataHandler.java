package com.zws.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on April 8, 2004, 4:40 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import com.zws.domo.document.Document;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class MetadataHandler extends DefaultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) {
    try {
      if ( qName.equalsIgnoreCase("metadata")) metadata.add(unmarshallDocument(mapAttributes(atts)));
      if ( qName.equalsIgnoreCase("object")) metadata.add(unmarshallDocument(mapAttributes(atts)));
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  private Document unmarshallDocument(Map atts) {
    Document c = new Document();
    Iterator i = atts.keySet().iterator();
    String key;
    while (i.hasNext()) {
      key = i.next().toString();
      c.set(key, atts.get(key).toString());
    }
    return c;
  }

  private Map mapAttributes(Attributes atts) {
    Map a = new HashMap();
    for (int idx = 0; idx < atts.getLength(); a.put(atts.getLocalName(idx), atts.getValue(idx++)));
    return a;
  }

  public Collection getMetadata() { return metadata; }
  
  private Collection metadata = new Vector();
}
