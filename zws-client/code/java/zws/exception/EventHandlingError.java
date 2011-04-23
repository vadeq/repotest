package zws.exception; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Jul 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

public class EventHandlingError extends zwsException {
  public EventHandlingError(String msg) { super("err.event.handling", msg); }

}
