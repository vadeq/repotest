package zws.service.event.watcher; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.exception.DuplicateName;
import zws.exception.InvalidConfiguration;
import zws.exception.NameNotFound;
import zws.exception.NotConnected;
import zws.repository.ilink3.Ilink3EventListener;
import zws.service.PrototypeSvc;
import zws.util.Named;
import zws.util.PrototypeCollection;

import java.util.Collection;

public class EventWatcherSvc {
  public static String NAMESPACE = "zws-ilink-event-watcher-service"; 
  public static String getNamespace() { return NAMESPACE; }
  
  public static Ilink3EventListener find(String name) throws NameNotFound { 
    return (Ilink3EventListener)PrototypeSvc.lookup(NAMESPACE, name);
  }

  public static Collection getPrototypeNames() { return PrototypeSvc.getPrototypeNames(NAMESPACE); }
  
  public static PrototypeCollection findAll() { return PrototypeSvc.findAll(NAMESPACE); }
  
  public static void add(Ilink3EventListener eventListener) throws DuplicateName, InvalidConfiguration, NotConnected, Exception  {
    try {
      Ilink3EventListener old = (Ilink3EventListener)PrototypeSvc.lookup(NAMESPACE, eventListener.getName());
      throw new DuplicateName(NAMESPACE, eventListener.getName());
    }
    catch (NameNotFound ignore) { }
    catch (Exception exp) {
      exp.printStackTrace();
      {} //System.out.println("Exception " + exp.getMessage());
    }
    PrototypeSvc.add(NAMESPACE, eventListener);
    if(eventListener.isLoadOnStartup()) eventListener.start();
    {} //System.out.println("Added " + eventListener.getName());
  }

  public static void update(Ilink3EventListener repository) throws InvalidConfiguration, NotConnected, Exception { 
    remove(repository.getName()); 
    try { add(repository); }
    catch (DuplicateName e) {} //will never be thrown if source is first removed;
  }

  public static void remove(Named op) { remove(op.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }
}
