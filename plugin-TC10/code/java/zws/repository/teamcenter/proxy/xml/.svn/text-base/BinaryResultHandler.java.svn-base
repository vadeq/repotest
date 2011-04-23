package zws.repository.teamcenter.proxy.xml;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.repository.teamcenter.TC10Constants;

import java.io.File;

import org.xml.sax.Attributes;

public class BinaryResultHandler extends TC10ResultHandler {
  
  public void startElement (String uri, String name, String qName, Attributes atts) {
    try {
      File f = null;
      if(qName.equalsIgnoreCase(TC10Constants.TAG_FILE)) {
        {} //System.out.println("Start Element found: " + TC10Constants.TAG_FILE );        
        f = unmarshallFile(atts);                
        if( f!=null)
          getResults().add(f);
      }      
    } catch (Exception ex) {
      ex.printStackTrace();
    }
 }
  
 /* 
 public void endElement(String uri, String localName, String qName) {
    if( localName.equalsIgnoreCase(TC10Constants.TAG_FILE) ) {
        {} //System.out.println("End Element found: " + TC10Constants.TAG_FILE );
        getResults().add(stack.pop());
    }
 }
 */
  
 protected File unmarshallFile(Attributes atts) throws Exception {
   File f = null;
   String key, value;
   for (int idx = 0; idx < atts.getLength(); idx++){
     key = atts.getQName(idx);
     value = atts.getValue(idx);
     {} //System.out.println("Key: " + key );
     {} //System.out.println("Value: " + value );
     if (key.equalsIgnoreCase(TC10Constants.ATT_ABS_PATH))
       f = new File(value);
   }
   return f;
 }
   
}
