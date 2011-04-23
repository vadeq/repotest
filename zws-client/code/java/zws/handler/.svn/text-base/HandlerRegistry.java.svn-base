package zws.handler; /*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 10:01 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;
import zws.util.MapUtil;
import java.util.*;

public class HandlerRegistry {

  public Collection getHandlers(RoutedEventBase event) {
    Collection validHandlers = new Vector();
    Collection registeredHandlers= MapUtil.getCollectionFromMap(handlers, event.getEventType());
    Iterator i = registeredHandlers.iterator();
    Handler h;
    while (i.hasNext()) {
      h = (Handler)i.next();
      if (h.handles(event)) validHandlers.add(h);
    }
    return validHandlers;
  }

/*
  public synchronized void handle(Event event) throws Exception{
   {} //System.out.println(event.getEventType());
    if(handlers.containsKey(event.getEventType())){
      Collection h = MapUtil.getCollectionFromMap(handlers, event.getEventType());
      Iterator iterator = h.iterator();
      Handler handler = null;
      while(iterator.hasNext()){
        handler = (Handler)iterator.next();
        handler.handle(event);
        {} //System.out.println("Event Handled");
      }
    }
  }
*/

  public void register(Handler handler) {
    Collection r=  new Vector();
    r.add("athakur@zerowait-state.com");

    handler.initRegistration(this); 
    //handler.initEmailAlert("IER@zerowait-state.com", r, "mail.zerowait-state.com");
    Collection c = MapUtil.getCollectionFromMap(handlers, handler.getEventType());
    c.add(handler);
    {} //System.out.println("Registered Handler for " + handler.getEventType());
  }

  public void unregister(Handler handler) {
    Collection c = MapUtil.getCollectionFromMap(handlers, handler.getEventType());
    Iterator i = c.iterator();
    Handler h = null;
    while(i.hasNext()){
      h = (Handler)i.next();
      if(h.equals(handler)) {
        i.remove();
        handler.closeRegistration();
      }
      {} //System.out.println("Unregistered Handler for " + handler.getEventType());
    }
  }

  private HashMap handlers = new HashMap();
}
