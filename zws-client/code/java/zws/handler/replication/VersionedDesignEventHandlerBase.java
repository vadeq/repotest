package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.space.DataSpace;
import zws.event.VersionedDesignEvent;
import zws.util.RoutedEventBase;

import java.io.Serializable;

public abstract class VersionedDesignEventHandlerBase extends ReplicationHandlerBase implements Serializable{
	public abstract Class getEventClass();
	
	public boolean handles(RoutedEventBase event) {
	  VersionedDesignEvent ev = (VersionedDesignEvent) event;
	  if (!wasFiredFromSource(ev)) return false;
	  try { if (!versionHasBeenSynchronized(ev)) return false; }
	  catch(Exception e) { 
	    return false;
	  }
	  return true;
	}

	public abstract void handleForSource(RoutedEventBase event) throws Exception;
	public abstract void finishHandling(RoutedEventBase event) throws Exception;
	public abstract void handleForTarget(RoutedEventBase event, DataSpace target) throws Exception;
}