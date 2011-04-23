package zws.service.replication.policy; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.service.Locator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ejb.EJBHome;

public class EJBLocator extends zws.service.EJBLocatorBase{

  public static ReplicationPolicyServiceRemote findService(String serverName) throws Exception {
  	ReplicationPolicyServiceRemoteLocator loc = new ReplicationPolicyServiceRemoteLocator();
    ReplicationPolicyServiceRemote service = (ReplicationPolicyServiceRemote)find(serverName, loc);
    return service;
  }
}

class ReplicationPolicyServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.ReplicationPolicyServiceEJB; }
  
	public Remote createService(EJBHome serviceHome){
		ReplicationPolicyServiceRemote service = null;
		try{
			service=((ReplicationPolicyServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
