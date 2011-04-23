package zws.handler.i2a.eco; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.handler.i2a.BaseI2AHandler;
import zws.data.eco.ECO;
import zws.util.RoutedEventBase;

import java.io.Serializable;

public abstract class BaseECOHandler extends BaseI2AHandler implements Serializable{
 //public void configure(I2APolicy p) { policy=p; }
  
  public abstract Class getEventClass();

  public boolean handles(RoutedEventBase event) {
    try {
      eco = retrieveECO(event);
      return hasBeenSynchronized(eco);
    }
    catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  
  public abstract void preHandleEvent(RoutedEventBase event) throws Exception;
  public abstract void handleEvent(RoutedEventBase event) throws Exception;
  public abstract void postHandleEvent(RoutedEventBase event) throws Exception;
  public void reset() throws Exception { eco=null; }
  
  protected final ECO getECO() { return eco; }
  private ECO eco=null;
}