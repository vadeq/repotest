 package zws.qx.service;

import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.Repository;
import zws.repository.ilink3.Ilink3EventListener;
import zws.repository.ilink3.event.watcher.client.ILink3EventWatcherClient;
import zws.service.event.watcher.EventWatcherSvc;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;

import java.util.Collection;

public class Ilink3EventWatcherQxService implements Qx {
   public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
     QxXML result = null;
     try {
     QxInstruction qxInstr = dataInstruction.toQxProgram();
     
     String methodName = qxInstr.get(ILink3EventWatcherClient.METHOD);
     String repositoryName = qxInstr.get(ILink3EventWatcherClient.REPOSITORY_NAME);
     String hours = qxInstr.get(ILink3EventWatcherClient.HOURS);
     String seconds = qxInstr.get(ILink3EventWatcherClient.SECONDS);
     
     RepositoryService r = RepositoryClient.getClient();
     Repository rep = r.findRepository(repositoryName);     
     Ilink3EventListener listener =  EventWatcherSvc.find(rep.getName());
     String state= null;
     if (ILink3EventWatcherClient.START_EVENT_LISTENER.equals(methodName)) {
       listener.start();
       result = new QxXML("<result value=\"START_EVENT_LISTENER completed\"/>");
     } else if (ILink3EventWatcherClient.PAUSE_EVENT_LISTENER.equals(methodName)) {
       listener.suspend();
       result = new QxXML("<result value=\" PAUSE_EVENT_LISTENER completed\"/>");
     } else if (ILink3EventWatcherClient.RESUME_EVENT_LISTENER.equals(methodName)) {
       listener.resume();
       result = new QxXML("<result value=\" RESUME_EVENT_LISTENER completed\"/>");
     } else if (ILink3EventWatcherClient.STOP_EVENT_LISTENER.equals(methodName)) {
       listener.stop();
       result = new QxXML("<result value=\" STOP_EVENT_LISTENER completed\"/>");
     } else if (ILink3EventWatcherClient.STOP_ALL_EVENT_LISTENERS.equals(methodName)) {
       //listener.storeAll(c)
       result = new QxXML("<result value=\" STOP_ALL_EVENT_LISTENERS completed\"/>");
     }else if (ILink3EventWatcherClient.FIRE_EVENT_LISTENER_EVENTS.equals(methodName)) {
       listener.fireEvents();
       result = new QxXML("<result value=\" FIRE_EVENT_LISTENER_EVENTS completed\"/>");
     } else if (ILink3EventWatcherClient.IGNORE_EVENT_LISTENER_EVENTS.equals(methodName)) {
       listener.ignoreEvents();
       result = new QxXML("<result value=\" IGNORE_EVENT_LISTENER_EVENTS completed\"/>");
     } else if (ILink3EventWatcherClient.GET_EVENT_LISTENER_RUN_PERIOD.equals(methodName)) {
       int runtime = listener.getRunPeriod();
       result = new QxXML("<result value=\"" + String.valueOf(runtime)+ "\"/>");
     } else if (ILink3EventWatcherClient.SET_EVENT_LISTENER_RUN_PERIOD.equals(methodName)) {
       listener.setRunPeriod(Integer.valueOf(seconds).intValue());
       result = new QxXML("<result value=\" SET_EVENT_LISTENER_RUN_PERIOD completed\"/>");
     } else if (ILink3EventWatcherClient.GET_EVENT_LISTENER_RUNNING_STATE.equals(methodName)) {
       state = listener.getRunningState();
       result = new QxXML("<result value=\"" + state + "\"/>");
     } else if (ILink3EventWatcherClient.GET_EVENT_LISTENER_EVENT_FIRING_STATE.equals(methodName)) {
       state = listener.getEventFiringState();
       result = new QxXML("<result value=\"" + state + "\"/>");
     } else if (ILink3EventWatcherClient.GET_EVENT_LISTENER_HISTORY_LOG.equals(methodName)) {
       Collection c = listener.getHistoryLog();
       result = new QxXML("<result value=\"" + c.toString() + "\"/>");
     } else if (ILink3EventWatcherClient.ADD_EVENT_LISTENER_TARGET_QUEUE_NODE.equals(methodName)) {
       listener.addTargetQueueNode(rep.getServerName());
       result = new QxXML("<result value=\" ADD_EVENT_LISTENER_TARGET_QUEUE_NODE completed\"/>");
     } else if (ILink3EventWatcherClient.GET_EVENT_LISTENER_TARGET_QUEUE_NODES.equals(methodName)) {
       Collection c= listener.getTargetQueueNodes();
       result = new QxXML("<result value=\"" + c.toString() + "\"/>");
     } else if (ILink3EventWatcherClient.REMOVE_EVENT_LISTENER_TARGET_QUEUE_NODE.equals(methodName)) {
       listener.removeTargetQueueNode(rep.getServerName());
       result = new QxXML("<result value=\" REMOVE_EVENT_LISTENER_TARGET_QUEUE_NODE completed\"/>");
     } else if (ILink3EventWatcherClient.SET_EVENT_LISTENER_HISTORY_LOG_DURATION.equals(methodName)) {
       listener.setHistoryLogDuration(Integer.valueOf(hours).intValue());
       result = new QxXML("<result value=\" SET_EVENT_LISTENER_HISTORY_LOG_DURATION completed\"/>");
     } else if (ILink3EventWatcherClient.GET_EVENT_LISTENER_HISTORY_LOG_DURATION.equals(methodName)) {
       int i = listener.getHistoryLogDuration();
       result = new QxXML("<result value=\" GET_EVENT_LISTENER_HISTORY_LOG_DURATION completed\"/>");
     }
     } catch(Exception exp) {
       exp.printStackTrace();
     }
     return result;
 }
   private Ilink3EventListener lookupListner(Repository rep) {
     if(null == eventListener) {
       eventListener = new Ilink3EventListener();
       eventListener.setServerMinutesOffset(0);
     }
     return eventListener;
   }
   private Ilink3EventListener eventListener = null;
   
}