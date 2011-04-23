package zws.handler.ier.hi;/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on May 26, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.util.RoutedEventBase;
import zws.application.Names;
import zws.event.ier.ReplicateDesign;
import zws.handler.HandlerBase;
import zws.replication.policy.BroadcastPolicy;
import zws.space.DataSpace;
import zws.Replicator;

import java.util.*;
import java.util.Iterator;
import java.util.Map;

public class ReplicateDesignHandler extends HandlerBase {
  public Class getEventClass() { return ReplicateDesign.class; }
    
  public boolean handles(RoutedEventBase event) { return true; }
  
  public void handle(RoutedEventBase event) {
    ReplicateDesign ev = (ReplicateDesign) event;
    try {
      Replicator rep = Replicator.getClient(ev.getPolicyServer());
      BroadcastPolicy policy = (BroadcastPolicy)rep.getPolicy(ev.getPolicyName());
      policy.setSpaceCriteria(ev.getCriteria());
      
      if (null!=ev.getConflictOeverwrites()) {
        Map overwrites = new HashMap();
        StringTokenizer tokens = new StringTokenizer(ev.getConflictOeverwrites(), Names.DELIMITER);
        while (tokens.hasMoreTokens()) overwrites.put(tokens.nextToken().trim(), "true");
        DataSpace x;
        Iterator d = policy.getTargetSpaces().iterator();
        while (d.hasNext()) {
          x = (DataSpace)d.next();
          if (overwrites.containsKey(x.getName())) x.setOverwriteConflicts(true);
        }
      }
      rep.replicateDesign(policy);
    }
    catch(Exception e) {
      e.printStackTrace(); 
    }
  }
  
}
