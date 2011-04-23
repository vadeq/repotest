package zws.service.topology; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.service.Locator;
import zws.topology.Node;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ejb.EJBHome;

public class EJBLocator extends zws.service.EJBLocatorBase{
  public static TopologyServiceRemote findService(String serverName) {
	  TopologyServiceRemote service = null;
  	try {
		  TopologyServiceRemoteLocator loc = new TopologyServiceRemoteLocator();
      service = (TopologyServiceRemote)find(serverName, loc);
  	}
    catch(javax.naming.NamingException ne){ ne.printStackTrace(); }
	  return service;
  }
  
  public static TopologyServiceRemote findService(Node node) {
	  TopologyServiceRemote service = null;
		try {
		  TopologyServiceRemoteLocator loc = new TopologyServiceRemoteLocator();
	    service = (TopologyServiceRemote)find(node, loc);
		}
	  catch(javax.naming.NamingException ne){ ne.printStackTrace(); }
	  return service;
	}
}

class TopologyServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.TopologyServiceEJB; }
	public Remote createService(EJBHome serviceHome){ 
    TopologyServiceRemote service = null;
		try{ service=((TopologyServiceRemoteHome)serviceHome).create(); }
    catch(javax.ejb.CreateException ce){ ce.printStackTrace(); }
    catch(RemoteException re){ re.printStackTrace(); }
		return service;
	}
}

