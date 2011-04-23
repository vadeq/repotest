package com.zws.xml;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class MetadataMappingHandler extends DefaultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts)
  { if ( qName.equalsIgnoreCase("attribute")) add(atts); }

  private void add(Attributes atts) {
    String key, value;
    key = atts.getValue(METADATA);
    if (null==key) key=atts.getValue(NAME);
    value=atts.getValue(MAP_TO);
    if (null==value) value=atts.getValue(VALUE);
    mapping.put(key, value);
  }

  public Map getMapping(){ return mapping; }
  private Map mapping = new HashMap();
  private static String METADATA = "metadata";
  private static String MAP_TO = "map-to";
  private static String NAME = "name";
  private static String VALUE = "value";
}
