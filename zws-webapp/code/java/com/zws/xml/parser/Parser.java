package com.zws.xml.parser;

import com.zws.xml.functor.XMLFunctor;
import com.zws.xml.functor.call.Caller;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


public class Parser extends DefaultHandler {

  public Parser () {
    actionMap = new HashMap();
    targetMap = new HashMap();
    attributeMaps = new HashMap();
  }
  public Parser (Map actions, Map targets, Map attributes)
      { actionMap = actions; targetMap = targets; attributeMaps = attributes; }

  public void load(InputSource xml, InputSource mapping) throws SAXException, ParserConfigurationException, IOException {
    XMLValidator.validate(xml, false);
    TagMap loader = new TagMap();
    loader.load(mapping, actionMap, targetMap, attributeMaps);
    getParser(false).parse(xml, new Parser(actionMap, targetMap, attributeMaps));
  }
  public void load(InputStream xml, InputStream mapping) throws SAXException, ParserConfigurationException, IOException {
    XMLValidator.validate(xml, false);
    TagMap loader = new TagMap();
    loader.load(mapping, actionMap, targetMap, attributeMaps);
    getParser(false).parse(xml, new Parser(actionMap, targetMap, attributeMaps));
  }
  public void load(File xml, File mapping) throws SAXException, ParserConfigurationException, IOException {
    XMLValidator.validate(xml, false);
    TagMap loader = new TagMap();
    loader.load(mapping, actionMap, targetMap, attributeMaps);
    getParser(false).parse(xml, new Parser(actionMap, targetMap, attributeMaps));
  }
  public void load(String xml, String mapping) throws SAXException, ParserConfigurationException, IOException {
    XMLValidator.validate(xml, false);
    TagMap loader = new TagMap();
    loader.load(mapping, actionMap, targetMap, attributeMaps);
    getParser(false).parse(xml, new Parser(actionMap, targetMap, attributeMaps));
  }
  private static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }
  private Map attributeMap(String tagName, Attributes atts) throws ParserException {
    if (null == tagName || null==atts) return null;
    Map mapping = null;
    try { mapping = (Map) attributeMaps.get(tagName); }
    catch (Exception e){ throw new ParserException("Typecast error, unknown element found in attribute list mappings!"); }
    if (null == mapping) return null;
    Map resultMapping = new HashMap();
    for (int i = 0; i < atts.getLength(); i++){
      String xmlName = atts.getQName(i);
      String value = atts.getValue(i);
      TagMap.AttributeMapping aMap = null;
      try {  aMap = (TagMap.AttributeMapping) mapping.get(xmlName); }
      catch (Exception e){ throw new ParserException("Typecast error, unknown element found in property mapping!"); }
      if (null==aMap) throw new ParserException("No attribute mapping specified for attribute: "+xmlName+" in tag: "+tagName);
      String propertyName = aMap.getPropertyName();
      try { resultMapping.put(propertyName, aMap.convertValue(value)); }
      catch (Exception e) {
        throw new ParserException("Can not convert given value: \""+value+"\" to specified type: " + aMap.getType()+ " for attribute: " + xmlName + " in tag: " + tagName + "!");
      }
    }
    checkForRequiredAttributes(tagName, resultMapping, mapping);
    addDefaultValues(resultMapping, mapping);
    return resultMapping;
  }
  private void checkForRequiredAttributes(String tagName, Map attributes, Map mappings) throws ParserException {
    if (mappings == null) return;
    Iterator i = mappings.entrySet().iterator();
    while (i.hasNext()){
      Map.Entry e = (Map.Entry)i.next();
      TagMap.AttributeMapping m = (TagMap.AttributeMapping) e.getValue();
      if (null!=m && m.getRequired())
        if (null == mappings  || null == attributes.get(m.getPropertyName()))
          throw new ParserException ("An attribute that maps to property name: "+m.getPropertyName()+" is required and was not specified for tag:"+tagName+"!");
    }
  }


  private void addDefaultValues(Map attributes, Map mappings){
    if (mappings == null) return;
    Iterator i = mappings.entrySet().iterator();
    while (i.hasNext()){
      Map.Entry e = (Map.Entry)i.next();
      TagMap.AttributeMapping m = (TagMap.AttributeMapping) e.getValue();
      if (null!=m && null!=m.getDefaultValue() && null==attributes.get(m.getPropertyName()))
        attributes.put(m.getPropertyName(), m.getDefaultValue());
    }
  }
  public void startElement(String namespaceURI, String localName, String qName, Attributes atts)throws ParserException {
    Caller targeting = (Caller)targetMap.get(qName);
    if (null==targeting) throw new ParserException("No target found for tag: " + qName + "!");
    targeting = (Caller)targeting.duplicate();
    XMLFunctor actor = (XMLFunctor)actionMap.get(qName);
    if (null == actor) throw new ParserException("No action found for tag: " + qName + "!");

    if (null==targeting.getTargetObject() && 0 < stack.size()){
      Caller parent = null;
      try { parent = (Caller)stack.peek(); }
      catch (Exception e) { throw new ParserException("Typecast exception, unknown object placed in stack!"); }
      Object targetObject = parent.parameter(0);
      int idx = stack.indexOf(parent);
      //climb stack for next available parent (stack may have no-ops in it.
      while (null==targetObject && 0 < idx) {
        {} //System.out.println("Climbing stack for next available parent..");
        try { parent = (Caller)stack.get(--idx); }
        catch (Exception e) { e.printStackTrace(); throw new ParserException("Typecast exception, unknown object placed in stack!"); }
        targetObject = parent.parameter(0);
      }
      targeting.setTargetObject(targetObject);
    }
    Object o;
    try { actor.initialize(attributeMap(qName, atts)); o = actor.process(); }
    catch (Exception e)
        { e.printStackTrace(); throw new ParserException("Could execute action for tag: " + qName + "!"); }
    targeting.resetParameters(new Object[] {o});
    stack.push(targeting);
  }
  public void endElement(String namespaceURI, String localName, String qName) throws ParserException {
    {} //System.out.println("end Element");
    Caller targeting = (Caller)stack.pop();
    try { targeting.process(); }
    catch (Exception e)
        { e.printStackTrace();  throw new ParserException("Could not execute target command for tag: " + qName + "!"); }
  }



  public void startDocument(){
    {} //System.out.println("Document");
  }
  public void endDocument(){
    {} //System.out.println("/Document");
  }
  public void characters(char[] ch, int start, int length){
    {} //System.out.println("CHARS:" + new String (ch, start, length));
  }
  public InputSource resolveEntity(String publicId, String systemId) {
    return null;
  }

  public Stack getStack(){ return stack; }

  public void setStack(Stack stack){ this.stack = stack; }

  public Map getActionMap(){ return actionMap; }

  public void setActionMap(Map actionMap){ this.actionMap = actionMap; }

  public Map getTargetMap(){ return targetMap; }

  public void setTargetMap(Map targetMap){ this.targetMap = targetMap; }

  public Map getAttributeMaps(){ return attributeMaps; }

  public void setAttributeMaps(Map attributeMaps){ this.attributeMaps = attributeMaps; }

  public void setDocumentLocator(Locator locator){ }

  private Stack stack = new Stack();
  private Map actionMap;
  private Map targetMap;
  private Map attributeMaps;
}