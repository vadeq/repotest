package com.zws.xml.parser; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.*;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class XMLValidator extends DefaultHandler
{
  public XMLValidator() {}

  public static void validate(InputSource x, boolean validate) throws SAXException, ParserConfigurationException, IOException
  { getParser(validate).parse(x, new XMLValidator()); }
  public static void validate(InputStream x, boolean validate) throws SAXException, ParserConfigurationException, IOException
  { getParser(validate).parse(x, new XMLValidator()); }
  public static void validate(File x, boolean validate) throws SAXException, ParserConfigurationException, IOException
  { getParser(validate).parse(x, new XMLValidator()); }
  public static void validate(String x, boolean validate) throws SAXException, ParserConfigurationException, IOException
  { getParser(validate).parse(x, new XMLValidator()); }

  private static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }
  public void fatalError(SAXParseException e) throws SAXException { logError (e); throw (e);}
  public void error(SAXParseException e) throws SAXException { logError (e);     throw (e); }
  public void warning(SAXParseException e) throws SAXException { logError (e); throw (e); }
  public static void logError(SAXParseException e) {
    System.err.println(e.getMessage());
    System.err.println("Line " + e.getLineNumber());
    System.err.println("Column " + e.getColumnNumber());
    System.err.println("");
  }

  public void setSource(InputSource s){ source = s; }
  public InputSource getSource(){return source; }
  public void setStream(InputStream s){ stream = s; }
  public InputStream getStream(){ return stream; }
  public void setFile(File f){ file = f;}
  public File getFile(){ return file; }
  public void setXML(String s){ xml = s; }
  public String getXML(){ return xml; }

  private InputSource source;
  private InputStream stream;
  private File file;
  private String xml;
}