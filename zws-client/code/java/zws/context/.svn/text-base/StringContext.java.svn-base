package zws.context;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */


import zws.util.KeyValue;

import java.io.NotSerializableException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


public class StringContext implements Serializable {

  public String get(String property) { return (String) properties.get(property); }

    public void set(String property, String value) { properties.put(property, value); }

    public void set(Map parameters) throws NotSerializableException {
      Iterator i = parameters.keySet().iterator();
      String key;
      String o;
      while (i.hasNext()) {
        key = i.next().toString();
        o = parameters.get(key).toString();
        if (null==key) continue;
          set(key, o);
    }
    }

    public void set(KeyValue pair) throws NotSerializableException {
      String o=null;
      o = pair.getValue().toString();
      if (null==o) throw new NullPointerException();
      set(pair.getKey(), o);
    }

    public boolean contains(String property){ return properties.containsKey(property); }

  public void dump(PrintStream stream){
    Iterator i = properties.keySet ().iterator();
    String field;
    while (i.hasNext()) {
      field = (String) i.next();
      stream.println(field + "=" + properties.get(field));
    }
  }

  public String toString(){
    Iterator i = properties.keySet().iterator();
    StringBuffer fieldBfr = new StringBuffer();
    fieldBfr.append("<string_context ");
    while (i.hasNext()) {
      String fld = (String) i.next();

      fieldBfr.append( fld )
      .append("=")
      .append("\"")
      .append(properties.get(fld))
      .append("\"")
      .append(" ");
    }
    fieldBfr.append("/>");
    return fieldBfr.toString();
  }



  public Map properties = new HashMap();

  public static String DESCRIPTION="description";
  public static String USERNAME="username";
  public static String PASSWORD="password";
  public static String DEFAULT_USERNAME="default-username";
  public static String DEFAULT_PASSWORD="default-password";
  public static String DOMAIN_NAME="domain-name";
  public static String SERVER_NAME="server-name";
  public static String REPOSITORY_TYPE="repository-type";
  public static String REPOSITORY_NAME="repository-name";
  public static String SERVICE="service";
  public static String METHOD="method";
  public static String OP_TYPE="op-type";
  public static String ACQUIRE_LICENSE="acquire-license";

  public String getProperties() { return toString(); }

  public void setProperties(String properties) {
    //this.properties.put("me", properties);
    AttributesHandler attHandler = new AttributesHandler();
    try {
      XMLReader xr = XMLReaderFactory.createXMLReader();
      xr.setContentHandler (attHandler);
      StringReader reader = new StringReader(properties);
      InputSource source = new InputSource(reader);
      xr.parse(source);
      this.properties = attHandler.getProperties();
    } catch(Exception ex){
      {} //System.out.println("Exception message in StringContext.setProperties: "+ex.getMessage() );
    }
  }
}

class AttributesHandler extends DefaultHandler {
  // This method is called when an element is encountered
  public void startElement(String namespaceURI, String localName,
                           String qName, Attributes atts)  {
      // Get the number of attribute
      int length = atts.getLength();

      // Process each attribute
      for (int i=0; i<length; i++) {
          // Get names and values for each attribute
          String name = atts.getQName(i);
          String value = atts.getValue(i);
          mapProperties.put(name, value);
      }
  }

  private HashMap mapProperties = new HashMap();
  public HashMap getProperties(){
    return mapProperties;
  }
}

