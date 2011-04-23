package com.zws.xml.processor;

/*
<tag-name ignore="true"/>
<tag-name service="fully.qualified.Class.Name">
<tag-name service="fully.qualified.Class.Name" find="name">
<tag-name build="fully.qualified.Class.Name"/>
<tag-name build="fully.qualified.Class.Name" property="name"/>
<tag-name build="fully.qualified.Class.Name" method="methodNameToCall"/>
<attribute [property="beanProperty"] [type="string"] [required="true/false" | default="value" ]/>
*/


import com.zws.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


public class InstructionSet extends DefaultHandler {
  private static String ROOT_ELEMENT = "instruction-set";

  private static String STATE_CONFIG = "config-instruction-set";
  private static String STATE_TAG_INSTRUCTION = "tag-instruction";
  private static String STATE_ATTRIBUTE_INSTRUCTION = "attribute-instruction";

  private static String ATT_NAME   = "name";
  private static String ATT_IGNORE   = "ignore";
  private static String ATT_BUILD    = "build";
  private static String ATT_SERVICE  = "service";
  private static String ATT_PROPERTY = "property";
  private static String ATT_METHOD   = "method";
  private static String ATT_DEFAULT  = "default";
  private static String ATT_TYPE     = "type";
  private static String ATT_REQUIRED = "required";

  private String name = null;
  private String state=null;
  private TagInstruction instruction = null;
  private InstructionSetService service = null;
  private Map instructions = new HashMap();

  public InstructionSet() { }

  public Map getInstructions() { return instructions; }

  public void add(InstructionSet iSet) throws DuplicateTagInstruction {
    Iterator i = iSet.getInstructions().values().iterator();
    while (i.hasNext()) add((TagInstruction) i.next());
  }

  public void add(TagInstruction ins) throws DuplicateTagInstruction {
    Object o = instructions.get(ins.getTagName());
    if (null!= o) throw new DuplicateTagInstruction(ins.getTagName(), getName());
    instructions.put(ins.getTagName(), ins);
  }

  public static void loadCode(File xml) throws SAXException, ParserConfigurationException, IOException {
    getParser(false).parse( xml, new InstructionSet());
  }
  public static void loadCode(String xml) throws SAXException, ParserConfigurationException, IOException {
    getParser(false).parse( xml, new InstructionSet());
  }

  public String getName() { return name; }
  public void setName(String s) { name=s; }

  private static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException{
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }

  public void startDocument(){
    state = STATE_CONFIG;
    service = InstructionSetService.getService();
    {} //System.out.println("Loading Instruction Set...");
  }
  public void endDocument(){
    {} //System.out.println("...Instruction Set loaded.");
    {} //System.out.println("");
  }

  private void config(Attributes atts) throws InvalidInstructionSet, DuplicateInstructionSet {
    String s = atts.getValue(ATT_NAME);
    if (null==s || s.equals("")) throw new InvalidInstructionSet("Instruction Set must be named");
    try {
      InstructionSet i = service.find(s);
      if (null!=i) throw new DuplicateInstructionSet(s);
    }
    catch (InstructionSetNotFound e) {;}
    setName(s);
    service.add(this);
  }

  public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
      throws InvalidInstructionSet, DuplicateInstructionSet, InvalidTagInstruction, DuplicateTagInstruction, InvalidAttributeInstruction {
    if (state.equals(STATE_CONFIG)) {
      config(atts);
      state = STATE_TAG_INSTRUCTION;
    }
    else if (state.equals(STATE_TAG_INSTRUCTION)) {
      buildTagInstruction(qName, atts);
      state = STATE_ATTRIBUTE_INSTRUCTION;
    }
    else if (state.equals(STATE_ATTRIBUTE_INSTRUCTION))
      buildAttributeInstruction(qName, atts);
  }

  public void endElement(String namespaceURI, String localName, String qName) {
    if (state.equals(STATE_ATTRIBUTE_INSTRUCTION) && instruction.getTagName().equals(qName)) {
      instructions.put(instruction.getTagName(), instruction);
      instruction=null;
      state = STATE_TAG_INSTRUCTION;
    }
  }

  private void buildTagInstruction(String qName, Attributes atts){
    instruction = createTagInstruction(atts);
    instruction.setTagName(qName);
  }

  public TagInstruction createTagInstruction(Attributes ats) {
    // <- you are here ->
    return null;
  }

  private void buildAttributeInstruction(String qName, Attributes atts) throws InvalidAttributeInstruction {
    AttributeInstruction ins = new AttributeInstruction();
    boolean required = false;
    String defaultValue = null;
    String javaProperty = null;
    String type = "string";
    if (null!= atts.getValue(ATT_DEFAULT) && null!= atts.getValue(ATT_REQUIRED))
      throw new InvalidAttributeInstruction("Can not specify both "+ATT_DEFAULT+" and "+ATT_REQUIRED+" for attribute "+qName+" in tag "+instruction.getTagName());
    if (null!= atts.getValue(ATT_TYPE)) type = atts.getValue(ATT_TYPE).toLowerCase();
    if (null!= atts.getValue(ATT_DEFAULT)) defaultValue = atts.getValue(ATT_DEFAULT);
    if (null!= atts.getValue(ATT_REQUIRED)) required = "true".equalsIgnoreCase(atts.getValue(ATT_REQUIRED));
    if (null!= atts.getValue(ATT_PROPERTY)) javaProperty = atts.getValue(ATT_PROPERTY);
    if (null == javaProperty) javaProperty = StringUtil.xmlAttribute2JavaProperty(qName);
    ins.setName(qName);
    if (!isValidType(type)) throw new InvalidAttributeInstruction(type + " is not a valid type");
    ins.setType(type);
    if (null!=defaultValue) {
      try { ins.setDefaultValue(StringUtil.valueOf(defaultValue, type)); }
      catch (Exception e) { throw new InvalidAttributeInstruction("Can not convert default value = \""+defaultValue+"\" to type " + type); }
    }
    ins.setIsRequired(required);
    ins.setJavaProperty(javaProperty);
    instruction.add(ins);
  }

  private boolean isValidType(String type) {
    if ("string".equalsIgnoreCase(type) ||
        "int".equalsIgnoreCase(type) || "integer".equalsIgnoreCase(type) ||
        "long".equalsIgnoreCase(type) ||
        "float".equalsIgnoreCase(type) ||
        "double".equalsIgnoreCase(type) ||
        "boolean".equalsIgnoreCase(type)) return true;
    return false;
  }

  public void fatalError(SAXParseException e) throws SAXException { dumpError (e); throw (e);}
  public void error(SAXParseException e) throws SAXException { dumpError (e);     throw (e); }
  public void warning(SAXParseException e) throws SAXException { dumpError (e); throw (e); }
  public static void dumpError(SAXParseException e) {
    System.err.println(e.getMessage());
    System.err.println("Line " + e.getLineNumber()+"Column " + e.getLineNumber());
    System.err.println("");
  }

}