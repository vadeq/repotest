package zws.log;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MessageBase implements Message, Serializable { //++ lookup message gey
  public MessageBase(String key) { messageKey = extractKey(key); }
  public MessageBase(String key, String arg1) { messageKey=extractKey(key); msgArg1=arg1; }
  public MessageBase(String key, String arg1, String arg2) { messageKey=extractKey(key); msgArg1=arg1; msgArg2=arg2; }
  public MessageBase(Exception e) { messageKey = extractKey(e.getMessage()); exception = e; }
  public MessageBase(String key, Exception e) { messageKey = extractKey(key); exception = e; }
  public MessageBase(String key, String arg1, Exception e) { messageKey = extractKey(key); msgArg1=arg1; exception = e; }
  public MessageBase(String key, String arg1, String arg2, Exception e) { messageKey = extractKey(key); msgArg1=arg1; msgArg2=arg2; exception = e; }

  public static String extractKey(String s) {
    if (null==s) return "err.unknown";
    if (s.indexOf(' ')>-1) return s.substring(s.lastIndexOf(' ')+1);
    else return s;
  }
  
  public Calendar getTime() { return time; }
  public String getMessageKey() { return messageKey; }
  public String getMessage() {
    return messageKey; //++ lookup key in resource file
  }
  public Exception getException() { return exception; }

  public String toString() { 
    String s = getMessageKey() +": " + getMessage(); 
    if (null!=exception) s += "  Exception: " + exception.getMessage();
    return s;
  }
  private String messageKey=null, msgArg1=null, msgArg2=null;
  private Exception exception=null;
  private Calendar time = new GregorianCalendar();
}