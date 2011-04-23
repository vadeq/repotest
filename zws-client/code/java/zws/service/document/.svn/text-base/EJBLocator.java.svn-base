package zws.service.document; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.service.Locator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ejb.EJBHome;

/** Retrieves the Remote EJB interface for a {@link DocumentService} */
public class EJBLocator extends zws.service.EJBLocatorBase{
  
  /** Returns the remote interface for a DocumentService
   * @param serverName name of server to retrieve the interface from
   * @throws Exception if an error occurs retrieving the service
   * @return interface to a {@link DocumentService}
   */  
  public static DocumentServiceRemote findService(String serverName) throws Exception {
	DocumentServiceRemoteLocator loc = new DocumentServiceRemoteLocator();
	DocumentServiceRemote service = (DocumentServiceRemote)find(serverName, loc);
	return service;
  }
  
}

class DocumentServiceRemoteLocator implements Locator{
  	
	public String getServiceName(){
		return Names.DocumentServiceEJB;
	}
	public Remote createService(EJBHome serviceHome){
		DocumentServiceRemote service = null;
		try{
			service=((DocumentServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
