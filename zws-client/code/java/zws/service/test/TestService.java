package zws.service.test; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import java.io.Serializable;
import java.rmi.RemoteException;

public interface TestService extends Serializable{
  //1. Be a JBOSS client and call Ping.
  //2. Be a Websphere client and login to Agile
  String runTest(String url, String user, String psswd) throws RemoteException ; 
  String ping() throws RemoteException ;
}
