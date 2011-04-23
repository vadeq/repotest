package com.zws.xml;

import com.zws.domo.document.*;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class BOMHandler extends DefaultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) {
    try {
      if ( qName.equalsIgnoreCase("assembly")) createDocument(atts);
      if ( qName.equalsIgnoreCase("reference")) addChild(atts);
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  private void createDocument(Attributes atts) {
    setDocument(unmarshallDocument(mapAttributes(atts)));
  }
  private void addChild(Attributes atts) throws Exception {
    getDocument().add(unmarshallReference(mapAttributes(atts)));
  }

  private Document unmarshallDocument(Map atts) {
    Document c = new Document();
//    c.setName(atts.get("name").toString());
    Iterator i = atts.keySet().iterator();
    String key;
    while (i.hasNext()) {
      key = i.next().toString();
      c.set(key, atts.get(key).toString());
    }
    return c;
  }

  private Reference unmarshallReference(Map atts) throws Exception {
    Document doc=unmarshallDocument(atts);
    Reference r = new Reference();
    originMaker.setDocument(doc);
    r.setOrigin(getOriginMaker().createOrigin());
    r.setCount(Integer.valueOf(atts.get("quantity").toString()).intValue());
    return r;
  }

  private Map mapAttributes(Attributes atts) {
    Map a = new HashMap();
    for (int idx = 0; idx < atts.getLength(); a.put(atts.getLocalName(idx), atts.getValue(idx++)));
    return a;
  }

  public Document getDocument() { return doc; }
  public void setDocument(Document d) { doc=d; }
  public OriginMaker getOriginMaker() { return originMaker; }
  public void setOriginMaker(OriginMaker m) { originMaker=m; }

  private Document doc;
  private OriginMaker originMaker=null;
}
