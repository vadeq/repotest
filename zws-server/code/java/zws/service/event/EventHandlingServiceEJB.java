package zws.service.event; /*
DesignState - Design Compression Technology.
@author: Guy Robinson
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
import zws.util.RoutedEventBase;
import zws.handler.Handler;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class EventHandlingServiceEJB implements SessionBean, EventHandlingService {

	public void fire(Collection events) throws RemoteException {	
	  try{ EventHandlingSvc.fire(events); }
	  catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
	}
	
	public void fire(RoutedEventBase event) throws RemoteException {	
	  try{ EventHandlingSvc.fire(event); }
	  catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
	}
	public void register(Handler handler) throws RemoteException {	
    try{ EventHandlingSvc.register(handler); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
	public void unregister(Handler handler) throws RemoteException {	
    try{ EventHandlingSvc.unregister(handler); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
	public String getRunningState() throws RemoteException {	
    try{ return EventHandlingSvc.getRunningState(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
	public String getEventHandlingState() throws RemoteException {	
    try{ return EventHandlingSvc.getEventHandlingState(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
	public void ignoreEvents() throws RemoteException {	
    try{ EventHandlingSvc.ignoreEvents(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
	public void handleEvents() throws RemoteException {	
    try{ EventHandlingSvc.handleEvents(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
	public void start() throws RemoteException {	
    try{ EventHandlingSvc.start(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
	public void stop() throws RemoteException {	
    try{ EventHandlingSvc.stop(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
	public void pause() throws RemoteException {	
    try{ EventHandlingSvc.pause(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
	public void resume() throws RemoteException {	
    try{ EventHandlingSvc.resume(); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
  }
	
  public Collection getHistoryLog() throws RemoteException {
	  try{ return EventHandlingSvc.getHistoryLog(); }
	  catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
	}
  public int getHistoryLogDuration() throws RemoteException {
	  try{ return EventHandlingSvc.getHistoryLogDuration(); }
	  catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
	}
  public void setHistoryLogDuration(int hours) throws RemoteException {
	  try{ EventHandlingSvc.setHistoryLogDuration(hours); }
	  catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }	
	}
	
  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() { }
  public void ejbPassivate() { }
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
  
}



