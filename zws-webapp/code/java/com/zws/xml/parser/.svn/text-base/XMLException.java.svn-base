package com.zws.xml.parser;

import org.xml.sax.SAXException;

public class XMLException extends Exception {
  XMLException (String message) { super (message); }
  XMLException (String message, SAXException ex) { super (message); e=ex; }

  public SAXException getSAXException() {return e; }
  private SAXException e;
}