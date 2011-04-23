package zws.service.synchronization; /*
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
  public static SynchronizationServiceRemote findService(String serverName) throws Exception {
    SynchronizationServiceRemoteLocator loc = new SynchronizationServiceRemoteLocator();
    SynchronizationServiceRemote service = (SynchronizationServiceRemote)find(serverName, loc);
    return service;
  }
}

class SynchronizationServiceRemoteLocator implements Locator{
  	
	public String getServiceName(){
		return Names.SynchronizationServiceEJB;
	}
	public Remote createService(EJBHome serviceHome){
		SynchronizationServiceRemote service = null;
		try{
			service=((SynchronizationServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
