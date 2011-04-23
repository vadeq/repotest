package zws.qx.event.handler.tc10;

/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Apr 9, 2007 3:17:33 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */


import zws.qx.QxContext;
import zws.qx.event.handler.EventHandlerBase;
import zws.qx.event.handler.QxHandler;
import zws.util.RoutedEventBase;

public class TC10RenameHandler extends EventHandlerBase implements QxHandler{
  public void execute(QxContext ctx, RoutedEventBase event) {
    {} //System.out.println(eventType + "  executed successfully...");
  }

  public boolean handles(QxContext ctx, RoutedEventBase event) {
    if(eventType.equals(event.getEventType()))
      return true;
    else
      return false;
  }
}
