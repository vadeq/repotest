package zws.service.recorder; /*
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

  public static RecorderServiceRemote findService(String serverName) throws Exception {
    RecorderServiceRemoteLocator loc = new RecorderServiceRemoteLocator();
  	RecorderServiceRemote service = (RecorderServiceRemote)find(serverName, loc);
    return service;
  }
}

class RecorderServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.RecorderServiceEJB; }
  
	public Remote createService(EJBHome serviceHome){
		RecorderServiceRemote service = null;
		try{
			service=((RecorderServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
