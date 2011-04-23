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
import zws.event.release.Promotion;

import java.io.Serializable;
import java.util.*;

public class Promote extends ReleaseEventHandlerBase implements Serializable{
  public Class getEventClass() { return Promotion.class; }

  public void handleForSource(RoutedEventBase event) throws Exception {
	  Promotion ev = (Promotion)event;
  	matches = filterMetadataOriginsMatchingPolicy(ev);
  	if (null==matches || matches.isEmpty()) return ;
  	{} //System.out.println("Promotion Matches found: " + matches.size());
	  Metadata data;
	  Iterator i = matches.iterator();
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
		    ilink.promote(targetOrigin, mappedReleaseLevel, getAuthentication());
	    }
	    else {
	      binaryOrigin=metadataBinaryOrigin(data);//get origin for generic
	      targetOrigin = targetOrigin(binaryOrigin, target);
	      recordTargetHandling(event, target);
	  	  ilink.promoteInstance(targetOrigin, data.getName(), mappedReleaseLevel, getAuthentication());
	    }
	  }
  }

  public void finishHandling(RoutedEventBase event) throws Exception { matches = null; }

  protected Collection getEventKeys(RoutedEventBase event) {
   Collection c= new Vector();
   return c;
  }

  public String getReleaseLevelField() { return releaseLevelField; }
  public void setReleaseLevelField(String s) { releaseLevelField=s; }

  private String releaseLevelField = "release-level";
  Collection matches = null;
}
