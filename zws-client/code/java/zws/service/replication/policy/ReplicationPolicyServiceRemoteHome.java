package zws.service.replication.policy; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface ReplicationPolicyServiceRemoteHome extends EJBHome {
  public ReplicationPolicyServiceRemote create() throws CreateException, RemoteException;
}