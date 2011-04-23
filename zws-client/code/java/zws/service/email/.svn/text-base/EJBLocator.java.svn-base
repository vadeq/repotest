package zws.service.email; /*
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
  public static EmailServiceRemote findService(String serverName) {
	  EmailServiceRemote service = null;
  	try {
		  EmailServiceRemoteLocator loc = new EmailServiceRemoteLocator();
      service = (EmailServiceRemote)find(serverName, loc);
  	}
    catch(javax.naming.NamingException ne){ ne.printStackTrace(); }
	  return service;
  }
}

class EmailServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.EmailServiceEJB; }
	public Remote createService(EJBHome serviceHome){ 
    EmailServiceRemote service = null;
		try{ service=((EmailServiceHome)serviceHome).create(); }
    catch(javax.ejb.CreateException ce){ ce.printStackTrace(); }
    catch(RemoteException re){ re.printStackTrace(); }
		return service;
	}
}

