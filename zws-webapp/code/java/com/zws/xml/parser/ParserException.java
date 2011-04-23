package com.zws.xml.parser;

import org.xml.sax.SAXException;

public class ParserException extends SAXException {
  ParserException(String message){ super(message); }
}