package zws.qx.event.handler; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.qx.QxContext;
import zws.util.RoutedEventBase;




public interface QxHandler{
  String getEventType();
  void setEventType(String s);
  boolean handles(QxContext ctx, RoutedEventBase event) throws Exception;
  void handle(QxContext ctx, RoutedEventBase event) throws Exception;
//  void initRegistration(HandlerRegistry r);
  void closeRegistration();
  public void unregister();

}