package zws.xml.util; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class XMLString {

  public static String asXMLName(String s){
    if (null==s) return null;
    return s.trim().replace(' ', '-');
  }

  public static void writeValue(String value, StringBuffer stream) throws Exception {
    if (null==value || null==stream) return;
    char[] v = value.toCharArray();
    for (int i = 0; i < v.length ; i++) {
      if ('&' == v[i]) stream.append("&amp;");
      else if ('<' == v[i]) stream.append("&lt;");
      else if ('>' == v[i]) stream.append("&gt;");
      else if ('\'' == v[i]) stream.append("&apos;");
      else if ('"' == v[i]) stream.append("&quot;");
      else stream.append(v[i]);
    }
  }
}