package zws.service.processor; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
import zws.processor.Processor;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class ProcessorServiceEJB implements SessionBean, ProcessorService {
  public Processor getProcessor(String name) throws RemoteException {
    try{ return ProcessorSvc.find(name); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }
  public Collection getProcessors()throws RemoteException{ return ProcessorSvc.findAll(); }
  public void execute(Processor p)throws RemoteException{
    try{ p.process(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage());	 }
  }  
  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
