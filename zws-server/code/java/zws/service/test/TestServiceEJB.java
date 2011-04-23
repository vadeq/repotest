package zws.service.test; /*
DesignState - Design Compression Technology.
@author: Guy Robinson
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
//import zws.util.{}//Logwriter;

import java.rmi.RemoteException;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class TestServiceEJB implements SessionBean, TestService {
  public String runTest(String url, String user, String psswd) throws RemoteException {
    try { return TestSvc.runTest(url, user, psswd); }
    catch (Throwable t) {
	    {}//Logwriter.printOnConsole(t.getMessage());
	    //t.printStackTrace();
	    throw new RemoteException (t.getMessage());
	  }
  }

  public String ping() throws RemoteException {
    try {  return TestSvc.ping(); }
    catch (Throwable t) {
      {}//Logwriter.printOnConsole(t.getMessage());
      //t.printStackTrace();
      throw new RemoteException (t.getMessage());
    }
  }

  public void ejbCreate () {}
  public void ejbRemove() { }
  public void ejbPassivate() { }
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}

}
