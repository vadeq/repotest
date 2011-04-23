package com.zws.xml.processor;

import org.xml.sax.SAXException;

public class InvalidTagInstruction extends SAXException {
  public InvalidTagInstruction(String msg) { super(msg); }
}
