package zws.service.replication; /*
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

  public static ReplicationServiceRemote findService(String serverName) throws Exception {
  	ReplicationServiceRemoteLocator loc = new ReplicationServiceRemoteLocator();
    ReplicationServiceRemote service = (ReplicationServiceRemote)find(serverName, loc);
    return service;
  }
}

class ReplicationServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.ReplicationServiceEJB; }
  
	public Remote createService(EJBHome serviceHome){
		ReplicationServiceRemote service = null;
		try{
			service=((ReplicationServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
