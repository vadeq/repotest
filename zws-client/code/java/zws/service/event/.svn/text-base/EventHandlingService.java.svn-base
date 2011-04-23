package zws.service.event; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;
import zws.handler.Handler;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;

public interface EventHandlingService extends Serializable{
  void fire(Collection events) throws RemoteException;
  void fire(RoutedEventBase event) throws RemoteException;
	void register(Handler handler) throws RemoteException;
	void unregister(Handler handler) throws RemoteException;

  String getRunningState() throws RemoteException;
  String getEventHandlingState() throws RemoteException;
  void ignoreEvents() throws RemoteException;
  void handleEvents() throws RemoteException;
  
  void start() throws RemoteException;
  void stop() throws RemoteException;
  void pause() throws RemoteException;
  void resume() throws RemoteException;

  public Collection getHistoryLog() throws RemoteException;
  public int getHistoryLogDuration() throws RemoteException;
  public void setHistoryLogDuration(int hours) throws RemoteException;  
}
