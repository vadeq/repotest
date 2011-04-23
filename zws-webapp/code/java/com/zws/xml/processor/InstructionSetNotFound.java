package com.zws.xml.processor;

import org.xml.sax.SAXException;

public class InstructionSetNotFound extends SAXException {
  public InstructionSetNotFound (String name) {
    super ("No Instruction Set has been defined for name = "+name);
  }
}
