package zws.service.event; /*
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
  public static EventHandlingServiceRemote findService(String serverName) {
	  EventHandlingServiceRemote service = null;
  	try {
		  EventHandlingServiceRemoteLocator loc = new EventHandlingServiceRemoteLocator();
      service = (EventHandlingServiceRemote)find(serverName, loc);
  	}
    catch(javax.naming.NamingException ne){ ne.printStackTrace(); }
	  return service;
  }
}

class EventHandlingServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.EventHandlingServiceEJB; }
	public Remote createService(EJBHome serviceHome){ 
    EventHandlingServiceRemote service = null;
		try{ service=((EventHandlingServiceHome)serviceHome).create(); }
    catch(javax.ejb.CreateException ce){ ce.printStackTrace(); }
    catch(RemoteException re){ re.printStackTrace(); }
		return service;
	}
}

