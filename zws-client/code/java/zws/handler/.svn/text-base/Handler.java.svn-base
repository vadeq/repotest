package zws.handler; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;




public interface Handler{
  String getEventType();
  boolean handles(RoutedEventBase event);
  void handle(RoutedEventBase event);
  void initRegistration(HandlerRegistry r);
  void closeRegistration();
  //void initEmailAlert(String from, Collection recipients, String emailHost);
  //void alert(String subject, String message) throws MessagingException;
  public void unregister();

}