package com.zws.util;

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
  
  public static String xmlAttribute2JavaProperty(String xmlAttribute){
    String token;
    String javaProperty=xmlAttribute;
    StringTokenizer tok = new StringTokenizer(xmlAttribute, DASH);
    if (tok.hasMoreTokens()) javaProperty = tok.nextToken();
    while (tok.hasMoreTokens()) javaProperty += capitalize(tok.nextToken());
    return javaProperty;
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
  
  
  
  /**
   * @author Rahul Deshmukh This method truncates the string and pads "..." if string.length() > value passed
   * @param String object
   * @param length int length of String to be displayed
   */
  public static String truncateWPadding(String obj, int length) {
      if ((obj == null) || (obj.trim ().length () == 0)) {
          obj = "";
      } else if (obj.trim ().length () > length) {
          if(length >3)
          {   
              obj = obj.trim ().substring (0, length-3).concat ("...");
          }else {
              obj = obj.trim ().substring (0, length).concat ("...");
          }   
      }
      return obj;
  }
  
  
  
  /** Checks for an empty string. Null strings are considered empty */
  public static boolean isEmptyNullString(String str) {
      if ((null == str) || (str.trim ().length () == 0)) {
          return true;
      } 
      return false;
  }
  
  public static String replace(String aInput, String aOldPattern, String aNewPattern){
     if (null ==aOldPattern || aOldPattern.equals("") || null ==aNewPattern) return aInput;
     
    Pattern pattern = Pattern.compile(aOldPattern);
    Matcher matcher = pattern.matcher(aInput);
    return matcher.replaceAll(aNewPattern);
  }
  public static String trimDate(String obj) {
      //3/12/20 12:12:12
      int len = 9;
      
      if ((obj == null) || (obj.trim ().length () == 0)) {
          obj = "";
      } else if (obj.trim ().length () > len) {
          obj = obj.trim ().substring (9);
      }
      return obj;
  }
 
  
  

  public static String DASH = "-";
}
