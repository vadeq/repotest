package zws.handler.i2a.custom.cisco; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;
import zws.data.Metadata;
import zws.event.custom.cisco.*;
import zws.event.custom.cisco.CustomCiscoEventBase;
import zws.event.design.*;

import java.io.Serializable;
import java.util.Iterator;

public class PublishCheckin extends IntralinkEventHandler implements Serializable{
  public Class getEventClass() { return CheckIn.class; }

  public boolean handles(RoutedEventBase event) { return true; }

  public CustomCiscoEventBase convertEvent(RoutedEventBase event) {
    CheckIn e = (CheckIn) event;
    //just do one at a time for now (Library)
    if (1!=e.getMetadataList().size()) return null; 
    Iterator i = e.getMetadataList().iterator();
    Metadata m;
    m = (Metadata) i.next();
    PublishLibraryItem ev = new PublishLibraryItem(m.getOrigin());
    return ev;
  }

  public void handleForSource(RoutedEventBase event) throws Exception { }
}
