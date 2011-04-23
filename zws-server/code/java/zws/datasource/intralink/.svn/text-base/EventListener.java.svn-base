package zws.datasource.intralink; /*
* DesignState - Design Compression
* Technology. @author: Arbind Thakur Created
* on Jan 26, 2006 @version: 1.0 Copywrite (c)
* 2002-2005 Zero Wait-State Inc. All rights
* reserved
*/
import zws.*;
//import zws.util.{}//Logwriter;
import zws.util.RoutedEventBase;
import zws.application.s;
import zws.datasource.intralink.op.commonspace.ListEvents;
import zws.op.PollingThreadOpBase;
import zws.recorder.ExecutionRecord;
import zws.util.TimeUtil;
import zws.util.comparator.AlphaNumericComparator;

import java.util.*;

public class EventListener extends PollingThreadOpBase {
  //public EventListener () {}
  public EventListener (IntralinkSource s) {
    ilink=s;
  }

  protected void initializeThreadExecution() {
    //initTimeWindow();
    //try { getThread().sleep(getSleepInterval()); }
    //catch (InterruptedException ignore) { }
  }

  protected boolean sleepConditionExists() { return NO_TARGET.equals(getRunningState()); }


  private Calendar lastRecordedTime=null;
  public String getTimeOfLastEvent() {
    String timeOfLastEvent=null;
    ExecutionRecord lastEventRecord = lastEventRecord();
    if (null!=lastEventRecord ) {
      {}//Logwriter.printOnConsole(getEventNamespace() + " has new events!!");
      lastRecordedTime = lastEventRecord.getDuration().getStartTime().asCalendar();
     }
    else if (null==lastRecordedTime) {
      {}//Logwriter.printOnConsole(getEventNamespace() + ": no new events.");
      lastRecordedTime = new GregorianCalendar();
      lastRecordedTime.add(Calendar.MINUTE, ilink.getServerMinutesOffset());
    }
    timeOfLastEvent = TimeUtil.asString(lastRecordedTime);
    return timeOfLastEvent;
  }

  protected void executePolling() throws Exception {
    Collection events = null;
    String pollAfter = getTimeOfLastEvent();
    {}//Logwriter.printOnConsole(  "polling for events after: " + pollAfter);
    try { events = listEvents(pollAfter); }
    catch (Exception e) { e.printStackTrace(); }
    if (null==events || events.isEmpty()) {
      {}//Logwriter.printOnConsole("none after: " + pollAfter);
      return;
    }
    Iterator i = events.iterator();
    RoutedEventBase ev;
    while (i.hasNext()) {
      ev = (RoutedEventBase) i.next();
      fire(ev);
    }
  }

  private void fire(RoutedEventBase ev) throws Exception {
    {}//Logwriter.printOnConsole("1");
    {}//Logwriter.printOnConsole(ev.toString());
    if (!firingEvents) {
      {}//Logwriter.printOnConsole("Not Firing Any Events!");
      return;
    }
    {}//Logwriter.printOnConsole("2");
    if (getTargetQueueNodes().isEmpty()) {
      {}//Logwriter.printOnConsole("No Target handler nodes specified!");
      return;
    }
    {}//Logwriter.printOnConsole("3");
    Iterator i = getTargetQueueNodes().iterator();
    String node;
    {}//Logwriter.printOnConsole("4");
    Recorder r = Recorder.getClient();
    r.recordFiredEvent(ev);
    {}//Logwriter.printOnConsole("5");
    while(i.hasNext()) {
      {}//Logwriter.printOnConsole("6");
      node = (String)i.next();
      {}//Logwriter.printOnConsole("7");
      EventHandlingClient.getClient(node).fire(ev);
      {}//Logwriter.printOnConsole("8");
      {}//Logwriter.printOnConsole(ev.getEventType() + "." + ev.getName() + " fired to  " + node);
      {}//Logwriter.printOnConsole("9");
    }
    {}//Logwriter.printOnConsole("10");
  }

  private void purgeHistoryLog() {
    //+++todo
  }

  public Collection getHistoryLog() { return null; }

  public int getHistoryLogDuration() { return logHistoryDuration; }
  public void setHistoryLogDuration(int hours) { logHistoryDuration=hours; }

  private Collection listEvents(String firedAfter) throws Exception {
    ListEvents op = new ListEvents();
    op.setDatasource(ilink);
    op.setAuthentication(ilink.getDefaultAuthentication());
    op.setFiredAfter(firedAfter);
    op.execute();
    return op.getResults();
  }

  private ExecutionRecord lastEventRecord() {
    ExecutionRecord record=null;

    try { record = Recorder.getClient().getLastRecording(getEventNamespace()); }
    catch (Exception ignore) { ignore.printStackTrace(); }
    return record;
  }

  private String key(RoutedEventBase ev) {
    return ev.getEventTime() + ev.getNamespace() + ev.getEventName();
  }

  private String key(ExecutionRecord record) {
    return TimeUtil.asString(record.getStartTime().asCalendar()) + record.getNamespace() + record.getName();
  }

  public Collection getTargetQueueNodes() {
    if (null==targetQueueNodes) {
      targetQueueNodes = new TreeSet(new AlphaNumericComparator());
      targetQueueNodes.add("DesignState-node-0"); //tmprary
    }
    return targetQueueNodes;
  }

  public synchronized void addTargetQueueNode(String serverNode) {
    getTargetQueueNodes().add(serverNode);
    notify();
  }

  public synchronized void removeTargetQueueNode(String serverNode) {
    Iterator i = getTargetQueueNodes().iterator();
    String node;
    while(i.hasNext()) {
      node = (String)i.next();
      if (serverNode.equals(node)) i.remove();
    }
    if ( getTargetQueueNodes().isEmpty()) stop(); //autostop thread
  }

  public void ignoreEvents() { firingEvents=false; }
  public void fireEvents() { firingEvents=true; }
  public String getEventFiringState() {
    if (firingEvents) return FIRING_EVENTS;
    else return IGNORING_EVENTS;
  }

  public String getRunningState() {
    if (getTargetQueueNodes().isEmpty()) return NO_TARGET;
    return super.getRunningState();
  }

  public String getEventNamespace() {
    if (null==ilinkEventNamespace) ilinkEventNamespace = "event" + s.dot + ilink.getDomainName() + s.dot + ilink.getServerName()+ s.dot + ilink.getName();
    return ilinkEventNamespace;
  }
  private IntralinkSource ilink=null;
  private String ilinkEventNamespace = null;
  private int logHistoryDuration=24; //hours
  private Collection targetQueueNodes = null;
  private boolean firingEvents = true;

  private static String FIRING_EVENTS = "firing";
  private static String IGNORING_EVENTS = "ignored";
  private static String NO_TARGET = "no target";
}
