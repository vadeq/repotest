package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.event.DesignEvent;
import zws.util.RoutedEventBase;

public abstract class DesignEventHandlerBase extends ReplicationHandlerBase {
  public boolean handles(RoutedEventBase event) {
    DesignEvent ev = (DesignEvent) event;
    if (!wasFiredFromSource(ev)) return false;
    return true;
  }
}