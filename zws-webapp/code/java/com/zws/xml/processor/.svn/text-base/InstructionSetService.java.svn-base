package com.zws.xml.processor;

import java.util.*;

public class InstructionSetService {

  private static InstructionSetService service=null;

  private InstructionSetService() { }
  public static InstructionSetService getService(){
    if (null==service) service = new InstructionSetService();
    return service;
  }

  public void add(InstructionSet ins) throws DuplicateInstructionSet {
    Object o = instructions.get(ins.getName());
    if (null==o) throw new DuplicateInstructionSet(ins.getName());
    instructions.put(ins.getName(), ins);
  }

  public InstructionSet find(String name) throws InstructionSetNotFound {
    InstructionSet ins = (InstructionSet) instructions.get(name);
    if (null==ins) throw new InstructionSetNotFound(name);
    return ins;
  }

  public InstructionSet combine(String newName, String instructionSetNames) throws InstructionSetNotFound, DuplicateTagInstruction {
    String name;
    InstructionSet ins = new InstructionSet();
    ins.setName(newName);
    StringTokenizer tok = new StringTokenizer(instructionSetNames, DELIMITER);
    while (tok.hasMoreTokens()) ins.add(find(tok.nextToken()));
    return ins;
  }

  private Map instructions = new HashMap();
  private static String DELIMITER = ";";
}
