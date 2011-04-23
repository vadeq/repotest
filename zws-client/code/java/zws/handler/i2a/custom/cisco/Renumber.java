package zws.handler.i2a.custom.cisco; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;
import zws.event.custom.cisco.CustomCiscoEventBase;
import zws.event.custom.cisco.RenumberItem;
import zws.event.design.*;

import java.io.Serializable;

public class Renumber extends IntralinkEventHandler implements Serializable{
  public Class getEventClass() { return Renamed.class; }

  public CustomCiscoEventBase  convertEvent(RoutedEventBase event) {
    Renamed e = (Renamed) event;
    RenumberItem ev = new RenumberItem(e.getName(), e.getNewName());
    return ev;
  }

  public boolean handles(RoutedEventBase event) {
    return true;
    /*
	  DesignEvent ev = (DesignEvent) event;
	  try { if (!binaryNameHasBeenSynchronized(ev)) return false; }
	  catch(Exception e) {
	    e.printStackTrace();
	    return false;
	  }
	  return true;
	  */
	}

  public void handleForSource(RoutedEventBase event) throws Exception {
	  Renamed ev = (Renamed)event;
	  if (ev.itemIsBinary()) {
	    syncronizer().rename(ev.getDomainName(), ev.getServerName(), ev.getDatasourceName(), ev.getOldName(), ev.getNewName());
	    {} //System.out.println(event.getEventName() + "["+event.getEventID()+"] being handled for source " + ev.getRoute());
	  }
  }
}
