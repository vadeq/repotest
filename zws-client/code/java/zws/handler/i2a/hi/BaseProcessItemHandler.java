package zws.handler.i2a.hi; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.handler.i2a.BaseI2AHandler;
import zws.data.Metadata;
import zws.util.RoutedEventBase;
import zws.event.hi.i2a.PromoteItem;

import java.io.Serializable;

public class BaseProcessItemHandler extends BaseI2AHandler implements Serializable{
 //public void configure(I2APolicy p) { policy=p; }
  
  public Class getEventClass() { return PromoteItem.class; }
  public boolean handles(RoutedEventBase event) {
    try {
      Metadata data = retrieveMetadata(event);
      if (hasBeenSynchronized(data.getOrigin())) return true;
      return false;
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  
  public void preHandleEvent(RoutedEventBase event) throws Exception {}
  public void handleEvent(RoutedEventBase event) throws Exception {
    
  }
  public void postHandleEvent(RoutedEventBase event) throws Exception {}
  public void reset() throws Exception {}
  
}