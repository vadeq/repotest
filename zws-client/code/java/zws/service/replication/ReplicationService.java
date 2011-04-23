package zws.service.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.replication.policy.BroadcastPolicy;
import zws.replication.policy.ReplicationPolicy;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ReplicationService extends Serializable {
  public void replicateDesign(ReplicationPolicy p)  throws RemoteException;
  public void replicate(ReplicationPolicy p)  throws RemoteException;
	public void replicate(BroadcastPolicy p, Collection updates) throws RemoteException;
	public void replicateDesign(BroadcastPolicy p, Collection updates) throws RemoteException;

	public void generateReplicationPackage(ReplicationPolicy p) throws RemoteException;
  public void generateReplicationPackage(ReplicationPolicy p, String tarballName) throws RemoteException;
  public void importReplicationPackage(ReplicationPolicy p) throws RemoteException;
  public void synchronizeReplicationPackage(ReplicationPolicy p) throws RemoteException;
  public Collection reportConflicts(ReplicationPolicy p) throws RemoteException;
}
