package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2003, 11:14 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.*;
import zws.util.RoutedEventBase;
import zws.handler.Handler;
import zws.service.event.EJBLocator;
import zws.service.event.EventHandlingService;

import java.rmi.RemoteException;
import java.util.Collection;

public class EventHandlingClient {
  public static EventHandlingClient getClient() { 
    return new EventHandlingClient(Properties.get(Names.CENTRAL_SERVER));
  }
  
  public static EventHandlingClient getClient(String server) { 
    return new EventHandlingClient(server);
  }
  
  private EventHandlingClient(String server) { serverName = server; }
  
  
  public void fire(Collection events) throws Exception {
    EventHandlingService service = EJBLocator.findService(serverName);
    try { service.fire(events);}
    catch (RemoteException e) { throw e; }
  }

  
  public void fire(RoutedEventBase event) throws Exception {
    EventHandlingService service = EJBLocator.findService(serverName);
    try { service.fire(event);}
    catch (RemoteException e) { throw e; }
  }

  public void register(Handler handler) throws Exception {
    EventHandlingService service = EJBLocator.findService(serverName);
    try { service.register(handler); }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void unregister(Handler handler) throws Exception {
    EventHandlingService service = EJBLocator.findService(serverName);
    try { service.unregister(handler); }
    catch (RemoteException e) { throw e; }
  }
  
  public String getRunningState() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { return service.getRunningState();}
    catch (RemoteException e) { throw e; }
	}
  
  public String getEventHandlingState() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { return service.getEventHandlingState();}
    catch (RemoteException e) { throw e; }
	}
  public void ignoreEvents() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { service.ignoreEvents();}
    catch (RemoteException e) { throw e; }
	}
  public void handleEvents() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { service.handleEvents();}
    catch (RemoteException e) { throw e; }
	}
  
  public void start() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { service.start();}
    catch (RemoteException e) { throw e; }
	}
  public void stop() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { service.stop();}
    catch (RemoteException e) { throw e; }
    }
  public void pause() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { service.pause();}
    catch (RemoteException e) { throw e; }
	}
  public void resume() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { service.resume();}
    catch (RemoteException e) { throw e; }
	}  

  
  public Collection getHistoryLog() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { return service.getHistoryLog();}
	  catch (RemoteException e) { throw e; }
	}
  
  public int getHistoryLogDuration() throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { return service.getHistoryLogDuration();}
	  catch (RemoteException e) { throw e; }
	}
  
  public void setHistoryLogDuration(int hours) throws Exception {
	  EventHandlingService service = EJBLocator.findService(serverName);
	  try { service.setHistoryLogDuration(hours);}
	  catch (RemoteException e) { throw e; }
	}  
  
  private String serverName=null;
}
