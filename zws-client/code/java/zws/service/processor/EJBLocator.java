/*
 * Created on Oct 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.service.processor;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import zws.application.Names;
import zws.service.Locator;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ejb.EJBHome;

public class EJBLocator extends zws.service.EJBLocatorBase{
  public static ProcessorServiceRemote findService(String serverName) {
	ProcessorServiceRemote service = null;
  	try{
  	
		ProcessorServiceRemoteLocator loc = new ProcessorServiceRemoteLocator();
		service = (ProcessorServiceRemote)find(serverName, loc);
  	}catch(javax.naming.NamingException ne){
  		ne.printStackTrace();
  	}
	return service;
  }
  
}

class ProcessorServiceRemoteLocator implements Locator{
  	
	public String getServiceName(){
		return Names.ProcessorServiceEJB;
	}
	public Remote createService(EJBHome serviceHome){
		ProcessorServiceRemote service = null;
		try{
			service=((ProcessorServiceHome)serviceHome).create();
		}catch(javax.ejb.CreateException ce){
			ce.printStackTrace();
		}catch(RemoteException re){
			re.printStackTrace();
		}
		return service;
	}
}

