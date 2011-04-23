package zws.service.space; /*
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

  public static DataSpaceServiceRemote findService(String serverName) throws Exception {
  	DataSpaceServiceRemoteLocator loc = new DataSpaceServiceRemoteLocator();
    DataSpaceServiceRemote service = (DataSpaceServiceRemote)find(serverName, loc);
    return service;
  }
}

class DataSpaceServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.DataSpaceServiceEJB; }
  
	public Remote createService(EJBHome serviceHome){
		DataSpaceServiceRemote service = null;
		try{
			service=((DataSpaceServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
