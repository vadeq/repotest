package com.zws.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 16, 2003, 3:14 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import com.zws.application.Constants;
import com.zws.util.FileNameUtil;

import java.io.FileOutputStream;
import java.util.*;

import org.xml.sax.Attributes;

public class TejasSearchCSVHandler extends IntralinkResultHandler { //+++bad inheritance structure -  need a common interface:getResults()
  public void startElement (String uri, String name, String qName, Attributes atts) {
    try { if ( qName.equalsIgnoreCase("object")) write(mapAttributes(atts)); }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void startDocument() {
    try{
      getUserOutputStream();
      getSystemOutputStream();
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  public void endDocument() {
    try {
    getUserOutputStream().close();
    getSystemOutputStream().close();
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  
  private Map mapAttributes(Attributes atts) {
    Map sysAtts = new HashMap();
    Map userAtts = new HashMap();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++) {
      key = atts.getLocalName(idx);
      value = atts.getValue(idx);
      if (systemAttributes.containsKey(key)) sysAtts.put(key,value);
      else userAtts.put(key, value);
    }
    sysAtts.put("type", FileNameUtil.lookupFileType(atts.getValue("name")));
    Map a = new HashMap();
    a.put(SYSTEM_ATTRIBUTES, sysAtts);
    a.put(USER_ATTRIBUTES, userAtts);
    return a;
  }
  
  private String getOrigin(Map atts) {
    String origin = getServerName();
    origin += Constants.ORIGIN_DELIMITER + getDatasourceName();
    origin += Constants.ORIGIN_DELIMITER + (String)atts.get("name");
    origin += Constants.ORIGIN_DELIMITER + (String)atts.get("revision");
    origin += Constants.ORIGIN_DELIMITER + (String)atts.get("version");
    return origin;
  }
  
  private void write(Map atts) throws Exception {
    Map m = (Map)atts.get(SYSTEM_ATTRIBUTES);
    String origin = getOrigin(m);
    m.put("name", FileNameUtil.getBaseName((String)m.get("name")));
    writeSystemAttributes(origin, m);
    getSystemOutputStream().write('\n');

    m = (Map) atts.get(USER_ATTRIBUTES);
    writeUserAttributes(origin, m);
  }
  
  private void writeSystemAttributes(String origin, Map m) throws Exception {
    FileOutputStream s = getSystemOutputStream();
    Iterator i = systemAttributeList.iterator();
    String field;
    write(s, origin);
    while (i.hasNext()) {
      s.write(',');
      field = (String)i.next();
      write(s, (String)m.get(field));
    }
  }

  private void writeUserAttributes(String origin, Map m) throws Exception {
    FileOutputStream s = getUserOutputStream();
    Iterator i = m.keySet().iterator();
    String field;
    if (i.hasNext()) {
      field = (String)i.next();
      write(s, origin);
      s.write(',');
      write(s, field);
      s.write(',');
      write(s, (String)m.get(field));
      s.write('\n');
    }
    while (i.hasNext()) {
      field = (String)i.next();
      write(s, origin);
      s.write(',');
      write(s, field);
      s.write(',');
      write(s, (String)m.get(field));
      s.write('\n');
    }
  }
  
  private void write(FileOutputStream out, String s) throws Exception {
    //out.write('\'');
    if (null==s) {
      //out.write('\'');
      return;
    }
    String value = s.replace(',', ':');
    char[] chars = value.toCharArray();
    for (int i = 0; i < chars.length; out.write(chars[i++]));
    //out.write('\'');
  }

  private FileOutputStream getSystemOutputStream() throws Exception {
    if (null==sysOut) sysOut = new FileOutputStream(getSystemAttributesFile());
    return sysOut;
  }

  private FileOutputStream getUserOutputStream() throws Exception {
    if (null==userOut) userOut = new FileOutputStream(getUserDefinedAttributesFile());
    return userOut;
  }
  /*
  private Map mapAttributes(Attributes atts) {
    Map a = new HashMap();
    for (int idx = 0; idx < atts.getLength(); a.put(atts.getLocalName(idx), atts.getValue(idx++)));
    return a;
  }
  */
  public void setSystemAttributes(String s) {
    StringTokenizer tokens = new StringTokenizer(s, DELIMITER); 
    String field;
    while (tokens.hasMoreTokens()) {
      field = tokens.nextToken();
      systemAttributeList.add(field);
      systemAttributes.put(field, field);
    }
  }

  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }

  public String getSystemAttributesFile() { return systemAttributesFile; }
  public void setSystemAttributesFile(String s) { systemAttributesFile = s; }

  public String getUserDefinedAttributesFile() { return userDefinedAttributesFile; }
  public void setUserDefinedAttributesFile(String s) { userDefinedAttributesFile = s; }
  
  public Collection getResults(){ return getComponents(); }
  public Collection getComponents() { return components; }
  private Collection components = new Vector();
  private Map systemAttributes = new HashMap();
  private Collection systemAttributeList = new Vector();
  private static String DELIMITER = ";";
  private String serverName="_";
  private String datasourceName="_";
  private FileOutputStream sysOut = null;
  private FileOutputStream userOut = null;
  private String systemAttributesFile = "/sys.csv";
  private String userDefinedAttributesFile = "/user.csv";
  private static String SYSTEM_ATTRIBUTES = "system";
  private static String USER_ATTRIBUTES = "user";
}
