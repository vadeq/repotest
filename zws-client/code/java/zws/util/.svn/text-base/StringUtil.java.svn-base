package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.c;
import zws.application.s;
import zws.exception.InvalidName;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
  public static String capitalize(String s) { return s.substring(0,1).toUpperCase()+s.substring(1); }

  public static String getTime() { return getTime(new GregorianCalendar()); }

  public static String getTime(Calendar c) {
    String y,M,d,h,m,s;
    y=""+c.get(Calendar.YEAR);
    M=""+(1+c.get(Calendar.MONTH));
    d=""+c.get(Calendar.DATE);
    h=""+c.get(Calendar.HOUR_OF_DAY);
    m=""+c.get(Calendar.MINUTE);
    s=""+c.get(Calendar.SECOND);
    if (M.length()==1) M = "0" + M;
    if (d.length()==1) d = "0" + d;
    if (h.length()==1) h = "0" + h;
    if (m.length()==1) m = "0" + m;
    if (s.length()==1) s = "0" + s;
    String dot = ".";
    return y + dot + M + dot + d + dot + h + dot + m + dot +s;
  }

  public static String attributeName2xmlAttribute(String name){
    return javaName2xmlAttribute(name);
  }

  public static String javaName2xmlAttribute(String javaName){
    char[] chars = javaName.toCharArray();
    StringBuffer xmlAttribute = new StringBuffer();
    if (chars.length==0) return null;
    for (int idx=0; idx<chars.length; idx++) {
      if (chars[idx]>='A' && chars[idx]<='Z') {
        if (idx!=0) xmlAttribute.append('-');
        xmlAttribute.append((char)(chars[idx]+32)); //to Lowercase
      }
      else xmlAttribute.append(chars[idx]);
    }
    return xmlAttribute.toString();
  }

  public static String name2xmlAttribute(String javaName){
    char[] chars = javaName.toCharArray();
    StringBuffer xmlAttribute = new StringBuffer();
    if (chars.length==0) return null;
    for (int idx=0; idx<chars.length; idx++) {
      if (chars[idx]>='A' && chars[idx]<='Z') {
        if (idx!=0) xmlAttribute.append('-');
        xmlAttribute.append((char)(chars[idx]+32)); //to Lowercase
      }
      else {
        if (0==idx && isValidXMLNameStartingCharacter(chars[idx])) xmlAttribute.append(chars[idx]);
        else if (0==idx) xmlAttribute.append("z-");
        else if (0<idx && isValidXMLNameCharacter(chars[idx])) xmlAttribute.append(chars[idx]);
      }
    }
    return xmlAttribute.toString();
  }

  public static String xmlAttribute2JavaProperty(String xmlAttribute){
    String token;
    String javaProperty=xmlAttribute;
    StringTokenizer tok = new StringTokenizer(xmlAttribute, DASH);
    if (tok.hasMoreTokens()) javaProperty = tok.nextToken();
    while (tok.hasMoreTokens()) javaProperty += capitalize(tok.nextToken());
    return javaProperty;
  }

  private static void validateAsXMLAttributeName(String name) throws InvalidName {
    if (null==name) throw new InvalidName("null");
    char[] chars = name.toCharArray();
    if (chars.length==0) throw new InvalidName("zero-length name is not allowed as an XML attribute");
    char c = chars[0];
    if (!isValidXMLNameStartingCharacter(c)) throw new InvalidName("XML attribute name ["+name+"] must start with an alpha character");
    for (int idx=1; idx<chars.length; idx++) {
      c = chars[idx];
      if (!isValidXMLNameCharacter(c)) throw new InvalidName("XML attribute name ["+name+"] can only include alpha-numeric, '-' or '_'  ['" + c + "' is an invalid character]");
    }
  }

  private static boolean isValidXMLNameStartingCharacter(char c) {
    if (!((c>='a' && c<='z') || (c>='A' && c<='Z'))) return false;
    return true;
  }

  private static boolean isValidXMLNameCharacter(char c) {
    if (!((c>='a' && c<='z') || (c>='A' && c<='Z') || (c>='0' && c<='9')  || c=='-' || c=='_')) return false;
    return true;
  }

  public static String unaryXMLTag(String tagName, Map attributes) throws InvalidName {
    StringBuffer xml = new StringBuffer();
    appendUnaryXMLTag(xml, tagName, attributes);
    return xml.toString();
  }

  public static String openBinaryXMLTag(String tagName, Map attributes) throws InvalidName {
    StringBuffer xml = new StringBuffer();
    appendBinaryXMLTag(xml, tagName, attributes);
    return xml.toString();
  }

  public static String closeBinaryXMLTag(String tagName) throws InvalidName {
    StringBuffer xml = new StringBuffer();
    xml.append(s.startClosingBinaryXML).append(tagName).append(s.closeBinaryXML);
    return xml.toString();
  }


  public static void appendUnaryXMLTag(StringBuffer xml, String tagName, Map attributes) throws InvalidName{
    xml.append(c.openXML).append(tagName);
    appendAsXMLAttributes(xml, attributes);
    xml.append(s.closeUnaryXML).append(s.newLine);
  }

  public static void appendBinaryXMLTag(StringBuffer xml, String tagName, Map attributes) throws InvalidName{
    xml.append(c.openXML).append(tagName);
    appendAsXMLAttributes(xml, attributes);
    xml.append(s.closeBinaryXML).append(s.newLine);
  }

  public static void appendAsXMLAttributes(StringBuffer xml, Map attributes) throws InvalidName {
    Iterator i = attributes.keySet().iterator();
    String key, value;
    Object val;
    while (i.hasNext()) {
      key = (String)i.next();
      val = attributes.get(key);
      if (null==val) value="";
      else value=val.toString();
      appendAsXMLAttribute(xml, StringUtil.name2xmlAttribute(key), value);
    }
  }

  public static void appendAsXMLAttribute(StringBuffer xml, String name, String value) throws InvalidName {
    validateAsXMLAttributeName(name);
    xml.append(c.space).append(name).append(c.equal);
    appendXMLValue(xml,value);
  }

  public static void appendXMLValue(StringBuffer xml, String value) {
    char[] chars = value.toCharArray();
    char x;
    xml.append(c.quote);
    for (int idx=0; idx<chars.length; idx++) {
      x=chars[idx];
      if (x=='&') xml.append("&amp;");
      else if (x=='"') xml.append("&quot;");
      else if (x=='\'') xml.append("&apos;");
      else if (x=='<') xml.append("&lt;");
      else if (x=='>') xml.append("&gt;");
      else xml.append(x);
    }
    xml.append(c.quote);
  }
  
  public static String getXMLValue(String value) {
    StringBuffer xml = new StringBuffer();
    char[] chars = value.toCharArray();
    char x;
    for (int idx=0; idx<chars.length; idx++) {
      x=chars[idx];
      if (x=='&') xml.append("&amp;");
      else if (x=='"') xml.append("&quot;");
      else if (x=='\'') xml.append("&apos;");
      else if (x=='<') xml.append("&lt;");
      else if (x=='>') xml.append("&gt;");
      else xml.append(x);
    }
    return xml.toString();
  }

  public static Object valueOf(String value, String type) throws Exception {
    if (null==value) return null;
    if ("string".equalsIgnoreCase(type)) return value;
    if ("boolean".equalsIgnoreCase(type) ) {
      if ( "true".equalsIgnoreCase(value) || !"t".equalsIgnoreCase(value)) Boolean.valueOf("true");
      else if ( "Faluse".equalsIgnoreCase(value) || !"f".equalsIgnoreCase(value)) Boolean.valueOf("false");
      else throw new Exception("Can not convert " + value + " to a Boolean");
    }
    if ("int".equalsIgnoreCase(type) || "integer".equalsIgnoreCase(type) ) {
      try { Integer.parseInt(value); }
      catch (Exception e) {throw new Exception("Can not convert " + value + " to an Integer");}
      return Integer.valueOf(value);
    }
    if ("long".equalsIgnoreCase(type) ) {
      try { Integer.parseInt(value); }
      catch (Exception e) {throw new Exception("Can not convert " + value + " to a Long");}
      return Long.valueOf(value);
    }
    if ("float".equalsIgnoreCase(type) ) {
      try { Float.parseFloat(value); }
      catch (Exception e) {throw new Exception("Can not convert " + value + " to a Float");}
      return Float.valueOf(value);
    }
    if ("double".equalsIgnoreCase(type) ) {
      try { Double.parseDouble(value); }
      catch (Exception e) {throw new Exception("Can not convert " + value + " to a Double");}
      return Double.valueOf(value);
    }
    return value;
  }

  public static String trimQuotes(String s) {
    if (null==s || s.length()<2) return s;
    String x = s.trim();
    String quote = s.substring(0,1);
    char c = quote.charAt(0);
    if ( (c>='a' && c<='z') || (c>='A' && c<='Z') || (c>='0' && c<='9') ) return s;
    if (x.startsWith(quote) && x.endsWith(quote)) {
     x = x.substring(0,x.length()-1);
     return x;
    }
    else return x;
  }

  public static boolean isWhiteSpace(char c) {
    boolean result = false;
    switch (c) {
      case ' ':  { result= true; break; }
      case '\t': { result= true; break;}
      case '\n': { result= true; break; }
      case '\f': { result= true; break; }
      case '\r': { result= true; break; }
    }
    return result;
  }

  public static String replace(String aInput, String aOldPattern, String aNewPattern){
    if (null ==aOldPattern || aOldPattern.equals("") || null ==aNewPattern) return aInput;
    Pattern pattern = Pattern.compile(aOldPattern);
    Matcher matcher = pattern.matcher(aInput);
    return matcher.replaceAll(aNewPattern);
 }

  public static String truncate(String s, int charCount) {
    String subString = s;
    if (s!= null && s.length() > charCount) {
      subString = s.substring(0,charCount);
    }
    return subString;
  }
  
  public static String truncateWithIndicator(String s, int charCount) {
    if (s == null || s.length() <  charCount) return s; 
    String subString = truncate(s, charCount-3);
    return subString + "...";
  }
  
  public static String DASH = "-";
}
