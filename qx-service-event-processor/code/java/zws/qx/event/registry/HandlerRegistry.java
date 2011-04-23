package zws.qx.event.registry; /*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 10:01 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.qx.QxContext;
import zws.qx.event.handler.QxHandler;
import zws.util.RoutedEventBase;
import zws.util.MapUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;



public class HandlerRegistry {

  private HandlerRegistry () {}
  public static HandlerRegistry getRegistry() {
    if(null ==registry) registry = new HandlerRegistry();
    return registry;
  }

  public Collection getHandlers(QxContext ctx, RoutedEventBase event) throws Exception {
    Collection validHandlers = null;
    Collection registeredHandlers= MapUtil.getCollectionFromMap(handlers, event.getEventType());
    Iterator i = registeredHandlers.iterator();
    QxHandler h;
    while (i.hasNext()) {
      h = (QxHandler)i.next();
      {} //System.out.println("checking handler " + h.getEventType());
      {} //System.out.println("checking handler " + h.toString());
      if (h.handles(ctx, event)) {
        if(null == validHandlers) validHandlers = new Vector();
        validHandlers.add(h);
      }
    }
    return validHandlers;
  }

  public void register(QxHandler handler, String eventType) {
    Collection r=  new Vector();
    handler.setEventType(eventType);
    Collection c = MapUtil.getCollectionFromMap(handlers, eventType);
    c.add(handler);
    {} //System.out.println("Registered Handler for " + handler.getEventType());
  }

  public void register(QxHandler handler) {
    Collection r=  new Vector();
    //handler.initRegistration(this);
    Collection c = MapUtil.getCollectionFromMap(handlers, handler.getEventType());
    c.add(handler);
    {} //System.out.println("Registered Handler for " + handler.getEventType());
  }

  public void unregister(QxHandler handler) {
    Collection c = MapUtil.getCollectionFromMap(handlers, handler.getEventType());
    Iterator i = c.iterator();
    QxHandler h = null;
    while(i.hasNext()){
      h = (QxHandler)i.next();
      if(h.equals(handler)) {
        i.remove();
        handler.closeRegistration();
      }
      {} //System.out.println("Unregistered Handler for " + handler.getEventType());
    }
  }

  private HashMap handlers = new HashMap();
  private static HandlerRegistry registry = null;
}
