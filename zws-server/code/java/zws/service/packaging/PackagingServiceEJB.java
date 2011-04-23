package zws.service.packaging; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Configurator;
import zws.pkg.DataPackage;

import java.net.URL;
import java.rmi.RemoteException;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class PackagingServiceEJB implements SessionBean, PackagingService {
  public void retrievePackage(DataPackage pkg) throws RemoteException {
    try { PackagingSvc.retrievePackage(pkg); }
    catch (Exception e ) {
      e.printStackTrace();        
      throw new RemoteException(Server.getName(), e); 
    }
  }
  
  public URL downloadPackage(String packageName) throws RemoteException {
    try { return PackagingSvc.downloadPackage(packageName); }
    catch (Exception e ) {
      e.printStackTrace();
      throw new RemoteException(Server.getName(), e);
    }
  }
  
  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}

}
