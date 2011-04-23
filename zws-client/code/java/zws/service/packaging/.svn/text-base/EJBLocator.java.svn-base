package zws.service.packaging; /*
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
  public static PackagingServiceRemote findService(String serverName) throws Exception {
    PackagingServiceRemoteLocator loc = new PackagingServiceRemoteLocator();
    PackagingServiceRemote service = (PackagingServiceRemote)find(serverName, loc);
    return service;
  }
}

class PackagingServiceRemoteLocator implements Locator{
  	
	public String getServiceName(){
		return Names.PackagingServiceEJB;
	}
	public Remote createService(EJBHome serviceHome){
		PackagingServiceRemote service = null;
		try{
    service=((PackagingServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
