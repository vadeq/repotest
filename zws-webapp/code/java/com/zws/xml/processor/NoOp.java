package com.zws.xml.processor;

import com.zws.functor.Functor;

public class NoOp extends TagInstruction {

  public Functor getTagEncounter(){return null; }
  public Functor getTagPlacement(){return null;}

}