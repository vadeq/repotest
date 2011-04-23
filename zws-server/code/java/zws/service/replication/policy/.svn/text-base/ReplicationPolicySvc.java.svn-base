package zws.service.replication.policy;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 14, 2004, 1:07 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.*;
import zws.EventHandlingClient;
import zws.event.design.Deleted;
import zws.exception.*;
import zws.exception.DuplicateName;
import zws.exception.NameNotFound;
import zws.handler.ier.hi.ReplicateDesignHandler;
import zws.handler.replication.*;
import zws.replication.policy.ReplicationPolicy;
import zws.service.PrototypeSvc;
import zws.util.PrototypeCollection;

import java.util.Collection;

public class ReplicationPolicySvc {
  public static String NAMESPACE = "zws-replication-policy-service";
  public static String getNamespace() { return NAMESPACE; }
  public static ReplicationPolicy find(String name) throws NameNotFound { return (ReplicationPolicy)PrototypeSvc.lookup(NAMESPACE, name); }

  public static Collection getPrototypeNames() { return PrototypeSvc.getPrototypeNames(NAMESPACE); }
  public static PrototypeCollection findAll() { return PrototypeSvc.findAll(NAMESPACE); }
  public static void add(ReplicationPolicy x) throws DuplicateName, InitializationError {
    PrototypeSvc.add(NAMESPACE, x);
    try {
      registerDesignReplicationHandler();
      EventHandlingClient c = EventHandlingClient.getClient(Properties.get(Names.CENTRAL_SERVER));
      Rename h = new Rename();
      h.configure(x);
      Move m = new Move();
      m.configure(x);
      Promote p = new Promote();
      p.configure(x);
      Demote d = new Demote();
      d.configure(x);
      ReplicateCheckin r = new ReplicateCheckin();
      r.configure(x);
      DeleteItem del = new DeleteItem();
      del.configure(x);
      DeleteVersion vDel = new DeleteVersion();
      vDel.configure(x);
      c.register(h);        
      c.register(m);        
      c.register(p);
      c.register(d);
      c.register(r);
      c.register(del);
      c.register(vDel);
    }
    catch(Exception e) { throw new InitializationError("registering policy " + x.getName() + ": " + e.getMessage()); }
  }
  
  private static void registerDesignReplicationHandler() throws Exception {
    if (null!=replicationDesignHandler) return;
    replicationDesignHandler = new ReplicateDesignHandler(); 
    EventHandlingClient.getClient(Server.getName()).register(replicationDesignHandler);
  }
  
  public static void update(ReplicationPolicy x) { remove(x.getName()); try{add(x);} catch (Exception a) {} }
  public static void remove(ReplicationPolicy x) { remove(x.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }
  
  private static ReplicateDesignHandler replicationDesignHandler=null;
}
