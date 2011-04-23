package zws.qx.program;

/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 25, 2007 11:32:15 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class QxInstruction {
  public QxInstruction() {}
  
  public QxInstruction(String name) { setName(name); }
  
  public final String getName() { return tagName; }
  public final void setName(String name) { tagName = name; }

  public void set(String parameter, String value) { properties.put(parameter, value); }
  public String get(String parameter) { return (String)properties.get(parameter); }  

  public void setBool(String parameter, boolean value) { properties.put(parameter, Boolean.valueOf(value).toString()); }
  public boolean getBool(String parameter) { return  Boolean.valueOf((String)properties.get(parameter)).booleanValue(); }  

  public void setLong(String parameter, long value) { properties.put(parameter, ""+value); }
  public long getLong(String parameter) { return  Long.valueOf((String)properties.get(parameter)).longValue(); } 
  
  public void setInteger(String parameter, int value) { properties.put(parameter, ""+value); }
  public int getInteger(String parameter) { return  Integer.valueOf((String)properties.get(parameter)).intValue(); }  

  public Map getProperties() { return properties; }
  public void setProperties(Map properties) { this.properties = properties; }

  public void add(QxInstruction instruction){ getSubInstructions().add(instruction); }
  public void setSubInstructions(Collection subInstructions) { subInstructions = subInstructions; }
  public Collection getSubInstructions() {
    if( subInstructions == null ){
      subInstructions = new Vector();
    }
    return subInstructions;
  }

  public final String toString() {
    StringBuffer xmlBuffer = new StringBuffer();
    write(xmlBuffer);
    return xmlBuffer.toString();
  }
  
  public final void write(StringBuffer xmlBuffer) {
    if (null==subInstructions || subInstructions.isEmpty()) writeUnaryTag(xmlBuffer);
    else writeBinaryTag(xmlBuffer);
  }
  
  public final void writeBinaryTag(StringBuffer xmlBuffer) {
    xmlBuffer.append(OPEN_TAG).append(tagName).append(SPACE);
    writeParameters(xmlBuffer);
    xmlBuffer.append(END_BINARY_OPEN_TAG).append(NL);
    if (null!=subInstructions && !subInstructions.isEmpty()) {
      Iterator i = subInstructions.iterator();
      QxInstruction sub=null;
      while(i.hasNext()) {
        sub = (QxInstruction)i.next();
        sub.write(xmlBuffer);
      }
    }
    xmlBuffer.append(OPEN_BINARY_CLOSE_TAG).append(tagName).append(END_BINARY_CLOSE_TAG).append(NL);
  }
  
  protected final void writeUnaryTag(StringBuffer xmlBuffer) {
    xmlBuffer.append(OPEN_TAG).append(tagName).append(SPACE);
    writeParameters(xmlBuffer);
    xmlBuffer.append(END_UNARY_OPEN_TAG).append(NL);
  }
  
  private void writeParameters(StringBuffer xmlBuffer) {
    if (null==properties || properties.isEmpty()) return;
    Iterator i = properties.keySet().iterator();
    String parameter, value;
    while (i.hasNext()) {
      parameter = i.next().toString();
      value = (String)properties.get(parameter);
      value = xmlValue(value);
      xmlBuffer.append(parameter).append(EQUALS).append(SINGLE_QUOTE).append(value).append(SINGLE_QUOTE).append(SPACE);
    }
  }

  protected String xmlValue(String s){
    if (null==s || "".equals(s.trim())) return "";
    StringBuffer x = new StringBuffer();
    int idx;
    char c;
    for (idx=0; idx<s.length(); idx++) {
      c = s.charAt(idx);
      switch (c) {
        case '&' : x.append("&amp;");  break;
        case '"' : x.append("&quot;"); break;
        case '\'': x.append("&apos;"); break;
        case '<' : x.append("&lt;");   break;
        case '>' : x.append("&gt;");   break;
        default  : x.append(c);
      }
    }
    return x.toString();
  }
  
  private String tagName = null;
  private Map properties = new HashMap();
  private Collection subInstructions = null;  
  
  
  private static String OPEN_TAG = "<";
  private static String END_UNARY_OPEN_TAG = "/>";
  private static String END_BINARY_OPEN_TAG = ">";
  private static String OPEN_BINARY_CLOSE_TAG = "</";
  private static String END_BINARY_CLOSE_TAG  = ">";
  private static String SPACE = " ";
  private static String EQUALS = "=";
  private static String SINGLE_QUOTE = "'";
  private static String DOUBLE_QUOTE = "\"";
  private static String NL = System.getProperty("line.separator");;
}
