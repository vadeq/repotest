package zws.datasource.intralink.xml; 
import zws.util.Storable;
import zws.log.failure.Failure;
import zws.exception.zwsException;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Jul 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

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
