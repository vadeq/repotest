package zws.xml.parser; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import org.xml.sax.SAXException;

public class XMLException extends Exception {
  XMLException (String message) { super (message); }
  XMLException (String message, SAXException ex) { super (message); e=ex; }

  public SAXException getSAXException() {return e; }
  private SAXException e;
}