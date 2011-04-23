package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.IntralinkAccess;
import zws.space.DataSpace;
import zws.event.DesignEvent;
import zws.util.RoutedEventBase;

import java.io.Serializable;

public abstract class BaseNameHandler extends ReplicationHandlerBase implements Serializable{
  public abstract Class getEventClass();

  public boolean handles(RoutedEventBase event) {
    DesignEvent ev = (DesignEvent) event;
    if (!wasFiredFromSource(ev)) return false;
    try { if (!binaryNameHasBeenSynchronized(ev)) return false; }
    catch(Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  
  protected String markForDeletion(DataSpace target, String name) throws Exception {
    IntralinkAccess ilink = IntralinkAccess.getAccess();
	  String node = target.getServerName();
	  String rep = target.getDatasourceName();

	  int counter = 0;
    String markedName = constructDeletedName(name, counter); 
	  while (ilink.contains(node, rep, markedName, getAuthentication())) {
	    markedName = constructDeletedName(name, ++counter);
	  }
    ilink.rename(node, rep, name, markedName, getAuthentication());
    ilink.trash(node, rep, markedName, getAuthentication());
    return markedName;
  }
  
  protected String constructDeletedName(String name, int counter) {
    String mark = deletePrefix;
    if (10>counter) mark += "0";
    mark += counter + "_" + name;
    return mark;
  }

  protected String markAsConflict(DataSpace target, String name) throws Exception {
	  IntralinkAccess ilink = IntralinkAccess.getAccess();
	  String node = target.getServerName();
	  String rep = target.getDatasourceName();
	
	  int counter = 0;
	  String markedName = constructConflictName(name, counter); 
	  while (ilink.contains(node, rep, markedName, getAuthentication())) {
	    markedName = constructConflictName(name, ++counter);
	  }
	  ilink.rename(node, rep, name, markedName, getAuthentication());
	  return markedName;
	}
	
	protected String constructConflictName(String name, int counter) {
	  String mark = conflictPrefix;
	  if (10>counter) mark += "0";
	  mark += counter + "_" + name;
	  return mark;
	}
  
  protected static String deletePrefix = "del_";
  protected static String conflictPrefix = "dup_";
  
  public abstract void handleForSource(RoutedEventBase event) throws Exception;
  public abstract void finishHandling(RoutedEventBase event) throws Exception;
  public abstract void handleForTarget(RoutedEventBase event, DataSpace target) throws Exception;
}