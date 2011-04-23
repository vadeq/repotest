package zws.service.event.watcher;

import zws.exception.InvalidState;
import zws.repository.ilink3.Ilink3EventListener;

import java.util.Collection;

public class Ilink3EventWatcherSvcOLD {
  public static void setEventListner(Ilink3EventListener e) { 
    eventListener= e;
    {} //System.out.println("listener added for " + eventListener.getRepositoryName());
    }
  protected void initializeThreadExecution() { }
  protected boolean sleepConditionExists() { return NO_TARGET.equals(eventListener.getRunningState()); }

  public static void pauseEventListener() throws InvalidState { eventListener.suspend(); }
  public static void resumeEventListener() throws InvalidState { eventListener.resume(); }
  public static void stopEventListener() throws InvalidState { eventListener.stop(); }
  public static void startEventListener() throws InvalidState { eventListener.start(); }
  public static String getEventListenerRunningState() { return eventListener.getRunningState(); }
  public static String getEventListenerEventFiringState() { return eventListener.getEventFiringState(); }
  public static void ignoreEventListenerEvents() { eventListener.ignoreEvents(); }
  public static void fireEventListenerEvents() { eventListener.fireEvents(); }

  public static Collection getEventListenerHistoryLog() { return eventListener.getHistoryLog(); }
  public static int getEventListenerHistoryLogDuration() { return eventListener.getHistoryLogDuration(); }
  public static void setEventListenerHistoryLogDuration(int hours) { eventListener.setHistoryLogDuration(hours); }
  public static int getEventListenerRunPeriod() { return eventListener.getRunPeriod(); }
  public static void setEventListenerRunPeriod(int seconds) { eventListener.setRunPeriod(seconds); }

  public static Collection getEventListenerTargetQueueNodes() { return eventListener.getTargetQueueNodes(); }
  public static void addEventListenerTargetQueueNode(String serverNode) { eventListener.addTargetQueueNode(serverNode); }
  public static void removeEventListenerTargetQueueNode(String serverNode) { eventListener.removeTargetQueueNode(serverNode); }
  public static void executePolling() throws Exception {eventListener.executePolling();}
  public static void setRunPeriod(int seconds) {eventListener.setRunPeriod(seconds);}
  public static String getEventFiringState() { return eventListener.getEventFiringState();}
  public static Collection getHistoryLog() {return eventListener.getHistoryLog();}
  
  private static Ilink3EventListener eventListener;
  private static String NO_TARGET = "no target";
  
}
