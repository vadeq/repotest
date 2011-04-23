package zws.service.datasource.intralink;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 10, 2003, 12:51 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.service.Locator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ejb.EJBHome;

/** Retrieves the Remote EJB interface for a {@link DatasourceService} */
public class EJBLocator extends zws.service.EJBLocatorBase{
  
  /** Returns the remote interface for a IntralinkAccessService
   * @param serverName name of server to retrieve the interface from
   * @throws Exception if an error occurs retrieving the service
   * @return interface to a {@link IntralinkAccessService}
   */  
  public static IntralinkAccessServiceRemote findService(String serverName) throws Exception {
	IntralinkAccessServiceRemoteLocator loc = new IntralinkAccessServiceRemoteLocator();
	IntralinkAccessServiceRemote service = (IntralinkAccessServiceRemote)find(serverName, loc);
	return service;
  }
  
}

class IntralinkAccessServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.IntralinkAccessServiceEJB; }

  public Remote createService(EJBHome serviceHome){
		IntralinkAccessServiceRemote service = null;
		try{
			service=((IntralinkAccessServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
