package zws.service.report; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.service.EJBLocatorBase;
import zws.service.Locator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ejb.EJBHome;

/** Retrieves the Remote EJB interface for a {@link ReportService} */
public class EJBLocator extends EJBLocatorBase{
  
  /** Returns the remote interface for a ReportService
   * @param serverName name of server to retrieve the interface from
   * @throws Exception if an error occurs retrieving the service
   * @return interface to a {@link ReportService}
   */  
  public static ReportServiceRemote findService(String serverName) throws Exception {
	ReportServiceRemoteLocator loc = new ReportServiceRemoteLocator();
	ReportServiceRemote service = (ReportServiceRemote)find(serverName, loc);
	return service;
  }
  
}

class ReportServiceRemoteLocator implements Locator{
  	
	public String getServiceName(){
		return Names.ReportServiceEJB;
	}
	public Remote createService(EJBHome serviceHome){
		ReportServiceRemote service = null;
		try{
			service=((ReportServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
