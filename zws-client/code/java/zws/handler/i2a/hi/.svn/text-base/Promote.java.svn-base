package zws.handler.i2a.hi; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.handler.i2a.BaseI2AHandler;
import zws.util.RoutedEventBase;

import java.io.Serializable;

public abstract class Promote extends BaseI2AHandler implements Serializable{
 //public void configure(I2APolicy p) { policy=p; }
  
  public abstract Class getEventClass();
  
  public abstract boolean handles(RoutedEventBase event);
  
  public abstract void preHandleEvent(RoutedEventBase event) throws Exception;
  public abstract void handleEvent(RoutedEventBase event) throws Exception;
  public abstract void postHandleEvent(RoutedEventBase event) throws Exception;
  public abstract void reset() throws Exception;
  
}