package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 13, 2004, 10:18 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.replication.policy.BroadcastPolicy;
import zws.replication.policy.ReplicationPolicy;
import zws.service.replication.EJBLocator;
import zws.service.replication.ReplicationService;
import zws.service.replication.policy.ReplicationPolicyService;

import java.rmi.RemoteException;
import java.util.Collection;

public class Replicator {
  private Replicator(String server) { serverName=server; }
  public static Replicator getClient(String server) { return new Replicator(server); }
  
  public Collection getPolicies() throws Exception {
    Collection c=policyService().getPolicies();
    if (null==c || c.isEmpty()) return null;
    return c;
  }

  public ReplicationPolicy getPolicy(String name) throws Exception {
    return policyService().getPolicy(name);
  }

  public void replicate(ReplicationPolicy p) throws Exception, RemoteException {
    ReplicationService service = EJBLocator.findService(getServerName());
    service.replicate(p);
  }

  public void replicateDesign(ReplicationPolicy p) throws Exception, RemoteException {
    ReplicationService service = EJBLocator.findService(getServerName());
    service.replicateDesign(p);
  }
  
  public void replicate(BroadcastPolicy p, Collection updates) throws Exception, RemoteException {
    ReplicationService service = EJBLocator.findService(getServerName());
    service.replicate(p, updates);
  }
  
  public void replicateDesign(BroadcastPolicy p, Collection updates) throws Exception, RemoteException {
    ReplicationService service = EJBLocator.findService(getServerName());
    service.replicate(p, updates);
  }
  
  public void generateReplicationPackage(ReplicationPolicy p, String tarballName) throws Exception, RemoteException {
    ReplicationService service = EJBLocator.findService(getServerName());
    service.generateReplicationPackage(p, tarballName);
  }
  public void importReplicationPackage(ReplicationPolicy p) throws Exception, RemoteException {
    ReplicationService service = EJBLocator.findService(getServerName());
    service.importReplicationPackage(p);
  }
  public void synchronizeReplicationPackage(ReplicationPolicy p) throws Exception, RemoteException {
    ReplicationService service = EJBLocator.findService(getServerName());
    service.synchronizeReplicationPackage(p);
  }
  public Collection reportConflicts(ReplicationPolicy p) throws Exception, RemoteException {
    ReplicationService service = EJBLocator.findService(getServerName());
    return service.reportConflicts(p);
  }
  
  
  public ReplicationPolicyService  policyService() throws Exception {
    return zws.service.replication.policy.EJBLocator.findService(getServerName());
  }

  
  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }

  String serverName = null;
}
