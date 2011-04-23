package com.zws.xml.parser;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.helpers.DefaultHandler;


public class Instructions extends DefaultHandler {
  private static String NO_OP = "no-op";
  private static String ATTRIBUTE_IGNORE="ignore";
  private static String ATTRIBUTE_CLASS="class";
  private static String ATTRIBUTE_STATIC_METHOD="static-method";
  private static String ATTRIBUTE_METHOD="method";
  private static String ATTRIBUTE_INSTANCE_OF="instance-of";
  private static String ATTRIBUTE_GET_SINGLETON="get-singleton";
  private static String ATTRIBUTE_SINGLETON_METHOD="singleton-method";

  private static String DEFAULT_METHOD="add";
  private static String DEFAULT_SINGLETON_METHOD="getInstance";

  private static String ATTRIBUTE_PROPERTY = "property";
  private static String ATTRIBUTE_PROPERTY_DEFAULT = "default";
  private static String ATTRIBUTE_PROPERTY_REQUIRED = "required";

  private static String TAG_INSTRUCTION = "instructions";

  private static String STATE_ELEMENT = "element";
  private static String STATE_ATTRIBUTE = "attribute";

  private String state;
  private String currentTagMapping;
  private Map finders;
  private Map placers;
  private Map attributeList;

  public Instructions() { finders = new HashMap(); placers = new HashMap(); }
/*
  protected Instructions(Map locateInstructions, Map placementInstructions, Map attributeInstructions) {
    finders = locateInstructions; placers = placementInstructions; attributeList = attributeInstructions;
  }

  public void load(InputSource xml, Map locateInstructions, Map placementInstructions, Map attributeInstructions)
      throws SAXException, ParserConfigurationException, IOException {
    XMLValidator.validate(xml, false);
    getParser(false).parse( xml, new Instructions(locateInstructions, placementInstructions, attributeInstructions));
  }
  public void load(InputStream xml, Map locateInstructions, Map placementInstructions, Map attributeInstructions)
      throws SAXException, ParserConfigurationException, IOException {
    XMLValidator.validate(xml, false);
    getParser(false).parse( xml, new Instructions(locateInstructions, placementInstructions, attributeInstructions));
  }
  public void load(File xml, Map locateInstructions, Map placementInstructions, Map attributeInstructions)
      throws SAXException, ParserConfigurationException, IOException {
    XMLValidator.validate(xml, false);
    getParser(false).parse( xml, new Instructions(locateInstructions, placementInstructions, attributeInstructions));
  }
  public void load(String xml, Map locateInstructions, Map placementInstructions, Map attributeInstructions)
      throws SAXException, ParserConfigurationException, IOException {
    XMLValidator.validate(xml, false);
    getParser(false).parse( xml, new Instructions(locateInstructions, placementInstructions, attributeInstructions));
  }

  private static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException{
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }

  public void startDocument(){
    state = STATE_ELEMENT;
    {} //System.out.println("");
    {} //System.out.println("Loading instructions.....");
  }
  public void endDocument(){
    {} //System.out.println("Instructions loaded.");
    {} //System.out.println("");
  }

  public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws TagMapException {
    if (STATE_ELEMENT == state)
      loadElement(namespaceURI, localName, qName, atts);
    else if (STATE_ATTRIBUTE==state)
      loadAttributes(namespaceURI, localName, qName, atts);
  }

  public void endElement(String namespaceURI, String localName, String qName) {
    if (STATE_ATTRIBUTE == state && qName == currentTagMapping){
      currentTagMapping = null;
      state = STATE_ELEMENT;
    }
  }
  private void loadElement(String namespaceURI, String localName, String qName, Attributes atts) throws TagMapException {
    currentTagMapping = qName;
    if (qName.equals(ROOT_ELEMENT)) return;
    XMLFunctor action = extractAction(qName, atts);
    Caller target = extractTarget(qName, atts);
//    {} //System.out.println(qName + " -> target=" + target.getClass().getName() + "           action=" + action.getClass().getName());
    Object o = finders.put(qName, action);
    Object oo = placers.put(qName, target);
    if (null!=o || null!=oo) throw new TagMapException ( "Duplicate instruction:"+qName+".");
  }

 private String xml2JavaNaming(String xmlName){
   StringTokenizer tok = new StringTokenizer(xmlName, "-");
   String nugget;
   String javaName = "";
   if (tok.hasMoreTokens()) javaName += tok.nextToken();
   while (tok.hasMoreTokens()) {
     nugget=tok.nextToken();
     javaName += nugget.substring(0,1).toUpperCase() + nugget.substring(1);
   }
   if (javaName.equals("")) javaName= xmlName;
   return javaName;
 }


  private void loadAttributes(String namespaceURI, String localName, String qName, Attributes atts) throws TagMapException {
    Map attributeList = (Map) attributeList.get(currentTagMapping);
    if (null==attributeList) { attributeList = new HashMap(); attributeList.put(currentTagMapping, attributeList); }
    String propertyName = atts.getValue(ATTRIBUTE_PROPERTY);
    if (null==propertyName) propertyName = xml2JavaNaming(qName);
    String defaultValue = atts.getValue(ATTRIBUTE_PROPERTY_DEFAULT_VALUE);
    String reqd = atts.getValue(ATTRIBUTE_PROPERTY_REQUIRED);
    boolean required = false;
    if (null!=reqd) required = reqd.toLowerCase().equals("true");
    if (defaultValue!=null && !defaultValue.equals("") && required)
      throw new TagMapException("Can not specify both "+ATTRIBUTE_PROPERTY_DEFAULT_VALUE+" and "+ATTRIBUTE_PROPERTY_REQUIRED+"=\"true\" for property mapping: " + qName+" in tag mapping: "+currentTagMapping+"!");
    if (reqd!=null && !(reqd.toLowerCase().equals("false") || reqd.toLowerCase().equals("true")))
      throw new TagMapException("Attribute "+ATTRIBUTE_PROPERTY_REQUIRED+"=\""+reqd+"\" - it must either be \"true\" or \"false\" for property mapping: " + qName+" in tag mapping: "+currentTagMapping+"!");
    String type = atts.getValue("type");
    AttributeMapping aMap = new AttributeMapping(propertyName, type, defaultValue, required);
    Object o = attributeList.put(qName, aMap);
    if (null!= o) throw new TagMapException ("Found duplicate property mapping:"+qName+"!");
  }

  private Caller extractTarget(String qName, Attributes atts) throws TagMapException {
    String targetType=null;
    if (null!=atts.getValue(ATTRIBUTE_IGNORE)) targetType=NO_OP;
    else if (null!=atts.getValue(ATTRIBUTE_STATIC_METHOD))targetType=TARGET_CAll_STATIC_METHOD_ON_CLASS;
    else if (null!=atts.getValue(ATTRIBUTE_CLASS))targetType=TARGET_CAll_STATIC_METHOD_ON_CLASS;
    else if (null!=atts.getValue(ATTRIBUTE_METHOD)) targetType=TARGET_CAll_METHOD_ON_PARENT;
    else if (null!=atts.getValue(ATTRIBUTE_PROPERTY)) targetType=TARGET_SET_PROPERTY_ON_PARENT;
    else if (null!= atts.getValue(ATTRIBUTE_TARGET_TYPE)) targetType = atts.getValue(ATTRIBUTE_TARGET_TYPE);
    else targetType=NO_OP;

    Caller target = null;
    if (null == targetType) targetType = DEFAULT_TARGET_TYPE;
    //+++ Configure the TagMap to look these up from a map (!)
    if (targetType.equals(TARGET_ADD_TO_PARENT)) target = new  AddInvoker();
    if (targetType.equals(TARGET_SET_PROPERTY_ON_PARENT)) {
      String propertyName = atts.getValue(ATTRIBUTE_PROPERTY);
      if(null==propertyName) propertyName = atts.getValue(ATTRIBUTE_TARGET_PROPERTY);
      if (null==propertyName || propertyName.equals(""))
        throw new TagMapException(qName+"property not specified.");
      target = new  PropertySetter(propertyName);
    }
    if (targetType.equals(TARGET_CAll_METHOD_ON_PARENT)) {
      String methodName = atts.getValue(ATTRIBUTE_METHOD);
      if(null==methodName) methodName = atts.getValue(ATTRIBUTE_TARGET_METHOD);
      if (null==methodName || methodName.equals(""))
        throw new TagMapException(qName+": method not specified.");
      target = new  Caller(methodName);
    }
    if (targetType.equals(TARGET_CAll_STATIC_METHOD_ON_CLASS)){
      String methodName = atts.getValue(ATTRIBUTE_STATIC_METHOD);
      if(null==methodName) methodName = atts.getValue(ATTRIBUTE_TARGET_METHOD);
      String fqcn =atts.getValue(ATTRIBUTE_CLASS);
      if (null==fqcn) fqcn=atts.getValue(ATTRIBUTE_TARGET_FQCN);
      if (null==fqcn || fqcn.equals(""))
        throw new TagMapException(qName+ " class not specified");
      if (null==methodName || methodName.equals(""))
        throw new TagMapException(qName+ " static-method not specified");
      Class c = null;
      try{ c = Class.forName(fqcn); }
      catch (Exception e) { throw new TagMapException("Could not find class:"+fqcn); }
      target = new  StaticMethodCaller(c, methodName);
    }
    if (targetType.equals(NO_OP)) target = new NoOpCommand();
    if (null == target) throw new TagMapException("Unknown "+ATTRIBUTE_TARGET_TYPE+" specified in tag: " + qName + " "+ATTRIBUTE_TARGET_TYPE+"=\""+targetType+"\"!");
    return target;
  }

  private XMLFunctor extractAction(String qName, Attributes atts) throws TagMapException {
    XMLFunctor action = null;
    String actionType=null;
    if ("true".equalsIgnoreCase(atts.getValue(ATTRIBUTE_IGNORE))) actionType=NO_OP;
    else if (null!=atts.getValue(ATTRIBUTE_INSTANCE_OF)) actionType=ACTION_CREATE_OBJECT;
    else actionType = atts.getValue(ATTRIBUTE_ACTION_TYPE);
    //+++ configure the TagMap to look these up from a map (!)
    if (null==actionType) throw new TagMapException(qName + ": Can not determine action. ["+ATTRIBUTE_IGNORE+", "+ATTRIBUTE_INSTANCE_OF+"]");
    if (actionType.equals(ACTION_CREATE_OBJECT)) {
      state = STATE_ATTRIBUTE;
      String fqcn = atts.getValue(ATTRIBUTE_INSTANCE_OF);
      if (null==fqcn) fqcn = atts.getValue(ATTRIBUTE_FQCN);
      if (null == fqcn || fqcn.equals(""))
        throw new TagMapException("Need to specify fqcn attribute (fully qualified class name) to create an object for tag: "+qName+"!");
      Class c = null;
      try {	c= Class.forName(fqcn); }
      catch (Exception e) { throw new TagMapException("Could not load class specified in tag: " + qName +"[fqcn="+fqcn+"]!"); }
      action = new InstanceCreator(fqcn);
    }
    if (actionType.equals(ACTION_CREATE_STRING)) action = new XMLStringCreator();
    if (actionType.equals(NO_OP))
      action = new NoOp();
    if (null == action) throw new TagMapException("Unknown "+ATTRIBUTE_ACTION_TYPE+" specified in tag: " + qName + " "+ATTRIBUTE_ACTION_TYPE+"=\""+actionType+"\"!");
    return action;
  }

  public class AttributeMapping {
    public AttributeMapping(String name, String type, String defaultValue, boolean required) throws TagMapException
    { this.propertyName = name; this.type = type; this.defaultValue = convertValue(defaultValue); this.required = required; }
    public String getPropertyName(){ return propertyName; }
    public void setPropertyName(String propertyName){ this.propertyName = propertyName; }
    public boolean getRequired(){ return required; }
    public void setRequired(boolean required){ this.required = required; }
    public Object getDefaultValue(){ return defaultValue; }
    public void setDefaultValue(Object defaultValue){ this.defaultValue = defaultValue; }

    public Object convertValue(String v) throws TagMapException {
      if (null==v) return null;
      if ("string".equals(type) ) return v;
      if ("boolean".equals(type) ) {
        if ( !v.toLowerCase().equals("true") && !v.toLowerCase().equals("t") &&
             !v.toLowerCase().equals("false")  && !v.toLowerCase().equals("f") )
          throw new TagMapException("The value: \""+v+"\" is not a valid boolean!");
        else return Boolean.valueOf(v);
      }
      if ("int".equals(type) || "integer".equals(type) ) {
        try { Integer.parseInt(v); }
        catch (Exception e) {throw new TagMapException("The value: \""+v+"\" is not a valid int!");}
        return Integer.valueOf(v);
      }
      if ("long".equals(type) ) {
        try { Integer.parseInt(v); }
        catch (Exception e) {throw new TagMapException("The value: \""+v+"\" is not a valid int!");}
        return Long.valueOf(v);
      }
      if ("float".equals(type) ) {
        try { Float.parseFloat(v); }
        catch (Exception e) {throw new TagMapException("The value: \""+v+"\" is not a valid floating point!");}
        return Float.valueOf(v);
      }
      if ("double".equals(type) ) {
        try { Double.parseDouble(v); }
        catch (Exception e) {throw new TagMapException("The value: \""+v+"\" is not a valid double floating point!");}
        return Double.valueOf(v);
      }
      // +++ create the specified object, with any initial parameters
      return v;
    }
    public void setType (String type) { if (null==type) this.type="string"; else this.type = type.toLowerCase(); }
    public String getType(){ return (null==type)? "String" : type; }

    private String propertyName;
    private boolean required;
    private Object defaultValue;
    private String type;
  }
*/
}