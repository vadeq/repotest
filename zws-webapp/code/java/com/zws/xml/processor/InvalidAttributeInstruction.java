package com.zws.xml.processor;

import org.xml.sax.SAXException;

public class InvalidAttributeInstruction extends SAXException {
  public InvalidAttributeInstruction(String msg) { super(msg); }
}
