package zws.service.topology; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface TopologyServiceRemoteHome extends EJBHome {
  public TopologyServiceRemote create() throws CreateException, RemoteException;
}
