package zws.service.event; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;
import zws.event.EventProcessor;
import zws.handler.Handler;

import java.util.*;

public class EventHandlingSvc {
  public static void fire(Collection events) throws Exception{ getProcessor().fire(events); }
  public static void fire(RoutedEventBase event) throws Exception{ getProcessor().fire(event); }
  public static void register(Handler handler) throws Exception { getProcessor().register(handler); }
  public static void unregister(Handler handler) throws Exception { getProcessor().unregister(handler); }

  public static String getRunningState() throws Exception { return getProcessor().getRunningState(); }
  public static String getEventHandlingState() throws Exception { return getProcessor().getEventHandlingState(); }
  public static void ignoreEvents() throws Exception { getProcessor().ignoreEvents(); }
  public static void handleEvents() throws Exception { getProcessor().handleEvents(); }
  
  public static void start() throws Exception { getProcessor().start(); }
  public static void stop() throws Exception { getProcessor().stop(); }
  public static void pause() throws Exception { getProcessor().suspend(); }
  public static void resume() throws Exception { getProcessor().resume(); }
  
  private static EventProcessor getProcessor() {
    if (null==processor) {
	    processor = new EventProcessor();
	    //processor.register(new testRenameHandler());
	    //processor.register(new testMoveHandler());
	    try { processor.start(); }
	    catch (Exception e) { e.printStackTrace(); }
	    }
    return processor;
  }

  public static Collection getHistoryLog() { return handledEventLog; }
  public static int getHistoryLogDuration() { return logHistoryDuration; }
  public static void setHistoryLogDuration(int hours) { logHistoryDuration=hours; }
  
  private static EventProcessor processor = null;
  private static List handledEventLog=null;
  private static int logHistoryDuration=24; //hours
}
