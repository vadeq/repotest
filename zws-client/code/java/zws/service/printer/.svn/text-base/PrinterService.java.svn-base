package zws.service.printer; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.printer.Printer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;

public interface PrinterService extends Serializable {
  public void print(Origin o, String printerAlias, int copyCount) throws RemoteException;
  public void add(Printer p) throws RemoteException;
  public Printer find(String name) throws RemoteException;
  public Collection findAll() throws RemoteException;
  public Collection getPrinterNames() throws RemoteException;
}
