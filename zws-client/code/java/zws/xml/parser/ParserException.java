package zws.xml.parser; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import org.xml.sax.SAXException;

public class ParserException extends SAXException {
  ParserException(String message){ super(message); }
}