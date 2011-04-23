package zws.service.test; /*
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
  public static TestServiceRemote findService(String serverName) {
	  TestServiceRemote service = null; 
  	try {
		  TestServiceRemoteLocator loc = new TestServiceRemoteLocator();
      service = (TestServiceRemote)find(serverName, loc);
  	}
    catch(javax.naming.NamingException ne){ ne.printStackTrace(); }
	  return service;
  }
}

class TestServiceRemoteLocator implements Locator{
	public String getServiceName(){ return Names.TestServiceEJB; }
	public Remote createService(EJBHome serviceHome){ 
    TestServiceRemote service = null;
		try{ service=((TestServiceRemoteHome)serviceHome).create(); }
    catch(javax.ejb.CreateException ce){ ce.printStackTrace(); }
    catch(RemoteException re){ re.printStackTrace(); }
		return service;
	}
}

