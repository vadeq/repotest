package zws.repository.ilink3.event.watcher.client;

import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.service.QxServiceFinder;
import zws.qx.xml.QxXML;
import zws.repository.ilink3.Ilink3EventWatcher;
//import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class ILink3EventWatcherClient implements Ilink3EventWatcher {
  
  private ILink3EventWatcherClient(String s) { respositoryName = s;}
  public static Ilink3EventWatcher getClient(String s) {return new ILink3EventWatcherClient(s);  }
  
  private QxXML execute(HashMap argMap) throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("ilink-event");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","Ilink3EventWatcherQxService");
    QxWebClient webClient = (QxWebClient) finder.materializeClient();    
    return webClient.executeQx(ctx, prepareInstruction(argMap));
  }

  private QxXML prepareInstruction(HashMap argMap) throws Exception {
    String key = null;
    String value = null;
    StringBuffer instBuffer = new StringBuffer();
    instBuffer.append("<event-method method=\"").append(argMap.get("method")).append("\" ");
    argMap.remove("method");
    Iterator itr = argMap.keySet().iterator();
    while (itr.hasNext()) {
      key = (String) itr.next();
      value = (String) argMap.get(key);
      instBuffer.append(key).append("=\"").append(value).append("\" ");
    }
    instBuffer.append("/>");
    {}//Logwriter.printOnConsole("instruction to be sent" + instBuffer.toString());
    return new QxXML(instBuffer.toString());
  }

  

  public void addEventListenerTargetQueueNode() throws Exception {
    QxXML result = null;
    argMap = new HashMap();
    argMap.put(METHOD, ADD_EVENT_LISTENER_TARGET_QUEUE_NODE);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("addEventListenerTargetQueueNode completed " + result);
  }
  public void fireEventListenerEvents() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, FIRE_EVENT_LISTENER_EVENTS);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
  public String getEventListenerEventFiringState() throws Exception {
      argMap = new HashMap();
      argMap.put(METHOD, GET_EVENT_LISTENER_EVENT_FIRING_STATE);
      argMap.put(REPOSITORY_NAME, respositoryName);
      result = execute(argMap);
      {}//Logwriter.printOnConsole("completed " + result);
      return result.toQxProgram().get("value");  
  }
  
  public Collection getEventListenerHistoryLog() throws Exception {return null;  }
  public Collection getEventListenerTargetQueueNodes() throws Exception {    return null;  }
  
  public int getEventListenerHistoryLogDuration() throws Exception {    
    argMap = new HashMap();
    argMap.put(METHOD, GET_EVENT_LISTENER_HISTORY_LOG_DURATION);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
    return new Integer(result.toQxProgram().get("value")).intValue();  
  }
  public int getEventListenerRunPeriod() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, GET_EVENT_LISTENER_RUN_PERIOD);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
    return new Integer(result.toQxProgram().get("value")).intValue();  
  }
  public String getEventListenerRunningState() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, GET_EVENT_LISTENER_RUNNING_STATE);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
    return result.toQxProgram().get("value");
  }
  
  public void ignoreEventListenerEvents() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, IGNORE_EVENT_LISTENER_EVENTS);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
  public void pauseEventListener() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, PAUSE_EVENT_LISTENER);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
  public void removeEventListenerTargetQueueNode() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, REMOVE_EVENT_LISTENER_TARGET_QUEUE_NODE);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
  public void resumeEventListener() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, RESUME_EVENT_LISTENER);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
  public void setEventListenerHistoryLogDuration(int hours) throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, SET_EVENT_LISTENER_HISTORY_LOG_DURATION);
    argMap.put(REPOSITORY_NAME, respositoryName);
    argMap.put(HOURS, String.valueOf(hours));
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
  public void setEventListenerRunPeriod(int seconds) throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, SET_EVENT_LISTENER_RUN_PERIOD);
    argMap.put(REPOSITORY_NAME, respositoryName);
    argMap.put(SECONDS, String.valueOf(seconds));
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
  public void startEventListener() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, START_EVENT_LISTENER);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
  public void stopAllEventListeners() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, STOP_ALL_EVENT_LISTENERS);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
  public void stopEventListener() throws Exception {
    argMap = new HashMap();
    argMap.put(METHOD, STOP_EVENT_LISTENER);
    argMap.put(REPOSITORY_NAME, respositoryName);
    result = execute(argMap);
    {}//Logwriter.printOnConsole("completed " + result);
  }
 
  public static String METHOD = "method";
  public static String REPOSITORY_NAME = "repositoryName";
    public static String HOURS = "hours";
  public static String SECONDS = "seconds";
  
  public static String ADD_EVENT_LISTENER_TARGET_QUEUE_NODE = "addEventListenerTargetQueueNode";
  public static String FIRE_EVENT_LISTENER_EVENTS ="fireEventListenerEvents";
  public static String GET_EVENT_LISTENER_EVENT_FIRING_STATE ="getEventListenerEventFiringState";
  public static String GET_EVENT_LISTENER_HISTORY_LOG ="Collection getEventListenerHistoryLog";
  public static String GET_EVENT_LISTENER_HISTORY_LOG_DURATION ="getEventListenerHistoryLogDuration";
  public static String GET_EVENT_LISTENER_RUN_PERIOD ="getEventListenerRunPeriod";
  public static String GET_EVENT_LISTENER_RUNNING_STATE ="getEventListenerRunningState";
  public static String GET_EVENT_LISTENER_TARGET_QUEUE_NODES ="Collection getEventListenerTargetQueueNodes";
  public static String IGNORE_EVENT_LISTENER_EVENTS = "ignoreEventListenerEvents";
  public static String PAUSE_EVENT_LISTENER = "pauseEventListener";
  public static String REMOVE_EVENT_LISTENER_TARGET_QUEUE_NODE = "removeEventListenerTargetQueueNode";
  public static String RESUME_EVENT_LISTENER = "resumeEventListener";
  public static String SET_EVENT_LISTENER_HISTORY_LOG_DURATION = "setEventListenerHistoryLogDuration";
  public static String SET_EVENT_LISTENER_RUN_PERIOD = "setEventListenerRunPeriod";
  public static String START_EVENT_LISTENER = "startEventListener";
  public static String STOP_ALL_EVENT_LISTENERS = "stopAllEventListeners";
  public static String STOP_EVENT_LISTENER = "stopEventListener";

  private QxXML result = null;
  private HashMap argMap = null;  
  private String respositoryName = null;
}
