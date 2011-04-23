package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.*;
import zws.space.DataSpace;
import zws.util.RoutedEventBase;
import zws.event.design.*;

import java.io.Serializable;
import java.util.*;
import java.util.Collection;

public class Rename extends BaseNameHandler implements Serializable{
  public Class getEventClass() { return Renamed.class; }

  public void handleForSource(RoutedEventBase event) throws Exception {
	  Renamed ev = (Renamed)event;
	  if (ev.itemIsBinary()) {
	    syncronizer().rename(ev.getDomainName(), ev.getServerName(), ev.getDatasourceName(), ev.getOldName(), ev.getNewName());
	    {} //System.out.println(event.getEventName() + "["+event.getEventID()+"] ["+getPolicy().getName()+"] being handled for source " + ev.getRoute());
	  }
  }

  public void handleForTarget(RoutedEventBase event, DataSpace target) throws Exception {
    Renamed ev = (Renamed)event;
	  if (ev.itemIsBinary()) {
	    Collection c = syncronizer().findMatches(target.getDomainName(), target.getServerName(), target.getDatasourceName(), ev.getOldName());
	    if (null==c || c.isEmpty()) {
	   	  c = syncronizer().findMatches(target.getDomainName(), target.getServerName(), target.getDatasourceName(), ev.getNewName());
	  	  if (null!=c && !c.isEmpty()) 
	        {} //System.out.println(ev.getOldName() + " already renamed to "+ev.getNewName()+" at target " + target.getRepositoryRoute());
	  	  else 
	        {} //System.out.println(ev.getOldName() + " not found at target: " + target.getRepositoryRoute() + " Could not rename to " + ev.getNewName());
	  	  return;
		  }
	  }
	
    IntralinkAccess ilink = IntralinkAccess.getAccess();
	  String node = target.getServerName();
	  String rep = target.getDatasourceName();
	  String oldName = ev.getOldName();
	  String newName = ev.getNewName();
	  
	  recordTargetHandling(event, target);
	  boolean hasOldName=false;
	  boolean hasNewName=true;
	  try { hasOldName = ilink.contains(node, rep, oldName, getAuthentication()); }
	  catch(Exception e) {
	    String msg = "Network may be down: could not verify that " +oldName+ "exists at "+target.getName()+"["+target.getRepositoryRoute()+"]! ";
      alert("Failure: Rename", msg);
	  }
	  try { hasNewName=ilink.contains(node, rep, newName, getAuthentication()); }
	  catch(Exception e) {
	    String msg = "Network may be down: could not verify that " +oldName+ "exists at "+target.getName()+"["+target.getRepositoryRoute()+"]! ";
	    alert("Failure: Rename", msg);   
	  }
	  if (hasOldName) {
	    if (hasNewName) {
	      try {
	        if (newName.startsWith(deletePrefix)) {
 	          String deletedName = markForDeletion(target, newName);
	        }
	        else {
	 	        String conflictName = markAsConflict(target, newName);
			      String msg = "Renaming " +oldName+ " to " +newName+ " but " +newName+ " already Exists! ";
	  	      msg += newName+ " has been renamed to " +conflictName+"!";
	          alert("Conflict Detected: Rename (same already exists)", msg);
	        }
	      }
	      catch(Exception e) {
          String msg = "Renaming " +oldName+ " to " +newName+ " but " +newName+ " already Exists! ";
	  	    msg += "Could not handle conflict: Tried to avoid conflict but failed to rename " +newName+ " to conflict_00_" +newName+ ".";
	  	    msg += "   Exeption Message: " + e.getMessage();
	        alert("Rename Failure!", msg);
	        return;
	      }
	    }
	    //EchoVerificationHandler v = materializeVerificationHandler(event, target);
      //getRegistry().register(v);
	    try {
     	  //recordTargetHandlingStatus(event, target, HANDLING);
	      ilink.rename(node, rep, oldName, newName, getAuthentication());
	  	  //recordTargetHandlingStatus(event, target, HANDLED);
	    }
	    catch(Exception e) {
	      //getRegistry().unregister(v);
		    String msg = "Failed To Rename " +oldName+ " to " +newName;
	      alert("Rename Failure", msg);
	    }
	  }
	  else { 
  	  if (hasNewName) 
  	    {} //System.out.println(oldName + " already renamed to "+newName +" at target " + target.getRepositoryRoute());
  	  else 
        {} //System.out.println(oldName + " not found at target: " + target.getRepositoryRoute() + " Could not rename to " + newName);
	  }
    {} //System.out.println(event.getEventName() + "["+event.getEventID()+"] ["+getPolicy().getName()+"] handled for " + target.getRoute());      
  }

  protected Collection getEventKeys(RoutedEventBase event) {
   Renamed ev = (Renamed) event;
   Collection c= new Vector();
   c.add(ev.getName());
   c.add(ev.getNewName());
   return c;
  }
  
  public void finishHandling(RoutedEventBase event) throws Exception { }
}
