package zws.service.search; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.service.Locator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ejb.EJBHome;

/** Retrieves the Remote EJB interface for a {@link SearchAgentService} */
public class EJBLocator extends zws.service.EJBLocatorBase{
  
  /** Returns the remote interface for a SearchAgentService
   * @param serverName name of server to retrieve the interface from
   * @throws Exception if an error occurs retrieving the service
   * @return interface to a {@link SearchAgentService}
   */  
  public static SearchAgentServiceRemote findService(String serverName) throws Exception {
	SearchAgentServiceRemoteLocator loc = new SearchAgentServiceRemoteLocator();
	SearchAgentServiceRemote service = (SearchAgentServiceRemote)find(serverName, loc);
    return service;
  }
  
}

class SearchAgentServiceRemoteLocator implements Locator{
  	
	public String getServiceName(){
		return Names.SearchAgentServiceEJB;
	}
	public Remote createService(EJBHome serviceHome){
		SearchAgentServiceRemote service = null;
		try{
			service=((SearchAgentServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
