package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.*;
import zws.space.DataSpace;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.util.RoutedEventBase;
import zws.event.design.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

public class Move extends BaseNameHandler implements Serializable{
  public Class getEventClass() { return Moved.class; }

  public void handleForSource(RoutedEventBase event) throws Exception { }

  public void handleForTarget(RoutedEventBase event, DataSpace target) throws Exception {
    Moved ev = (Moved)event;
    
    MetadataBase data = materializeNamedMetadata(event);
    data.set(getLocationField(), ev.getNewLocation());
    
    IntralinkAccess ilink = IntralinkAccess.getAccess();
	  String node = target.getServerName();
	  String rep = target.getDatasourceName();
	  String mappedLocation = target.map(data, getLocationField());
	  recordTargetHandling(event, target);
	  recordTargetHandling(event, target);
	  try {
  	  Metadata m = ilink.findLatest(node, rep, ev.getName(), getAuthentication());
	    String existingPath = m.get("folder"); 
	    {} //System.out.println("Existing Path = " + existingPath);
	    {} //System.out.println("New Path = " + mappedLocation);
	    if (existingPath.equals(mappedLocation)) {
		    {} //System.out.println("Move not necessary");
		    return;
	    }
      ilink.move(node, rep, ev.getName(), mappedLocation, getAuthentication());
    }
	  catch(Exception e) {
	    String msg = ev.getName() + " not found at target: " + target.getRepositoryRoute() + " Could not move to " + mappedLocation;
      {} //System.out.println(msg);
      alert("Failed to move item", msg);
	  }
	  //recordTargetHandlingStatus(event, target, HANDLED);
  }

  public void finishHandling(RoutedEventBase event) throws Exception { }

  protected Collection getEventKeys(RoutedEventBase event) {
   Moved ev = (Moved) event;
   Collection c= new Vector();
   c.add(ev.getName());
   c.add(ev.getNewLocation());
   return c;
  }  

  public String getLocationField() { return locationField; }
  public void setLocationField(String s) { locationField=s; }

  private String locationField = "folder";
}