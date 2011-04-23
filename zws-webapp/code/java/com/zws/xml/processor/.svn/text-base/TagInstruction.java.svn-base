package com.zws.xml.processor;

import com.zws.functor.Functor;

import java.util.HashMap;
import java.util.Map;

public abstract class TagInstruction {

  public String getTagName() { return tagName; }
  public void setTagName(String s) { tagName=s; }
  public abstract Functor getTagEncounter(); //{ return tagEncounter; }
//  public void setTagEncounter(Functor f)  { tagEncounter = f; }
  public abstract Functor getTagPlacement();// { return tagPlacement; }
//  public void setTagPlacement(Functor f) { tagPlacement = f; }

  public void add(AttributeInstruction attInstruction) {
    attInstruction.setParentTagInstruction(this);
    attributeInstructions.put(attInstruction.getName(), attInstruction);
    if (attInstruction.isRequired()) requiredAttributes.put(attInstruction.getName(), attInstruction);
    if (null!=attInstruction.getDefaultValue()) defaultAttributeValues.put(attInstruction.getName(), attInstruction);
  }

  public AttributeInstruction lookup(String xmlAttribute) {
    AttributeInstruction i = (AttributeInstruction)attributeInstructions.get(xmlAttribute);
    if (null==i) i = new AttributeInstruction(xmlAttribute);
    return i;
  }

  private String tagName=null;
  private Map attributeInstructions = new HashMap();
  private Map requiredAttributes = new HashMap();
  private Map defaultAttributeValues = new HashMap();
}
