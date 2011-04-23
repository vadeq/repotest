package zws.repository.ilink3.qx.client.op.xml;/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Jul 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */



import org.xml.sax.Attributes;



public class IlinkQxResponseHandler extends IntralinkResultHandler {
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    try {
	    if ( qName.equalsIgnoreCase("status")) { logStatus(atts); }
	    if ( qName.equalsIgnoreCase("warning")) { logWarning(atts); }
	    if ( qName.equalsIgnoreCase("exception")) { logError(atts); }
	    if ( qName.equalsIgnoreCase("performance")) { logPerformance(atts); }
	    if ( qName.equalsIgnoreCase("missing-end-time-stamp")) { logError(atts); }
    }
    catch (Exception e) { e.printStackTrace(); }
  }
}
