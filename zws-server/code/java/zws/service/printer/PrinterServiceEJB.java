package zws.service.printer; /*
DesignState - Design Compression Technology.
@author: athakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
import zws.origin.Origin;
import zws.printer.Printer;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class PrinterServiceEJB implements SessionBean, PrinterService {
  public void print(Origin o, String printerAlias, int copyCount) throws RemoteException {
    try{ PrinterSvc.print(o, printerAlias, copyCount); }
    catch(Exception e){ e.printStackTrace(); throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public void add(Printer printer) throws RemoteException  {
    try{ PrinterSvc.add(printer); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public Printer find(String name) throws RemoteException  {
    try{ return PrinterSvc.find(name); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public Collection findAll() throws RemoteException  {
    try{ return PrinterSvc.findAll(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public Collection getPrinterNames() throws RemoteException {
    try { return PrinterSvc.getPrinterNames(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }

  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
