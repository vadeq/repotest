package zws.qx.event.handler;


/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Apr 9, 2007 3:17:33 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.data.Metadata;
import zws.qx.QxContext;
import zws.qx.event.handler.QxHandler;
import zws.recorder.util.RecorderUtil;
import zws.util.RoutedEventBase;

import java.util.Iterator;

public abstract class EventHandlerBase implements QxHandler{
  public String getEventType() {return eventType;}
  public void setEventType(String eType) {eventType = eType;}

  public void handle(QxContext mainCtx, RoutedEventBase event) throws Exception {
    QxContext eventCtx = RecorderUtil.startNewProcess(mainCtx, Names.PEN_NAMESPACE, Names.PEN_PUBLISH, event.getEventType() + " " + event.get("repositry-name"));
    if(null != event.getMetadataList()) {
      Metadata data =null;
      Iterator itr = event.getMetadataList().iterator();
      while(itr.hasNext()) {
        data = (Metadata) itr.next();
        RecorderUtil.logActivity(eventCtx, "processing", data.getName());
      }
    }
    execute(eventCtx, event);
    RecorderUtil.endRecProcess(eventCtx, Names.STATUS_COMPLETE);
  }
  public abstract void execute(QxContext eventCtx, RoutedEventBase event) throws Exception;
  public abstract boolean handles(QxContext ctx, RoutedEventBase event) throws Exception;
  public void closeRegistration() {}
  public void unregister() {}
  protected String eventType = null;
  //protected QxContext ctx = null;

}
