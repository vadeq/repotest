package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.IntralinkOrigin;
import zws.space.DataSpace;
import zws.data.Metadata;
import zws.event.DesignEvent;
import zws.util.RoutedEventBase;
import zws.event.design.*;

import java.io.Serializable;
import java.util.*;

public class ReplicateCheckin extends BaseNameHandler implements Serializable{
  public Class getEventClass() { return CheckIn.class; }

  public boolean handles(RoutedEventBase event) {
    DesignEvent ev = (DesignEvent) event;
    if (!wasFiredFromSource(ev)) return false;
    try {
      Collection matches = filterMetadataNamesMatchingPolicy(ev); 
      if (matches.isEmpty()) return false;
      return true;
    }
    catch(Exception e) {
      return false;
    }
  }

  public void handleForSource(RoutedEventBase event) throws Exception { }

  public void handleForTarget(RoutedEventBase event, DataSpace target) throws Exception {
    CheckIn ev = (CheckIn)event;
    Collection matches = filterMetadataNamesMatchingPolicy(ev);
    String criteria = materializeCriteria(matches);  //"( name='"+m.getName()+"' )"
    {} //System.out.println("Criteria = " + criteria);
    getPolicy().setSpaceCriteria(criteria);
    replicator().replicateDesign(getPolicy());
  }
  
  private String materializeCriteria(Collection matches) {
    String criteria = null;
    if (null==matches) return null;
    if (0==matches.size()) return null;
    Iterator i = matches.iterator();
    Metadata m = (Metadata) i.next();
    criteria = materializeCriteria((IntralinkOrigin)m.getOrigin());
    while (i.hasNext()) {
      m = (Metadata) i.next();
      criteria += " | " + materializeCriteria((IntralinkOrigin)m.getOrigin());
    }
    return criteria;
  }

  private String materializeCriteria(IntralinkOrigin o) {
    String criteria = "( ";
    criteria += "name='" + o.getName() +"' ";
    criteria += "+ branch='" + o.getBranch() +"' ";
    criteria += "+ rev='" + o.getRevision() +"' ";
    criteria += "+ ver='" + o.getVersion() +"' ";
    criteria += " )";
    return criteria;
  }

  public void finishHandling(RoutedEventBase event) throws Exception {}

  protected Collection getEventKeys(RoutedEventBase event) {
    Collection c= new Vector();
    return c;
  }

}