package com.zws.xml.processor;

import org.xml.sax.SAXException;

public class DuplicateTagInstruction extends SAXException {
  public DuplicateTagInstruction(String instructionName, String instructionSetName) {
    super("An Instruction for tag named "+instructionName+" has already been defined in InstructionSet named " + instructionSetName);
  }
}
