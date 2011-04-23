package zws.service; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ejb.EJBHome;

/** Retrieves the Remote EJB interface for a {@link PrototypeService} */
public abstract class EJBLocator extends EJBLocatorBase{
 
  /** Returns the remote interface for a PrototypeService
   * @param serverName name of server to retrieve the interface from
   * @throws Exception if an error occurs retrieving the service
   * @return interface to a {@link PrototypeService}
   */  
  public static PrototypeServiceRemote findService(String serverName) throws Exception {
    //++if serverName is the local server, use LocalHome to get local interface.
	  PrototypeServiceRemoteLocator loc = new PrototypeServiceRemoteLocator();
	  PrototypeServiceRemote service = (PrototypeServiceRemote)find(serverName, loc);
    return service;
  }
  
}

class PrototypeServiceRemoteLocator implements Locator{
  	
	public String getServiceName(){
		return Names.PrototypeServiceEJB;
	}
	public Remote createService(EJBHome serviceHome){
		PrototypeServiceRemote service = null;
		try{
			service=((PrototypeServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}