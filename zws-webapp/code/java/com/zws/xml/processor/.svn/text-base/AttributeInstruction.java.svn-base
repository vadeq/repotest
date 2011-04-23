package com.zws.xml.processor;

import com.zws.util.StringUtil;

public class AttributeInstruction {
  public AttributeInstruction(){}
  public AttributeInstruction(String xmlAtt){ setName(xmlAtt); }
  public AttributeInstruction(String xmlAtt, String javaProp, String defaultVal, String type, boolean required){
      setName(xmlAtt);
      setJavaProperty(javaProp);
      setDefaultValue(defaultVal);
      setType(type);
      setIsRequired(required);
  }

  public String getName(){ return xmlAttribute; }
  public void setName(String s) { xmlAttribute = s; }
  public String getJavaProperty() {
    if(null==javaProperty) javaProperty = StringUtil.xmlAttribute2JavaProperty(xmlAttribute);
    return javaProperty;
  }
  public void setJavaProperty(String s) { javaProperty = s; }
  public Object getDefaultValue() { return defaultValue; }
  public void setDefaultValue(Object o) { defaultValue=o; }
  public String getType() { return type; }
  public void setType(String s) { type=s; }
  public boolean isRequired() { return isRequired; }
  public void setIsRequired(boolean b) { isRequired=b; }

  public Object valueOf(String value) throws InvalidAttributeInstruction {
    if (null==value) return defaultValue;
    try { return StringUtil.valueOf(value, getType()); }
    catch (Exception e) { throw new InvalidAttributeInstruction("Can not convert attribute "+getName()+" = \""+value+"\" to type " + type + " for tag " + getParentTagInstruction().getTagName()); }
  }

  public void setParentTagInstruction(TagInstruction ins) { parentTagInstruction = ins; }
  public TagInstruction getParentTagInstruction() { return parentTagInstruction; }

  private String xmlAttribute=null;
  private String javaProperty=null;
  private Object defaultValue=null;
  private String type="string";
  private boolean isRequired=false;
  private TagInstruction parentTagInstruction=null;
}
