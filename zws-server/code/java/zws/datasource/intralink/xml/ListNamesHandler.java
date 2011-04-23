package zws.datasource.intralink.xml; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.log.failure.Failure;

import org.xml.sax.Attributes;

public class ListNamesHandler extends IntralinkResultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    if ( qName.equals("m")) { add(atts); return; }
    if ( qName.equalsIgnoreCase("metadata")) { add(atts); return; }
    try {
      if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  private void add(Attributes atts) {
    try {getStorable().store(unmarshallFastName(atts));}
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }
}
