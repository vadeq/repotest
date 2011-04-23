package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.*;
import zws.origin.Origin;
import zws.space.DataSpace;
import zws.data.Metadata;
import zws.util.RoutedEventBase;
import zws.event.release.Demotion;


import java.io.Serializable;
import java.util.*;

public class Demote extends ReleaseEventHandlerBase implements Serializable{
  public Class getEventClass() { return Demotion.class; }

  public void handleForSource(RoutedEventBase event) throws Exception {
	  Demotion ev = (Demotion)event;
		matches = filterMetadataOriginsMatchingPolicy(ev);
	  Iterator i = matches.iterator();
	  Metadata data;
	  while (i.hasNext()) {
	    data = (Metadata) i.next();
	 	  data.set(getReleaseLevelField(), ev.getNewReleaseLevel(data));
	  }
  }

  public void handleForTarget(RoutedEventBase event, DataSpace target) throws Exception {
    IntralinkAccess ilink = IntralinkAccess.getAccess();
	  String mappedReleaseLevel;
	  Iterator i = matches.iterator();
	  Metadata data;
	  Origin targetOrigin;
	  Origin binaryOrigin;
	  while(i.hasNext()) {
	    data = (Metadata) i.next();
	    mappedReleaseLevel = target.map(data, getReleaseLevelField());
	    if (itemIsBinary(data)) {
        targetOrigin = targetOrigin(data.getOrigin(), target);
	      recordTargetHandling(event, target);
				ilink.demote(targetOrigin, mappedReleaseLevel, getAuthentication());
		  }
		  else {
		    binaryOrigin=metadataBinaryOrigin(data);
	      targetOrigin = targetOrigin(binaryOrigin, target);
	      recordTargetHandling(event, target);
		    ilink.demoteInstance(targetOrigin, data.getName(), mappedReleaseLevel, getAuthentication());
		  }
		  //recordTargetHandlingStatus(event, target, HANDLED);
	  }
  }

  public void finishHandling(RoutedEventBase event) throws Exception { matches = null; }

  public String getReleaseLevelField() { return releaseLevelField; }
  public void setReleaseLevelField(String s) { releaseLevelField=s; }

  protected Collection getEventKeys(RoutedEventBase event) {
   Collection c= new Vector();
   return c;
  }

  private String releaseLevelField = "release-level";
  Collection matches = null;
}
