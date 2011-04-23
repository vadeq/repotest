package zws.service.printer; /*
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

  public static PrinterServiceRemote findService(String serverName) throws Exception {
  	PrinterServiceRemoteLocator loc = new PrinterServiceRemoteLocator();
    PrinterServiceRemote service = (PrinterServiceRemote)find(serverName, loc);
    return service;
  }
}

class PrinterServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.PrinterServiceEJB; }
  
	public Remote createService(EJBHome serviceHome){
		PrinterServiceRemote service = null;
		try{
			service=((PrinterServiceRemoteHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}
