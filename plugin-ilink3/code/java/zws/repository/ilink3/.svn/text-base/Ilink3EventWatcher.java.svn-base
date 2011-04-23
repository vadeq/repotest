package zws.repository.ilink3;

import java.util.Collection;

public interface Ilink3EventWatcher {

  // control event Listners
  public void startEventListener() throws Exception;
  public void pauseEventListener() throws Exception;
  public void resumeEventListener() throws Exception;
  public void stopEventListener() throws Exception;
  public void stopAllEventListeners() throws Exception;

  public void addEventListenerTargetQueueNode() throws Exception ;
  public void removeEventListenerTargetQueueNode() throws Exception ;
  public Collection getEventListenerTargetQueueNodes() throws Exception ;
  
  public void setEventListenerHistoryLogDuration(int hours) throws Exception ;
  public int getEventListenerHistoryLogDuration() throws Exception ;
  
  public void setEventListenerRunPeriod( int seconds) throws Exception ;
  public int getEventListenerRunPeriod() throws Exception ;
  
  public String getEventListenerEventFiringState() throws Exception;  
  public String getEventListenerRunningState() throws Exception ;
  public Collection getEventListenerHistoryLog() throws Exception ;
  
  // control events
  public void ignoreEventListenerEvents() throws Exception ;
  public void fireEventListenerEvents() throws Exception ;  

}
