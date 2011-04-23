package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.IntralinkAccess;
import zws.util.RoutedEventBase;
import zws.space.DataSpace;
import zws.application.Names;
import zws.data.Metadata;
import zws.event.design.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

public class DeleteItem extends BaseNameHandler implements Serializable{
  public Class getEventClass() { return Deleted.class; }

  public void handleForSource(RoutedEventBase event) throws Exception { }

  public void finishHandling(RoutedEventBase event) throws Exception {
    syncronizer().purgeByName(event.getDomainName(), event.getServerName(), event.getDatasourceName(), event.getName());
  }

  public void handleForTarget(RoutedEventBase event, DataSpace target) throws Exception {
    if (event.getName().toLowerCase().startsWith(deletePrefix.toLowerCase())) return;
	  Deleted ev = (Deleted)event;
    Collection c = syncronizer().findMatches(target.getDomainName(), target.getServerName(), target.getDatasourceName(), ev.getName());
    //syncronizer().purgeByName(target.getDomainName(), target.getServerName(), target.getDatasourceName(), ev.getName());
    if (null==c || c.isEmpty()) {
      {} //System.out.println(ev.getName() + " not found at target: " + target.getRepositoryRoute() + " may already be marked for deletion");
      return;
	  }
	  IntralinkAccess ilink = IntralinkAccess.getAccess();
	  String node = target.getServerName();
	  String rep = target.getDatasourceName();
	  String name = ev.getName();
	  
	  recordTargetHandling(event, target);
	  
	  try {
   	  Metadata m = ilink.findLatest(node, rep, name, getAuthentication());
	    boolean isGeneric= "true".equalsIgnoreCase(m.get("is-generic")); 
	    String markedName = markForDeletion(target, name);
	    String msg = name + " marked for deletion and renamed to " + markedName;
	    {} //System.out.println(msg);
	    alert("Item Marked for Deletion", msg);
	    msg = "Family Table Generic: " + msg;
	    msg += Names.NEW_LINE;
	    msg += "  Please locate " + markedName + " and mark its instances for deletion.";
	    if (isGeneric) alert("IER: Action Required!!", msg);
	  }
	  catch(Exception e) {
	    {} //System.out.println(name + " not found at target: " + target.getRepositoryRoute() + " Could not mark for Deletion!");
	  }
/*
	  if (ilink.contains(node, rep, name, getAuthentication())) {
	     String markedName = markForDeletion(target, name);
	     String msg = name + " marked for deletion and renamed to " + markedName;
	     {} //System.out.println(msg);
	     alert("Item Marked for Deletion", msg);
	  }
    else {} //System.out.println(name + " not found at target: " + target.getRepositoryRoute() + " Could not mark for Deletion!");
    */
	  //recordTargetHandlingStatus(event, target, HANDLED);
	  {} //System.out.println(event.getEventName() + "["+event.getEventID()+"] ["+getPolicy().getName()+"] handled for " + target.getRoute());      
  }

  protected Collection getEventKeys(RoutedEventBase event) {
   Deleted  ev = (Deleted ) event;
   Collection c= new Vector();
   c.add(ev.getName());
   return c;
  }
}
