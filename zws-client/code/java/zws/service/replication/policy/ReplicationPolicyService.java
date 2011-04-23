package zws.service.replication.policy; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.replication.policy.ReplicationPolicy;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ReplicationPolicyService extends Serializable {
  public ReplicationPolicy getPolicy(String name) throws RemoteException;
  public Collection getPolicies() throws RemoteException;
}
