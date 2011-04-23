package zws.handler.replication; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.event.ReleaseEvent;
import zws.util.RoutedEventBase;

public abstract class ReleaseEventHandlerBase extends ReplicationHandlerBase {
  public boolean handles(RoutedEventBase event) {
    ReleaseEvent ev = (ReleaseEvent) event;
    if (DESIGNSTATE_USER.equals(ev.getAuthor())) return false; //skip echo events.
    if (!wasFiredFromSource(ev)) return false;
    return true;
  }
}