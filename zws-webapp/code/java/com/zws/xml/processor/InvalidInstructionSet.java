package com.zws.xml.processor;

import org.xml.sax.SAXException;

public class InvalidInstructionSet extends SAXException {
  public InvalidInstructionSet(String msg) { super(msg); }
}
