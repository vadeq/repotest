package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.IntralinkAccess;
import zws.util.RoutedEventBase;
import zws.origin.Origin;
import zws.space.DataSpace;
import zws.application.Names;
import zws.data.Metadata;
import zws.event.design.*;
import zws.exception.NameNotFound;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

public class DeleteVersion extends VersionedDesignEventHandlerBase implements Serializable{
  public Class getEventClass() { return VersionDeleted.class; }

  public void handleForSource(RoutedEventBase event) throws Exception { }

  public void finishHandling(RoutedEventBase event) throws Exception {
    VersionDeleted ev = (VersionDeleted)event;
    syncronizer().purgeByUID(event.getDomainName(), event.getServerName(), event.getDatasourceName(), ev.getOrigin().getUniqueID());
  }

  public void handleForTarget(RoutedEventBase event, DataSpace target) throws Exception {
	  VersionDeleted ev = (VersionDeleted)event;
	  Origin o=null;
	  try {o =targetOrigin(ev.getOrigin(), target); }
	  catch(Exception e) {
		  {} //System.out.println("--Could not find synchronized version" + ev.getOrigin().toString ()+ ": it does not exists at "+target.getName()+"["+target.getRepositoryRoute()+"]! "); 
		  {} //System.out.println("--Version may have already been deleted"); 
		  return;
	  }
	  //SynchronizationRecordBase record = new SynchronizationRecordBase(ev.getOrigin(), o);
	  //syncronizer().remove(record);
	  
	  Metadata m=null;
	  IntralinkAccess ilink = IntralinkAccess.getAccess();

	  try { m =ilink.find(o, getAuthentication()); }	  
	  catch(NameNotFound e) {
      String msg = "Network may be down: could not verify that " + ev.getName()+ "exists at "+target.getName()+"["+target.getRepositoryRoute()+"]! ";
      alert("Failure: DeleteVersion", msg);
	  }
	  catch(Exception e) {
	    String msg = "Network may be down: could not verify that " + ev.getName()+ "exists at "+target.getName()+"["+target.getRepositoryRoute()+"]! ";
      alert("Failure: DeleteVersion", msg);
	  }
  
	  //--do nothing when version is deleted
	  //if (null!=m) ilink.deleteVersionFromRepository(o, getAuthentication());

    String msg = ev.getOrigin().getUniqueID() +" was deleted in " + ev.getDatasourceName() + Names.NEW_LINE;
    alert( "Version Deleted [" + ev.getOrigin().getUniqueID() +"]!", msg);
	  
	  //if this name is no longer synchronized at the target, unlock it
    Collection c = sync.findNameSynchronization(target.getDomainName(), target.getServerName(), target.getDatasourceName(), ev.getName());
    if (null==c || c.isEmpty() || 1==c.size()) {
      try { 
        ilink.unlock(m.getOrigin(), getAuthentication());
        msg = ev.getOrigin().getUniqueID() + " was deleted in " + event.getDatasourceName() + " and is no longer synchronized to " + target.getDatasourceName() + "." + Names.NEW_LINE;
        alert( "Version No Longer Synchronized [" + ev.getOrigin().getUniqueID() +"]!", msg);
      }
  	  catch(Exception e) {
		    msg = "Network may be down: could not unlock the last deleted version " + ev.getName()+ " at "+target.getName()+"["+target.getRepositoryRoute()+"]! ";
 	      alert("Warning: DeleteVersion", msg);
		  }
    }
  }

  protected Collection getEventKeys(RoutedEventBase event) {
   VersionDeleted  ev = (VersionDeleted ) event;
   Collection c= new Vector();
   c.add(ev.getName());
   return c;
  }

}
