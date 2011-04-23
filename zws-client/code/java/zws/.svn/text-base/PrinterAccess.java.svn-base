package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on March 1, 2005, 12:48 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.NameNotFound;
import zws.exception.ServerNotFound;
import zws.origin.Origin;
import zws.printer.Printer;
import zws.service.printer.EJBLocator;
import zws.service.printer.PrinterServiceRemote;

import java.util.Collection;

public class PrinterAccess {
  private PrinterAccess(String serverName) { server = serverName; }
  
  public static PrinterAccess getClient(String serverName) { return new PrinterAccess(serverName); }
  
  public void print(Origin o, String printerAlias, int copyCount) throws Exception { getService().print(o, printerAlias, copyCount); }
  
  public Printer find(String alias) throws ServerNotFound, NameNotFound, Exception { return getService().find(alias); }

  public Collection getAllPrinters() throws Exception { return getService().findAll(); }

  public Collection getPrinterNames() throws Exception { return getService().getPrinterNames(); }

  private PrinterServiceRemote getService() throws Exception{
    if (service==null) {
      try { service = EJBLocator.findService(server); }
      catch (Exception e) { e.printStackTrace(); throw new ServerNotFound(server); }
    }
    return service;
  }
  
  private String server=null;
  private PrinterServiceRemote service=null;
}
