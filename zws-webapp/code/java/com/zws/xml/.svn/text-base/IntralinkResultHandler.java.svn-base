package com.zws.xml;

import com.zws.application.Properties;
import com.zws.domo.document.Document;
import com.zws.util.Value;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class IntralinkResultHandler extends DefaultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts)
  { if ( qName.equalsIgnoreCase("object")) add(atts); }

  private void add(Attributes atts)
  { components.add(unmarshallComponent(mapAttributes(atts)));}

  private Document unmarshallComponent(Map atts) {
    Document c = new Document();
    c.setName(atts.get("name").toString());
    Iterator i = atts.keySet().iterator();
    String key;
    while (i.hasNext()) {
      key = i.next().toString();
      
      c.set(key, atts.get(key).toString(), 
            new Value(key, Properties.get(Properties.typeIlinkAttribute)));
    }
    return c;
  }

  private Map mapAttributes(Attributes atts) {
    Map a = new HashMap();
    for (int idx = 0; idx < atts.getLength(); a.put(atts.getLocalName(idx), atts.getValue(idx++)));
    return a;
  }
  public Collection getResults(){ return getComponents(); }
  public Collection getComponents() { return components; }
  private Collection components = new Vector();
}
