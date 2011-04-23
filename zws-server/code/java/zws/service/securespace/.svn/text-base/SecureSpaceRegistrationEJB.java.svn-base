package zws.service.securespace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Configurator;
import zws.securespace.User;
import zws.securespace.vade.Domain;
import zws.securespace.vade.VadeSpace;
import zws.security.Password;

import java.rmi.RemoteException;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class SecureSpaceRegistrationEJB implements SessionBean, SecureSpaceRegistration  {
  public void register(User user, Domain domain, Password password) throws RemoteException {
    try { SecureSpaceSvc.register(user, domain, password); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public User authenticate(String username, VadeSpace vSpace, Password password) throws RemoteException{
    //+++todo
    return null;
  }
  public User authenticateGuest(VadeSpace vSpace) throws RemoteException{
    //+++todo
    return null;
  }
  public void updatePassword(User user, VadeSpace dSpace, Password old, Password password) throws RemoteException {  
    try { SecureSpaceSvc.updatePassword(user, dSpace, old, password); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void changePassword(User user, VadeSpace dSpace, Password password) throws RemoteException {  
    try { SecureSpaceSvc.changePassword(user, dSpace, password); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
